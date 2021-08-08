package net.sf.openrocket.aerodynamics;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
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

    private final CoefficientsMap coefficientsNormalForce;
    private final CoefficientsMap coefficientsPitchingMoment;
    private final CoefficientsMap coefficientsRollingMoment;
    private final CoefficientsMap coefficientsAxialForce;
    private final CoefficientsMap coefficientsSideForce;
    private final CoefficientsMap coefficientsYawMoment;

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
        coefficientsNormalForce = coefficientsMapFactory.make(
            json.getCoefficientNormalForceList()
        );
        coefficientsPitchingMoment = coefficientsMapFactory.make(
            json.getCoefficientPitchingMomentList()
        );
        coefficientsRollingMoment = coefficientsMapFactory.make(
            json.getCoefficientRollingMomentList()
        );
        coefficientsAxialForce = coefficientsMapFactory.make(
            json.getCoefficientAxialForceList()
        );
        coefficientsSideForce = coefficientsMapFactory.make(
            json.getCoefficientSideForceList()
        );
        coefficientsYawMoment = coefficientsMapFactory.make(
            json.getCoefficientYawMomentList()
        );
    }

    @Override
    public double getCoefficientNormalForce(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsNormalForce.getCoefficientInterpolated(
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
    public double getCoefficientYawMoment(
        double machNumber,
        double angleOfAttack
    ) {
        return coefficientsYawMoment.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal(machNumber),
                new BigDecimal(angleOfAttack)
            )
        ).doubleValue();
    }

    @Override
    public Map<String, CoefficientsMap> getCoefficients() {
        Map<String, CoefficientsMap> coefficients = new LinkedHashMap<>();
        coefficients.put("coefficients_normal_force", coefficientsNormalForce);
        coefficients.put("coefficients_pitching_moment", coefficientsPitchingMoment);
        coefficients.put("coefficients_rolling_moment", coefficientsRollingMoment);
        coefficients.put("coefficients_axial_force", coefficientsAxialForce);
        coefficients.put("coefficients_side_force", coefficientsSideForce);
        coefficients.put("coefficients_yaw_moment", coefficientsYawMoment);
        return coefficients;
    }

    @Deprecated
    @Override
    public double getCoefficientLift(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

    @Deprecated
    @Override
    public double getCoefficientDrag(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

    @Deprecated
    @Override
    public double getCoefficientSideForceAlphaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

    @Deprecated
    @Override
    public double getCoefficientAxialForceBetaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

    @Deprecated
    @Override
    public double getCoefficientSideForceBetaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

    @Deprecated
    @Override
    public double getCoefficientPitchingMomentAlphaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

    @Deprecated
    @Override
    public double getCoefficientRollingMomentBetaDerivative(
        double machNumber,
        double angleOfAttack
    ) {
        return 0;
    }

}
