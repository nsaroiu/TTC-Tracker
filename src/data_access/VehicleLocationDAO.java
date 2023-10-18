package data_access;

import entity.Route;
import entity.Vehicle;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class VehicleLocationDAO implements VehicleLocationDataAccessInterface {

    public static final String baseUrl = "https://webservices.umoiq.com/service/publicXMLFeed";

    public ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag) {

        OkHttpClient client = new OkHttpClient();

        // Construct set of parameters for the request
        Map<String, String> params = new HashMap<>();
        params.put("command", "vehicleLocations");
        params.put("a", "ttc");
        params.put("r", routeTag);

        // Builds the URL with the parameters
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        String url = httpBuilder.build().toString();

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            // Execute the request
            Response response = client.newCall(request).execute();

            // Convert the response to an XML document
            Document doc = convertStringToXMLDocument(response.body().string());

            // Normalize the XML response
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("vehicle");

            ArrayList<Vehicle> vehicles = new ArrayList<>();

            // For each vehicle in the nodeList, create a new Vehicle object and add it to the ArrayList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                vehicles.add(new Vehicle(
                        Integer.parseInt(element.getAttribute("id")),
                        Integer.parseInt(element.getAttribute("speedKmHr")),
                        Float.parseFloat(element.getAttribute("lat")),
                        Float.parseFloat(element.getAttribute("lon")),
                        Integer.parseInt(element.getAttribute("heading")
                        )));
            }

            return vehicles;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;

    }

    public HashSet<String> getStopTagsByRouteTag(String routeTag) {

        OkHttpClient client = new OkHttpClient();

        // Construct set of parameters for the request
        Map<String, String> params = new HashMap<>();
        params.put("command", "routeConfig");
        params.put("a", "ttc");
        params.put("r", routeTag);

        // Builds the URL with the parameters
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        String url = httpBuilder.build().toString();

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            // Execute the request
            Response response = client.newCall(request).execute();

            // Convert the response to an XML document
            Document doc = convertStringToXMLDocument(response.body().string());

            // Normalize the XML response
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("stop");

            HashSet<String> stopTags = new HashSet<>();

            // For each vehicle in the nodeList, create a new Vehicle object and add it to the ArrayList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                stopTags.add(element.getAttribute("tag"));
            }

            return stopTags;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * @return HashMap mapping route ids to a set of stop tags for TTC
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getRouteTagsToStopTags() {

        // Get list of all route ids
        ArrayList<String> routeTags = getRouteList();

        // Get set of all stop tags for each route id and map the route id to set of stop tags
        HashMap<String, HashSet<String>> routeIdToStopTags = new HashMap<>();
        for (String routeTag : routeTags) {
            routeIdToStopTags.put(routeTag, getStopTagsByRouteTag(routeTag));
        }

        // TODO: Above loop is far too slow

        return routeIdToStopTags;

    }

    /**
     * @return HashMap mapping stop tags to a set of route ids for TTC
     * @see HashMap
     */
    public HashMap<String, HashSet<String>> getStopTagsToRouteTags() {

        HashMap<String, HashSet<String>> routeIdToStopTags = getRouteTagsToStopTags();

        // Reverse set to map stop tag to set of route ids
        HashMap<String, HashSet<String>> stopTagToRouteIds = new HashMap<>();
        for (Map.Entry<String, HashSet<String>> entry : routeIdToStopTags.entrySet()) {
            for (String stopTag : entry.getValue()) {
                if (stopTagToRouteIds.containsKey(stopTag)) {
                    stopTagToRouteIds.get(stopTag).add(entry.getKey());
                } else {
                    HashSet<String> routeIds = new HashSet<>();
                    routeIds.add(entry.getKey());
                    stopTagToRouteIds.put(stopTag, routeIds);
                }
            }
        }

        return stopTagToRouteIds;

    }

    public ArrayList<Route> getRoutesByStopTag(String tag) {

        ArrayList<Route> routes = new ArrayList<>();

        HashSet<String> routeTags = new HashSet<>(getStopTagsToRouteTags().get(tag));

        for (String routeTag : routeTags) {

            // Generate Map of vehicle ids to vehicles
            HashMap<Integer, Vehicle> vehicles = new HashMap<>();
            for (Vehicle vehicle : getVehiclesByRouteTag(routeTag)) {
                vehicles.put(vehicle.getId(), vehicle);
            }

            // Add new Route object to ArrayList
            routes.add(new Route(
                    vehicles,
                    routeTag));
        }

        return routes;

    }

    public ArrayList<String> getRouteList() {

        OkHttpClient client = new OkHttpClient();

        // Construct set of parameters for the request
        Map<String, String> params = new HashMap<>();
        params.put("command", "routeList");
        params.put("a", "ttc");

        // Builds the URL with the parameters
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        String url = httpBuilder.build().toString();

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            // Execute the request
            Response response = client.newCall(request).execute();

            // Convert the response to an XML document
            Document doc = convertStringToXMLDocument(response.body().string());

            // Normalize the XML response
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("route");

            ArrayList<String> routes = new ArrayList<>();

            // For each vehicle in the nodeList, create a new Vehicle object and add it to the ArrayList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                routes.add(element.getAttribute("tag"));
            }

            return routes;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
