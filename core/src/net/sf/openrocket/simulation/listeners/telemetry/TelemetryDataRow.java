package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.List;

/**
 * Interface that represents a row of telemetry data.
 */
public interface TelemetryDataRow {

    /**
     * @return The status portion of the data.
     */
    TelemetryDataSegment getStatus();

    /**
     * @return The forces portion of the data.
     */
    TelemetryDataSegment getForces();

    /**
     * @return The conditions portion of the data.
     */
    TelemetryDataSegment getConditions();

    /**
     * @return The headers corresponding to the row of data.
     */
    List<String> getHeaders();

    /**
     * @return The values corresponding to the row of data.
     */
    List<String> getValues();
}
