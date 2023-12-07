package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Stop;

import java.util.HashSet;

public interface StopsDataAccessInterface {

    /** Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    HashSet<Stop> getAllStops();
}
