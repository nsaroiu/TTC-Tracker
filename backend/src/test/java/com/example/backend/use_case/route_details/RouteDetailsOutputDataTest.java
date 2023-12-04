package com.example.backend.use_case.route_details;

import com.example.backend.entity.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RouteDetailsOutputDataTest {

    @Test
    void testGetShapeAndDirName() {
        // Create sample locations
        Location location1 = new Location(40.7128f, -74.0060f);
        Location location2 = new Location(34.0522f, -118.2437f);
        ArrayList<Location> shape = new ArrayList<>();
        shape.add(location1);
        shape.add(location2);

        // Create RouteDetailsOutputData instance
        RouteDetailsOutputData outputData = new RouteDetailsOutputData("SampleDir", shape);

        // Test getShape and getDirName methods
        assertNotNull(outputData.getShape());
        assertNotNull(outputData.getDirName());

        assertEquals(2, outputData.getShape().size());
        assertEquals(location1, outputData.getShape().get(0));
        assertEquals(location2, outputData.getShape().get(1));

        assertEquals("SampleDir", outputData.getDirName());
    }
}
