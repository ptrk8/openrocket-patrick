package net.sf.openrocket.aerodynamics.coefficients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;

public class CoefficientsKeyFinderUpperNeighbourTest {

    @Test
    public void findShouldReturnUpperNeighbour() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("1.2"),
                new BigDecimal("0.5")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnUpperNeighbourIfMachBelowRange() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("0.5"),
                new BigDecimal("1.2")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnUpperNeighbourIfAngleOfAttackBelowRange() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("1.2"),
                new BigDecimal("0.5")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnUpperNeighbourIfBothBelowRange() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("0.5"),
                new BigDecimal("0.5")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnUpperNeighbourIfEqualToMachNumber() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal("1.4")
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnUpperNeighbourIfEqualToAngleOfAttack() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal("1.4"),
                new BigDecimal(2)
            )
        );

        assertEquals(
            new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2)),
            actualKey
        );
    }

    @Test
    public void findShouldReturnUpperNeighbourIfEqualToBoth() {
        CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
            new HashSet<>(Arrays.asList(
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
            )),
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal(2)
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
    public void findShouldThrowErrorIfNoUpperNeighbour() {
        assertThrows(IllegalArgumentException.class, () -> {
            CoefficientsKey actualKey = new CoefficientsKeyFinderUpperNeighbour().find(
                new HashSet<>(Arrays.asList(
                    new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(1)),
                    new CoefficientsKeyImpl(new BigDecimal(1), new BigDecimal(2)),
                    new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(1)),
                    new CoefficientsKeyImpl(new BigDecimal(2), new BigDecimal(2))
                )),
                new CoefficientsKeyImpl(
                    new BigDecimal("2.1"),
                    new BigDecimal("2.1")
                )
            );
        });
    }
}