package data_access;

import entity.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface RouteDataAccessInterface {

    ArrayList<String> getRouteTagList();

    ArrayList<Route> getRoutesByStopTag(String tag);

    HashMap<String, HashSet<String>> getStopTagsToRouteTags();

}
