package use_case;

import entity.Stop;

import java.util.HashSet;
import java.util.List;

public class StopsOutputData {

    private HashSet<Stop> stopsSet;

    public StopsOutputData(HashSet<Stop> stopsSet) {
        this.stopsSet = stopsSet;
    }

    public HashSet<Stop> getStopsSet() {
        return stopsSet;
    }

}
