package com.example.backend.interface_adapters;
import com.example.backend.use_case.stop_details.StopDetailsOutputData;
import com.example.backend.use_case.stop_details.StopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This is the controller for the stop details use case.
 */
@RestController
@CrossOrigin(origins = "*")
public class StopDetailsController {
    /**
    *  Instance variables:
    *  - stopDetailsService: The service for this controller
    */
    @Autowired
    private StopDetailsService stopDetailsImplementation;

    /**
     * Given a stopTag, creates the input data and executes the service with it.
     */
    @PostMapping("/stop-details")
    public StopDetailsOutputData execute(@RequestParam String stopTag){
        return stopDetailsImplementation.execute(stopTag);
    }
}
