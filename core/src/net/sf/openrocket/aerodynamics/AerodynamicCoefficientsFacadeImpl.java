package net.sf.openrocket.aerodynamics;

import java.math.BigDecimal;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsInterpolator;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsKeyImpl;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMap;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMapFactory;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsMapFactoryImpl;

/**
 * Concrete implementation of our aerodynamic coefficients facade.
 */
public class AerodynamicCoefficientsFacadeImpl implements AerodynamicCoefficientsFacade {

    private final CoefficientsMapFactory coefficientsMapFactory;

    private final CoefficientsMap coefficientsLift;
    private final CoefficientsMap coefficientsPitchingMoment;
    private final CoefficientsMap coefficientsRollingMoment;
    private final CoefficientsMap coefficientsDrag;
    private final CoefficientsMap coefficientsAxialForce;
    private final CoefficientsMap coefficientsSideForce;
    private final CoefficientsMap coefficientsSideForceAlphaDerivative;
    private final CoefficientsMap coefficientsAxialForceBetaDerivative;
    private final CoefficientsMap coefficientsSideForceBetaDerivative;
    private final CoefficientsMap coefficientsPitchingMomentAlphaDerivative;
    private final CoefficientsMap coefficientsRollingMomentBetaDerivative;

    /**
     * Constructor for our concrete implementation of our aerodynamic coefficients facade.
     * @param json The JSON object containing our aerodynamic coefficients.
     * @param interpolator The interpolator, which specify how values are interpolated.
     */
    public AerodynamicCoefficientsFacadeImpl(
        AerodynamicCoefficients json,
        CoefficientsInterpolator interpolator
    ) {
        coefficientsMapFactory = new CoefficientsMapFactoryImpl(
            json.getMachList(),
            json.getAngleOfAttackList(),
            interpolator
        );
        coefficientsLift = coefficientsMapFactory.make(
            json.getCoefficientLiftList()
        );
        coefficientsPitchingMoment = coefficientsMapFactory.make(
            json.getCoefficientPitchingMomentList()
        );
        coefficientsRollingMoment = coefficientsMapFactory.make(
            json.getCoefficientRollingMomentList()
        );
        coefficientsDrag = coefficientsMapFactory.make(
            json.getCoefficientDragList()
        );
        coefficientsAxialForce = coefficientsMapFactory.make(
            json.getCoefficientAxialForceList()
        );
        coefficientsSideForce = coefficientsMapFactory.make(
            json.getCoefficientSideForceList()
        );
        coefficientsSideForceAlphaDerivative = coefficientsMapFactory.make(
            json.getCoefficientSideForceAlphaDerivativeList()
        );
        coefficientsAxialForceBetaDerivative = coefficientsMapFactory.make(
            json.getCoefficientAxialForceBetaDerivativeList()
        );
        coefficientsSideForceBetaDerivative = coefficientsMapFactory.make(
            json.getCoefficientSideForceBetaDerivativeList()
        );
        coefficientsPitchingMomentAlphaDerivative = coefficientsMapFactory.make(
            json.getCoefficientPitchingMomentAlphaDerivativeList()
        );
        coefficientsRollingMomentBetaDerivative = coefficientsMapFactory.make(
            json.getCoefficientRollingMomentBetaDerivativeList()
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

    @Override
    public double getCoefficientSideForceAlphaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsSideForceAlphaDerivative.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientAxialForceBetaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsAxialForceBetaDerivative.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientSideForceBetaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsSideForceBetaDerivative.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientPitchingMomentAlphaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsPitchingMomentAlphaDerivative.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public double getCoefficientRollingMomentBetaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsRollingMomentBetaDerivative.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }
}
