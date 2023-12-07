package com.example.backend.use_case.stop_details;

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
import static org.mockito.Mockito.when;

public class StopDetailsImplementationTest {

    @Mock
    private StopDetailsDataAccessInterface stopDetailsDAO;

    @InjectMocks
    private StopDetailsImplementation stopDetailsImplementation;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {

            // Mock data setup

            HashSet<String> mockRouteTags = new HashSet<>();
            mockRouteTags.add("routeTag");

            String stopTag = "exampleStopTag";
            HashSet<Stop> mockStops = new HashSet<>();
            Stop mockStop = new Stop("exampleStopTag", "Example Stop", 0.0f, 0.0f, mockRouteTags);
            mockStops.add(mockStop);

            ArrayList<String> stopsList = new ArrayList<>();
            stopsList.add("exampleStopTag");

            RouteDirection mockDirection = new RouteDirection(stopsList, "dirTag", "Direction Name");

            HashMap<String, RouteDirection> mockDirections = new HashMap<>();
            mockDirections.put("dirTag", mockDirection);

            HashMap<String, Stop> mockStopsMap = new HashMap<>();
            mockStopsMap.put("exampleStopTag", mockStop);


            String routeTag = "routeTag";
            Route mockRoute = new Route(mockStopsMap, routeTag, mockDirections);

            // Set up mock behavior for data access objects
            when(stopDetailsDAO.getAllStops()).thenReturn(mockStops);
            when(stopDetailsDAO.getRouteTagsByStopTag(stopTag)).thenReturn(mockRouteTags);
            when(stopDetailsDAO.getRouteByRouteTag(routeTag)).thenReturn(mockRoute);
        } catch (Exception e) {
            throw new RuntimeException("Error during test setup", e);
        }
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
