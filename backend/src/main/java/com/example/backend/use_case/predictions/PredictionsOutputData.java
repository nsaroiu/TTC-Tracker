package com.example.backend.use_case.predictions;

import java.util.AbstractList;
import java.util.ArrayList;

public class PredictionsOutputData {
    ArrayList<Float> predictionTimes;

    public PredictionsOutputData(ArrayList<Float> predictionTimes) {
        this.predictionTimes = predictionTimes;
    }

    public ArrayList<Float> getPredictionTimes() { return predictionTimes; }
}
