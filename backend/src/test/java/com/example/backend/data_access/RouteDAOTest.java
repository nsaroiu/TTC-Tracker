package com.example.backend.data_access;

import com.example.backend.data_access.direction.DirectionDAO;
import com.example.backend.data_access.route.RouteDAO;
import com.example.backend.data_access.stop.StopDAO;
import com.example.backend.entity.Route;
import com.example.backend.entity.RouteDirection;
import com.example.backend.entity.Stop;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RouteDAOTest {

    private static MockedStatic<UmoiqApiCaller> apiCallerMockedStatic;

    @InjectMocks
    private RouteDAO routeDAO;

    @BeforeEach
    void setUp() throws IOException, ParserConfigurationException, SAXException {
        MockitoAnnotations.openMocks(this);
        apiCallerMockedStatic = mockStatic(UmoiqApiCaller.class);
        final String xmlString = "<body copyright=\"All data copyright Toronto Transit Commission 2023.\">\n" +
                "<route tag=\"22\" title=\"22-Coxwell\"/>" +
                "</body>";
        Document doc = stringToXMLDocument(xmlString);
        String[][] params = {{"command", "routeList"}};
        apiCallerMockedStatic.when(() -> UmoiqApiCaller.getRequest(eq(params))).thenReturn(doc);
    }

    private Document stringToXMLDocument(String xmlString) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlString)));
    }

    @Test
    void testGetRouteTagsByStopTag() {
        try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
            try (MockedConstruction<CSVReader> mockCSVReader = mockConstruction(CSVReader.class, (mock, context) -> {
                when(mock.readNext()).thenReturn(new String[]{"header"}, new String[]{"3648", "Wilson Heights Blvd At Cocksfield Ave", "43.75293", "-79.45795", "104"}, null);
            })) {
                HashSet<String> routeTags = routeDAO.getRouteTagsByStopTag("3648");
                assertEquals(1, routeTags.size());
                assertEquals("104", routeTags.toArray()[0]);
            }
        }
    }

    @Test
    void testGetRouteByRouteTag() {
        HashSet<Stop> stops = new HashSet<>();
        stops.add(new Stop("3648", "Wilson Heights Blvd At Cocksfield Ave", 0, 0, null));

        HashMap<String, RouteDirection> routeDirections = new HashMap<>();
        routeDirections.put("69_0_69A", new RouteDirection(null, "69_0_69A", "South - 69a Warden South towards Kingston Rd via Warden"));
        try (MockedConstruction<StopDAO> mockStopDAO = mockConstruction(StopDAO.class, (mock, context) -> {
            when(mock.getStopsByRouteTag(any())).thenReturn(stops);
        })) {
            try (MockedConstruction<DirectionDAO> mockDirectionDAO = mockConstruction(DirectionDAO.class, (mock, context) -> {
                when(mock.getDirectionsByRouteTag(any())).thenReturn(routeDirections);
            })) {
                Route route = routeDAO.getRouteByRouteTag("69");
                assertEquals(1, route.getStops().size());
                assertEquals("3648", route.getStops().get("3648").getTag());
                assertEquals(1, route.getRouteDirections().size());
                assertEquals("69_0_69A", route.getRouteDirections().get("69_0_69A").getDirTag());
            }
        }

    }

    @Test
    void testGetRouteTags() {
        HashSet<String> routeTags = routeDAO.getRouteTags();
        assertEquals(1, routeTags.size());
        assertEquals("22", routeTags.toArray()[0]);
    }

    @AfterEach
    void close() {
        apiCallerMockedStatic.close();
    }
}
