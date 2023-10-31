package app;

import au.com.bytecode.opencsv.CSVWriter;
import data_access.*;
import entity.Location;
import entity.Stop;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        StopDataAccessInterface stopDAO = new StopDAO();

        HashSet<Stop> stops = stopDAO.getAllStops();

        String filename = "src/data/stopData.csv";

        CSVWriter writer = new CSVWriter(new FileWriter(filename));

        ArrayList<String[]> dataLines = new ArrayList<>();

        for (Stop stop : stops) {
            String[] data = new String[4];

            data[0] = stop.getTag();

            Float lat = stop.getLocation().getLatitude();
            Float lon = stop.getLocation().getLongitude();
            data[1] = lat.toString();
            data[2] = lon.toString();

            StringBuilder routeTags = new StringBuilder();

            for (String routeTag : stop.getRoutes().keySet()) {
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
