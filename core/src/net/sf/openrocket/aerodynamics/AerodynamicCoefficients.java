package net.sf.openrocket.aerodynamics;

import java.math.BigDecimal;
import java.util.List;

public interface AerodynamicCoefficients {

    List<BigDecimal> getMachList();

    List<BigDecimal> getAngleOfAttackList();

    List<BigDecimal> getCoefficientNormalForceList();

    List<BigDecimal> getCoefficientPitchingMomentList();

    List<BigDecimal> getCoefficientRollingMomentList();

    List<BigDecimal> getCoefficientAxialForceList();

    List<BigDecimal> getCoefficientSideForceList();

    List<BigDecimal> getCoefficientYawMomentList();

    BigDecimal getAngleOfAttackMin();

    BigDecimal getAngleOfAttackMax();

    @Deprecated
    List<BigDecimal> getCoefficientDragList();

    @Deprecated
    List<BigDecimal> getCoefficientLiftList();

    @Deprecated
    List<BigDecimal> getCoefficientSideForceAlphaDerivativeList();

    @Deprecated
    List<BigDecimal> getCoefficientAxialForceBetaDerivativeList();

    @Deprecated
    List<BigDecimal> getCoefficientSideForceBetaDerivativeList();

    @Deprecated
    List<BigDecimal> getCoefficientPitchingMomentAlphaDerivativeList();

    @Deprecated
    List<BigDecimal> getCoefficientRollingMomentBetaDerivativeList();

}
