package use_case.stop_details;

public interface StopDetailsOutputBoundary {
    void prepareSuccessView(StopDetailsOutputData data);

    void prepareFailView(String error);
}
