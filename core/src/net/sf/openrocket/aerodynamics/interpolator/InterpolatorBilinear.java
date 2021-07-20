package net.sf.openrocket.aerodynamics.interpolator;

import java.math.BigDecimal;

public class InterpolatorBilinear implements Interpolator {

    private final Interpolator interpolatorLinear = new InterpolatorLinear();

    @Override
    public boolean hasValidArgs(InterpolatorArgs args) {
        return args.getX() != null &&
            args.getY() != null &&
            args.getX1() != null &&
            args.getY1() != null &&
            args.getX2() != null &&
            args.getY2() != null &&
            args.getValueAtx1y1() != null &&
            args.getValueAtx1y2() != null &&
            args.getValueAtx2y1() != null &&
            args.getValueAtx2y2() != null;
    }

    @Override
    public BigDecimal interpolate(InterpolatorArgs args) {
        if (!hasValidArgs(args)) {
            throw new IllegalArgumentException("Invalid arguments supplied to Bilinear interpolator");
        }
        BigDecimal x = args.getX();
        BigDecimal y = args.getY();
        BigDecimal x1 = args.getX1();
        BigDecimal x2 = args.getX2();
        BigDecimal y1 = args.getY1();
        BigDecimal y2 = args.getY2();
        BigDecimal valueAtx1y1 = args.getValueAtx1y1();
        BigDecimal valueAtx1y2 = args.getValueAtx1y2();
        BigDecimal valueAtx2y1 = args.getValueAtx2y1();
        BigDecimal valueAtx2y2 = args.getValueAtx2y2();

        // Interpolate for x holding y constant at y1
        BigDecimal interpolatedXAtY1 = interpolatorLinear.interpolate(
            new InterpolatorArgsLinear(
                x,
                x1,
                x2,
                valueAtx1y1,
                valueAtx2y1
            )
        );
        // Interpolate for x holding y constant at y2
        BigDecimal interpolatedXAtY2 = interpolatorLinear.interpolate(
            new InterpolatorArgsLinear(
                x,
                x1,
                x2,
                valueAtx1y2,
                valueAtx2y2
            )
        );
        // Interpolate in the y direction for our interpolated X values
        BigDecimal interpolateAcrossY = interpolatorLinear.interpolate(
            new InterpolatorArgsLinear(
                y,
                y1,
                y2,
                interpolatedXAtY1,
                interpolatedXAtY2
            )
        );

        return interpolateAcrossY;

//        BigReal x = new BigReal(args.getX());
//        BigReal y = new BigReal(args.getY());
//        BigReal x1 = new BigReal(args.getX1());
//        BigReal x2 = new BigReal(args.getX2());
//        BigReal y1 = new BigReal(args.getY1());
//        BigReal y2 = new BigReal(args.getY2());
//        BigReal valueAtx1y1 = new BigReal(args.getValueAtx1y1());
//        BigReal valueAtx1y2 = new BigReal(args.getValueAtx1y2());
//        BigReal valueAtx2y1 = new BigReal(args.getValueAtx2y1());
//        BigReal valueAtx2y2 = new BigReal(args.getValueAtx2y2());
//
//        BigReal[][] leftMatrixData = new BigReal[][] {
//            { new BigReal(1), x1, y1, x1.multiply(y1) },
//            { new BigReal(1), x1, y2, x1.multiply(y2) },
//            { new BigReal(1), x2, y1, x2.multiply(y1) },
//            { new BigReal(1), x2, y2, x2.multiply(y2) },
//        };
//        BigReal[][] rightVectorData = new BigReal[][] {
//            { new BigReal(1) },
//            { x },
//            { y },
//            { x.multiply(y) }
//        };
//
//        FieldMatrix<BigReal> leftMatrix = MatrixUtils.createFieldMatrix(leftMatrixData);
//        FieldMatrix<BigReal> rightVector = MatrixUtils.createFieldMatrix(rightVectorData);
//
//        FieldMatrix<BigReal> leftMatrixInverse = new FieldLUDecomposition<>(leftMatrix)
//            .getSolver()
//            .getInverse();
//
//        FieldMatrix<BigReal> coefficients = leftMatrixInverse
//            .transpose()
//            .multiply(rightVector);
//
//        BigReal b11 = coefficients.getEntry(0, 0);
//        BigReal b12 = coefficients.getEntry(1, 0);
//        BigReal b21 = coefficients.getEntry(2, 0);
//        BigReal b22 = coefficients.getEntry(3, 0);
//
//        BigReal b11TimesValueAtx1y1 = b11.multiply(valueAtx1y1);
//        BigReal b12TimesValueAtx1y2 = b12.multiply(valueAtx1y2);
//        BigReal b21TimesValueAtx2y1 = b21.multiply(valueAtx2y1);
//        BigReal b22TimesValueAtx2y2 = b22.multiply(valueAtx2y2);
//
//        return b11TimesValueAtx1y1
//            .add(b12TimesValueAtx1y2)
//            .add(b21TimesValueAtx2y1)
//            .add(b22TimesValueAtx2y2)
//            .bigDecimalValue();
    }
}
