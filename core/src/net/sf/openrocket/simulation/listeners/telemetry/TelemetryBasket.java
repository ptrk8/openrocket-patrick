package net.sf.openrocket.simulation.listeners.telemetry;

/**
 * Interface that represents the "value" portion of our Telemetry map, which is stored in TelemetryList.
 */
public interface TelemetryBasket {

    /**
     * @param forces The aerodynamic forces telemetry.
     */
    void setTelemetryAerodynamicForces(TelemetryAerodynamicForces forces);

    /**
     * @param conditions The flight conditions telemetry.
     */
    void setTelemetryFlightConditions(TelemetryFlightConditions conditions);

    /**
     * @return Returns the aerodynamic forces telemetry.
     */
    TelemetryAerodynamicForces getTelemetryAerodynamicForces();

    /**
     * @return Returns the flight conditions telemetry.
     */
    TelemetryFlightConditions getTelemetryFlightConditions();
}
