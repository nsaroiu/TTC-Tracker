package use_case.predictions;

public interface PredictionsOutputBoundary {
    void prepareSuccessView(PredictionsOutputData data);

    void prepareFailView(String error);
}
