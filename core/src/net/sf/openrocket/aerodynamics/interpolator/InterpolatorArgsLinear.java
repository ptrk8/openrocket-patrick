package net.sf.openrocket.aerodynamics.interpolator;

import java.math.BigDecimal;

public class InterpolatorArgsLinear extends InterpolatorArgs {

    private final BigDecimal x;
    private final BigDecimal xLower;
    private final BigDecimal xUpper;
    private final BigDecimal yLower;
    private final BigDecimal yUpper;

    public InterpolatorArgsLinear(
        BigDecimal x,
        BigDecimal xLower,
        BigDecimal xUpper,
        BigDecimal yLower,
        BigDecimal yUpper
    ) {
        this.x = x;
        this.xLower = xLower;
        this.xUpper = xUpper;
        this.yLower = yLower;
        this.yUpper = yUpper;
    }

    @Override
    public BigDecimal getX() {
        return x;
    }

    @Override
    public BigDecimal getXLower() {
        return xLower;
    }

    @Override
    public BigDecimal getXUpper() {
        return xUpper;
    }

    @Override
    public BigDecimal getYLower() {
        return yLower;
    }

    @Override
    public BigDecimal getYUpper() {
        return yUpper;
    }

}
