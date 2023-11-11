package interface_adapters.route_details;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RouteDetailsViewModel extends ViewModel {
    private RouteDetailsState state = new RouteDetailsState();
    

    public RouteDetailsViewModel() {
        super("predictions");
    }
    
    public void setState(RouteDetailsState state) {
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

    public RouteDetailsState getState() {
        return state;
    }
}
