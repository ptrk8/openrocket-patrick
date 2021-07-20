package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.Map;
import net.sf.openrocket.aerodynamics.interpolator.Interpolator;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorArgsBilinear;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorArgsLinear;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorBilinear;
import net.sf.openrocket.aerodynamics.interpolator.InterpolatorLinear;

public class CoefficientsInterpolatorBilinear implements CoefficientsInterpolator {

    private final Interpolator interpolatorBilinear = new InterpolatorBilinear();
    private final Interpolator interpolatorLinear = new InterpolatorLinear();
    private final CoefficientsKeyFinder lowerNeighbourFinder = new CoefficientsKeyFinderLowerNeighbour();
    private final CoefficientsKeyFinder upperNeighbourFinder = new CoefficientsKeyFinderUpperNeighbour();

    @Override
    public BigDecimal interpolate(
        CoefficientsKey key,
        Map<CoefficientsKey, BigDecimal> coefficients
    ) {
        CoefficientsKey lowerKey = lowerNeighbourFinder.find(
            coefficients.keySet(),
            key
        );
        CoefficientsKey upperKey = upperNeighbourFinder.find(
            coefficients.keySet(),
            key
        );

        // If the lower key is exactly the same as the upper key, return the exact coefficient
        if (lowerKey.equals(key) &&
            upperKey.equals(key)) {
            return coefficients.get(key);
        }

        // If the lower key is for some reason larger than the upper key, we have a problem.
        if (lowerKey.compareTo(upperKey) >= 0) {
            throw new IllegalStateException("Invalid coefficients retrieved");
        }

        // If mach numbers are all equal, linearly interpolate by angles of attack only
        if (lowerKey.getMachNumber().compareTo(key.getMachNumber()) == 0 &&
            upperKey.getMachNumber().compareTo(key.getMachNumber()) == 0) {
            return interpolatorLinear.interpolate(
                new InterpolatorArgsLinear(
                    key.getAngleOfAttack(),
                    lowerKey.getAngleOfAttack(),
                    upperKey.getAngleOfAttack(),
                    coefficients.get(lowerKey),
                    coefficients.get(upperKey)
                )
            );
        }

        // If angles of attack are all equal, linearly interpolate by mach number only
        if (lowerKey.getAngleOfAttack().compareTo(key.getAngleOfAttack()) == 0 &&
            upperKey.getAngleOfAttack().compareTo(key.getAngleOfAttack()) == 0) {
            return interpolatorLinear.interpolate(
                new InterpolatorArgsLinear(
                    key.getMachNumber(),
                    lowerKey.getMachNumber(),
                    upperKey.getMachNumber(),
                    coefficients.get(lowerKey),
                    coefficients.get(upperKey)
                )
            );
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
