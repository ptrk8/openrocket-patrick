package net.sf.openrocket.simulation.listeners.telemetry;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.sf.openrocket.aerodynamics.AerodynamicForces;

public class TelemetryAerodynamicForcesImpl implements TelemetryAerodynamicForces {

    private AerodynamicForces forces;

    public TelemetryAerodynamicForcesImpl(AerodynamicForces forces) {
        this.forces = forces.clone();
    }

    @Override
    public Map<String, String> getTelemetryDataMap() {
        DecimalFormat df = new DecimalFormat("#.########");
        df.setRoundingMode(RoundingMode.HALF_UP);
        Map<String, String> dataMap = new LinkedHashMap<>();
        dataMap.put("CP_x", df.format(forces.getCP().x));
        dataMap.put("CP_y", df.format(forces.getCP().y));
        dataMap.put("CP_z", df.format(forces.getCP().z));
        dataMap.put("CNa", df.format(forces.getCNa()));
        dataMap.put("CN", df.format(forces.getCN()));
        dataMap.put("Cm", df.format(forces.getCm()));
        dataMap.put("Cside", df.format(forces.getCside()));
        dataMap.put("Cyaw", df.format(forces.getCyaw()));
        dataMap.put("Croll", df.format(forces.getCroll()));
        dataMap.put("Croll_damp", df.format(forces.getCrollDamp()));
        dataMap.put("Croll_force", df.format(forces.getCrollForce()));
        dataMap.put("Caxial", df.format(forces.getCaxial()));
        dataMap.put("CD", df.format(forces.getCD()));
        dataMap.put("pressure_CD", df.format(forces.getPressureCD()));
        dataMap.put("base_CD", df.format(forces.getBaseCD()));
        dataMap.put("friction_CD", df.format(forces.getFrictionCD()));
        dataMap.put("pitch_damping_moment", df.format(forces.getPitchDampingMoment()));
        dataMap.put("yaw_damping_moment", df.format(forces.getYawDampingMoment()));
        return dataMap;
    }
}
