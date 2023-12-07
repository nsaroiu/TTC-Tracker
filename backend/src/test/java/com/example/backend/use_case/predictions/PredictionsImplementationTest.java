package com.example.backend.use_case.predictions;

import com.example.backend.use_case.predictions.strategies.Strategy;
import com.example.backend.use_case.predictions.strategies.StrategyInputData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PredictionsImplementationTest {

    private static MockedStatic<LocalDateTime> mockedLocalDateTime;
    private static final LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 8, 0, 0, 1);

    @Mock
    private PredictionsDataAccessInterface predictionsDAO;

    @Mock
    private Strategy strategy;

    @InjectMocks
    private PredictionsImplementation predictionsImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        predictionsImplementation = spy(predictionsImplementation);

        mockedLocalDateTime = mockStatic(LocalDateTime.class);
        mockedLocalDateTime.when(() -> LocalDateTime.now((ZoneId) any())).thenReturn(localDateTime);

        when(predictionsDAO.getRouteByRouteTag("routeTag")).thenReturn(null);
        when(predictionsDAO.getVehiclesByRouteTag("routeTag")).thenReturn(null);
        when(predictionsDAO.getShapeByDirTag("dirTag")).thenReturn(null);
        when(predictionsDAO.getAverageSpeed("dirTag", "8")).thenReturn(0.0f);
        when(strategy.execute(any(StrategyInputData.class))).thenReturn(new ArrayList<>(List.of(0.5f, 1.0f, 1.5f)));

        doNothing().when(predictionsImplementation).setStrategy(any(Strategy.class));

    }

    @Test
    void execute_shouldReturnPredictions() {
        PredictionsOutputData predictionsOutputData = predictionsImplementation.execute("routeTag", "dirTag", "stopTag");

        assertEquals(predictionsOutputData.getPredictionTimes(), new ArrayList<>(List.of(0.5f, 1.0f, 1.5f)));
    }

    @AfterEach
    void close() {
        mockedLocalDateTime.close();
    }

}
