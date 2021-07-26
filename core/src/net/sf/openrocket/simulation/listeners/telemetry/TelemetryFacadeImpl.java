package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import net.sf.openrocket.aerodynamics.AerodynamicCalculator;
import net.sf.openrocket.aerodynamics.LookupCalculator;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMap;
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquations;

public class TelemetryFacadeImpl implements TelemetryFacade {

    private final TelemetryList telemetryList;
    private final AerodynamicCalculator aerodynamicCalculator;

    private final Writer csvWriter = new WriterCsv();
    private File directory;

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
    public void setDirectory(String directoryName) {
        String downloadsDir = System.getProperty("user.home") + File.separatorChar + "Downloads";
        directory = new File(downloadsDir + File.separatorChar + directoryName);
        if (directory.exists()) {
            throw new IllegalArgumentException(
                String.format("Error: %s already exists.", directory.getAbsolutePath())
            );
        }
        directory.mkdirs();
    }

    @Override
    public void exportTelemetry() throws
        FileNotFoundException {
        if (directory == null) {
            throw new IllegalStateException("Directory has not been set");
        }
        File telemetryFile = new File(directory.getAbsolutePath() + File.separatorChar + String.format("telemetry_%d.csv", new Date().getTime()));
        csvWriter.write(
            telemetryFile,
            telemetryList.getList()
        );
        System.out.printf(
            "Finished exporting Telemetry to %s%n",
            telemetryFile.getAbsolutePath()
        );
    }

    @Override
    public void exportCoefficients() throws
        FileNotFoundException {
        if (directory == null) {
            throw new IllegalStateException("Directory has not been set");
        }
        // If the aerodynamic calculator is not a lookup calculator, don't print anything
        if (!(aerodynamicCalculator instanceof LookupCalculator)) {
            return;
        }
        LookupCalculator lookupCalculator = (LookupCalculator) aerodynamicCalculator;
        AerodynamicForceEquations equations = lookupCalculator.getAeroForceEquations();

        for (Map.Entry<String, CoefficientsMap> entry : equations
            .getAerodynamicCoefficientsFacade()
            .getCoefficients()
            .entrySet()) {
            String fileNameWithExtension = String.format("%s.csv", entry.getKey());
            File coefficientsFile = new File(directory.getAbsolutePath() + File.separatorChar + fileNameWithExtension);
            csvWriter.write(
                coefficientsFile,
                entry.getValue().getList()
            );
        }
        System.out.printf(
            "Finished printing Coefficients to %s%n",
            directory.getAbsolutePath()
        );
    }
}
