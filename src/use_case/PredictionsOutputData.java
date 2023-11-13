package use_case;

import entity.Location;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.HashMap;

public class PredictionsOutputData {
    //direction string,
    // Change Addressed!!!
    //          prediction number of minutes until the next bus
    // maps direction to a collection of arrival times
    private HashMap<String, ArrayList<Integer>> arrivalsByDirection;
    public PredictionsOutputData(HashMap<String, ArrayList<Integer>> arrivalsByDirection) {
        this.arrivalsByDirection = arrivalsByDirection;
    }
    public HashMap<String, ArrayList<Integer>> getArrivalsByDirection(){
        return arrivalsByDirection;
    }
}
