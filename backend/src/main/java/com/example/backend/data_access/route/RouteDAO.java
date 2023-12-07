package com.example.backend.data_access.route;

import com.example.backend.data_access.direction.DirectionDAO;
import com.example.backend.data_access.direction.DirectionDataAccessInterface;
import com.example.backend.data_access.stop.StopDAO;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.example.backend.data_access.*;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Repository
public class RouteDAO implements RouteDataAccessInterface {

    private final String stopCsvFilename = "backend/src/main/java/com/example/backend/data/stopData.csv";

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
                    return new HashSet<>(Arrays.asList(nextRecord[4].split(",")));
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /** Returns a Route object for the given route tag.
     *
     * @return Route object for the given route tag
     * @see Route
     */
    public Route getRouteByRouteTag(String tag) {

        // Create Stop Map of stops serving the route
        StopDataAccessInterface stopDAO = new StopDAO();
        HashSet<Stop> stopSet = stopDAO.getStopsByRouteTag(tag);
        HashMap<String, Stop> stopMap = new HashMap<>();
        for (Stop stop : stopSet) {
            stopMap.put(stop.getTag(), stop);
        }

        // Get Direction Map of directions for the route
        DirectionDataAccessInterface directionDAO = new DirectionDAO();
        HashMap<String, RouteDirection> routeDirections = directionDAO.getDirectionsByRouteTag(tag);

        return new Route(
                stopMap,
                tag,
                routeDirections
        );

    }


}
