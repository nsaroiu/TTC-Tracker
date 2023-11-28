package com.example.backend.use_case.predictions.strategies;
import com.example.backend.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class CalculateDistanceStrategy implements Strategy {
    private final int speedConstant = 1;

    private float distanceAlongShape(ArrayList<Location> shape, DistanceMeasurable obj1, DistanceMeasurable obj2) {
        int i = indexOfClosestPointOnShape(obj1, shape);
        int j = indexOfClosestPointOnShape(obj2, shape);

        if (i < shape.size() - 1) {
            // The closest point is not the end of the shape. We consider the closest point and the next point.
            // obj1 will have passed the closest point if theta is acute, i.e. d1^2 + d3^2 - d^2 > 2 * d1 * d3.
            float d1 = obj1.distanceTo(shape.get(i));
            float d2 = obj1.distanceTo(shape.get(i + 1));
            float d3 = shape.get(i).distanceTo(shape.get(i + 1));
            if (Math.pow(d1, 2) + Math.pow(d3, 2) - Math.pow(d2, 2) > 2 * d1 * d3) {
                // If obj1 has passed the closest point, then the next point will be at i + 1
                i += 1;
            }
        } else {
            // The closest point is the end of the shape. We consider the closest point and the previous point.
            // obj1 will have passed the closest point if theta is obtuse, i.e. d1^2 + d3^2 - d^2 < 2 * d1 * d3.
            float d1 = obj1.distanceTo(shape.get(i));
            float d2 = obj1.distanceTo(shape.get(i - 1));
            float d3 = shape.get(i).distanceTo(shape.get(i - 1));
            if (Math.pow(d1, 2) + Math.pow(d3, 2) - Math.pow(d2, 2) < 2 * d1 * d3) {
                // obj1 has passed the end of the shape, but there is no next point in shape. This is an error.
                throw new IndexOutOfBoundsException("failed because i is out of bounds");
            }
        }

        if (j > 0) {
            // The closest point is not the start of the shape. We consider the closest point and the previous point.
            // obj1 will have not passed the closest point if theta is acute, i.e. d1^2 + d3^2 - d^2 > 2 * d1 * d3.
            float d1 = obj2.distanceTo(shape.get(j));
            float d2 = obj2.distanceTo(shape.get(j - 1));
            float d3 = shape.get(j).distanceTo(shape.get(j - 1));
            if (Math.pow(d1, 2) + Math.pow(d3, 2) - Math.pow(d2, 2) > 2 * d1 * d3) {
                // If obj2 has passed the closest point, then the next point will be at i + 1
                j -= 1;
            }
        } else {
            // The closest point is the start of the shape. We consider the closest point and the next point.
            // obj1 will have not passed the closest point if theta is obtuse, i.e. d1^2 + d3^2 - d^2 < 2 * d1 * d3.
            float d1 = obj2.distanceTo(shape.get(i));
            float d2 = obj2.distanceTo(shape.get(i + 1));
            float d3 = shape.get(i).distanceTo(shape.get(i + 1));
            if (Math.pow(d1, 2) + Math.pow(d3, 2) - Math.pow(d2, 2) < 2 * d1 * d3) {
                // obj2 has not passed the start of the shape, but there is no previous point in shape. This is an error.
                throw new IndexOutOfBoundsException("failed because j is out of bounds");
            }
        }
        // Now, distance =
        // distance from obj1 to shape.get(i) +
        // distance from shape.get(i) to shape.get(j) +
        // distance from shape.get(j) to obj2

        if (i > j) {
            throw new IndexOutOfBoundsException("failed because i=" + i + " and j=" + j);
        }

        System.out.println("Stop coords is " + shape.get(j).getLatitude() + ", " + shape.get(j).getLongitude());
        System.out.println("Vehicle coords is " + shape.get(i).getLatitude() + ", " + shape.get(i).getLongitude());

        float startDistance = obj1.distanceTo(shape.get(i));
        float endDistance = shape.get(j).distanceTo(obj2);
        float middleDistance = 0;
        while (i != j) {
            middleDistance += shape.get(i).distanceTo(shape.get(i + 1));
            i = (i + 1) % shape.size();
        }

        System.out.println("Start: " + startDistance + "\nMiddle: " + middleDistance + "\nEnd: " + endDistance);
        return startDistance + middleDistance + endDistance;
    }

    private int indexOfClosestPointOnShape(DistanceMeasurable point, ArrayList<Location> shape) {
        int index = 0;
        float minDistance = point.distanceTo(shape.get(0));
        for (int j = 1; j < shape.size(); j++) {
            float distance = point.distanceTo(shape.get(j));
            if (distance < minDistance) {
                minDistance = distance;
                index = j;
            }
        }
        return index;
    }


    public ArrayList<Float> execute(Route route, ArrayList<Vehicle> vehicles, ArrayList<Location> shape, String dirTag, String stopTag) {
        RouteDirection direction = route.getRouteDirections().get(dirTag);
        Stop stop;
        if (!direction.getStops().contains(stopTag)) {
            return new ArrayList<>();
        } else {
            stop = route.getStops().get(stopTag);
        }
        HashSet<Vehicle> vehiclesInDirection = new HashSet();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getDirectionTag().equals(dirTag)) {
                vehiclesInDirection.add(vehicle);
            }
        }

        ArrayList<Float> distances = new ArrayList<>();
        for (Vehicle vehicle : vehiclesInDirection) {
            System.out.println("Vehicle is at " + vehicle.getLocation().getLatitude() + ", " + vehicle.getLocation().getLongitude());
            try {
                float distance = distanceAlongShape(shape, vehicle, stop);
                distances.add(distance);
            } catch (IndexOutOfBoundsException e) {
                return new ArrayList<>();
            }
        }
        Collections.sort(distances);

        int i = 0;
        ArrayList<Float> predictions = new ArrayList<>();
        for (float distance : distances) {
            if (i < 3) {
                float prediction = (distance / speedConstant);
                predictions.add(prediction);
                i += 1;
            }
            else {
                break;
            }

        }
        System.out.println(predictions);
        return predictions;
    }

}
