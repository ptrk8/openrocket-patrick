package net.sf.openrocket.aerodynamics.coefficients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;

public class CoefficientsMapImplTest {

    List<List<BigDecimal>> invalidMachLists = Arrays.asList(
        Arrays.asList(
            new BigDecimal("0"),
            new BigDecimal("0.3"),
            new BigDecimal("0.2")
        ),
        Arrays.asList(
            new BigDecimal("0.4"),
            new BigDecimal("0.3"),
            new BigDecimal("0.2")
        )
    );

    List<List<BigDecimal>> validMachLists = Arrays.asList(
        Arrays.asList(
            new BigDecimal("0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        ),
        Arrays.asList(
            new BigDecimal("-0.1"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        )
    );

    List<List<BigDecimal>> invalidAngleOfAttackLists = Arrays.asList(
        Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("0"),
            new BigDecimal("-1")
        ),
        Arrays.asList(
            new BigDecimal("0.1"),
            new BigDecimal("0.1"),
            new BigDecimal("-0.2")
        )
    );

    List<List<BigDecimal>> validAngleOfAttackLists = Arrays.asList(
        Arrays.asList(
            new BigDecimal("-1"),
            new BigDecimal("0"),
            new BigDecimal("1")
        ),
        Arrays.asList(
            new BigDecimal("-0.1"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        )
    );

    List<BigDecimal> oneToThree = Arrays.asList(
        new BigDecimal(1),
        new BigDecimal(2),
        new BigDecimal(3)
    );
    List<BigDecimal> oneToNine = Arrays.asList(
        new BigDecimal(1),
        new BigDecimal(2),
        new BigDecimal(3),
        new BigDecimal(4),
        new BigDecimal(5),
        new BigDecimal(6),
        new BigDecimal(7),
        new BigDecimal(8),
        new BigDecimal(9)
    );


    @Test
    public void constructorShouldThrowErrIfInvalidMachOrdering() {
        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9")
        );

        for (List<BigDecimal> machList : invalidMachLists) {
            for (List<BigDecimal> angleOfAttackList : validAngleOfAttackLists) {
                assertThrows(IllegalArgumentException.class, () -> {
                    new CoefficientsMapImpl(
                        machList,
                        angleOfAttackList,
                        coefficientsList,
                        new CoefficientsInterpolatorBilinear()
                    );
                });
            }
        }
    }

    @Test
    public void constructorShouldThrowErrIfInvalidAngleOfAttackOrdering() {

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9")
        );

        for (List<BigDecimal> machList : validMachLists) {
            for (List<BigDecimal> angleOfAttackList : invalidAngleOfAttackLists) {
                assertThrows(IllegalArgumentException.class, () -> {
                    new CoefficientsMapImpl(
                        machList,
                        angleOfAttackList,
                        coefficientsList,
                        new CoefficientsInterpolatorBilinear()
                    );
                });
            }
        }
    }

    public void constructorShouldThrowErrIfInvalidAngleOfAttackAndMachOrdering() {

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9")
        );

        for (List<BigDecimal> machList : invalidMachLists) {
            for (List<BigDecimal> angleOfAttackList : invalidAngleOfAttackLists) {
                assertThrows(IllegalArgumentException.class, () -> {
                    new CoefficientsMapImpl(
                        machList,
                        angleOfAttackList,
                        coefficientsList,
                        new CoefficientsInterpolatorBilinear()
                    );
                });
            }
        }
    }

    @Test
    public void constructorShouldAcceptCoefficientsWithValidLength() {
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal("0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        );

        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal("-1"),
            new BigDecimal("0"),
            new BigDecimal("1")
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9")
        );

        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientsList,
            new CoefficientsInterpolatorBilinear()
        );
    }

    @Test
    public void constructorShouldThrowErrIfTooManyCoefficients() {
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal("0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        );

        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal("-1"),
            new BigDecimal("0"),
            new BigDecimal("1")
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9"),
            new BigDecimal("10")
        );

        assertThrows(IllegalArgumentException.class, () -> {
            new CoefficientsMapImpl(
                machList,
                angleOfAttackList,
                coefficientsList,
                new CoefficientsInterpolatorBilinear()
            );
        });
    }

    @Test
    public void constructorShouldThrowErrIfNotEnoughCoefficients() {
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal("0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        );

        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal("-1"),
            new BigDecimal("0"),
            new BigDecimal("1")
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8")
        );

        assertThrows(NoSuchElementException.class, () -> {
            new CoefficientsMapImpl(
                machList,
                angleOfAttackList,
                coefficientsList,
                new CoefficientsInterpolatorBilinear()
            );
        });
    }

    @Test
    public void getCoefficientShouldRetrieveCorrectCoefficient() {
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal("0"),
            new BigDecimal("0.1"),
            new BigDecimal("0.2")
        );

        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal("-1"),
            new BigDecimal("0"),
            new BigDecimal("1")
        );

        List<BigDecimal> coefficientsList = Arrays.asList(
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9")
        );

        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientsList,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficient(
            new CoefficientsKeyImpl(
                new BigDecimal("0.1"),
                new BigDecimal("0")
            )
        );

        assertEquals(
            new BigDecimal("5"),
            actualCoefficient
        );
    }


    @Test
    public void getCoefficientBilinearInterpolatedMiddle() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2.5"),
                new BigDecimal("1.5")
            )
        );

        assertEquals(
            0,
            new BigDecimal("4.0").compareTo(actualCoefficient)
        );
    }

    @Test
    public void getCoefficientBilinearInterpolatedHigher() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2.75"),
                new BigDecimal("1.75")
            )
        );

        assertEquals(
            0,
            new BigDecimal("5.0").compareTo(actualCoefficient)
        );
    }

    @Test
    public void getCoefficientBilinearInterpolatedHigherReversed() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            Arrays.asList(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3)
            ),
            Arrays.asList(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3)
            ),
            Arrays.asList(
                new BigDecimal(9),
                new BigDecimal(8),
                new BigDecimal(7),
                new BigDecimal(6),
                new BigDecimal(5),
                new BigDecimal(4),
                new BigDecimal(3),
                new BigDecimal(2),
                new BigDecimal(1)
            ),
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2.75"),
                new BigDecimal("1.75")
            )
        );

        assertEquals(
            0,
            new BigDecimal("5.0").compareTo(actualCoefficient)
        );
    }

    @Test
    public void getCoefficientBilinearInterpolatedOverMachOnlyIfExistingAngleOfAttack() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2.2"),
                new BigDecimal("1") // This angle of attack exists
            )
        );

        assertTrue(
            actualCoefficient.compareTo(new BigDecimal("2.2")) == 0
        );
    }

    @Test
    public void getCoefficientBilinearInterpolatedOverAngleOfAttackOnlyIfExistingMachNumber() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2"), // This mach number exists
                new BigDecimal("1.8")
            )
        );

        assertTrue(
            actualCoefficient.compareTo(new BigDecimal("4.4")) == 0
        );
    }

    @Test
    public void getCoefficientBilinearInterpolatedShouldThrowErrIfOutOfMachRange() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        List<BigDecimal> invalidMachNumbers = Arrays.asList(
            new BigDecimal("0.99"),
            new BigDecimal("3.01")
        );

        for (BigDecimal invalidMachNumber : invalidMachNumbers) {
            assertThrows(IllegalArgumentException.class, () -> {
                coefficientsMap.getCoefficientInterpolated(
                    new CoefficientsKeyImpl(
                        invalidMachNumber,
                        new BigDecimal("2")
                    )
                );
            });
        }
    }

    @Test
    public void getCoefficientBilinearInterpolatedShouldThrowErrIfOutOfAngleOfAttackRange() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        List<BigDecimal> invalidAngleOfAttacks = Arrays.asList(
            new BigDecimal("0.99"),
            new BigDecimal("3.01")
        );

        for (BigDecimal invalidAngleOfAttack : invalidAngleOfAttacks) {
            assertThrows(IllegalArgumentException.class, () -> {
                coefficientsMap.getCoefficientInterpolated(
                    new CoefficientsKeyImpl(
                        new BigDecimal("2"),
                        invalidAngleOfAttack
                    )
                );
            });
        }
    }

    @Test
    public void getCoefficientBilinearInterpolatedLongDecimal() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            oneToThree,
            oneToThree,
            oneToNine,
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2.666666666666666666666666"),
                new BigDecimal("1.666666666666666666666666")
            )
        );

        assertEquals(
            0,
            new BigDecimal("4.666666666666666666666664").compareTo(actualCoefficient)
        );
    }

    @Test
    public void getCoefficientBilinearInterpolatedShouldNotInterpolateIfCoefficientExists() {
        CoefficientsMap coefficientsMap = new CoefficientsMapImpl(
            Arrays.asList(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3)
            ),
            Arrays.asList(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3)
            ),
            Arrays.asList(
                new BigDecimal(90),
                new BigDecimal(82),
                new BigDecimal(12),
                new BigDecimal(22),
                new BigDecimal(4),
                new BigDecimal(1),
                new BigDecimal(43),
                new BigDecimal(100),
                new BigDecimal(1)
            ),
            new CoefficientsInterpolatorBilinear()
        );

        BigDecimal actualCoefficient = coefficientsMap.getCoefficientInterpolated(
            new CoefficientsKeyImpl(
                new BigDecimal("2.00"),
                new BigDecimal("2.00")
            )
        );

        assertEquals(
            new BigDecimal(4),
            actualCoefficient
        );
    }
}
