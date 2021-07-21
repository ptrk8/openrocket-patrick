package net.sf.openrocket.aerodynamics.coefficients;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CoefficientsInterpolatorBilinearTest {
    @Test
    public void interpolateShouldInterpolateByAngleOfAttackIfMachNumbersEqual() {
        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2)
        );
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2)
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("1.5"),
            new BigDecimal("3"),
            new BigDecimal("4.5")
        );

        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientsList,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = new CoefficientsInterpolatorBilinear().interpolate(
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal("1.5")
            ),
            coefficientsMap.getCoefficients()
        );

        assertTrue(
            actualCoefficient.compareTo(new BigDecimal(2)) == 0
        );
    }

    @Test
    public void interpolateShouldInterpolateIfMachNumberAngleOfAttacksEqual() {
        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2)
        );
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2)
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("1.5"),
            new BigDecimal("3"),
            new BigDecimal("6")
        );

        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientsList,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = new CoefficientsInterpolatorBilinear().interpolate(
            new CoefficientsKeyImpl(
                new BigDecimal("1.5"),
                new BigDecimal("1.000")
            ),
            coefficientsMap.getCoefficients()
        );

        assertTrue(
            actualCoefficient.compareTo(new BigDecimal("1.25")) == 0
        );
    }


    @Test
    public void interpolateShouldNotInterpolateIfMachAndAngleOfAttackExist() {
        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2)
        );
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2)
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("1.5"),
            new BigDecimal("3234"),
            new BigDecimal("62312")
        );

        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientsList,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = new CoefficientsInterpolatorBilinear().interpolate(
            new CoefficientsKeyImpl(
                new BigDecimal("2"),
                new BigDecimal("1.000")
            ),
            coefficientsMap.getCoefficients()
        );

        assertTrue(
            actualCoefficient.compareTo(new BigDecimal("1.5")) == 0
        );
    }

    @Test
    public void interpolateShouldInterpolateIfEverythingUnique() {
        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal("0.2"),
            new BigDecimal("1.5")
        );
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal(0),
            new BigDecimal(1)
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal(10),
            new BigDecimal(11),
            new BigDecimal(12),
            new BigDecimal(13)
        );

        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientsList,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = new CoefficientsInterpolatorBilinear().interpolate(
            new CoefficientsKeyImpl(
                new BigDecimal("0.7"),
                new BigDecimal("0.8")
            ),
            coefficientsMap.getCoefficients()
        );

        assertTrue(
            new BigDecimal("11.62307692310").compareTo(actualCoefficient) == 0
        );
    }
}