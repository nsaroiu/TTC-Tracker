package entity;

public class Vehicle {

    int id;
    int speed;
    float latitude;
    float longitude;
    int direction;

    public Vehicle(int id, int speed, float latitude, float longitude, int direction) {
        this.id = id;
        this.speed = speed;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
    }

    public int getId() {
        return this.id;
    }

    public int getSpeed() {
        return speed;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getDirection() {
        return direction;
    }
}
