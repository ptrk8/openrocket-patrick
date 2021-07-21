package net.sf.openrocket.aerodynamics;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.google.common.io.Resources;
import java.io.File;
import java.net.URISyntaxException;
import org.junit.Test;

public class LookupCalculatorTest {

    File validCoefficientsFile = new File(
        Resources.getResource(
            "test/coefficients/test-aero-coefficients.json"
        ).toURI()
    );

    File invalidCoefficientsFile = new File(
        Resources.getResource(
            "test/coefficients/test-aero-coefficients-invalid.json"
        ).toURI()
    );


    public LookupCalculatorTest() throws
        URISyntaxException {
    }

    @Test
    public void getAerodynamicForcesShouldThrowErrorIfNoAeroCoefficientsFacade() {


    }

    @Test
    public void setAeroCoefficientsShouldSetAeroCoefficients() throws
        URISyntaxException {

        LookupCalculator lookupCalculator = new LookupCalculator();

        assertNull(lookupCalculator.getAeroCoefficients());

        lookupCalculator.setAeroCoefficients(validCoefficientsFile);

        assertNotNull(lookupCalculator.getAeroCoefficients());
    }

    @Test
    public void setAeroCoefficientsShouldThrowErrorIfInvalidFile() throws
        URISyntaxException {
        LookupCalculator lookupCalculator = new LookupCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            lookupCalculator.setAeroCoefficients(invalidCoefficientsFile);
        });
    }

    @Test
    public void getAerodynamicForcesShouldThrowErrorIfAeroCoefficientsHaveNotBeenSet() {
        LookupCalculator lookupCalculator = new LookupCalculator();
        assertThrows(IllegalStateException.class, () -> {
            lookupCalculator.getAerodynamicForces(null, null, null);
        });
    }

    @Test
    public void getAerodynamicForcesShouldThrowErrorIfAeroCoefficientsHaveBeenSetToSomethingInvalid() {
        LookupCalculator lookupCalculator = new LookupCalculator();

        // Set coefficients to something valid
        lookupCalculator.setAeroCoefficients(validCoefficientsFile);

        // Set the aero coefficients to something invalid
        assertThrows(IllegalArgumentException.class, () -> {
            lookupCalculator.setAeroCoefficients(invalidCoefficientsFile);
        });

        // Assert that getAerodynamicForces still throws an IllegalStateException
        assertThrows(IllegalStateException.class, () -> {
            lookupCalculator.getAerodynamicForces(null, null, null);
        });
    }



}