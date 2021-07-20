package net.sf.openrocket.aerodynamics;

public interface AerodynamicCoefficientsFacade {

    double getCoefficientLift(
        double machNumber,
        double angleOfAttack
    );

    double getCoefficientPitchingMoment(
        double machNumber,
        double angleOfAttack
    );

    double getCoefficientRollingMoment(
        double machNumber,
        double angleOfAttack
    );

    double getCoefficientDrag(
        double machNumber,
        double angleOfAttack
    );

    double getCoefficientAxialForce(
        double machNumber,
        double angleOfAttack
    );

    double getCoefficientSideForce(
        double machNumber,
        double angleOfAttack
    );
}
