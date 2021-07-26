package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Represents the interface for our coefficients map.
 */
public interface CoefficientsMap {

    /**
     * @return The coefficients map comprising of the key and the value.
     */
    Map<CoefficientsKey, BigDecimal> getCoefficients();

    /**
     * @param key The key we want the coefficient for.
     * @return The coefficient matching the provided key.
     */
    BigDecimal getCoefficient(
        CoefficientsKey key
    );

    /**
     * @param key A key that does not exactly match the existing keys.
     * @return The interpolated coefficient.
     */
    BigDecimal getCoefficientInterpolated(
        CoefficientsKey key
    );

    /**
     * @return CoefficientsMap as a list of rows.
     */
    List<CoefficientsMapRow> getList();
}
