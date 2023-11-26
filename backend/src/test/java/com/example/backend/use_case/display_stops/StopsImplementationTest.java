package com.example.backend.use_case.display_stops;

import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Stop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StopsImplementationTest {

    @Mock
    private StopDataAccessInterface stopDataAccessObject;

    @InjectMocks
    private StopsImplementation stopsImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ReturnsStopsOutputData_WhenStopsExist() {
        // Arrange
        HashSet<Stop> mockedStops = new HashSet<>();
        mockedStops.add(new Stop("Tag1", "Stop1", 1.0f, 2.0f, new HashSet<>()));
        mockedStops.add(new Stop("Tag2", "Stop2", 3.0f, 4.0f, new HashSet<>()));

        when(stopDataAccessObject.getAllStops()).thenReturn(mockedStops);

        // Act
        StopsOutputData result = stopsImplementation.execute();

        // Assert
        assertEquals(mockedStops.size(), result.getStops().size());
        // Add more assertions based on your specific requirements
    }

    @Test
    void execute_ReturnsEmptyStopsOutputData_WhenNoStopsExist() {
        // Arrange
        when(stopDataAccessObject.getAllStops()).thenReturn(new HashSet<>());

        // Act
        StopsOutputData result = stopsImplementation.execute();

        // Assert
        assertEquals(0, result.getStops().size());
    }
}
