package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.Map;

/**
 * Interface that represents a segment of data in our Telemetry row.
 */
public interface TelemetryDataSegment {

    /**
     * @return A map corresponding to the headers and values of our telemetry data segment.
     */
    Map<String, String> getTelemetryDataMap();
}
