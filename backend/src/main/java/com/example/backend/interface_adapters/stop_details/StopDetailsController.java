package com.example.backend.interface_adapters.stop_details;

import com.example.backend.entity.Route;
import com.example.backend.use_case.stop_details.StopDetailsInputBoundary;
import com.example.backend.use_case.stop_details.StopDetailsInputData;
import com.example.backend.use_case.stop_details.StopDetailsOutputData;
import com.example.backend.use_case.stop_details.StopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
@CrossOrigin(origins = "*")
public class StopDetailsController {
    @Autowired
    private StopDetailsService stopDetailsService;


    @GetMapping("/stops")
    public StopDetailsOutputData execute(String stopTag) throws Exception {
        StopDetailsInputData inputData = new StopDetailsInputData(stopTag);
        return stopDetailsService.execute(inputData);
    }
}
