package use_case;

public interface PredictionsOutputBoundary {
    void prepareSuccessView(PredictionsOutputData predictionsOutputData);

    void prepareFailView(String error);
}
