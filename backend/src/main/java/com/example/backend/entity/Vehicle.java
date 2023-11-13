package com.example.backend.entity;

public class Vehicle {

    int id;
    int speed;
    Location location;
    int direction;

    public Vehicle(int id, int speed, float latitude, float longitude, int direction) {
        this.id = id;
        this.speed = speed;
        this.location = new Location(latitude, longitude);
        this.direction = direction;
    }

    public int getId() {
        return this.id;
    }

    public int getSpeed() {
        return speed;
    }

    public Location getLocation() { return location; }

    public int getDirection() {
        return direction;
    }
}
