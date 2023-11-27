package com.example.backend.interface_adapters;
import com.example.backend.use_case.stop_details.StopDetailsOutputData;
import com.example.backend.use_case.stop_details.StopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling stop details use case.
 */
@RestController
@CrossOrigin(origins = "*")
public class StopDetailsController {
    /**
     * The service for this controller.
     */
    @Autowired
    private StopDetailsService stopDetailsImplementation;

    /**
     * Executes the stop details service with the provided stopTag.
     *
     * @param stopTag The identifier for the stop details.
     * @return The output data from the stop details service.
     */
    @PostMapping("/stop-details")
    public StopDetailsOutputData execute(@RequestParam String stopTag){
        return stopDetailsImplementation.execute(stopTag);
    }
}
