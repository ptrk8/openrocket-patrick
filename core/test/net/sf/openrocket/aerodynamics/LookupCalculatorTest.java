package net.sf.openrocket.aerodynamics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import com.google.common.io.Resources;
import com.google.common.primitives.Doubles;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import java.io.File;
import java.net.URISyntaxException;
import net.sf.openrocket.ServicesForTesting;
import net.sf.openrocket.plugin.PluginModule;
import net.sf.openrocket.rocketcomponent.FlightConfiguration;
import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.util.TestRockets;
import org.junit.BeforeClass;
import org.junit.Test;

public class LookupCalculatorTest {

    File validCoefficientsFileSymmetric = new File(
        Resources.getResource(
            "test/coefficients/test-aero-coefficients-radians.json"
        ).toURI()
    );

    File invalidCoefficientsFile = new File(
        Resources.getResource(
            "test/coefficients/test-aero-coefficients-invalid-radians.json"
        ).toURI()
    );

    Rocket falconHeavy = TestRockets.makeFalcon9Heavy();

    // It is not actually possible to set negative angles of attack in OpenRocket
    double[] anglesOfAttackLookup = new double[] {
        0.0,
        0.08
    };

    // It is not actually possible to set negative angles of attack in OpenRocket
    double[] anglesOfAttackBarrowman = new double[] {
        0.09,
        0.10,
        0.12,
        1.1,
        2.1,
        3.0,
        3.13,
        Math.PI
    };

    double[] anglesOfAttackAll = Doubles.concat(anglesOfAttackLookup, anglesOfAttackBarrowman);

    double[] machNumbersLookup = new double[] {
        0.11,
        0.2,
        0.3
    };

    double[] machNumbersBarrowman = new double[] {
        0.02,
        0.03,
        0.09
    };

    double[] machNumbersAll = Doubles.concat(machNumbersBarrowman, machNumbersLookup);

    private static Injector injector;

    @BeforeClass
    public static void setup() {
        Module applicationModule = new ServicesForTesting();
        Module pluginModule = new PluginModule();

        injector = Guice.createInjector( applicationModule, pluginModule);
        Application.setInjector(injector);
    }

    public LookupCalculatorTest() throws
        URISyntaxException {
    }

    @Test
    public void setAeroCoefficientsShouldSetAeroCoefficients() throws
        URISyntaxException {

        LookupCalculator lookupCalculator = new LookupCalculator();

        assertNull(lookupCalculator.getAeroCoefficients());

        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);

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
        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);
        // Facade should be non-null now
        assertNotNull(lookupCalculator.getAeroCoefficientsFacade());

        // Set the aero coefficients to something invalid
        assertThrows(IllegalArgumentException.class, () -> {
            lookupCalculator.setAeroCoefficients(invalidCoefficientsFile);
        });
        // Facade should be null again after setting aero coefficients to something invalid
        assertNull(lookupCalculator.getAeroCoefficientsFacade());

        // Assert that getAerodynamicForces still throws an IllegalStateException
        assertThrows(IllegalStateException.class, () -> {
            lookupCalculator.getAerodynamicForces(null, null, null);
        });
    }

    @Test
    public void getAerodynamicForcesShouldUseLookupTableIfWithinAngleOfAttackRangeAndFastEnough() {
        LookupCalculator lookupCalculator = new LookupCalculator();
        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);

        FlightConfiguration configuration = falconHeavy.getSelectedConfiguration();
        WarningSet warnings = new WarningSet();
        FlightConditions conditions = new FlightConditions(configuration);

        for (double angleOfAttack : anglesOfAttackLookup) {
            for (double machNumber : machNumbersLookup) {
                conditions.setAOA(angleOfAttack);
                conditions.setMach(machNumber);
                AerodynamicForces actualForces = lookupCalculator.getAerodynamicForces(
                    configuration,
                    conditions,
                    warnings
                );
                assertNotEquals(
                    new BarrowmanCalculator().getAerodynamicForces(configuration, conditions, warnings),
                    actualForces
                );
            }
        }
    }

    @Test
    public void getAerodynamicForcesShouldUseBarrowmanIfOutsideAngleOfAttackRange() {
        LookupCalculator lookupCalculator = new LookupCalculator();
        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);

        FlightConfiguration configuration = falconHeavy.getSelectedConfiguration();
        WarningSet warnings = new WarningSet();
        FlightConditions conditions = new FlightConditions(configuration);

        for (double angleOfAttack : anglesOfAttackBarrowman) {
            for (double machNumber : machNumbersAll) {
                conditions.setAOA(angleOfAttack);
                conditions.setMach(machNumber);
                AerodynamicForces actualForces = lookupCalculator.getAerodynamicForces(
                    configuration,
                    conditions,
                    warnings
                );
                assertEquals(
                    new BarrowmanCalculator().getAerodynamicForces(configuration, conditions, warnings),
                    actualForces
                );
            }
        }
    }

    @Test
    public void getAerodynamicForcesShouldUseBarrowmanIfMachNumberTooLow() {
        LookupCalculator lookupCalculator = new LookupCalculator();
        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);

        FlightConfiguration configuration = falconHeavy.getSelectedConfiguration();
        WarningSet warnings = new WarningSet();
        FlightConditions conditions = new FlightConditions(configuration);

        for (double angleOfAttack : anglesOfAttackAll) {
            for (double machNumber : machNumbersBarrowman) {
                conditions.setAOA(angleOfAttack);
                conditions.setMach(machNumber);
                AerodynamicForces actualForces = lookupCalculator.getAerodynamicForces(
                    configuration,
                    conditions,
                    warnings
                );
                assertEquals(
                    new BarrowmanCalculator().getAerodynamicForces(configuration, conditions, warnings),
                    actualForces
                );
            }
        }
    }


}