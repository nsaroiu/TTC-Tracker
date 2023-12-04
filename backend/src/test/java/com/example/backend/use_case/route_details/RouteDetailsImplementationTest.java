package com.example.backend.use_case.route_details;

import com.example.backend.data_access.direction.DirectionDataAccessInterface;
import com.example.backend.data_access.route.RouteDataAccessInterface;
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
    private RouteDataAccessInterface routeDAO;

    @Mock
    private DirectionDataAccessInterface directionDAO;

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
        when(routeDAO.getRouteByRouteTag(routeTag)).thenReturn(route);
        when(directionDAO.getShapeByDirTag(dirTag)).thenReturn(new ArrayList<>());

        // Test execute method
        RouteDetailsOutputData outputData = routeDetailsImplementation.execute(routeTag, dirTag);

        // Verify behavior
        verify(routeDAO, times(1)).getRouteByRouteTag(routeTag);
        verify(directionDAO, times(1)).getShapeByDirTag(dirTag);

        // Verify output data
        assertEquals(routeDirection.getName(), outputData.getDirName());
        assertEquals(directionDAO.getShapeByDirTag(dirTag), outputData.getShape());
    }

    @Test
    void testExecute_InvalidRoute() {
        // Create sample data
        String routeTag = "InvalidRoute";
        String dirTag = "SampleDirection";

        // Mock behavior of DAOs
        when(routeDAO.getRouteByRouteTag(routeTag)).thenReturn(null);

        // Test execute method with invalid route
        RouteDetailsOutputData outputData = routeDetailsImplementation.execute(routeTag, dirTag);

        // Verify behavior
        verify(routeDAO, times(1)).getRouteByRouteTag(routeTag);
        verify(directionDAO, never()).getShapeByDirTag(anyString());

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
        when(routeDAO.getRouteByRouteTag(routeTag)).thenReturn(route);
        when(directionDAO.getShapeByDirTag(dirTag)).thenReturn(null);

        // Test execute method with invalid direction
        RouteDetailsOutputData outputData = routeDetailsImplementation.execute(routeTag, dirTag);

        // Verify behavior
        verify(routeDAO, times(1)).getRouteByRouteTag(routeTag);
        verify(directionDAO, times(0)).getShapeByDirTag(dirTag);

        // Verify output data
        assertNull(outputData);
    }
}
