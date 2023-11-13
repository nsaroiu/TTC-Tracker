package com.example.backend.app;

import com.opencsv.CSVWriter;
import com.example.backend.data_access.route.RouteDAO;
import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDAO;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Route;
import com.example.backend.entity.Stop;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        StopDataAccessInterface stopDAO = new StopDAO();
        RouteDataAccessInterface routeDAO = new RouteDAO();

        

    }

    public static void updateStopDataCsv() throws IOException {

        StopDataAccessInterface stopDAO = new StopDAO();

        HashSet<Stop> stops = stopDAO.getAllStops();

        String filename = "src/data/stopData.csv";

        CSVWriter writer = new CSVWriter(new FileWriter(filename));

        ArrayList<String[]> dataLines = new ArrayList<>();

        for (Stop stop : stops) {
            String[] data = new String[4];

            data[0] = stop.getTag();

            float lat = stop.getLocation().getLatitude();
            float lon = stop.getLocation().getLongitude();
            data[1] = Float.toString(lat);
            data[2] = Float.toString(lon);

            StringBuilder routeTags = new StringBuilder();

            for (String routeTag : stop.getRoutes()) {
                routeTags.append(routeTag).append(",");
            }

            data[3] = routeTags.substring(0, routeTags.length() - 1);

            dataLines.add(data);
        }

        writer.writeNext(new String[]{"stopTag", "latitude", "longitude", "routeTags"});

        for (String[] data : dataLines) {
            writer.writeNext(data);
        }

        writer.close();

    }

}