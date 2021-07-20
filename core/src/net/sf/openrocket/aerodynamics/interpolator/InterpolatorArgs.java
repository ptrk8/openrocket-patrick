package net.sf.openrocket.aerodynamics.interpolator;

import java.math.BigDecimal;

public abstract class InterpolatorArgs {

    BigDecimal getY() {
        return null;
    }

    BigDecimal getX1() {
        return null;
    }

    BigDecimal getY1() {
        return null;
    }

    BigDecimal getX2() {
        return null;
    }

    BigDecimal getY2() {
        return null;
    }

    BigDecimal getValueAtx1y1() {
        return null;
    }

    BigDecimal getValueAtx1y2() {
        return null;
    }

    BigDecimal getValueAtx2y1() {
        return null;
    }

    BigDecimal getValueAtx2y2() {
        return null;
    }

    BigDecimal getX() {
        return null;
    }

    BigDecimal getXLower() {
        return null;
    }

    BigDecimal getXUpper() {
        return null;
    }

    BigDecimal getYLower() {
        return null;
    }

    BigDecimal getYUpper() {
        return null;
    }
}
