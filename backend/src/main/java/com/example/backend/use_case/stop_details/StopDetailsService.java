package com.example.backend.use_case.stop_details;

/**
 * This is the service interface for the stop details use case.
 */

public interface StopDetailsService {
     StopDetailsOutputData execute(String stopTag);
}
