package com.example.backend.use_case.predictions;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PredictionsOutputDataTest {

    @Test
    void getPredictionTimes_shouldReturnCorrectList() {
        // Create a sample list of prediction times
        ArrayList<Float> samplePredictions = new ArrayList<>();
        samplePredictions.add(10.5f);
        samplePredictions.add(15.3f);
        samplePredictions.add(20.0f);

        // Create an instance of PredictionsOutputData
        PredictionsOutputData predictionsOutputData = new PredictionsOutputData(samplePredictions);

        // Call getPredictionTimes and verify the returned list
        ArrayList<Float> retrievedPredictions = predictionsOutputData.getPredictionTimes();
        assertNotNull(retrievedPredictions);
        assertEquals(samplePredictions.size(), retrievedPredictions.size());
        assertIterableEquals(samplePredictions, retrievedPredictions);
    }
}
