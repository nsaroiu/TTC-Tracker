package com.example.backend.interface_adapters.stop_details;

import com.example.backend.interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StopDetailsViewModel extends ViewModel {
    private StopDetailsState state = new StopDetailsState();
    

    public StopDetailsViewModel() {
        super("predictions");
    }
    
    public void setState(StopDetailsState state) {
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

    public StopDetailsState getState() {
        return state;
    }
}
