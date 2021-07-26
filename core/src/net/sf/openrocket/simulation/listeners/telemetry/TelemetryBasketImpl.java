package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.ArrayList;
import java.util.List;

public class TelemetryBasketImpl implements TelemetryBasket {

    private TelemetryAerodynamicForces forces;
    private TelemetryFlightConditions conditions;

    public TelemetryBasketImpl(TelemetryAerodynamicForces forces) {
        this.forces = forces;
    }

    public TelemetryBasketImpl(TelemetryFlightConditions conditions) {
        this.conditions = conditions;
    }

    @Override
    public void setTelemetryAerodynamicForces(TelemetryAerodynamicForces forces) {
        this.forces = forces;
    }

    @Override
    public void setTelemetryFlightConditions(TelemetryFlightConditions conditions) {
        this.conditions = conditions;
    }

    @Override
    public TelemetryAerodynamicForces getTelemetryAerodynamicForces() {
        return forces;
    }

    @Override
    public TelemetryFlightConditions getTelemetryFlightConditions() {
        return conditions;
    }

    @Override
    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>(conditions.getHeaders());
        headers.add("");
        headers.addAll(forces.getHeaders());
        return headers;
    }

    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>(conditions.getValues());
        values.add("");
        values.addAll(forces.getValues());
        return values;
    }
}
