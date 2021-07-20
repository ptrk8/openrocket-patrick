package net.sf.openrocket.aerodynamics.interpolator;

import java.math.BigDecimal;

public class InterpolatorArgsBilinear extends InterpolatorArgs {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal x1;
    private final BigDecimal y1;
    private final BigDecimal x2;
    private final BigDecimal y2;
    private final BigDecimal valueAtx1y1;
    private final BigDecimal valueAtx1y2;
    private final BigDecimal valueAtx2y1;
    private final BigDecimal valueAtx2y2;

    public InterpolatorArgsBilinear(
        BigDecimal x,
        BigDecimal y,
        BigDecimal x1,
        BigDecimal y1,
        BigDecimal x2,
        BigDecimal y2,
        BigDecimal valueAtx1y1,
        BigDecimal valueAtx1y2,
        BigDecimal valueAtx2y1,
        BigDecimal valueAtx2y2
    ) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.valueAtx1y1 = valueAtx1y1;
        this.valueAtx1y2 = valueAtx1y2;
        this.valueAtx2y1 = valueAtx2y1;
        this.valueAtx2y2 = valueAtx2y2;
    }

    @Override
    public BigDecimal getX() {
        return x;
    }

    @Override
    public BigDecimal getY() {
        return y;
    }

    @Override
    public BigDecimal getX1() {
        return x1;
    }

    @Override
    public BigDecimal getY1() {
        return y1;
    }

    @Override
    public BigDecimal getX2() {
        return x2;
    }

    @Override
    public BigDecimal getY2() {
        return y2;
    }

    @Override
    public BigDecimal getValueAtx1y1() {
        return valueAtx1y1;
    }

    @Override
    public BigDecimal getValueAtx1y2() {
        return valueAtx1y2;
    }

    @Override
    public BigDecimal getValueAtx2y1() {
        return valueAtx2y1;
    }

    @Override
    public BigDecimal getValueAtx2y2() {
        return valueAtx2y2;
    }
}
