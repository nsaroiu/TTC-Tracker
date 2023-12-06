package com.example.backend.use_case.predictions.strategies;

import com.example.backend.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrategyInputDataTest {

    StrategyInputData strategyInputData;
    Route route;
    String DIRTAG = "North";
    float AVERAGE_SPEED = 10.0f;
    String STOPTAG = "A";
    ArrayList<String> schedule = new ArrayList<>();
    ArrayList<Location> shape = new ArrayList<>();
    HashMap<String, Stop> stops = new HashMap<>();
    HashSet<String> routeTags = new HashSet<>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();



    @BeforeEach
    public void setUp(){
        //Instantiate the builder
        StrategyInputDataBuilder strategyInputDataBuilder = new StrategyInputDataBuilder();

        //set the schedule
        schedule.add("18:30:00");
        strategyInputDataBuilder.setSchedule(schedule);

        //set the dirTag
        strategyInputDataBuilder.setDirTag(DIRTAG);

        //set the average speed
        strategyInputDataBuilder.setAvgSpeed(AVERAGE_SPEED);

        //set the shape
        shape.add(new Location(1,1));
        strategyInputDataBuilder.setShape(shape);

        //set the stops
        //set the routeTags
        routeTags.add("100");
        stops.put(STOPTAG,new Stop(STOPTAG,STOPTAG,1.0f,1.0f,routeTags));
        ArrayList<String> stops2 = new ArrayList<>();
        stops2.add(STOPTAG);

        //set the routeDirections
        HashMap<String, RouteDirection> routeDirections = new HashMap<>();
        routeDirections.put(DIRTAG,new RouteDirection(stops2,DIRTAG,"A"));
        route = new Route(stops, "routeTag", routeDirections);
        strategyInputDataBuilder.setRoute(new Route(stops, "routeTag", routeDirections));
        strategyInputDataBuilder.setStopTag("A");

        //set the vehicles
        vehicles.add(new Vehicle(1,1,1.0f,1.0f,1,DIRTAG));
        strategyInputDataBuilder.setVehicles(vehicles);

        //Build the strategyInputData
        strategyInputData = strategyInputDataBuilder.build();
    }

    @Test
    public void testInputData() {
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
