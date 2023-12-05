package com.example.backend.data_access;

import com.example.backend.data_access.direction.DirectionDAO;
import com.example.backend.entity.Location;
import com.example.backend.entity.RouteDirection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DirectionDAOTest {

    private DirectionDAO directionDAO;

    @BeforeEach
    void setUp() {
        directionDAO = new DirectionDAO();
    }

    @Test
    void testGetDirectionsByRouteTag() throws IOException {
        try (MockedConstruction<BufferedReader> mockReader = mockConstruction(BufferedReader.class, (mock, context) -> {
            when(mock.readLine()).thenReturn("header", "222,33_1_33Con,33,North - 33 Forest Hill towards Eglinton (Roselawn),24633|14125|10432|1600|7328|2929|8756|6019|2810|3128|4394|3129,43.683765|-79.415278/43.683772|-79.415224/43.684131|-79.413503/43.684156|-79.413382/43.684333|-79.41252/43.684384|-79.412289/43.684618|-79.411157/43.685049|-79.411337/43.685691|-79.411605/43.686769|-79.412033/43.687334|-79.412261/43.687424|-79.412297/43.687762|-79.412432/43.688096|-79.412562/43.688196|-79.41259/43.688332|-79.412614/43.688427|-79.412619/43.68851|-79.412631/43.688662|-79.412657/43.689424|-79.412996/43.689812|-79.413164/43.690214|-79.413339/43.690969|-79.413641/43.692044|-79.414108/43.692821|-79.414413/43.692924|-79.414466/43.694318|-79.415014/43.694452|-79.415082/43.695199|-79.415417/43.695958|-79.415722/43.69608|-79.41578/43.696702|-79.416029/43.697414|-79.41633/43.698204|-79.416666/43.699015|-79.417024/43.699754|-79.41733/43.700551|-79.417671/43.70113|-79.417907/43.701233|-79.41796/43.701314|-79.418021/43.701381|-79.418085/43.701965|-79.418697/43.702345|-79.419096/43.702179|-79.419872/43.701994|-79.420878/43.701731|-79.42208/43.702887|-79.422395", null);
        })) {
            try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
                HashMap<String, RouteDirection> result = directionDAO.getDirectionsByRouteTag("33");

                assertEquals(1, result.size());
                RouteDirection direction = result.get("33_1_33Con");
                assertEquals("33_1_33Con", direction.getDirTag());
                assertEquals(12, direction.getStops().size());
                assertEquals("North - 33 Forest Hill towards Eglinton (Roselawn)", direction.getName());
            }
        }
    }

    @Test
    void testGetShapeByDirTag() throws IOException {
        try (MockedConstruction<BufferedReader> mockReader = mockConstruction(BufferedReader.class, (mock, context) -> {
            when(mock.readLine()).thenReturn("header", "222,33_1_33Con,33,North - 33 Forest Hill towards Eglinton (Roselawn),24633|14125|10432|1600|7328|2929|8756|6019|2810|3128|4394|3129,43.683765|-79.415278/43.683772|-79.415224/43.684131|-79.413503/43.684156|-79.413382/43.684333|-79.41252/43.684384|-79.412289/43.684618|-79.411157/43.685049|-79.411337/43.685691|-79.411605/43.686769|-79.412033/43.687334|-79.412261/43.687424|-79.412297/43.687762|-79.412432/43.688096|-79.412562/43.688196|-79.41259/43.688332|-79.412614/43.688427|-79.412619/43.68851|-79.412631/43.688662|-79.412657/43.689424|-79.412996/43.689812|-79.413164/43.690214|-79.413339/43.690969|-79.413641/43.692044|-79.414108/43.692821|-79.414413/43.692924|-79.414466/43.694318|-79.415014/43.694452|-79.415082/43.695199|-79.415417/43.695958|-79.415722/43.69608|-79.41578/43.696702|-79.416029/43.697414|-79.41633/43.698204|-79.416666/43.699015|-79.417024/43.699754|-79.41733/43.700551|-79.417671/43.70113|-79.417907/43.701233|-79.41796/43.701314|-79.418021/43.701381|-79.418085/43.701965|-79.418697/43.702345|-79.419096/43.702179|-79.419872/43.701994|-79.420878/43.701731|-79.42208/43.702887|-79.422395", null);
        })) {
            try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
                ArrayList<Location> result = directionDAO.getShapeByDirTag("33_1_33Con");

                assertEquals(47, result.size());
                assertEquals(43.683765, result.get(0).getLatitude(), 0.01f);
                assertEquals(-79.415278, result.get(0).getLongitude(), 0.01f);
                assertEquals(43.683772, result.get(11).getLatitude(), 0.01f);
                assertEquals(-79.415224, result.get(11).getLongitude(), 0.01f);
            }
        }
    }

    @Test
    void testGetAverageSpeed() throws IOException {
        try (MockedConstruction<BufferedReader> mockReader = mockConstruction(BufferedReader.class, (mock, context) -> {
            when(mock.readLine()).thenReturn("header", "1,19_0_19,10.26086956521739", null);
        })) {
            try (MockedConstruction<FileReader> mockFileReader = mockConstruction(FileReader.class)) {
                float result = directionDAO.getAverageSpeed("19_0_19", "1");

                assertEquals(10.260869, result, 0.01f);
            }
        }
    }
}
