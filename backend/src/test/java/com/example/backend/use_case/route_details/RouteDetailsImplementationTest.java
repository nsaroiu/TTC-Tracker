package com.example.backend.use_case.route_details;

import com.example.backend.entity.Route;
import com.example.backend.entity.RouteDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class RouteDetailsImplementationTest {

    @Mock
    private RouteDetailsDataAccessInterface routeDetailsDAO;

    @InjectMocks
    private RouteDetailsImplementation routeDetailsImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ValidRouteAndDirection() {
        // Create sample data
        String routeTag = "SampleRoute";
        String dirTag = "SampleDirection";

        // Create sample Route and RouteDirection
        RouteDirection routeDirection = new RouteDirection(new ArrayList<>(), dirTag, "SampleDirectionName");
        HashMap<String, RouteDirection> routeDirections = new HashMap<>();
        routeDirections.put(dirTag, routeDirection);
        Route route = new Route(new HashMap<>(), routeTag, routeDirections);

        // Mock behavior of DAOs
        when(routeDetailsDAO.getRouteByRouteTag(routeTag)).thenReturn(route);
        when(routeDetailsDAO.getShapeByDirTag(dirTag)).thenReturn(new ArrayList<>());

        // Test execute method
        RouteDetailsOutputData outputData = routeDetailsImplementation.execute(routeTag, dirTag);

        // Verify behavior
        verify(routeDetailsDAO, times(1)).getRouteByRouteTag(routeTag);
        verify(routeDetailsDAO, times(1)).getShapeByDirTag(dirTag);

        // Verify output data
        assertEquals(routeDirection.getName(), outputData.getDirName());
        assertEquals(routeDetailsDAO.getShapeByDirTag(dirTag), outputData.getShape());
    }

    @Test
    void testExecute_InvalidRoute() {
        // Create sample data
        String routeTag = "InvalidRoute";
        String dirTag = "SampleDirection";

        // Mock behavior of DAOs
        when(routeDetailsDAO.getRouteByRouteTag(routeTag)).thenReturn(null);

        // Test execute method with invalid route
        RouteDetailsOutputData outputData = routeDetailsImplementation.execute(routeTag, dirTag);

        // Verify behavior
        verify(routeDetailsDAO, times(1)).getRouteByRouteTag(routeTag);
        verify(routeDetailsDAO, never()).getShapeByDirTag(anyString());

        // Verify output data
        assertNull(outputData);
    }

    @Test
    void testExecute_InvalidDirection() {
        // Create sample data
        String routeTag = "SampleRoute";
        String dirTag = "InvalidDirection";

        // Create sample Route
        Route route = new Route(new HashMap<>(), routeTag, new HashMap<>());

        // Mock behavior of DAOs
        when(routeDetailsDAO.getRouteByRouteTag(routeTag)).thenReturn(route);
        when(routeDetailsDAO.getShapeByDirTag(dirTag)).thenReturn(null);

        // Test execute method with invalid direction
        RouteDetailsOutputData outputData = routeDetailsImplementation.execute(routeTag, dirTag);

        // Verify behavior
        verify(routeDetailsDAO, times(1)).getRouteByRouteTag(routeTag);
        verify(routeDetailsDAO, times(0)).getShapeByDirTag(dirTag);

        // Verify output data
        assertNull(outputData);
    }
}
