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

@Deprecated
public class AerodynamicForceEquationsURTModifiedTest {

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
        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();

        assertThrows(IllegalStateException.class, () -> {
            equations.getCN(conditions, forces);
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
        // We are not setting CD so it shouldn't depend on the coefficient facade
        equations.getCD(conditions, forces);
    }

    @Test
    public void getCDShouldReturnOriginalForce() {
        double expectedValue = 100.2;
        double lookupValue = 11.3;

        // The value should come from here
        when(forces.getCD()).thenReturn(expectedValue);

        // This lookup table should be ignored
        when(aerodynamicCoefficientsFacade.getCoefficientDrag(
            anyDouble(),
            anyDouble()
        )).thenReturn(lookupValue);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCD(conditions, forces);
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void getCNShouldReturnModifiedForce() {
        double originalValue = 100.2;
        double coefficientLift = 11.3;
        double coefficientSideForceAlphaDerivative = 1.2;
        double alpha = 2.0;

        // This original value should be ignored and overridden by the below
        when(forces.getCN()).thenReturn(originalValue);

        // The value should be computed from the lookup table
        when(aerodynamicCoefficientsFacade.getCoefficientLift(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientLift);
        when(aerodynamicCoefficientsFacade.getCoefficientSideForceAlphaDerivative(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientSideForceAlphaDerivative);
        when(conditions.getAOA()).thenReturn(alpha);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCN(conditions, forces);
        assertEquals(
            coefficientLift + (coefficientSideForceAlphaDerivative * alpha),
            actualValue,
            delta
        );
    }

    @Test
    public void getCsideShouldReturnModifiedForce() {
        double originalValue = 100.2;
        double coefficientAxialForce = 11.3;
        double coefficientAxialForceBetaDerivative = 1.2;
        double beta = 2.01;

        // This original value should be ignored and overridden by the below
        when(forces.getCside()).thenReturn(originalValue);

        // The value should be computed from the lookup table
        when(aerodynamicCoefficientsFacade.getCoefficientAxialForce(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientAxialForce);
        when(aerodynamicCoefficientsFacade.getCoefficientAxialForceBetaDerivative(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientAxialForceBetaDerivative);
        when(conditions.getBeta()).thenReturn(beta);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCside(conditions, forces);
        assertEquals(
            coefficientAxialForce + (coefficientAxialForceBetaDerivative * beta),
            actualValue,
            delta
        );
    }

    @Test
    public void getCyawShouldReturnModifiedForce() {
        double originalValue = 100.2;
        double coefficientSideForce = 11.3;
        double coefficientSideForceBetaDerivative = 1.2;
        double beta = 3.01;

        // This original value should be ignored and overridden by the below
        when(forces.getCyaw()).thenReturn(originalValue);

        // The value should be computed from the lookup table
        when(aerodynamicCoefficientsFacade.getCoefficientSideForce(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientSideForce);
        when(aerodynamicCoefficientsFacade.getCoefficientSideForceBetaDerivative(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientSideForceBetaDerivative);
        when(conditions.getBeta()).thenReturn(beta);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCyaw(conditions, forces);
        assertEquals(
            coefficientSideForce + (coefficientSideForceBetaDerivative * beta),
            actualValue,
            delta
        );
    }

    @Test
    public void getCmShouldReturnModifiedForce() {
        double originalValue = 100.2;
        double coefficientPitchingMoment = 11.3;
        double coefficientPitchingMomentAlphaDerivative = 1.2;
        double alpha = 3.01;

        // This original value should be ignored and overridden by the below
        when(forces.getCm()).thenReturn(originalValue);

        // The value should be computed from the lookup table
        when(aerodynamicCoefficientsFacade.getCoefficientPitchingMoment(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientPitchingMoment);
        when(aerodynamicCoefficientsFacade.getCoefficientPitchingMomentAlphaDerivative(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientPitchingMomentAlphaDerivative);
        when(conditions.getAOA()).thenReturn(alpha);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCm(conditions, forces);
        assertEquals(
            coefficientPitchingMoment + (coefficientPitchingMomentAlphaDerivative * alpha),
            actualValue,
            delta
        );
    }

    @Test
    public void getCrollShouldReturnModifiedForce() {
        double originalValue = 100.2;
        double coefficientRollingMoment = 11.3;
        double coefficientRollingMomentBetaDerivative = 1.2;
        double beta = 3.01;

        // This original value should be ignored and overridden by the below
        when(forces.getCroll()).thenReturn(originalValue);

        // The value should be computed from the lookup table
        when(aerodynamicCoefficientsFacade.getCoefficientRollingMoment(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientRollingMoment);
        when(aerodynamicCoefficientsFacade.getCoefficientRollingMomentBetaDerivative(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientRollingMomentBetaDerivative);
        when(conditions.getBeta()).thenReturn(beta);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCroll(conditions, forces);
        assertEquals(
            coefficientRollingMoment + (coefficientRollingMomentBetaDerivative * beta),
            actualValue,
            delta
        );
    }

    @Test
    public void getCaxialShouldReturnModifiedForce() {
        double originalValue = 100.2;
        double coefficientDrag = 11.3;

        // This original value should be ignored and overridden by the below
        when(forces.getCaxial()).thenReturn(originalValue);

        // The value should be computed from the lookup table
        when(aerodynamicCoefficientsFacade.getCoefficientDrag(
            anyDouble(),
            anyDouble()
        )).thenReturn(coefficientDrag);

        AerodynamicForceEquations equations = new AerodynamicForceEquationsURTModified();
        equations.setAerodynamicCoefficientsFacade(aerodynamicCoefficientsFacade);

        double actualValue = equations.getCaxial(conditions, forces);
        assertEquals(
            coefficientDrag,
            actualValue,
            delta
        );
    }
}