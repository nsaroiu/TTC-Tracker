package com.example.backend.use_case.predictions.strategies;

import java.util.ArrayList;

public interface Strategy {
    ArrayList<Float> execute(StrategyInputData data);
}
