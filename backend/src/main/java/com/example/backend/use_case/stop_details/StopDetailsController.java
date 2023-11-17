package com.example.backend.use_case.stop_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller for the stop details use case.
 */
@RestController
public class StopDetailsController {
    /**
    *  Instance variables:
    *  - stopDetailsService: The service for this controller
    */
    @Autowired
    private StopDetailsService stopDetailsService;

//    public StopDetailsController(StopDetailsService stopDetailsService){
//        this.stopDetailsService = stopDetailsService;
//    }

    /**
     * Given a stopTag, creates the input data and executes the service with it.
     */
    @PostMapping("/stopdetails")
    public StopDetailsOutputData execute(@RequestParam String stopTag){
        StopDetailsInputData inputData = new StopDetailsInputData(stopTag);
        return stopDetailsService.execute(inputData);
    }
}
