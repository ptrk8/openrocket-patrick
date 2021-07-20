package net.sf.openrocket.aerodynamics.interpolator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InterpolatorLinear implements Interpolator {

    @Override
    public boolean hasValidArgs(InterpolatorArgs args) {
        return args.getX() != null &&
            args.getXLower() != null &&
            args.getXUpper() != null &&
            args.getYLower() != null &&
            args.getYUpper() != null;
    }

    @Override
    public BigDecimal interpolate(InterpolatorArgs args) {
        if (!hasValidArgs(args)) {
            throw new IllegalArgumentException("Invalid arguments supplied");
        }
        BigDecimal x = args.getX();
        BigDecimal xLower = args.getXLower();
        BigDecimal xUpper = args.getXUpper();
        BigDecimal yLower = args.getYLower();
        BigDecimal yUpper = args.getYUpper();

        BigDecimal xMinusXLower = x.subtract(xLower);
        BigDecimal yUpperMinusYLower = yUpper.subtract(yLower);
        BigDecimal xUpperMinusXLower = xUpper.subtract(xLower);

        return yLower.add(
            xMinusXLower.multiply(
                yUpperMinusYLower.divide(xUpperMinusXLower, 10, RoundingMode.HALF_UP)
            )
        );
    }
}
