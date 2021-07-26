package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.File;
import java.io.FileNotFoundException;
import net.sf.openrocket.aerodynamics.AerodynamicCalculator;
import net.sf.openrocket.aerodynamics.LookupCalculator;

public class TelemetryFacadeImpl implements TelemetryFacade {

    private final TelemetryList telemetryList;

    private final AerodynamicCalculator aerodynamicCalculator;
    private final Writer csvWriter = new WriterCsv();
    private final String downloadsDir = String.format("%s/Downloads", System.getProperty("user.home"));
    private File telemetryFile;

    public TelemetryFacadeImpl(AerodynamicCalculator aerodynamicCalculator) {
        telemetryList = new TelemetryListImpl();
        this.aerodynamicCalculator = aerodynamicCalculator;
    }

    @Override
    public TelemetryList getTelemetryList() {
        return telemetryList;
    }

    @Override
    public String getAerodynamicEquationsName() {
        if (aerodynamicCalculator instanceof LookupCalculator) {
            LookupCalculator lookupCalculator = (LookupCalculator) aerodynamicCalculator;
            return lookupCalculator.getAeroForceEquations().toString();
        }
        return "";
    }

    @Override
    public AerodynamicCalculator getAerodynamicCalculator() {
        return aerodynamicCalculator;
    }

    @Override
    public File getTelemetryFile() {
        return telemetryFile;
    }

    @Override
    public void printTelemetry(String fileName) throws
        FileNotFoundException {
        String fileNameWithExtension = String.format("%s.csv", fileName);
        String filePath = String.format("%s/%s", downloadsDir, fileNameWithExtension);
        telemetryFile = new File(filePath);
        csvWriter.write(
            telemetryFile,
            telemetryList.getList()
        );
    }
}
