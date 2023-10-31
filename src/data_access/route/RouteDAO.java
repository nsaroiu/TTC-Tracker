package data_access.route;

import au.com.bytecode.opencsv.CSVReader;
import data_access.*;
import data_access.vehicle.VehicleDAO;
import data_access.vehicle.VehicleDataAccessInterface;
import entity.Route;
import entity.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RouteDAO implements RouteDataAccessInterface {

    private final String stopCsvFilename = "src/data/stopData.csv";

    /** Returns a list of all route tags for TTC.
     *
     * @return ArrayList of route tags for TTC
     */
    public ArrayList<String> getRouteTagList() {

        try {
            String[][] params = {{"command", "routeList"}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList nodeList = doc.getElementsByTagName("route");

            ArrayList<String> routes = new ArrayList<>();

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

    /** Given a stop tag, return a list of all routes that pass through the given stop.
     *
     * @param tag String representing stop tag
     * @return List of Route objects for TTC
     */
    public ArrayList<Route> getRoutesByStopTag(String tag) {

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
        ArrayList<Route> routes = new ArrayList<>();

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

                        // Add new Route object to ArrayList
                        routes.add(new Route(
                                vehicles,
                                routeTag));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return routes;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stopTagsToRouteTags;
    }

}
