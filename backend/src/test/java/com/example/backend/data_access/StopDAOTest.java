package com.example.backend.data_access;

import com.example.backend.data_access.stop.StopDAO;
import com.example.backend.entity.Stop;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StopDAOTest {
    private StopDAO stopDAO;

    @BeforeEach
    void setUp() {
        stopDAO = new StopDAO();
    }

    @Test
    void getStopTagsByRouteTag() {
        try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
            try (MockedConstruction<CSVReader> mockCSVReader = mockConstruction(CSVReader.class, (mock, context) -> {
                when(mock.readNext()).thenReturn(new String[]{"header"},
                        new String[]{"7023","Cummer Ave At Bayview Ave","43.79212","-79.39381","42"},
                        new String[]{"8399","Cummer Ave At Craigmont Dr","43.79716","-79.37914","42"},
                        null);
            })) {
                HashSet<String> stopTags = stopDAO.getStopTagsByRouteTag("42");
                assertEquals(2, stopTags.size());
                assert(stopTags.contains("7023"));
                assert(stopTags.contains("8399"));
            }
        }
    }

    @Test
    void getStopsByRouteTag() {
        StopDAO mockStopDAO = spy(new StopDAO());
        HashSet<Stop> allStops = new HashSet<>();
        HashSet<String> routeTags = new HashSet<>();
        routeTags.add("19");
        allStops.add(new Stop("1642", "Bedford Rd At Davenport Rd", 0, 0, routeTags));
        HashSet<String> stopTags = new HashSet<>();
        stopTags.add("1642");
        doReturn(stopTags).when(mockStopDAO).getStopsByRouteTag(any());
        doReturn(allStops).when(mockStopDAO).getAllStops();

        HashSet<Stop> stops = mockStopDAO.getStopsByRouteTag("19");

        assertEquals(1, stops.size());
        assertEquals("1642", stops.iterator().next());
    }

    @Test
    void testGetAllStops() {
        try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
            try (MockedConstruction<CSVReader> mockCSVReader = mockConstruction(CSVReader.class, (mock, context) -> {
                when(mock.readNext()).thenReturn(
                        new String[]{"header"},
                        new String[]{"1642","Bedford Rd At Davenport Rd","0","0","26,19"},
                        new String[]{"2106","Davenport Rd At Avenue Rd West Side","0","0","19"},
                        null
                );
            })) {
                HashSet<Stop> stops = stopDAO.getAllStops();
                assertEquals(2, stops.size());
            }
        }
    }

    @Test
    void testGetScheduledArrivals() {
        try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
            try (MockedConstruction<BufferedReader> mockReader = mockConstruction(BufferedReader.class, (mock, context) -> {
                when(mock.readLine()).thenReturn(
                        "header",
                        "510_0_510A,20:57:37,3741",
                        "510_0_510A,22:12:37,3741",
                        null
                );
            })) {
                ArrayList<String> schedules = stopDAO.getScheduledArrivals("3741", "510_0_510A");
                assertEquals(2, schedules.size());
                assertEquals("20:57:37", schedules.get(0));
                assertEquals("22:12:37", schedules.get(1));
            }
        }
//        try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
//            try (MockedConstruction<CSVReader> mockCSVReader = mockConstruction(CSVReader.class, (mock, context) -> {
//                when(mock.readNext()).thenReturn(
//                        new String[]{"header"},
//                        new String[]{"510_0_510A","20:57:37","3741"},
//                        new String[]{"510_0_510A","22:12:37","3741"},
//                        null
//                );
//            })) {
//                ArrayList<String> schedules = stopDAO.getScheduledArrivals("3741", "510_0_510A");
//                assertEquals(2, schedules.size());
//                assertEquals("20:57:37", schedules.get(0));
//                assertEquals("22:12:37", schedules.get(1));
//            }
//        }
    }
}
