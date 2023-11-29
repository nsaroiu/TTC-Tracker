package com.example.backend.data_access.stop;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.example.backend.data_access.route.RouteDAO;
import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Stop;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StopDAO implements StopDataAccessInterface {

    private final String stopCsvFilename = "backend/src/main/java/com/example/backend/data/stopData.csv";
    private final String scheduleCsvFileName = "backend/src/main/java/com/example/backend/data/schedules.csv";

    /** Given a route tag, returns a set of stops on that route.
     *
     * @param routeTag String representing the route tag
     * @return HashSet of stop tags on the route
     */
    public HashSet<String> getStopTagsByRouteTag(String routeTag) {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        HashSet<String> stopTags = new HashSet<>();

        try {
            String[] nextRecord = csvReader.readNext();

            // For each entry in csv, iterate over all routes in the entry
            while ((nextRecord = csvReader.readNext()) != null) {

                String stopTag = nextRecord[0];
                String[] csvRouteTags = nextRecord[4].split(",");

                // If the route tag is in the list of routes for the stop, add the stop tag to the list
                if (List.of(csvRouteTags).contains(routeTag)) {
                    stopTags.add(stopTag);
                }

            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return stopTags;

    }

    /** Given a route tag, return a list of all stops that the route passes through.
     *
     * @param routeTag String representing route tag
     * @return HashSet of Stop objects that the route passes through
     */
    public HashSet<Stop> getStopsByRouteTag(String routeTag) {

        HashSet<Stop> stops = new HashSet<>();
        HashSet<Stop> allStops = getAllStops();

        HashSet<String> stopTags = getStopTagsByRouteTag(routeTag);

        for (Stop stop : allStops) {
            if (stopTags.contains(stop.getTag())) {
                stops.add(stop);
            }
        }

        return stops;

    }

    /** Returns a HashMap mapping route tags to a set of all stops that the route passes through.
     *
     * @return HashMap mapping route tags to a set of stop tags
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getRouteTagsToStopTags() {

        RouteDataAccessInterface routeDAO = new RouteDAO();
        // Get list of all route tags
        HashSet<String> routeTags = routeDAO.getRouteTags();

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Map of routeTag to set of stopTags
        HashMap<String, HashSet<String>> routeTagsToStopTags = new HashMap<>();

        try {
            String[] nextRecord = csvReader.readNext();

            // For each entry in csv, iterate over all routes in the entry
            while ((nextRecord = csvReader.readNext()) != null) {

                String stopTag = nextRecord[0];
                String[] csvRouteTags = nextRecord[4].split(",");

                // Add curr stop to set of stops for each route tag
                for (String routeTag : csvRouteTags) {
                    if (!routeTagsToStopTags.containsKey(routeTag)) {
                        routeTagsToStopTags.put(routeTag, new HashSet<>());
                    }

                    routeTagsToStopTags.get(routeTag).add(stopTag);
                }

            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        assert routeTags.size() == routeTagsToStopTags.size(); // If this fails, the csv file is outdated or incorrect

        return routeTagsToStopTags;

    }

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    public HashSet<Stop> getAllStops() {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize HashSet to store Stop objects
        HashSet<Stop> stops = new HashSet<>();

        try {
            String[] nextRecord = csvReader.readNext();

            // Iterate over each entry in csv and create Stop object for each entry
            while ((nextRecord = csvReader.readNext()) != null) {
                String stopTag = nextRecord[0];
                String stopName = nextRecord[1];
                float lat = Float.parseFloat(nextRecord[2]);
                float lon = Float.parseFloat(nextRecord[3]);
                HashSet<String> routeTags = new HashSet<>(List.of(nextRecord[4].split(",")));

                // Add new Stop object to HashSet
                stops.add(new Stop(
                        stopTag,
                        stopName,
                        lat,
                        lon,
                        routeTags
                ));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return stops;
    }

    /** Returns a list of all stop tags and their respective locations.
     *
     * @return ArrayList of HashMaps mapping stop tags to locations.
     */
    public HashMap<String, Location> getAllStopTagsAndLocations() {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize HashMap to store stop tags and locations
        HashMap<String, Location> stopTagsAndLocations = new HashMap<>();

        try {
            String[] nextRecord = csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                String stopTag = nextRecord[0];
                float lat = Float.parseFloat(nextRecord[2]);
                float lon = Float.parseFloat(nextRecord[3]);

                // Add new Stop object to HashSet
                stopTagsAndLocations.put(stopTag, new Location(lat, lon));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return stopTagsAndLocations;
    }

    public ArrayList<String> getScheduledArrivals(String stopTag, String dirTag) {
        ArrayList<String> scheduledArrivals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(scheduleCsvFileName))) {
            String line;
            // Skip first row (headers)
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row[0].equals(dirTag) & row[2].equals(stopTag)) {
                    // Add arrival time if direction and stop tags match
                    scheduledArrivals.add(row[1]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scheduledArrivals;
    }

}
