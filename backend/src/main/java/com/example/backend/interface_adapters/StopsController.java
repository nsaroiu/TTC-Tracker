package com.example.backend.interface_adapters;

import com.example.backend.entity.Location;
import com.example.backend.use_case.display_stops.StopsImplementation;
import com.example.backend.use_case.display_stops.StopsOutputData;
import com.example.backend.use_case.display_stops.StopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
 
@RestController
@CrossOrigin(origins = "*")
public class StopsController {
    @Autowired
    private StopsService stopsImplementation;

    @GetMapping("/stops")
    public StopsOutputData  execute() {
        return stopsImplementation.execute();
    }

}
