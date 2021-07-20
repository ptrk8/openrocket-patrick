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

    @JsonProperty("c_L")
    public List<BigDecimal> coefficientLiftList;

    @JsonProperty("c_m")
    public List<BigDecimal> coefficientPitchingMomentList;

    @JsonProperty("c_l")
    public List<BigDecimal> coefficientRollingMomentList;

    @JsonProperty("c_d")
    public List<BigDecimal> coefficientDragList;

    @JsonProperty("c_y")
    public List<BigDecimal> coefficientAxialForceList;

    @JsonProperty("c_n")
    public List<BigDecimal> coefficientSideForceList;

    @Override
    public List<BigDecimal> getMachList() {
        return machList;
    }

    @Override
    public List<BigDecimal> getAngleOfAttackList() {
        return angleOfAttackList;
    }

    @Override
    public List<BigDecimal> getCoefficientLiftList() {
        return coefficientLiftList;
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
    public List<BigDecimal> getCoefficientDragList() {
        return coefficientDragList;
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
    public BigDecimal getAngleOfAttackMin() {
        return angleOfAttackList
            .stream()
            .min(Comparator.naturalOrder())
            .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getAngleOfAttackMax() {
        return angleOfAttackList
            .stream()
            .max(Comparator.naturalOrder())
            .orElse(BigDecimal.ZERO);
    }
}
