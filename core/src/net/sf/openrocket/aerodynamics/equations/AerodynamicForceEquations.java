package net.sf.openrocket.aerodynamics.equations;

import net.sf.openrocket.aerodynamics.AerodynamicCoefficientsFacade;
import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;

/**
 * Interface for aerodynamic force equations
 */
public interface AerodynamicForceEquations {

    /**
     * Sets the facade from which we will retrieve aerodynamic coefficients from.
     * @param aerodynamicCoefficientsFacade The aerodynamic coefficients facade used by this class.
     */
    void setAerodynamicCoefficientsFacade(AerodynamicCoefficientsFacade aerodynamicCoefficientsFacade);

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCN(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCD(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCside(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCyaw(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCm(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCroll(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

    /**
     * Pre-condition: A non-null aerodynamic coefficients facade must be set.
     * @param conditions The flight conditions.
     * @param currentForces The current aerodynamic forces.
     * @return The new aerodynamic force we want to be set.
     * @throws IllegalStateException If the aerodynamic coefficients facade is null.
     */
    double getCaxial(
        FlightConditions conditions,
        AerodynamicForces currentForces
    ) throws
        IllegalStateException;

}
