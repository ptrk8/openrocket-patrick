package net.sf.openrocket.simulation.listeners.telemetry;

import com.google.common.io.Resources;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import net.sf.openrocket.aerodynamics.AerodynamicCalculator;
import net.sf.openrocket.aerodynamics.LookupCalculator;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMap;
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquations;
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquationsSimpleLookup;
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquationsURTModified;
import org.junit.Test;

public class TelemetryFacadeImplTest {

    File validCoefficientsFileFullAsymmetric = new File(
        Resources
            .getResource(
            "test/coefficients/coefficients-panel-full-radians.json"
        ).toURI()
    );

    AerodynamicForceEquations equationsSimpleLookup = new AerodynamicForceEquationsSimpleLookup();
    AerodynamicForceEquations equationsURTModified = new AerodynamicForceEquationsURTModified();

    public TelemetryFacadeImplTest() throws
        URISyntaxException {
    }

    @Test
    public void exportCoefficientsShouldExportCoefficients() throws
        FileNotFoundException {
//        LookupCalculator lookupCalculator = new LookupCalculator(equationsURTModified);
//        lookupCalculator.setAeroCoefficients(validCoefficientsFileFullAsymmetric);
//
//        TelemetryFacade telemetryFacade = new TelemetryFacadeImpl(lookupCalculator);
//
//        telemetryFacade.setDirectory("test5");
//        telemetryFacade.exportCoefficients();
    }
}