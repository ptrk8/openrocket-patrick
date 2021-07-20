package net.sf.openrocket.aerodynamics.interpolator;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Test;

public class InterpolatorBilinearTest {

//    @Test
//    public void apacheMathShouldFindInverseOfInvertibleMatrix() {
//        BigReal[][] leftMatrixData = new BigReal[][] {
//            { new BigReal(1), new BigReal(0), new BigReal(0), new BigReal(0) },
//            { new BigReal(1), new BigReal(0), new BigReal(1), new BigReal(0) },
//            { new BigReal(1), new BigReal(1), new BigReal(0), new BigReal(0) },
//            { new BigReal(1), new BigReal(1), new BigReal(1), new BigReal(1) },
//        };
//
//        FieldMatrix<BigReal> leftMatrix = MatrixUtils.createFieldMatrix(leftMatrixData);
//        FieldMatrix<BigReal> leftMatrixInverse = new FieldLUDecomposition<>(leftMatrix)
//            .getSolver()
//            .getInverse();
//    }

    @Test
    public void interpolateShouldReturnCorrectValues() {

        Interpolator interpolator = new InterpolatorBilinear();

        BigDecimal actualValue = interpolator.interpolate(
            new InterpolatorArgsBilinear(
                new BigDecimal("0.5"),
                new BigDecimal("0.5"),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(10),
                new BigDecimal(11),
                new BigDecimal(12),
                new BigDecimal(13)
            )
        );

        assertTrue(
            new BigDecimal("11.5").compareTo(actualValue) == 0
        );

        actualValue = interpolator.interpolate(
            new InterpolatorArgsBilinear(
                new BigDecimal("0.666"),
                new BigDecimal("0.723"),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(10),
                new BigDecimal(11),
                new BigDecimal(12),
                new BigDecimal(13)
            )
        );

        assertTrue(
            new BigDecimal("12.055").compareTo(actualValue) == 0
        );

        actualValue = interpolator.interpolate(
            new InterpolatorArgsBilinear(
                new BigDecimal("0.8"),
                new BigDecimal("0.7"),
                new BigDecimal("0.2"),
                new BigDecimal(0),
                new BigDecimal("1.5"),
                new BigDecimal(1),
                new BigDecimal(10),
                new BigDecimal(11),
                new BigDecimal(12),
                new BigDecimal(13)
            )
        );

        assertTrue(
            new BigDecimal("11.62307692310").compareTo(actualValue) == 0
        );
    }

}
