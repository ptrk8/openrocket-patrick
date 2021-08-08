package net.sf.openrocket.aerodynamics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import net.sf.openrocket.aerodynamics.coefficients.CoefficientsInterpolatorBilinear;
import org.junit.BeforeClass;
import org.junit.Test;

public class AerodynamicCoefficientsFacadeImplTest {

    public static AerodynamicCoefficients aerodynamicCoefficientsJson;
    public final double delta = 0.0000001;

    @BeforeClass
    public static void beforeAll() throws
        IOException {
        aerodynamicCoefficientsJson = new ObjectMapper().readValue(
            Resources.toString(
                Resources.getResource(
                    "test/coefficients/coefficients.json"
                ),
                StandardCharsets.UTF_8
            ),
            AerodynamicCoefficientsImpl.class
        );
    }

    @Test
    public void getCoefficientLiftShouldInterpolateMiddleCorrectly() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double machNumber = 0.35;
        double angleOfAttack = 1.5;

        double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientYawMoment = coefficients.getCoefficientDrag(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
            machNumber,
            angleOfAttack
        );

        // C_L
        assertEquals(-0.26117667275, actualCoefficientNormalForce, delta);
        // C_m
        assertEquals(-0.9228306405, actualCoefficientPitchingMoment, delta);
        // C_l
        assertEquals(-0.0000644421, actualCoefficientRollingMoment, delta);
        // C_n
        assertEquals(0, actualCoefficientYawMoment, delta);
        // C_a
        assertEquals(0, actualCoefficientAxialForce, delta);
        // C_y
        assertEquals(0, actualCoefficientSideForce, delta);
    }

    @Test
    public void getCoefficientLiftShouldInterpolateSideCorrectly() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double machNumber = 0.38;
        double angleOfAttack = -0.97;

        double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientYawMoment = coefficients.getCoefficientDrag(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
            machNumber,
            angleOfAttack
        );

        // C_N
        assertEquals(0.164801601154, actualCoefficientNormalForce, delta);
        // C_m
        assertEquals(0.573195243184, actualCoefficientPitchingMoment, delta);
        // C_l
        assertEquals(-0.000067513728, actualCoefficientRollingMoment, delta);
        // C_n
        assertEquals(0, actualCoefficientYawMoment, delta);
        // C_a
        assertEquals(0, actualCoefficientAxialForce, delta);
        // C_y
        assertEquals(0, actualCoefficientSideForce, delta);
    }

    @Test
    public void getCoefficientLiftShouldInterpolateAngleOfAttackOnlyCorrectly() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double machNumber = 0.6;
        double angleOfAttack = -1.3;

        double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientYawMoment = coefficients.getCoefficientYawMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
            machNumber,
            angleOfAttack
        );

        // C_N
        assertEquals(0.2254051912, actualCoefficientNormalForce, delta);
        // C_m
        assertEquals(0.8067427936, actualCoefficientPitchingMoment, delta);
        // C_n
        assertEquals(0.0, actualCoefficientYawMoment, delta);
        // C_l
        assertEquals(-0.000032578301, actualCoefficientRollingMoment, delta);
        // C_a
        assertEquals(0, actualCoefficientAxialForce, delta);
        // C_y
        assertEquals(0, actualCoefficientSideForce, delta);
    }

    @Test
    public void getCoefficientLiftShouldInterpolateMachNumberOnlyCorrectly() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double machNumber = 0.66;
        double angleOfAttack = -5;

        double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientYawMoment = coefficients.getCoefficientDrag(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
            machNumber,
            angleOfAttack
        );

        // C_N
        assertEquals(0.9304426248000001, actualCoefficientNormalForce, delta);
        // C_m
        assertEquals(3.4363543058, actualCoefficientPitchingMoment, delta);
        // C_l
        assertEquals(0.00046811920000000004, actualCoefficientRollingMoment, delta);
        // C_n
        assertEquals(0, actualCoefficientYawMoment, delta);
        // C_a
        assertEquals(0, actualCoefficientAxialForce, delta);
        // C_y
        assertEquals(0, actualCoefficientSideForce, delta);
    }

    @Test
    public void getCoefficientLiftShouldNotInterpolateForExactValues() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double machNumber = 0.6;
        double angleOfAttack = 3;

        double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientYawMoment = coefficients.getCoefficientDrag(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
            machNumber,
            angleOfAttack
        );
        double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
            machNumber,
            angleOfAttack
        );

        // C_N
        assertEquals(-0.5471648751, actualCoefficientNormalForce, delta);
        // C_m
        assertEquals(-2.019845759, actualCoefficientPitchingMoment, delta);
        // C_l
        assertEquals(-0.0000458939, actualCoefficientRollingMoment, delta);
        // C_n
        assertEquals(0, actualCoefficientYawMoment, delta);
        // C_a
        assertEquals(0, actualCoefficientAxialForce, delta);
        // C_y
        assertEquals(0, actualCoefficientSideForce, delta);
    }

    @Test
    public void getCoefficientLiftShouldThrowErrIfMachOutOfRange() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double angleOfAttack = 3;
        double[] invalidMachNumbers = {
            -1000,
            -0.1,
            1.1,
            1000
        };

        for (double machNumber : invalidMachNumbers) {
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
                    machNumber,
                    angleOfAttack
                );
            });
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
                    machNumber,
                    angleOfAttack
                );
            });

            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
                    machNumber,
                    angleOfAttack
                );
            });
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
                    machNumber,
                    angleOfAttack
                );
            });
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
                    machNumber,
                    angleOfAttack
                );
            });
        }
    }

    @Test
    public void getCoefficientLiftShouldThrowErrIfAngleOfAttackOutOfRange() {
        AerodynamicCoefficientsFacade coefficients = new AerodynamicCoefficientsFacadeImpl(
            aerodynamicCoefficientsJson,
            new CoefficientsInterpolatorBilinear()
        );
        double machNumber = 0.5;

        double[] invalidAnglesOfAttack = {
            -5.1,
            5.1,
            10,
            -10
        };

        for (double angleOfAttack : invalidAnglesOfAttack) {
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientNormalForce = coefficients.getCoefficientNormalForce(
                    machNumber,
                    angleOfAttack
                );
            });
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientPitchingMoment = coefficients.getCoefficientPitchingMoment(
                    machNumber,
                    angleOfAttack
                );
            });

            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientRollingMoment = coefficients.getCoefficientRollingMoment(
                    machNumber,
                    angleOfAttack
                );
            });
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientAxialForce = coefficients.getCoefficientAxialForce(
                    machNumber,
                    angleOfAttack
                );
            });
            assertThrows(IllegalArgumentException.class, () -> {
                double actualCoefficientSideForce = coefficients.getCoefficientSideForce(
                    machNumber,
                    angleOfAttack
                );
            });
        }
    }
}
