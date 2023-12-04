package com.example.backend.data_access;

import com.example.backend.data_access.vehicle.VehicleDAO;
import com.example.backend.entity.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class VehicleDAOTest {

    @InjectMocks
    private VehicleDAO vehicleDAO;

    private Document stringToXMLDocument(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlString)));
    }

    @BeforeEach
    void setUp() throws ParserConfigurationException, IOException, SAXException {
        MockitoAnnotations.openMocks(this);
        MockedStatic<UmoiqApiCaller> mockedStatic = mockStatic(UmoiqApiCaller.class);

        // Mock UmoiqApiCaller.getRequest to return a sample XML document
        final String xmlString = "<body>" +
                "<vehicle id=\"4575\" routeTag=\"510\" dirTag=\"510_1_510A\" lat=\"43.645922\" lon=\"-79.3789333\" secsSinceReport=\"13\" predictable=\"true\" heading=\"285\" speedKmHr=\"0\"/>" +
                "<vehicle id=\"4439\" routeTag=\"510\" dirTag=\"510_1_510A\" lat=\"43.6557159\" lon=\"-79.3990936\" secsSinceReport=\"14\" predictable=\"true\" heading=\"344\" speedKmHr=\"17\"/>" +
                "<lastTime time=\"1701666202700\"/>" +
                "</body>";
        Document doc = stringToXMLDocument(xmlString);
        String[][] params = {{"command", "vehicleLocations"}, {"r", "510"}, {"t", "0"}};
        mockedStatic.when(() -> UmoiqApiCaller.getRequest(eq(params))).thenReturn(doc);
    }

    @Test
    void execute_ReturnsVehicles_WhenVehiclesExist() {
        // Arrange
        String routeTag = "510";

        // Act
        ArrayList<Vehicle> result = vehicleDAO.getVehiclesByRouteTag(routeTag);

        // Assert
        assert(!result.isEmpty());
        assert(result.size() == 2);
        assert(result.get(0).getId() == 4575);
        assert(result.get(1).getId() == 4439);
    }
}
