package com.example.backend.use_case.stop_details;

/**
 * Service interface for the stop details use case.
 */
public interface StopDetailsService {
     /**
      * Executes the stop details use case based on the provided stopTag.
      *
      * @param stopTag The identifier for the stop.
      * @return The output data containing stop details.
      */
     StopDetailsOutputData execute(String stopTag);
}
