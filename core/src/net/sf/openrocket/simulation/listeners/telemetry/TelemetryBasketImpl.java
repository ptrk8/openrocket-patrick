package net.sf.openrocket.simulation.listeners.telemetry;

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
}
