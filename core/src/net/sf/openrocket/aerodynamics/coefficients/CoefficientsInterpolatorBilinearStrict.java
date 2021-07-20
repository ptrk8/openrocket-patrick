package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.Map;
import net.sf.openrocket.aerodynamics.interpolator.Interpolator;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorArgsBilinear;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorBilinear;

public class CoefficientsInterpolatorBilinearStrict implements CoefficientsInterpolator {

    private final Interpolator interpolatorBilinear = new InterpolatorBilinear();
    private final CoefficientsKeyFinder lowerNeighbourFinder = new CoefficientsKeyFinderLowerNeighbourStrict();
    private final CoefficientsKeyFinder upperNeighbourFinder = new CoefficientsKeyFinderUpperNeighbourStrict();

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

        BigDecimal valueAtx1y1 = coefficients.get(lowerKey);
        BigDecimal valueAtx1y2 = coefficients.get(
            new CoefficientsKeyImpl(
                lowerKey.getMachNumber(),
                upperKey.getAngleOfAttack()
            )
        );
        BigDecimal valueAtx2y1 = coefficients.get(
            new CoefficientsKeyImpl(
                upperKey.getMachNumber(),
                lowerKey.getAngleOfAttack()
            )
        );
        BigDecimal valueAtx2y2 = coefficients.get(upperKey);

        BigDecimal interpolated = interpolatorBilinear.interpolate(
            new InterpolatorArgsBilinear(
                machNumber,
                angleOfAttack,
                lowerKey.getMachNumber(),
                lowerKey.getAngleOfAttack(),
                upperKey.getMachNumber(),
                upperKey.getAngleOfAttack(),
                valueAtx1y1,
                valueAtx1y2,
                valueAtx2y1,
                valueAtx2y2
            )
        );

        return interpolated;
    }
}
