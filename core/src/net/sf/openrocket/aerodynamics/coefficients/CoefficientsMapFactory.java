package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for a factory that creates CoefficientsMaps.
 */
public interface CoefficientsMapFactory {

    /**
     * Precondition: Corresponding mach list, angle of attack list and coefficients interpolator must be set and be non-null.
     * @param coefficientsList The list of coefficients to fill our coefficients map.
     * @return A new map object containing the coefficients and their respective keys.
     */
    CoefficientsMap make(List<BigDecimal> coefficientsList);
}
