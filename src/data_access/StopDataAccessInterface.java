package data_access;

import java.util.HashMap;
import java.util.HashSet;

public interface StopDataAccessInterface {

    HashSet<String> getStopTagsByRouteTag(String routeTag);

    HashMap<String, HashSet<String>> getRouteTagsToStopTags();

}
