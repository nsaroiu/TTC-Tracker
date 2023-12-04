package com.example.backend.use_case.predictions.strategies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

// A Strategy used in the predictions use case that returns the predicted times of arrival, in minutes,
// for vehicles in the specified direction, to the specified stop.
// The strategy uses the scheduled arrival times of the stop to calculate predicted times.
public class ScheduleStrategy implements Strategy {

    // A helper method that calculates the time difference in minutes between two times in the format HH:MM:SS
    private float calculateTimeDifference(String time1, String time2) {
        String[] time1Split = time1.split(":");
        String[] time2Split = time2.split(":");
        int time1Seconds = Integer.parseInt(time1Split[0]) * 3600 + Integer.parseInt(time1Split[1]) * 60 + Integer.parseInt(time1Split[2]);
        int time2Seconds = Integer.parseInt(time2Split[0]) * 3600 + Integer.parseInt(time2Split[1]) * 60 + Integer.parseInt(time2Split[2]);
        // Check if time2 is earlier than time1
        if (time2Seconds < time1Seconds) {
            // Adjust time2 to represent the time on the next day
            time2Seconds += 24 * 3600;
        }
        return (float) ((time2Seconds - time1Seconds) / 60.0);
    }

    public ArrayList<Float> execute(StrategyInputData data) {
        String currentTime = LocalDateTime.now().toString().split("T")[1].split("\\.")[0];
        ArrayList<String> times = data.getSchedule();
        times.sort(Comparator.comparing(time -> calculateTimeDifference(currentTime, time)));
        int i = 0;
        ArrayList<Float> closestTimes = new ArrayList<>();
        for (String time: times) {
            if (i < 3) {
                closestTimes.add(calculateTimeDifference(currentTime, time));
            } else {
                break;
            }
            i++;
        }
        return closestTimes;
    }
}
