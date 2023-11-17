package com.example.backend.use_case.stop_details;
/**
 *  This is the input data class for the stop details use case.
 * */
public class StopDetailsInputData {
    /**
     * Instance variables:
     * - stopTag: the stop tag of the stop
     */
    private String stopTag;

    /**
     * Creates new instance of StopDetailsInputData
     */
    public StopDetailsInputData(String stopTag){
        this.stopTag = stopTag;
    }

    /**
     * Returns the stopTag
     */
    public String getStopTag() {
        return this.stopTag;
    }
}
