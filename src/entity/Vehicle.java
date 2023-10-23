package entity;

public class Vehicle {

    int id;
    int speed;
    Location location;
    int direction;

    public Vehicle(int id, int speed, double latitude, double longitude, int direction) {
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

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }

    public int getDirection() {
        return direction;
    }
}
