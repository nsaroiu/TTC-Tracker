package use_case;

public interface StopsOutputBoundary {
    void prepareSuccessView(StopsOutputData stopsOutputData);

    void prepareFailView(String error);
}
