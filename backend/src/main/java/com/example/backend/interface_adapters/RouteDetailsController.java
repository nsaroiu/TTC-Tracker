package com.example.backend.interface_adapters;

import com.example.backend.use_case.route_details.RouteDetailsOutputData;
import com.example.backend.use_case.route_details.RouteDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class RouteDetailsController {
    @Autowired
    private RouteDetailsService routeDetailsImplementation;

    /** Returns the route details for the given routeTag and dirTag. Returns null if the routeTag or dirTag is invalid.
     *
     * @param routeTag String representing the routeTag
     * @param dirTag String representing the dirTag
     * @return RouteDetailsOutputData object containing the routeTag, dirTag, dirName, and routeShape. Null if the routeTag or dirTag is invalid.
     */
    @PostMapping("/route-details")
    public RouteDetailsOutputData execute(@RequestParam String routeTag, @RequestParam String dirTag) {
        return routeDetailsImplementation.execute(routeTag, dirTag);
    }

}
