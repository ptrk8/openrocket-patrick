package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.List;
import java.util.Map;

/**
 * Interface representing our telemetry.
 */
public interface TelemetryList {

    /**
     * @return The telemetry map.
     */
    Map<TelemetryStatus, TelemetryBasket> getTelemetryBasketMap();

    /**
     * @param status The flight status telemetry.
     * @param forces The aerodynamic forces telemetry.
     */
    void addTelemetryAerodynamicForces(TelemetryStatus status, TelemetryAerodynamicForces forces);

    /**
     * @param status The flight status telemetry.
     * @param conditions The flight conditions telemetry.
     */
    void addTelemetryFlightConditions(TelemetryStatus status, TelemetryFlightConditions conditions);

    List<TelemetryListRow> getList();
}
