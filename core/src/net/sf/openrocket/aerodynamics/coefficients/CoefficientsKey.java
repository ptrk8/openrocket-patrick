package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;

/**
 * Interface that represents the composite key for our coefficients Map.
 */
public interface CoefficientsKey extends Comparable<CoefficientsKey> {

    /**
     * @return The angle of attack portion of the composite key.
     */
    BigDecimal getAngleOfAttack();

    /**
     * @return The mach number portion of the composite key.
     */
    BigDecimal getMachNumber();

    /**
     * Compares two CoefficientKeys in a non-strict manner.
     * @param a The first key to compare.
     * @param b The second key to compare.
     * @return 0 if a equals b, -1 if a less than b and 1 if a greater than b
     */
    int compare(
        CoefficientsKey a,
        CoefficientsKey b
    );

    boolean isLessThanOrEqualTo(CoefficientsKey o);

    boolean isGreaterThanOrEqualTo(CoefficientsKey o);

    /**
     * @param o The CoefficientKeys to compare against.
     * @return True if strictly less than the other key, false otherwise.
     */
    boolean isStrictlyLessThan(CoefficientsKey o);

    /**
     * @param o The CoefficientKeys to compare against.
     * @return True if strictly greater than the other key, false otherwise.
     */
    boolean isStrictlyGreaterThan(CoefficientsKey o);
}
