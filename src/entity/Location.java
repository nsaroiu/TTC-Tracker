package entity;

public class Location {
    private float latitude;
    private float longitude;

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance(Location other) {
        throw new UnsupportedOperationException("Not implemented");
//        // untested
//        double lat1 = Math.toRadians(latitude);
//        double lat2 = Math.toRadians(other.latitude);
//        double lon1 = Math.toRadians(longitude);
//        double lon2 = Math.toRadians(other.longitude);
//
//        double dlon = lon2 - lon1;
//        double dlat = lat2 - lat1;
//
//        double a = Math.pow(Math.sin(dlat / 2), 2)
//                + Math.cos(lat1) * Math.cos(lat2)
//                * Math.pow(Math.sin(dlon / 2), 2);
//
//        double c = 2 * Math.asin(Math.sqrt(a));
//
//        double r = 6371;
//
//        return (c * r);
    }
}
