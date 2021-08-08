package net.sf.openrocket.aerodynamics;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class AerodynamicCoefficientsImpl implements AerodynamicCoefficients {

    @JsonProperty("descriptor")
    public String descriptor;

    @JsonProperty("mach_number")
    public List<BigDecimal> machList;

    @JsonProperty("angle_of_attack")
    public List<BigDecimal> angleOfAttackList;

    @JsonProperty("c_N")
    public List<BigDecimal> coefficientNormalForceList;

    @JsonProperty("c_m")
    public List<BigDecimal> coefficientPitchingMomentList;

    @JsonProperty("c_l")
    public List<BigDecimal> coefficientRollingMomentList;

    @JsonProperty("c_a")
    public List<BigDecimal> coefficientAxialForceList;

    @JsonProperty("c_y")
    public List<BigDecimal> coefficientSideForceList;

    @JsonProperty("c_n")
    public List<BigDecimal> coefficientYawMomentList;

    @Override
    public List<BigDecimal> getMachList() {
        return machList;
    }

    @Override
    public List<BigDecimal> getAngleOfAttackList() {
        return angleOfAttackList;
    }

    @Override
    public List<BigDecimal> getCoefficientNormalForceList() {
        return coefficientNormalForceList;
    }

    @Override
    public List<BigDecimal> getCoefficientPitchingMomentList() {
        return coefficientPitchingMomentList;
    }

    @Override
    public List<BigDecimal> getCoefficientRollingMomentList() {
        return coefficientRollingMomentList;
    }

    @Override
    public List<BigDecimal> getCoefficientAxialForceList() {
        return coefficientAxialForceList;
    }

    @Override
    public List<BigDecimal> getCoefficientSideForceList() {
        return coefficientSideForceList;
    }

    @Override
    public List<BigDecimal> getCoefficientYawMomentList() {
        return coefficientYawMomentList;
    }

    @Override
    public BigDecimal getAngleOfAttackMin() {
        return angleOfAttackList
            .stream()
            .min(Comparator.naturalOrder())
            .orElse(null);
    }

    @Override
    public BigDecimal getAngleOfAttackMax() {
        return angleOfAttackList
            .stream()
            .max(Comparator.naturalOrder())
            .orElse(null);
    }

    @Deprecated
    @JsonProperty("c_L")
    public List<BigDecimal> coefficientLiftList;

    @Deprecated
    @JsonProperty("c_d")
    public List<BigDecimal> coefficientDragList;

    @Deprecated
    @JsonProperty("c_na")
    public List<BigDecimal> coefficientSideForceAlphaDerivativeList;

    @Deprecated
    @JsonProperty("c_yb")
    public List<BigDecimal> coefficientAxialForceBetaDerivativeList;

    @Deprecated
    @JsonProperty("c_nb")
    public List<BigDecimal> coefficientSideForceBetaDerivativeList;

    @Deprecated
    @JsonProperty("c_ma")
    public List<BigDecimal> coefficientPitchingMomentAlphaDerivativeList;

    @Deprecated
    @JsonProperty("c_lb")
    public List<BigDecimal> coefficientRollingMomentBetaDerivativeList;

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientLiftList() {
        return coefficientLiftList;
    }

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientDragList() {
        return coefficientDragList;
    }

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientSideForceAlphaDerivativeList() {
        return coefficientSideForceAlphaDerivativeList;
    }

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientAxialForceBetaDerivativeList() {
        return coefficientAxialForceBetaDerivativeList;
    }

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientSideForceBetaDerivativeList() {
        return coefficientSideForceBetaDerivativeList;
    }

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientPitchingMomentAlphaDerivativeList() {
        return coefficientPitchingMomentAlphaDerivativeList;
    }

    @Deprecated
    @Override
    public List<BigDecimal> getCoefficientRollingMomentBetaDerivativeList() {
        return coefficientRollingMomentBetaDerivativeList;
    }

}
