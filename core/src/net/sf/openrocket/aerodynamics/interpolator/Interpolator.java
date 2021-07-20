package net.sf.openrocket.aerodynamics.interpolator;

import java.math.BigDecimal;

public interface Interpolator {

    boolean hasValidArgs(InterpolatorArgs args);

    BigDecimal interpolate(InterpolatorArgs args);
}
