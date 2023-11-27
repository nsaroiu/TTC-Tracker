package com.example.backend.entity;

public class Vehicle implements DistanceMeasurable {

    int id;
    int speed;
    Location location;
    int heading;
    String directionTag;

    public Vehicle(int id, int speed, float latitude, float longitude, int heading, String directionTag) {
        this.id = id;
        this.speed = speed;
        this.location = new Location(latitude, longitude);
        this.heading = heading;
        this.directionTag = directionTag;
    }

    public int getId() {
        return this.id;
    }

    public int getSpeed() {
        return speed;
    }

    public Location getLocation() { return location; }

    public int getHeading() {
        return heading;
    }

    public String getDirectionTag() { return directionTag; }

    public float distanceTo(DistanceMeasurable other) {
        return location.distanceTo(other.getLocation());
    }
}
