package net.sf.openrocket.simulation.listeners.telemetry;

import net.sf.openrocket.util.Coordinate;

/**
 * Interface representing the flight status telemetry.
 */
public interface TelemetryStatus extends TelemetryDataSegment, WriterRowComponent {

    /**
     * @return The flight time.
     */
    double getTime();

    /**
     * @return The position of the rocket.
     */
    Coordinate getPosition();

    /**
     * @return The velocity of the rocket.
     */
    Coordinate getVelocity();

}
