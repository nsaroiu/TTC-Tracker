package com.example.backend.data_access.route;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.example.backend.data_access.*;
import com.example.backend.data_access.stop.StopDAO;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.data_access.vehicle.VehicleDAO;
import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import com.example.backend.entity.Route;
import com.example.backend.entity.Stop;
import com.example.backend.entity.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RouteDAO implements RouteDataAccessInterface {

    private final String stopCsvFilename = "src/data/stopData.csv";

    /** Returns a set of all route tags for TTC.
     *
     * @return HashSet of route tags for TTC
     */
    public HashSet<String> getRouteTags() {

        try {
            String[][] params = {{"command", "routeList"}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList nodeList = doc.getElementsByTagName("route");

            HashSet<String> routes = new HashSet<>();

            // Add each route tag to the ArrayList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                routes.add(element.getAttribute("tag"));
            }

            return routes;

        } catch (NullPointerException | InvalidRequestException e) {
            e.printStackTrace();
        }

        return null;

    }

    /** Given a stop tag, return a set of route tags that pass through the stop.
     *
     * @param stopTag String representing stop tag
     * @return HashSet of route tags that pass through the stop
     */
    public HashSet<String> getRouteTagsByStopTag(String stopTag) {
        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        try {
            String[] nextRecord = csvReader.readNext();

            // If the stop tag is found, return the set of route tags
            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].equals(stopTag)) {
                    return new HashSet<>(Arrays.asList(nextRecord[3].split(",")));
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /** Given a stop tag, return a set of all routes that pass through the given stop.
     *
     * @param tag String representing stop tag
     * @return HashSet of Route objects that pass through the stop
     */
    public HashSet<Route> getRoutesByStopTag(String tag) {

        StopDataAccessInterface stopDAO = new StopDAO();

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize ArrayList to store routes
        HashSet<Route> routes = new HashSet<>();

        try {
            String[] nextRecord = csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                if (nextRecord[0].equals(tag)) {
                    String[] routeTags = nextRecord[3].split(",");
                    for (String routeTag : routeTags) {
                        // Generate Map of vehicle ids to vehicles
                        HashMap<Integer, Vehicle> vehicles = new HashMap<>();
                        VehicleDataAccessInterface vehicleDAO = new VehicleDAO();
                        for (Vehicle vehicle : vehicleDAO.getVehiclesByRouteTag(routeTag)) {
                            vehicles.put(vehicle.getId(), vehicle);
                        }

                        // Create Map of Stops for Route
                        HashSet<Stop> stops = stopDAO.getStopsByRouteTag(routeTag);
                        HashMap<String, Stop> stopMap= new HashMap<>();
                        for (Stop stop : stops) {
                            stopMap.put(stop.getTag(), stop);
                        }

                        // Add new Route object to ArrayList
                        routes.add(new Route(
                                vehicles,
                                stopMap,
                                routeTag));
                    }

                    return routes;
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /** Returns a HashMap mapping stop tags to a set of all routes that pass through the given stop.
     *
     * @return HashMap mapping stop tags to a set of route tags for TTC
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getStopTagsToRouteTags() {

        // Initialize CSV reader for Stop data
        FileReader filereader = null;
        try {
            filereader = new FileReader(stopCsvFilename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert filereader != null; // If this fails, the filename needs to be fixed
        CSVReader csvReader = new CSVReader(filereader);

        // Initialize HashMap to store stop tags and route tags
        HashMap<String, HashSet<String>> stopTagsToRouteTags = new HashMap<>();

        try {
            String[] nextRecord = csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                HashSet<String> routeTags = new HashSet<>(Arrays.asList(nextRecord[3].split(",")));
                stopTagsToRouteTags.put(nextRecord[0], routeTags);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return stopTagsToRouteTags;
    }

}
