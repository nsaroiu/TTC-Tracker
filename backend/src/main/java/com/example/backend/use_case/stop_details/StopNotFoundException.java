package com.example.backend.use_case.stop_details;

public class StopNotFoundException extends Exception{
    /** An exception thrown when the StopDetailsService returns an error message.
     *
     * @param message Error message received from the StopDetailsService
     */
    public StopNotFoundException(String message) {
        super(message);
    }
}
