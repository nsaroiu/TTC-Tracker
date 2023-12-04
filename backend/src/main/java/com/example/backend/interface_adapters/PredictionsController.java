package com.example.backend.interface_adapters;

import com.example.backend.use_case.predictions.PredictionsOutputData;
import com.example.backend.use_case.predictions.PredictionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class PredictionsController {
    @Autowired
    private PredictionsService predictionsImplementation;

    @PostMapping("/predictions")
    public PredictionsOutputData execute(@RequestParam String routeTag, @RequestParam String dirTag, @RequestParam String stopTag) {
        return predictionsImplementation.execute(routeTag, dirTag, stopTag);
    }

}
