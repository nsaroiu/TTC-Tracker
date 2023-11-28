package com.example.backend.use_case.predictions;

import com.example.backend.entity.Location;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface PredictionsService {
    PredictionsOutputData execute(String routeTag, String dirTag, String stopTag);
}
