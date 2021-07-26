package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.FileNotFoundException;
import net.sf.openrocket.aerodynamics.AerodynamicCalculator;

/**
 * Interface that represents our telemetry facade.
 */
public interface TelemetryFacade {

    /**
     * @return The telemetry that is being recorded.
     */
    TelemetryList getTelemetryList();

    /**
     * @return The name of our aerodynamic force equations if the aerodynamic calculator is LookupCalculator. Otherwise, returns and empty string.
     */
    String getAerodynamicEquationsName();

    /**
     * @return The aerodynamic calculator used in simulation.
     */
    AerodynamicCalculator getAerodynamicCalculator();

    /**
     * @return The file path of the telemetry CSV file.
     */
    String getFilePath();

    /**
     * @param fileName The name of the file.
     * @throws FileNotFoundException If the file cannot be created.
     */
    void printToCsvFile(String fileName) throws
        FileNotFoundException;
}
