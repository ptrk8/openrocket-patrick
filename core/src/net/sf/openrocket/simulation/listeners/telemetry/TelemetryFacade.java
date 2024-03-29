package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.File;
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
     * @param directoryName The name of the directory where telemetry data is to be saved
     */
    void setDirectory(String directoryName);

    /**
     * Precondition: A valid directory must be set
     * @throws FileNotFoundException If the file cannot be created.
     */
    void exportTelemetry() throws
        FileNotFoundException;

    /**
     * Precondition: A valid directory must be set.
     * @throws FileNotFoundException If the file cannot be created.
     */
    void exportCoefficients() throws
        FileNotFoundException;
}
