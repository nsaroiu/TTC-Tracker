package com.example.backend.use_case.stop_details;

import org.springframework.stereotype.Component;

@Component
public class StopDetailsInputData {
    private String stopTag;

    public String getStopTag() {
        return this.stopTag;
    }

    public void setStopId(String newStopTag) {
        this.stopTag = newStopTag;
    }
}
