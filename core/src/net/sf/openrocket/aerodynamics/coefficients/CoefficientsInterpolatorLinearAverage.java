package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import net.sf.openrocket.aerodynamics.interpolator.Interpolator;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorArgsLinear;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorLinear;

public class CoefficientsInterpolatorLinearAverage implements CoefficientsInterpolator {

    Interpolator interpolatorLinear = new InterpolatorLinear();
    CoefficientsKeyFinder lowerNeighbourFinder = new CoefficientsKeyFinderLowerNeighbour();
    CoefficientsKeyFinder upperNeighbourFinder = new CoefficientsKeyFinderUpperNeighbour();

    @Override
    public BigDecimal interpolate(
        CoefficientsKey key,
        Map<CoefficientsKey, BigDecimal> coefficients
    ) {
        // If the coefficient already exists just return the coefficient
        BigDecimal existingCoefficient = coefficients.get(key);
        if (existingCoefficient != null) {
            return existingCoefficient;
        }

        CoefficientsKey lowerKey = lowerNeighbourFinder.find(
            coefficients.keySet(),
            key
        );
        CoefficientsKey upperKey = upperNeighbourFinder.find(
            coefficients.keySet(),
            key
        );

        if (lowerKey.compareTo(upperKey) >= 0) {
            throw new IllegalStateException("Invalid coefficients retrieved");
        }

        BigDecimal machNumber = key.getMachNumber();
        BigDecimal angleOfAttack = key.getAngleOfAttack();

        BigDecimal coefficientInterpolatedByMach = interpolatorLinear.interpolate(
            new InterpolatorArgsLinear(
                machNumber,
                lowerKey.getMachNumber(),
                upperKey.getMachNumber(),
                coefficients.get(lowerKey),
                coefficients.get(upperKey)
            )
        );

        BigDecimal coefficientInterpolatedByAngle = interpolatorLinear.interpolate(
            new InterpolatorArgsLinear(
                angleOfAttack,
                lowerKey.getAngleOfAttack(),
                upperKey.getAngleOfAttack(),
                coefficients.get(lowerKey),
                coefficients.get(upperKey)
            )
        );

        BigDecimal avgCoefficient = coefficientInterpolatedByAngle
            .add(coefficientInterpolatedByMach)
            .divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);

        return avgCoefficient;
    }
}
