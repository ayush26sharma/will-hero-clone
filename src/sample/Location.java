package sample;

import java.io.Serializable;

public class Location implements Serializable {

    private float x_coordinate;
    private float y_coordinate;

    public Location(float x, float y){
        this.x_coordinate = x;
        this.y_coordinate = y;
    }

    public float getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(float x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public float getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(float y_coordinate) {
        this.y_coordinate = y_coordinate;
    }
}
