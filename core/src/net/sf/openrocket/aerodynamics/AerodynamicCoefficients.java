package net.sf.openrocket.aerodynamics;

import java.math.BigDecimal;
import java.util.List;

public interface AerodynamicCoefficients {

    List<BigDecimal> getMachList();

    List<BigDecimal> getAngleOfAttackList();

    List<BigDecimal> getCoefficientLiftList();

    List<BigDecimal> getCoefficientPitchingMomentList();

    List<BigDecimal> getCoefficientRollingMomentList();

    List<BigDecimal> getCoefficientDragList();

    List<BigDecimal> getCoefficientAxialForceList();

    List<BigDecimal> getCoefficientSideForceList();

    BigDecimal getAngleOfAttackMin();

    BigDecimal getAngleOfAttackMax();
}
