package com.example.backend.entity;

public class Location implements DistanceMeasurable {
    private float latitude;
    private float longitude;

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location getLocation() { return this; }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float distanceTo(DistanceMeasurable other) {
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(other.getLocation().getLatitude());
        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(other.getLocation().getLongitude());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;

        return (float) (c * r);
    }
}
