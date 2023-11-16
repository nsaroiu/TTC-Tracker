package com.example.backend.use_case.stop_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class StopDetailsInputData {
    private String stopTag;

    public StopDetailsInputData(String stopTag){
        this.stopTag = stopTag;
    }

    public String getStopTag() {
        return this.stopTag;
    }
}
