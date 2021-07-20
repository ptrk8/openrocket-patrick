package net.sf.openrocket.aerodynamics;

import java.math.BigDecimal;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsInterpolator;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsKeyImpl;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMap;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMapImpl;

public class AerodynamicCoefficientsFacadeImpl implements AerodynamicCoefficientsFacade {

    private final CoefficientsMap coefficientsLift;
    private final CoefficientsMap coefficientsPitchingMoment;
    private final CoefficientsMap coefficientsRollingMoment;
    private final CoefficientsMap coefficientsDrag;
    private final CoefficientsMap coefficientsAxialForce;
    private final CoefficientsMap coefficientsSideForce;

    public AerodynamicCoefficientsFacadeImpl(
        AerodynamicCoefficients json,
        CoefficientsInterpolator interpolator
    ) {
        coefficientsLift = new CoefficientsMapImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            json.getCoefficientLiftList(),
            interpolator
        );
        coefficientsPitchingMoment = new CoefficientsMapImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            json.getCoefficientPitchingMomentList(),
            interpolator
        );
        coefficientsRollingMoment = new CoefficientsMapImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            json.getCoefficientRollingMomentList(),
            interpolator
        );
        coefficientsDrag = new CoefficientsMapImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            json.getCoefficientDragList(),
            interpolator
        );
        coefficientsAxialForce = new CoefficientsMapImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            json.getCoefficientAxialForceList(),
            interpolator
        );
        coefficientsSideForce = new CoefficientsMapImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            json.getCoefficientSideForceList(),
            interpolator
        );
    }

    @Override
    public double getCoefficientLift(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsLift.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientPitchingMoment(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsPitchingMoment.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientRollingMoment(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsRollingMoment.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientDrag(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsDrag.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientAxialForce(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsAxialForce.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientSideForce(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsSideForce.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }
}
