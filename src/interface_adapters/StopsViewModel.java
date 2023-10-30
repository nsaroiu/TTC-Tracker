package interface_adapters;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StopsViewModel extends ViewModel{
    private StopsState state = new StopsState();

    public final String TITLE_LABEL = "Stops View";

    public StopsViewModel(){super("stops");}
    public void setState(StopsState state) {
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

    public StopsState getState() {
        return state;
    }
}
