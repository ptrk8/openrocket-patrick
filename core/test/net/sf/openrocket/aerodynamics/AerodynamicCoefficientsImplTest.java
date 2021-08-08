package net.sf.openrocket.aerodynamics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class AerodynamicCoefficientsImplTest {

    @Test
    public void shouldRepresentAPartialJson() throws
        IOException {
        AerodynamicCoefficients aerodynamicCoefficients = new ObjectMapper().readValue(
            Resources.toString(
                Resources.getResource(
                    "test/coefficients/coefficients.json"
                ),
                StandardCharsets.UTF_8
            ),
            AerodynamicCoefficientsImpl.class
        );

        int expectedMachListSize = 11;
        int expectedAngleOfAttackListSize = 11;
        int expectedCoefficientsListSize = expectedMachListSize * expectedAngleOfAttackListSize;

        assertEquals(
            expectedMachListSize,
            aerodynamicCoefficients.getMachList().size()
        );
        assertEquals(
            expectedAngleOfAttackListSize,
            aerodynamicCoefficients.getAngleOfAttackList().size()
        );
//        assertEquals(
//            expectedCoefficientsListSize,
//            aerodynamicCoefficients.getCoefficientLiftList().size()
//        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientPitchingMomentList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientRollingMomentList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientDragList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientAxialForceList().size()
        );
        assertNull(
            aerodynamicCoefficients.getCoefficientSideForceAlphaDerivativeList()
        );
        assertNull(
            aerodynamicCoefficients.getCoefficientAxialForceBetaDerivativeList()
        );
        assertNull(
            aerodynamicCoefficients.getCoefficientSideForceBetaDerivativeList()
        );
        assertNull(
            aerodynamicCoefficients.getCoefficientPitchingMomentAlphaDerivativeList()
        );
        assertNull(
            aerodynamicCoefficients.getCoefficientRollingMomentBetaDerivativeList()
        );
    }

    @Test
    public void shouldRepresentACompleteJson() throws
        IOException {
        AerodynamicCoefficients aerodynamicCoefficients = new ObjectMapper().readValue(
            Resources.toString(
                Resources.getResource(
                    "test/coefficients/coefficients-panel-full-radians.json"
                ),
                StandardCharsets.UTF_8
            ),
            AerodynamicCoefficientsImpl.class
        );

        int expectedMachListSize = 20;
        int expectedAngleOfAttackListSize = 6;
        int expectedCoefficientsListSize = expectedMachListSize * expectedAngleOfAttackListSize;

        assertEquals(
            expectedMachListSize,
            aerodynamicCoefficients.getMachList().size()
        );
        assertEquals(
            expectedAngleOfAttackListSize,
            aerodynamicCoefficients.getAngleOfAttackList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientLiftList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientPitchingMomentList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientRollingMomentList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientDragList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientAxialForceList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientSideForceAlphaDerivativeList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientAxialForceBetaDerivativeList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientSideForceBetaDerivativeList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientPitchingMomentAlphaDerivativeList().size()
        );
        assertEquals(
            expectedCoefficientsListSize,
            aerodynamicCoefficients.getCoefficientRollingMomentBetaDerivativeList().size()
        );

    }
}