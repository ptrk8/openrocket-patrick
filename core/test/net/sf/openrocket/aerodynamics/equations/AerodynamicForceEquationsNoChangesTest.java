package net.sf.openrocket.aerodynamics.equations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import net.sf.openrocket.aerodynamics.AerodynamicCoefficientsFacade;
import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;
import org.junit.Before;
import org.junit.Test;

public class AerodynamicForceEquationsNoChangesTest {

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
    public void getMethodsShouldNotThrowErrorIfFacadeNotSet() {
        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.getCN(conditions, forces);
        equations.getCD(conditions, forces);
        equations.getCside(conditions, forces);
        equations.getCyaw(conditions, forces);
        equations.getCm(conditions, forces);
        equations.getCroll(conditions, forces);
        equations.getCaxial(conditions, forces);
    }

    @Test
    public void getCNShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 12.3;

        // The value should come from here
        when(forces.getCN()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientLift(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCN(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCDShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 11.3;

        // The value should come from here
        when(forces.getCD()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientDrag(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCD(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCsideShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 32.2;

        // The value should come from here
        when(forces.getCside()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientAxialForce(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCside(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCyawShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 2.2;

        // The value should come from here
        when(forces.getCyaw()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientSideForce(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCyaw(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCmShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 23.2;

        // The value should come from here
        when(forces.getCm()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientPitchingMoment(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCm(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCrollShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 23.2;

        // The value should come from here
        when(forces.getCroll()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientRollingMoment(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCroll(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCaxialShouldReturnCurrentForces() {
        double expectedValue = 100.2;
        double lookupValue = 3.2;

        // The value should come from here
        when(forces.getCaxial()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientDrag(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsNoChanges();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCaxial(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }
}