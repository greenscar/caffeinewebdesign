package security;

import java.beans.*;

/**
 * GuardBean will do all security checks for the system.
 * @author  jsandlin
 */
public class GuardBean extends Object implements java.io.Serializable {
    
    private static final String PROP_SAMPLE_PROPERTY = "SampleProperty";
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    /** Creates new GuardBean */
    public GuardBean() {
        propertySupport = new PropertyChangeSupport( this );
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
}
