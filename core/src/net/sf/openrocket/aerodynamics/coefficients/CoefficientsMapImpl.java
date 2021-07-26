package net.sf.openrocket.aerodynamics.coefficients;

import com.google.common.collect.Ordering;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoefficientsMapImpl implements CoefficientsMap {
    private final Map<CoefficientsKey, BigDecimal> coefficients = new LinkedHashMap<>();
    private final CoefficientsInterpolator interpolator;

    /**
     * @param machList The list of mach numbers.
     * @param angleOfAttackList The list of angles of attacks.
     * @param coefficientsList The list of coefficients corresponding to mach and angle of attack.
     * @param interpolator The interpolator to use.
     */
    public CoefficientsMapImpl(
        List<BigDecimal> machList,
        List<BigDecimal> angleOfAttackList,
        List<BigDecimal> coefficientsList,
        CoefficientsInterpolator interpolator
    ) {
        // Check that mach numbers and angle of attacks are ascending
        boolean isMachAscending = Ordering.natural().isOrdered(machList);
        boolean isAngleOfAttackAscending = Ordering.natural().isOrdered(angleOfAttackList);
        if (!isMachAscending || !isAngleOfAttackAscending) {
            throw new IllegalArgumentException("Mach or angle of attack not ascending order.");
        }

        this.interpolator = interpolator;

        Iterator<BigDecimal> coefficientsIterator = coefficientsList.listIterator();
        for (BigDecimal angleOfAttack : angleOfAttackList) {
            for (BigDecimal machNumber : machList) {
                coefficients.put(
                    new CoefficientsKeyImpl(
                        machNumber,
                        angleOfAttack
                    ),
                    coefficientsIterator.next()
                );
            }
        }
        if (coefficientsIterator.hasNext()) {
            throw new IllegalArgumentException("The number of coefficients does not match the number of mach numbers and angle of attacks we should have values for");
        }
    }

    @Override
    public Map<CoefficientsKey, BigDecimal> getCoefficients() {
        return coefficients;
    }

    @Override
    public BigDecimal getCoefficient(CoefficientsKey key) {
        return coefficients.get(key);
    }

    @Override
    public BigDecimal getCoefficientInterpolated(CoefficientsKey key) {
        return interpolator.interpolate(
            key,
            coefficients
        );
    }

    @Override
    public String toString() {
        String value = "---------------------\n";
        for (Map.Entry<CoefficientsKey, BigDecimal> entry : coefficients.entrySet()) {
            value += String.format(
                "%s, Coefficient: %.2f\n",
                entry.getKey(),
                entry.getValue()
            );
        }
        value += "--------------------\n";
        return value;
    }

    @Override
    public List<CoefficientsMapRow> getList() {
        List<CoefficientsMapRow> list = new ArrayList<>();
        for (Map.Entry<CoefficientsKey, BigDecimal> entry : coefficients.entrySet()) {
            list.add(
                new CoefficientsMapRowImpl(
                    entry.getKey(),
                    entry.getValue()
                )
            );
        }
        return list;
    }

}
