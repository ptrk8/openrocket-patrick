package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.List;

/**
 * Interface representing a row component that can be used to write to a file by Writer.
 */
public interface WriterRowComponent {

    /**
     * @return The headers corresponding to the row of data.
     */
    List<String> getHeaders();

    /**
     * @return The values corresponding to the row of data.
     */
    List<String> getValues();
}
