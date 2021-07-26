package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Concrete implementation of a CSV writer.
 */
public class WriterCsv implements Writer {

    @Override
    public <T extends WriterRowComponent> void write(File file, List<T> rows) throws
        FileNotFoundException {

        PrintWriter printWriter = new PrintWriter(file);
        int i = 0;
        for (WriterRowComponent row : rows) {
            if (i == 0) {
                printWriter.println(String.join(",", row.getHeaders()));
            }
            printWriter.println(String.join(",", row.getValues()));
            i++;
        }
        printWriter.close();
    }
}
