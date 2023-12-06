package com.example.backend.use_case.predictions.strategies;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScheduleStrategyTest {

    @Test
    void execute_shouldReturnClosestTimes() {
        // Mock data setup
        StrategyInputData data = mock(StrategyInputData.class);
        when(data.getSchedule()).thenReturn(getSampleSchedule());

        // Create an instance of the ScheduleStrategy
        ScheduleStrategy scheduleStrategy = new ScheduleStrategy();

        // Call the execute method
        ArrayList<Float> predictions = scheduleStrategy.execute(data);

        // Verify the interaction
        assertEquals(3, predictions.size());
        // Add your specific assertions based on the sample schedule and current time
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
