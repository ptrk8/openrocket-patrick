package net.sf.openrocket.aerodynamics;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.google.gson.antations.SerializedName;
////import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class AerodynamicCoefficientsImpl implements AerodynamicCoefficients {
//    @JSONField(alternateNames = {"mach_number"})
    @SerializedName("mach_number")
//    @JsonProperty("mach_number")
    public List<BigDecimal> machList;

//    @JSONField(alternateNames = {"angle_of_attack"})
    @SerializedName("angle_of_attack")
//    @JsonProperty("angle_of_attack")
    public List<BigDecimal> angleOfAttackList;

//    @JSONField(alternateNames = {"c_L"})
    @SerializedName("c_L")
//    @JsonProperty("c_L")
    public List<BigDecimal> coefficientLiftList;

//    @JSONField(alternateNames = {"c_m"})
    @SerializedName("c_m")
//    @JsonProperty("c_m")
    public List<BigDecimal> coefficientPitchingMomentList;

//    @JSONField(alternateNames = {"c_l"})
    @SerializedName("c_l")
//    @JsonProperty("c_l")
    public List<BigDecimal> coefficientRollingMomentList;

//    @JSONField(alternateNames = {"c_d"})
    @SerializedName("c_d")
//    @JsonProperty("c_d")
    public List<BigDecimal> coefficientDragList;

//    @JSONField(alternateNames = {"c_y"})
    @SerializedName("c_y")
//    @JsonProperty("c_y")
    public List<BigDecimal> coefficientAxialForceList;

//    @JSONField(alternateNames = {"c_n"})
    @SerializedName("c_n")
//    @JsonProperty("c_n")
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
