package net.sf.openrocket.aerodynamics.coefficients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;

public class CoefficientsKeyFinderLowerNeighbourTest {

    @Test
    public void findShouldReturnLowerNeighbour() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("1.2"),
                new BigDecimal("1.3")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnLowerNeighbourIfMachAboveRange() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(3),
                new BigDecimal("1.3")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnLowerNeighbourIfAngleOfAttackAboveRange() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("1.5"),
                new BigDecimal(3)
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnLowerNeighbourIfBothAboveRange() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(3),
                new BigDecimal(3)
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnLowerNeighbourIfEqualToMachNumber() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal(3)
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnLowerNeighbourIfEqualToAngleOfAttack() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(3),
                new BigDecimal(2)
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnLowerNeighbourIfEqualToBoth() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderLowerNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal(1)
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
            actualKey
        );
    }

    @Test
    public void findShouldThrowErrorIfNoLowerNeighbour() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CoefficientsKeyFinderLowerNeighbour().find(
                new HashSet<>(Arrays.asList(
                    new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                    new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                    new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                    new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
                )),
                new CoefficientsKeyImpl(
                    new BigDecimal("0.3"),
                    new BigDecimal("1.3")
                )
            );
        });
    }
}