package net.sf.openrocket.aerodynamics.equations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import net.sf.openrocket.aerodynamics.AerodynamicCoefficientsFacade;
import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;
import org.junit.Before;
import org.junit.Test;

public class AerodynamicForceEquationsSimpleTest {

    public final double delta = 0.0000001;

    private FlightConditions conditions;
    private AerodynamicForces forces;
    private AerodynamicCoefficientsFacade aerodynamicCoefficientsFacade;

    @Before
    public void beforeEach() {
        conditions = mock(FlightConditions.class);
        forces = mock(AerodynamicForces.class);
        aerodynamicCoefficientsFacade = mock(AerodynamicCoefficientsFacade.class);
    }

    @Test
    public void getMethodsShouldThrowErrorIfFacadeNotSet() {
        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();

        assertThrows(IllegalStateException.class, () -> {
            equations.getCN(conditions, forces);
        });
        assertThrows(IllegalStateException.class, () -> {
            equations.getCD(conditions, forces);
        });
        assertThrows(IllegalStateException.class, () -> {
            equations.getCside(conditions, forces);
        });
        assertThrows(IllegalStateException.class, () -> {
            equations.getCyaw(conditions, forces);
        });
        assertThrows(IllegalStateException.class, () -> {
            equations.getCm(conditions, forces);
        });
        assertThrows(IllegalStateException.class, () -> {
            equations.getCroll(conditions, forces);
        });
        assertThrows(IllegalStateException.class, () -> {
            equations.getCaxial(conditions, forces);
        });
    }

    @Test
    public void getCNShouldUseLookupTable() {
        double expectedValue = 12.3;

        when(aerodynamicCoefficientsFacade.getCoefficientLift(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCN(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCDShouldUseLookupTable() {
        double expectedValue = 11.3;

        when(aerodynamicCoefficientsFacade.getCoefficientDrag(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCD(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCsideShouldUseLookupTable() {
        double expectedValue = 32.2;

        when(aerodynamicCoefficientsFacade.getCoefficientAxialForce(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCside(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCyawShouldUseLookupTable() {
        double expectedValue = 2.2;

        when(aerodynamicCoefficientsFacade.getCoefficientSideForce(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCyaw(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCmShouldUseLookupTable() {
        double expectedValue = 23.2;

        when(aerodynamicCoefficientsFacade.getCoefficientPitchingMoment(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCm(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCrollShouldUseLookupTable() {
        double expectedValue = 23.2;

        when(aerodynamicCoefficientsFacade.getCoefficientRollingMoment(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCroll(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCaxialShouldUseLookupTable() {
        double expectedValue = 3.2;

        when(aerodynamicCoefficientsFacade.getCoefficientDrag(
            anyDouble(),
            anyDouble()
        )).thenReturn(expectedValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsSimple();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCaxial(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

}