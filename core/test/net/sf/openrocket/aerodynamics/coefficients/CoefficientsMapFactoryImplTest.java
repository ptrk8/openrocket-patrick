package net.sf.openrocket.aerodynamics.coefficients;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CoefficientsMapFactoryImplTest {

    @Test
    public void makeShouldReturnACoefficientsMapWithAListOfZerosIfCoefficientListIsNull() {
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2),
            new BigDecimal(3)
        );

        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2),
            new BigDecimal(3)
        );

        CoefficientsMapFactory factory = new CoefficientsMapFactoryImpl(
            machList,
            angleOfAttackList,
            mock(CoefficientsInterpolator.class)
        );

        CoefficientsMap actualCoefficientsMap = factory.make(null);

        List<BigDecimal> actualCoefficients = new ArrayList<>(actualCoefficientsMap
            .getCoefficients()
            .values());

        assertEquals(
            Arrays.asList(
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0)
            ),
            actualCoefficients
        );
    }

    @Test
    public void makeShouldReturnACoefficientsMapWithTheCorrectValues() {
        List<BigDecimal> machList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2),
            new BigDecimal(3)
        );

        List<BigDecimal> angleOfAttackList = Arrays.asList(
            new BigDecimal(1),
            new BigDecimal(2),
            new BigDecimal(3)
        );

        List<BigDecimal> coefficients = Arrays.asList(
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

        CoefficientsMapFactory factory = new CoefficientsMapFactoryImpl(
            machList,
            angleOfAttackList,
            mock(CoefficientsInterpolator.class)
        );

        CoefficientsMap actualCoefficientsMap = factory.make(coefficients);

        Map<CoefficientsKey, BigDecimal> actualLinkedHashMap = actualCoefficientsMap.getCoefficients();

        Map<CoefficientsKey, BigDecimal> expectedLinkedHashMap = new LinkedHashMap<>();

        // The list is constructed so that it goes angleOfAttack first and then machNumber
        int index = 0;
        for (BigDecimal angleOfAttack : angleOfAttackList) {
            for (BigDecimal machNumber : machList) {
                expectedLinkedHashMap.put(
                    new CoefficientsKeyImpl(machNumber, angleOfAttack),
                    coefficients.get(index)
                );
                index++;
            }
        }
        assertEquals(
            expectedLinkedHashMap,
            actualLinkedHashMap
        );
    }

}