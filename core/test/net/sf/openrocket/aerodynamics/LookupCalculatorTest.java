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
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquations;
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquationsSimpleLookup;
import net.sf.openrocket.aerodynamics.equations.AerodynamicForceEquationsURTModified;
import net.sf.openrocket.plugin.PluginModule;
import net.sf.openrocket.rocketcomponent.FlightConfiguration;
import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.util.TestRockets;
import org.junit.BeforeClass;
import org.junit.Test;

public class LookupCalculatorTest {

    public final double delta = 0.0000001;

    File validCoefficientsFileSymmetric = new File(
        Resources.getResource(
            "test/coefficients/coefficients-radians.json"
        ).toURI()
    );

    File validCoefficientsFileFullAsymmetric = new File(
        Resources.getResource(
            "test/coefficients/coefficients-panel-full-radians.json"
        ).toURI()
    );

    File invalidCoefficientsFile = new File(
        Resources.getResource(
            "test/coefficients/coefficients-invalid-radians.json"
        ).toURI()
    );

    Rocket falconHeavy = TestRockets.makeFalcon9Heavy();

    AerodynamicForceEquations equationsSimpleLookup = new AerodynamicForceEquationsSimpleLookup();
    AerodynamicForceEquations equationsURTModified = new AerodynamicForceEquationsURTModified();

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

        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);

        assertNull(lookupCalculator.getAeroCoefficients());

        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);

        assertNotNull(lookupCalculator.getAeroCoefficients());
    }

    @Test
    public void setAeroCoefficientsShouldThrowErrorIfInvalidFile() throws
        URISyntaxException {
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);
        assertThrows(IllegalArgumentException.class, () -> {
            lookupCalculator.setAeroCoefficients(invalidCoefficientsFile);
        });
    }

    @Test
    public void getAerodynamicForcesShouldThrowErrorIfAeroCoefficientsHaveNotBeenSet() {
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);
        assertThrows(IllegalStateException.class, () -> {
            lookupCalculator.getAerodynamicForces(null, null, null);
        });
    }

    @Test
    public void getAerodynamicForcesShouldThrowErrorIfAeroCoefficientsHaveBeenSetToSomethingInvalid() {
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);

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
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);
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
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);
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
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);
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

    @Test
    public void getAerodynamicForcesShouldReturnCorrectLookupValuesSimple() {
        LookupCalculator lookupCalculator = new LookupCalculator(equationsSimpleLookup);
        lookupCalculator.setAeroCoefficients(validCoefficientsFileSymmetric);

        FlightConfiguration configuration = falconHeavy.getSelectedConfiguration();
        WarningSet warnings = new WarningSet();
        FlightConditions conditions = new FlightConditions(configuration);

        conditions.setAOA(0.03);
        conditions.setMach(0.28);
        AerodynamicForces actualForces = lookupCalculator.getAerodynamicForces(
            configuration,
            conditions,
            warnings
        );
        assertNotEquals(
            new BarrowmanCalculator().getAerodynamicForces(configuration, conditions, warnings),
            actualForces
        );

        assertEquals(-0.30086657517515, actualForces.getCN(), delta);
        assertEquals(-0.3671171661558981, actualForces.getCD(), delta);
        assertEquals(0.0, actualForces.getCside(), delta);
        assertEquals(0.0, actualForces.getCyaw(), delta);
        assertEquals(0.0, actualForces.getCm(), delta);
        assertEquals(-0.00006873511531597171, actualForces.getCroll(), delta);
        assertEquals(-0.3671171661558981, actualForces.getCaxial(), delta);
    }

    @Test
    public void getAerodynamicForcesShouldReturnCorrectCalculatedValuesURTModified() {
        LookupCalculator lookupCalculator = new LookupCalculator(equationsURTModified);
        lookupCalculator.setAeroCoefficients(validCoefficientsFileFullAsymmetric);

        FlightConfiguration configuration = falconHeavy.getSelectedConfiguration();
        WarningSet warnings = new WarningSet();
        FlightConditions conditions = new FlightConditions(configuration);

        conditions.setAOA(0.03);
        conditions.setMach(0.28);
        AerodynamicForces actualForces = lookupCalculator.getAerodynamicForces(
            configuration,
            conditions,
            warnings
        );
        assertNotEquals(
            new BarrowmanCalculator().getAerodynamicForces(configuration, conditions, warnings),
            actualForces
        );

        assertEquals(0.34379771561893385, actualForces.getCN(), delta);
        assertEquals(0.0, actualForces.getCD(), delta); // Note we are not overriding CD in USYD URT
        assertEquals(-0.18808719307800298, actualForces.getCside(), delta);
        assertEquals(0.7452465802759779, actualForces.getCyaw(), delta);
        assertEquals(0.0, actualForces.getCm(), delta);
        assertEquals(0.0020672724351863936, actualForces.getCroll(), delta);
        assertEquals(0.3658121993886502, actualForces.getCaxial(), delta);
    }

}