package net.sf.openrocket.aerodynamics.equations;


import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;

/**
 * Represents USYD Rocketry Team custom aerodynamic force equations.
 */
@Deprecated
public class AerodynamicForceEquationsURTModified extends AerodynamicForceEquationsBase {
    @Override
    public double getCN(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();

        double coefficientLift = aeroCoefficientsFacade.getCoefficientLift(
            conditions.getMach(),
            conditions.getAOA()
        );
        double coefficientSideForceAlphaDerivative = aeroCoefficientsFacade.getCoefficientSideForceAlphaDerivative(
            conditions.getMach(),
            conditions.getAOA()
        );
        double alpha = conditions.getAOA();

        return coefficientLift + (coefficientSideForceAlphaDerivative * alpha);
    }

    @Override
    public double getCside(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();

        double coefficientAxialForce = aeroCoefficientsFacade.getCoefficientAxialForce(
            conditions.getMach(),
            conditions.getAOA()
        );
        double coefficientAxialForceBetaDerivative = aeroCoefficientsFacade.getCoefficientAxialForceBetaDerivative(
            conditions.getMach(),
            conditions.getAOA()
        );
        double beta = conditions.getBeta();

        return coefficientAxialForce + (coefficientAxialForceBetaDerivative * beta);
    }

    @Override
    public double getCyaw(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();

        double coefficientSideForce = aeroCoefficientsFacade.getCoefficientSideForce(
            conditions.getMach(),
            conditions.getAOA()
        );
        double coefficientSideForceBetaDerivative = aeroCoefficientsFacade.getCoefficientSideForceBetaDerivative(
            conditions.getMach(),
            conditions.getAOA()
        );
        double beta = conditions.getBeta();

        return coefficientSideForce + (coefficientSideForceBetaDerivative * beta);
    }

    @Override
    public double getCm(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();

        double coefficientPitchingMoment = aeroCoefficientsFacade.getCoefficientPitchingMoment(
            conditions.getMach(),
            conditions.getAOA()
        );
        double coefficientPitchingMomentAlphaDerivative = aeroCoefficientsFacade.getCoefficientPitchingMomentAlphaDerivative(
            conditions.getMach(),
            conditions.getAOA()
        );
        double alpha = conditions.getAOA();

        return coefficientPitchingMoment + (coefficientPitchingMomentAlphaDerivative * alpha);
    }

    @Override
    public double getCroll(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();

        double coefficientRollingMoment = aeroCoefficientsFacade.getCoefficientRollingMoment(
            conditions.getMach(),
            conditions.getAOA()
        );
        double coefficientRollingMomentBetaDerivative = aeroCoefficientsFacade.getCoefficientRollingMomentBetaDerivative(
            conditions.getMach(),
            conditions.getAOA()
        );
        double beta = conditions.getBeta();

        return coefficientRollingMoment + (coefficientRollingMomentBetaDerivative * beta);
    }

    @Override
    public double getCaxial(FlightConditions conditions, AerodynamicForces currentForces) {
        checkAerodynamicCoefficientsFacadeIsNotNull();
        return aeroCoefficientsFacade.getCoefficientDrag(
            conditions.getMach(),
            conditions.getAOA()
        );
    }

    @Override
    public String toString() {
        return "URT Modified";
    }
}
