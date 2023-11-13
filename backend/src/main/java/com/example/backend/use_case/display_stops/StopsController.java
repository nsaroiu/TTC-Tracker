package com.example.backend.use_case.display_stops;

import com.example.backend.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class StopsController {
    @Autowired
    private StopsService stopsService;

    @GetMapping("/stops")
    public HashMap<String, Location>  execute() {
        return stopsService.execute();
    }

}
