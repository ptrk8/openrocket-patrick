package net.sf.openrocket.aerodynamics;

import java.util.Map;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMap;

/**
 * Represents the interface for our Coefficients facade.
 */
public interface AerodynamicCoefficientsFacade {

    /**
     * Retrieves the exact or interpolated coefficient in our lookup table.
     * @param machNumber A mach number in the range of our lookup table.
     * @param angleOfAttack An angle of attack in the range of our lookup table.
     * @return The requested coefficient derived from our lookup table.
     */
    double getCoefficientNormalForce(
        double machNumber,
        double angleOfAttack
    );

    /**
     * Retrieves the exact or interpolated coefficient in our lookup table.
     * @param machNumber A mach number in the range of our lookup table.
     * @param angleOfAttack An angle of attack in the range of our lookup table.
     * @return The requested coefficient derived from our lookup table.
     */
    double getCoefficientPitchingMoment(
        double machNumber,
        double angleOfAttack
    );

    /**
     * Retrieves the exact or interpolated coefficient in our lookup table.
     * @param machNumber A mach number in the range of our lookup table.
     * @param angleOfAttack An angle of attack in the range of our lookup table.
     * @return The requested coefficient derived from our lookup table.
     */
    double getCoefficientRollingMoment(
        double machNumber,
        double angleOfAttack
    );

    /**
     * Retrieves the exact or interpolated coefficient in our lookup table.
     * @param machNumber A mach number in the range of our lookup table.
     * @param angleOfAttack An angle of attack in the range of our lookup table.
     * @return The requested coefficient derived from our lookup table.
     */
    double getCoefficientAxialForce(
        double machNumber,
        double angleOfAttack
    );

    /**
     * Retrieves the exact or interpolated coefficient in our lookup table.
     * @param machNumber A mach number in the range of our lookup table.
     * @param angleOfAttack An angle of attack in the range of our lookup table.
     * @return The requested coefficient derived from our lookup table.
     */
    double getCoefficientSideForce(
        double machNumber,
        double angleOfAttack
    );

    /**
     * Retrieves the exact or interpolated coefficient in our lookup table.
     * @param machNumber A mach number in the range of our lookup table.
     * @param angleOfAttack An angle of attack in the range of our lookup table.
     * @return The requested coefficient derived from our lookup table.
     */
    double getCoefficientYawMoment(
        double machNumber,
        double angleOfAttack
    );

    /**
     * Simple accessor to retrieve all of the coefficients stored in memory.
     * @return Map of the coefficient's name and the corresponding coefficients map.
     */
    Map<String, CoefficientsMap> getCoefficients();

    @Deprecated
    double getCoefficientLift(
        double machNumber,
        double angleOfAttack
    );

    @Deprecated
    double getCoefficientDrag(
        double machNumber,
        double angleOfAttack
    );

    @Deprecated
    double getCoefficientSideForceAlphaDerivative(
        double machNumber,
        double angleOfAttack
    );

    @Deprecated
    double getCoefficientAxialForceBetaDerivative(
        double machNumber,
        double angleOfAttack
    );

    @Deprecated
    double getCoefficientSideForceBetaDerivative(
        double machNumber,
        double angleOfAttack
    );

    @Deprecated
    double getCoefficientPitchingMomentAlphaDerivative(
        double machNumber,
        double angleOfAttack
    );

    @Deprecated
    double getCoefficientRollingMomentBetaDerivative(
        double machNumber,
        double angleOfAttack
    );

}
