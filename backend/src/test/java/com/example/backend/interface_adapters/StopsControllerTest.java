package com.example.backend.interface_adapters;

import com.example.backend.entity.Stop;
import com.example.backend.use_case.display_stops.StopsOutputData;
import com.example.backend.use_case.display_stops.StopsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StopsControllerTest {

    @Mock
    private StopsService stopsImplementation;

    @InjectMocks
    private StopsController stopsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        // Mock data for testing
        HashSet<Stop> mockStops = new HashSet<>();

        // Mock the behavior of stopsService
        when(stopsImplementation.execute()).thenReturn(new StopsOutputData(mockStops));

        // Call the controller method
        StopsOutputData stopsOutputData = stopsController.execute();
        System.out.println(stopsOutputData.getStops());
        System.out.println(mockStops);
        // Assertions
        assertEquals(mockStops, stopsOutputData.getStops());
    }
}
