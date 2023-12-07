package com.example.backend.use_case.predictions.strategies;

import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StrategyInputDataBuilderTest {

    @Test
    void setDirTag() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        StrategyInputData data = builder.setDirTag("testDirTag").build();
        assertEquals("testDirTag", data.getDirTag());
    }

    @Test
    void setStopTag() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        StrategyInputData data = builder.setStopTag("testStopTag").build();
        assertEquals("testStopTag", data.getStopTag());
    }

    @Test
    void setRoute() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        Route testRoute = new Route(new HashMap<>(), "testRouteTag", new HashMap<>());
        StrategyInputData data = builder.setRoute(testRoute).build();
        assertEquals(testRoute, data.getRoute());
    }

    @Test
    void setVehicles() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        ArrayList<Vehicle> testVehicles = new ArrayList<>();
        StrategyInputData data = builder.setVehicles(testVehicles).build();
        assertEquals(testVehicles, data.getVehicles());
    }

    @Test
    void setShape() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        ArrayList<Location> testShape = new ArrayList<>();
        StrategyInputData data = builder.setShape(testShape).build();
        assertEquals(testShape, data.getShape());
    }

    @Test
    void setSchedule() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        ArrayList<String> testSchedule = new ArrayList<>();
        StrategyInputData data = builder.setSchedule(testSchedule).build();
        assertEquals(testSchedule, data.getSchedule());
    }

    @Test
    void setAvgSpeed() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        StrategyInputData data = builder.setAvgSpeed(5.0f).build();
        assertEquals(5.0f, data.getAvgSpeed());
    }

    @Test
    void build() {
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        assertNotNull(builder.build());
    }
}
