package net.sf.openrocket.aerodynamics.equations;

import net.sf.openrocket.aerodynamics.AerodynamicCoefficientsFacade;
import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;

public abstract class AerodynamicForceEquationsBase implements AerodynamicForceEquations {
    AerodynamicCoefficientsFacade aeroCoefficientsFacade;

    @Override
    public void setAerodynamicCoefficientsFacade(AerodynamicCoefficientsFacade aeroCoefficientsFacade) {
        this.aeroCoefficientsFacade = aeroCoefficientsFacade;
    }

    void checkAerodynamicCoefficientsFacadeIsNotNull() {
        if (aeroCoefficientsFacade == null) {
            throw new IllegalStateException("AerodynamicCoefficientsFacade must not be null.");
        }
    }

    @Override
    public double getCN(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCN();
    }

    @Override
    public double getCD(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCD();
    }

    @Override
    public double getCside(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCside();
    }

    @Override
    public double getCyaw(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCyaw();
    }

    @Override
    public double getCm(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCm();
    }

    @Override
    public double getCroll(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCroll();
    }

    @Override
    public double getCaxial(FlightConditions conditions, AerodynamicForces currentForces) {
        return currentForces.getCaxial();
    }
}
