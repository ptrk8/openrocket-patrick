package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Interface to interpolate coefficients.
 */
public interface CoefficientsInterpolator {

    BigDecimal interpolate(
        CoefficientsKey key,
        Map<CoefficientsKey, BigDecimal> coefficients
    );
}
