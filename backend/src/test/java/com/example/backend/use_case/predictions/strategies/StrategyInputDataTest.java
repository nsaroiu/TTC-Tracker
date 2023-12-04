package com.example.backend.use_case.predictions.strategies;

import com.example.backend.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class StrategyInputDataTest {

    @Test
    public void testExecute() {
        //Instantiate the builder
        StrategyInputDataBuilder strategyInputDataBuilder = new StrategyInputDataBuilder();

        //set the schedule
        ArrayList<String> schedule = new ArrayList<>();
        schedule.add("18:30:00");
        strategyInputDataBuilder.setSchedule(schedule);

        //set the dirTag
        String DIRTAG = "North";
        strategyInputDataBuilder.setDirTag(DIRTAG);

        //set the average speed
        float AVERAGE_SPEED = 10.0f;
        strategyInputDataBuilder.setAvgSpeed(AVERAGE_SPEED);

        //set the shape
        ArrayList<Location> shape = new ArrayList<>();
        shape.add(new Location(1,1));
        strategyInputDataBuilder.setShape(shape);

        //set the stops
        HashMap<String, Stop> stops = new HashMap<String, Stop>();

        //set the routeTags
        HashSet<String> routeTags = new HashSet<String>();
        routeTags.add("100");
        String STOPTAG = "A";
        stops.put(STOPTAG,new Stop(STOPTAG,STOPTAG,1.0f,1.0f,routeTags));
        ArrayList<String> stops2 = new ArrayList<>();
        stops2.add(STOPTAG);

        //set the routeDirections
        HashMap<String, RouteDirection> routeDirections = new HashMap<String, RouteDirection>();
        routeDirections.put("A",new RouteDirection(stops2,DIRTAG,"A"));
        Route route = new Route(stops, "routeTag", routeDirections);
        strategyInputDataBuilder.setRoute(new Route(stops, "routeTag", routeDirections));
        strategyInputDataBuilder.setStopTag("A");

        //set the vehicles
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle(1,1,1.0f,1.0f,1,DIRTAG));
        strategyInputDataBuilder.setVehicles(vehicles);

        //Build the strategyInputData
        StrategyInputData strategyInputData = strategyInputDataBuilder.build();

        //Assert the average speed is correct
        assertEquals(AVERAGE_SPEED, strategyInputData.getAvgSpeed(), 1);

        //Assert the vehicles are correct
        assertEquals(vehicles, strategyInputData.getVehicles());

        //Assert the schedule is correct
        assertEquals(schedule, strategyInputData.getSchedule());

        //Assert the Route is correct
        assertEquals(route.getRouteTag(), strategyInputData.getRoute().getRouteTag());

        //Assert the Shape is correct
        assertEquals(shape, strategyInputData.getShape());

        //Assert the dirTag is correct
        assertEquals(DIRTAG,strategyInputData.getDirTag());

        //Assert the stopTag is correct
        assertEquals(STOPTAG, strategyInputData.getStopTag());

    }

}
