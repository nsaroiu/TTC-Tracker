package com.example.backend.use_case.display_routes;

import com.example.backend.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class DisplayRoutesController {
    @Autowired
    private DisplayRoutesService displayRoutesService;

    @GetMapping("/shapes")
    public HashMap<String, ArrayList<Location>> execute() {
        return displayRoutesService.execute();
    }
}
