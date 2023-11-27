package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Route;
import com.example.backend.entity.RouteDirection;
import com.example.backend.entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class StopDetailsImplementationTest {

    @Mock
    private RouteDataAccessInterface routeDataAccessObject;

    @Mock
    private StopDataAccessInterface stopDataAccessObject;

    @InjectMocks
    private StopDetailsImplementation stopDetailsImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock data setup
        String stopTag = "exampleStopTag";
        HashSet<Stop> mockStops = new HashSet<>();
        Stop mockStop = new Stop("exampleStopTag", "Example Stop", 0.0f, 0.0f, new HashSet<>());
        mockStops.add(mockStop);

        ArrayList<String> stopsList = new ArrayList<>();
        stopsList.add("exampleStopTag");

        RouteDirection mockDirection = new RouteDirection(stopsList, "dirTag", "Direction Name");

        HashMap<String, RouteDirection> mockDirections = new HashMap<>();
        mockDirections.put("dirTag", mockDirection);

        HashMap<String, Stop> mockStopsMap = new HashMap<>();
        mockStopsMap.put("exampleStopTag", mockStop);

        Route mockRoute = new Route(mockStopsMap, "routeTag", mockDirections);

        // Set up mock behavior for data access objects
        when(stopDataAccessObject.getAllStops()).thenReturn(mockStops);
        when(routeDataAccessObject.getRouteByRouteTag(anyString())).thenReturn(mockRoute);
    }

    @Test
    void execute() {
        // Expected data
        String stopTag = "exampleStopTag";
        HashMap<String, HashMap<String, String>> expectedRouteTagsToDir = new HashMap<>();
        HashMap<String, String> expectedDirTagsToNames = new HashMap<>();
        expectedDirTagsToNames.put("dirTag", "Direction Name");
        expectedRouteTagsToDir.put("routeTag", expectedDirTagsToNames);

        StopDetailsOutputData expectedOutputData = new StopDetailsOutputData("Example Stop", expectedRouteTagsToDir);

        // Call the implementation method
        StopDetailsOutputData result = stopDetailsImplementation.execute(stopTag);

        // Verify the interaction
        assertEquals(expectedOutputData.getStopName(), result.getStopName());

    }
}