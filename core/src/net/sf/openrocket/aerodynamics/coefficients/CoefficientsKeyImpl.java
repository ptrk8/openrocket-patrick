package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;

/**
 * Represents a concrete implementation of our CoefficientsKey interface.
 */
public class CoefficientsKeyImpl implements CoefficientsKey {
    private final BigDecimal machNumber;
    private final BigDecimal angleOfAttack;

    /**
     * @param machNumber The mach number portion of the key.
     * @param angleOfAttack The angle of attack portion of the key.
     */
    public CoefficientsKeyImpl(BigDecimal machNumber, BigDecimal angleOfAttack) {
        this.machNumber = machNumber;
        this.angleOfAttack = angleOfAttack;
    }

    @Override
    public BigDecimal getAngleOfAttack() {
        return angleOfAttack;
    }

    @Override
    public BigDecimal getMachNumber() {
        return machNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CoefficientsKey)) {
            return false;
        }

        CoefficientsKey other = (CoefficientsKey) o;

        return machNumber.compareTo(other.getMachNumber()) == 0
            && angleOfAttack.compareTo(other.getAngleOfAttack()) == 0;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + new Double(machNumber.doubleValue()).hashCode();
        result = 31 * result + new Double(angleOfAttack.doubleValue()).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format(
            "CoefficientsKey(Mach: %.2f, Angle of attack: %.2f)",
            machNumber,
            angleOfAttack
        );
    }

    @Override
    public int compare(CoefficientsKey a, CoefficientsKey b) {
        if (a.equals(b)) {
            return 0;
        }
        if (a.getMachNumber().compareTo(b.getMachNumber()) <= 0 &&
            a.getAngleOfAttack().compareTo(b.getAngleOfAttack()) <= 0) {
            return -1;
        }
        if (a.getMachNumber().compareTo(b.getMachNumber()) >= 0 &&
            a.getAngleOfAttack().compareTo(b.getAngleOfAttack()) >= 0) {
            return 1;
        }
        throw new IllegalArgumentException("Unable to compare coefficient keys");
    }

    @Override
    public int compareTo(CoefficientsKey o) {
        return compare(this, o);
    }

    @Override
    public boolean isLessThanOrEqualTo(CoefficientsKey o) {
        return this.getMachNumber().compareTo(o.getMachNumber()) <= 0 &&
            this.getAngleOfAttack().compareTo(o.getAngleOfAttack()) <= 0;
    }

    @Override
    public boolean isGreaterThanOrEqualTo(CoefficientsKey o) {
        return this.getMachNumber().compareTo(o.getMachNumber()) >= 0 &&
            this.getAngleOfAttack().compareTo(o.getAngleOfAttack()) >= 0;
    }

    @Override
    public boolean isStrictlyLessThan(CoefficientsKey o) {
        return this.getMachNumber().compareTo(o.getMachNumber()) < 0 &&
            this.getAngleOfAttack().compareTo(o.getAngleOfAttack()) < 0;
    }

    @Override
    public boolean isStrictlyGreaterThan(CoefficientsKey o) {
        return this.getMachNumber().compareTo(o.getMachNumber()) > 0 &&
            this.getAngleOfAttack().compareTo(o.getAngleOfAttack()) > 0;
    }
}
