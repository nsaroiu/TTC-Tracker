package com.example.backend.data_access.direction;

import com.example.backend.entity.Location;
import com.example.backend.entity.RouteDirection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DirectionDAO implements DirectionDataAccessInterface {
    private final String directionsCsvFilename = "backend/src/main/java/com/example/backend/data/directions.csv";

    public HashMap<String, RouteDirection> getDirectionsByRouteTag(String routeTag) {
        HashMap<String, RouteDirection> routeDirections = new HashMap<>();

        // Used BufferedReader for massively improved performance
        try (BufferedReader reader = new BufferedReader(new FileReader(directionsCsvFilename))) {
            String line;
            // Skip first row (headers)
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (Objects.equals(row[2], routeTag)) {
                    String dirTag = row[1];
                    String name = row[3];
                    List<String> stopsList = Arrays.asList(row[4].split("\\|"));
                    ArrayList<String> stops = new ArrayList<>(stopsList);
                    RouteDirection direction = new RouteDirection(stops, dirTag, name);
                    routeDirections.put(dirTag, direction);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return routeDirections;
    }

    public ArrayList<Location> getShapeByDirTag(String dirTag) {
        ArrayList<Location> shape = new ArrayList<>();

        // Used BufferedReader for massively improved performance
        try (BufferedReader reader = new BufferedReader(new FileReader(directionsCsvFilename))) {
            String line;
            // Skip first row (headers)
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (Objects.equals(row[1], dirTag)) {
                    String[] points = row[5].split("/");
                    for (String point : points) {
                        String[] coords = point.split("\\|");
                        float lat = Float.parseFloat(coords[0]);
                        float lon = Float.parseFloat(coords[1]);
                        Location location = new Location(lat, lon);
                        shape.add(location);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return shape;
    }
}
