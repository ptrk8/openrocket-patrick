package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface representing a writer that writes a list of data to files.
 */
public interface Writer {

    /**
     * @param file The file to write to.
     * @param rows The rows of data to be written.
     * @param <T> The type of row.
     * @throws FileNotFoundException If the file was not found.
     */
    <T extends WriterRowComponent> void write(File file, List<T> rows) throws FileNotFoundException;
}
