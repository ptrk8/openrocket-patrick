package net.sf.openrocket.aerodynamics.interpolator;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Test;

public class InterpolatorLinearTest {

    @Test
    public void interpolateShouldReturnCorrectValue() {
        Interpolator interpolator = new InterpolatorLinear();

        BigDecimal actualValue = interpolator.interpolate(
            new InterpolatorArgsLinear(
                new BigDecimal("0.35"),
                new BigDecimal("0.3"),
                new BigDecimal("0.4"),
                new BigDecimal("-0.1814"),
                new BigDecimal("-0.1675")
            )
        );

        assertTrue(
            new BigDecimal("-0.17445").compareTo(actualValue) == 0
        );
    }
}
