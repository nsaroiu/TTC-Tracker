package com.example.backend.use_case.stop_details;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StopDetailsOutputDataTest {

    @Test
    void testGetters() {
        // Mock data
        String stopName = "Example Stop";
        HashMap<String, String> dirTagsToNames = new HashMap<>();
        dirTagsToNames.put("dirTag", "Direction Name");
        HashMap<String, HashMap<String, String>> routeTagsToDir = new HashMap<>();
        routeTagsToDir.put("routeTag", dirTagsToNames);

        // Create StopDetailsOutputData instance
        StopDetailsOutputData outputData = new StopDetailsOutputData(stopName, routeTagsToDir);

        // Verify getters
        assertEquals(stopName, outputData.getStopName());
        assertEquals(routeTagsToDir, outputData.getRouteTagsToDir());
    }

    @Test
    void testEquals() {
        // Mock data
        String stopName = "Example Stop";
        HashMap<String, String> dirTagsToNames = new HashMap<>();
        dirTagsToNames.put("dirTag", "Direction Name");
        HashMap<String, HashMap<String, String>> routeTagsToDir = new HashMap<>();
        routeTagsToDir.put("routeTag", dirTagsToNames);

        // Create StopDetailsOutputData instances
        StopDetailsOutputData outputData1 = new StopDetailsOutputData(stopName, routeTagsToDir);
        StopDetailsOutputData outputData2 = new StopDetailsOutputData(stopName, routeTagsToDir);

        // Verify equals method
        assertEquals(outputData1.getStopName(), outputData2.getStopName());
        assertEquals(outputData1.getRouteTagsToDir(), outputData2.getRouteTagsToDir());
    }

    @Test
    void testNotEquals() {
        // Mock data
        String stopName = "Example Stop";
        HashMap<String, String> dirTagsToNames1 = new HashMap<>();
        dirTagsToNames1.put("dirTag", "Direction Name");
        HashMap<String, HashMap<String, String>> routeTagsToDir1 = new HashMap<>();
        routeTagsToDir1.put("routeTag", dirTagsToNames1);

        HashMap<String, String> dirTagsToNames2 = new HashMap<>();
        dirTagsToNames2.put("dirTag", "Different Direction Name");
        HashMap<String, HashMap<String, String>> routeTagsToDir2 = new HashMap<>();
        routeTagsToDir2.put("routeTag", dirTagsToNames2);

        // Create StopDetailsOutputData instances
        StopDetailsOutputData outputData1 = new StopDetailsOutputData(stopName, routeTagsToDir1);
        StopDetailsOutputData outputData2 = new StopDetailsOutputData(stopName, routeTagsToDir2);

        // Verify not equals
        assertNotEquals(outputData1, outputData2);
    }


}
