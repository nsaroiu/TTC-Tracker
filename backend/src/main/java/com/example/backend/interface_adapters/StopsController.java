package com.example.backend.interface_adapters;

import com.example.backend.use_case.display_stops.StopsOutputData;
import com.example.backend.use_case.display_stops.StopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling stops-related HTTP requests.
 */
@RestController
@CrossOrigin(origins = "*")
public class StopsController {

    @Autowired
    private StopsService stopsImplementation;

    /**
     * Handles the HTTP GET request for retrieving stops data.
     *
     * @return StopsOutputData containing information about stops.
     */
    @GetMapping("/stops")
    public StopsOutputData execute() {
        return stopsImplementation.execute();
    }
}
