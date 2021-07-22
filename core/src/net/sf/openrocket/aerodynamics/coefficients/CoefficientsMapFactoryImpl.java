package net.sf.openrocket.aerodynamics.coefficients;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The concrete implementation of our coefficients map.
 */
public class CoefficientsMapFactoryImpl implements CoefficientsMapFactory {

    private List<BigDecimal> machList;
    private List<BigDecimal> angleOfAttackList;
    private CoefficientsInterpolator interpolator;

    /**
     * Constructor for our coefficients map factory.
     * @param machList The list of mach numbers for our coefficients.
     * @param angleOfAttackList The list of angle of attack numbers for our coefficients.
     * @param interpolator The interpolator we wish use.
     */
    public CoefficientsMapFactoryImpl(
        List<BigDecimal> machList,
        List<BigDecimal> angleOfAttackList,
        CoefficientsInterpolator interpolator
    ) {
        this.machList = machList;
        this.angleOfAttackList = angleOfAttackList;
        this.interpolator = interpolator;
    }

    @Override
    public CoefficientsMap make(List<BigDecimal> coefficientList) {
        // If the coefficientList is null, just use a list of zeros
        if (coefficientList == null) {
            long coefficientListLength = (long) machList.size() * angleOfAttackList.size();
            coefficientList = Stream.generate(() -> new BigDecimal(0))
                             .limit(coefficientListLength)
                             .collect(Collectors.toList());
        }
        return new CoefficientsMapImpl(
            machList,
            angleOfAttackList,
            coefficientList,
            interpolator
        );
    }
}
