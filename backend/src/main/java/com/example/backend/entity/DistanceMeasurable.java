package com.example.backend.entity;

public interface DistanceMeasurable {
    Location getLocation();

    float distanceTo(DistanceMeasurable other);
}
