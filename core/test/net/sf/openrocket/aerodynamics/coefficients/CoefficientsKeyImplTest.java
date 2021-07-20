package net.sf.openrocket.aerodynamics.coefficients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;
import org.junit.Test;

public class CoefficientsKeyImplTest {

    @Test
    public void hashCodeShouldBeIdenticalForNumericallyIdenticalValues() {
        assertEquals(
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal(2)
            ).hashCode(),
            new CoefficientsKeyImpl(
                new BigDecimal("1.0000"),
                new BigDecimal("2.0")
            ).hashCode()
        );
        assertEquals(
            new CoefficientsKeyImpl(
                new BigDecimal("1.3"),
                new BigDecimal("5.321")
            ).hashCode(),
            new CoefficientsKeyImpl(
                new BigDecimal("1.30000"),
                new BigDecimal("5.321000")
            ).hashCode()
        );
    }

    @Test
    public void hashCodeShouldBeDifferentForNumericallyDifferentValues() {
        assertNotEquals(
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal(2)
            ).hashCode(),
            new CoefficientsKeyImpl(
                new BigDecimal("1.0000"),
                new BigDecimal("3.0")
            ).hashCode()
        );
        assertNotEquals(
            new CoefficientsKeyImpl(
                new BigDecimal("1.3"),
                new BigDecimal("5.321")
            ).hashCode(),
            new CoefficientsKeyImpl(
                new BigDecimal("1.30000"),
                new BigDecimal("5.321001")
            ).hashCode()
        );
    }

    @Test
    public void equalsShouldReturnTrueForNumericallyIdenticalKeys() {
        assertEquals(
            new CoefficientsKeyImpl(
                new BigDecimal(1),
                new BigDecimal(2)
            ),
            new CoefficientsKeyImpl(
                new BigDecimal("1.0000"),
                new BigDecimal("2.0")
            )
        );
        assertEquals(
            new CoefficientsKeyImpl(
                new BigDecimal("1.3"),
                new BigDecimal("5.321")
            ),
            new CoefficientsKeyImpl(
                new BigDecimal("1.30000"),
                new BigDecimal("5.321000")
            )
        );
    }

    @Test
    public void equalsShouldReturnFalseForNumericallyDifferentKeys() {
        assertNotEquals(
            new CoefficientsKeyImpl(
                new BigDecimal("1.2"),
                new BigDecimal(1)
            ),
            new CoefficientsKeyImpl(
                new BigDecimal("1.3"),
                new BigDecimal(1)
            )
        );
        assertNotEquals(
            new CoefficientsKeyImpl(
                new BigDecimal("1.2"),
                new BigDecimal(1)
            ),
            new CoefficientsKeyImpl(
                new BigDecimal("1.2"),
                new BigDecimal(2)
            )
        );
    }
    @Test
    public void compareToEquals() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal("0.2"),
            new BigDecimal("0.5")
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal("0.2"),
            new BigDecimal("0.5")
        );

        assertEquals(
            0,
            a.compareTo(b)
        );
    }

    @Test
    public void compareToLess() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal("0.2"),
            new BigDecimal("0.5")
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal(1)
        );

        assertEquals(
            -1,
            a.compareTo(b)
        );
    }

    @Test
    public void compareToGreater() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal(2),
            new BigDecimal(2)
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal(1)
        );

        assertEquals(
            1,
            a.compareTo(b)
        );
    }

    @Test
    public void compareToLessPartial() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal("0.5")
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal(1)
        );

        assertEquals(
            -1,
            a.compareTo(b)
        );
    }

    @Test
    public void compareToGreaterPartial() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal(2),
            new BigDecimal(1)
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal(1)
        );

        assertEquals(
            1,
            a.compareTo(b)
        );
    }

    @Test
    public void compareToLessShouldErrorIfConflicted1() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal(2)
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal(2),
            new BigDecimal(1)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            a.compareTo(b);
        });
    }

    @Test
    public void compareToLessShouldErrorIfConflicted2() {
        CoefficientsKey a = new CoefficientsKeyImpl(
            new BigDecimal(2),
            new BigDecimal(1)
        );
        CoefficientsKey b = new CoefficientsKeyImpl(
            new BigDecimal(1),
            new BigDecimal(2)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            a.compareTo(b);
        });
    }
}