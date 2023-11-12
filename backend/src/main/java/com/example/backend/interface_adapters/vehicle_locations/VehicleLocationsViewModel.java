package com.example.backend.interface_adapters.vehicle_locations;

import com.example.backend.interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VehicleLocationsViewModel extends ViewModel {
    private VehicleLocationsState state = new VehicleLocationsState();
    

    public VehicleLocationsViewModel() {
        super("predictions");
    }
    
    public void setState(VehicleLocationsState state) {
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

    public VehicleLocationsState getState() {
        return state;
    }
}
