package com.example.backend.app;

import com.example.backend.data_access.InvalidRequestException;
import com.example.backend.data_access.UmoiqApiCaller;
import com.example.backend.entity.Route;
import com.opencsv.CSVWriter;
import com.example.backend.data_access.route.RouteDAO;
import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.entity.Stop;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        RouteDataAccessInterface routeDAO = new RouteDAO();

        Route route = routeDAO.getRouteByRouteTag("510");

        route.getRouteDirections().forEach(routeDirection -> {
            System.out.println(routeDirection.getName());
            System.out.println(routeDirection.getStops());
        });

    }

    public static void updateStopDataCsv() throws IOException {

        RouteDataAccessInterface routeDAO = new RouteDAO();
        HashSet<String> routeTags = routeDAO.getRouteTags();
        HashSet<String> stopTags = new HashSet<>(); // Keeps track of visited stops
        HashMap<String, Stop> stopMap = new HashMap<>(); // Stores all stops

        for (String routeTag : routeTags) {
            try {
                String[][] params = {{"command", "routeConfig"}, {"r", routeTag}};
                Document doc = UmoiqApiCaller.getRequest(params);

                NodeList directionNodeList = doc.getElementsByTagName("stop");

                // Add each stop in each route to stopTags and stopMap
                for (int i = 0; i < directionNodeList.getLength(); i++) {
                    Element element = (Element) directionNodeList.item(i);

                    String stopTag = element.getAttribute("tag").split("_")[0];

                    System.out.println(routeTag + " " + stopTag);

                    // If set does not contain the stop, create new stop and add it
                    if (!stopTags.contains(stopTag)) {
                        stopTags.add(stopTag);
                        String stopName = element.getAttribute("title");
                        float latitude = Float.parseFloat(element.getAttribute("lat"));
                        float longitude = Float.parseFloat(element.getAttribute("lon"));
                        HashSet<String> routeTagsSet = new HashSet<>();
                        routeTagsSet.add(routeTag);

                        Stop stop = new Stop(stopTag, stopName, latitude, longitude, routeTagsSet);
                        stopMap.put(stopTag, stop);
                    }
                    // If set contains the stop, updated the stop's routeTags to include the route
                    else {
                        stopMap.get(stopTag).getRoutes().add(routeTag);
                    }
                }

            } catch (NullPointerException | InvalidRequestException e) {
                throw new IOException(e);
            }

        }

        HashSet<Stop> stops = new HashSet<>(stopMap.values());


        String filename = "backend/src/main/java/com/example/backend/data/stopData.csv";

        CSVWriter writer = new CSVWriter(new FileWriter(filename));

        ArrayList<String[]> dataLines = new ArrayList<>();

        for (Stop stop : stops) {
            String[] data = new String[5];

            data[0] = stop.getTag();
            data[1] = stop.getName();
            float lat = stop.getLocation().getLatitude();
            float lon = stop.getLocation().getLongitude();
            data[2] = Float.toString(lat);
            data[3] = Float.toString(lon);

            StringBuilder routeTagsBuilder = new StringBuilder();

            for (String routeTag : stop.getRoutes()) {
                routeTagsBuilder.append(routeTag).append(",");
            }

            data[4] = routeTagsBuilder.substring(0, routeTagsBuilder.length() - 1);

            dataLines.add(data);
        }

        writer.writeNext(new String[]{"stopTag", "stopName", "latitude", "longitude", "routeTags"});

        for (String[] data : dataLines) {
            writer.writeNext(data);
        }

        writer.close();

    }

}
