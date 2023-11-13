package com.example.backend.interface_adapters.predictions;

import com.example.backend.interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PredictionsViewModel extends ViewModel {
    private PredictionsState state = new PredictionsState();
    

    public PredictionsViewModel() {
        super("predictions");
    }
    
    public void setState(PredictionsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PredictionsState getState() {
        return state;
    }
}
