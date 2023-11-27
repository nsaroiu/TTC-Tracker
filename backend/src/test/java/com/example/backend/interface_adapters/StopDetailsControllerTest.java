package com.example.backend.interface_adapters;

import com.example.backend.use_case.stop_details.StopDetailsOutputData;
import com.example.backend.use_case.stop_details.StopDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class StopDetailsControllerTest {
    @Mock
    private StopDetailsService stopDetailsService;

    @InjectMocks
    private StopDetailsController stopDetailsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute() {
        // Mock data
        String stopTag = "exampleStopTag";
        StopDetailsOutputData mockOutputData = new StopDetailsOutputData("Example Stop", null);

        // Set up the mock behavior
        when(stopDetailsService.execute(anyString())).thenReturn(mockOutputData);

        // Call the controller method
        StopDetailsOutputData result = stopDetailsController.execute(stopTag);

        // Verify the interaction
        assertEquals(mockOutputData, result);
    }
}
