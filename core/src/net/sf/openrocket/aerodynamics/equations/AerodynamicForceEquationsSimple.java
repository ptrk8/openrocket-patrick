package net.sf.openrocket.aerodynamics.equations;

import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;

/**
 * Simple equations that just look up the coefficients in our aerodynamic coefficients facade.
 */
public class AerodynamicForceEquationsSimple extends AerodynamicForceEquationsBase {

    @Override
    public double getCN(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientLift(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public double getCD(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientDrag(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public double getCside(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientAxialForce(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public double getCyaw(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientSideForce(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public double getCm(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientPitchingMoment(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public double getCroll(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientRollingMoment(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public double getCaxial(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientDrag(
            conditions.getMach(),
            conditions.getAOA()
        );
    }
}
