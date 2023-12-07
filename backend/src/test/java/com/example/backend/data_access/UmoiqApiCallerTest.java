package com.example.backend.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

public class UmoiqApiCallerTest {
    private UmoiqApiCaller umoiqApiCaller;

    @BeforeEach
    void setUp() {
        umoiqApiCaller = new UmoiqApiCaller();
    }

    @Test
    void testGetRequest() throws InvalidRequestException {
        String[][] params = new String[][]{{"command", "routeList"}};

        Document doc = umoiqApiCaller.getRequest(params);

        assert(doc != null);
    }
}
