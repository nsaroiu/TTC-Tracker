package com.example.backend.use_case.predictions.strategies;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScheduleStrategyTest {

    private static MockedStatic<LocalDateTime> mockedLocalDateTime;
    private static LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 8, 0, 0, 1);

    @InjectMocks
    private StrategyInputData data;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockedLocalDateTime = mockStatic(LocalDateTime.class);

        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(localDateTime);

    }

    @Test
    void execute_shouldReturnClosestTimes() {
        // Mock data setup
        data = mock(StrategyInputData.class);
        when(data.getSchedule()).thenReturn(getSampleSchedule());

        // Create an instance of the ScheduleStrategy
        ScheduleStrategy scheduleStrategy = new ScheduleStrategy();

        // Call the execute method
        ArrayList<Float> predictions = scheduleStrategy.execute(data);

        // Verify the interaction
        assertEquals(3, predictions.size());
        // Add your specific assertions based on the sample schedule and current time
    }

    @AfterEach
    void close() {
        mockedLocalDateTime.close();
    }

    private ArrayList<String> getSampleSchedule() {
        ArrayList<String> schedule = new ArrayList<>();
        schedule.add("08:00:00");
        schedule.add("08:30:00");
        schedule.add("09:00:00");
        schedule.add("09:30:00");
        schedule.add("10:00:00");
        // Add more sample schedule times if needed
        return schedule;
    }
}
