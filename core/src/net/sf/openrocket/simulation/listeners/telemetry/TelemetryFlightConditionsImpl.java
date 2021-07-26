package net.sf.openrocket.simulation.listeners.telemetry;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.openrocket.aerodynamics.FlightConditions;

public class TelemetryFlightConditionsImpl implements TelemetryFlightConditions {

    private final Map<String, String> dataMap = new LinkedHashMap<>();

    public TelemetryFlightConditionsImpl(FlightConditions conditions) {
        conditions = conditions.clone();

        DecimalFormat df = new DecimalFormat("#.########");
        df.setRoundingMode(RoundingMode.HALF_UP);

        dataMap.put("aoa_rad", df.format(conditions.getAOA()));
        dataMap.put("aoa_deg", df.format(Math.toDegrees(conditions.getAOA())));
        dataMap.put("mach", df.format(conditions.getMach()));
        dataMap.put("beta", df.format(conditions.getBeta()));
        dataMap.put("velocity", df.format(conditions.getVelocity()));
        dataMap.put("pitch_centre_x", df.format(conditions.getPitchCenter().x));
        dataMap.put("pitch_centre_y", df.format(conditions.getPitchCenter().y));
        dataMap.put("pitch_centre_z", df.format(conditions.getPitchCenter().z));
        dataMap.put("roll_rate", df.format(conditions.getRollRate()));
        dataMap.put("yaw_rate", df.format(conditions.getYawRate()));
    }

    @Override
    public Map<String, String> getTelemetryDataMap() {
        return dataMap;
    }

    @Override
    public List<String> getHeaders() {
        return new ArrayList<>(dataMap.keySet());
    }

    @Override
    public List<String> getValues() {
        return new ArrayList<>(dataMap.values());
    }
}
