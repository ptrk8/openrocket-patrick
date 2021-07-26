package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.util.Coordinate;

public class TelemetryStatusImpl implements TelemetryStatus {

    private double time;
    private Coordinate position;
    private Coordinate velocity;

    public TelemetryStatusImpl(SimulationStatus status) {
        time = status.getSimulationTime();
        position = status.getRocketPosition().clone();
        velocity = status.getRocketVelocity().clone();
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public Coordinate getVelocity() {
        return velocity;
    }

    @Override
    public Map<String, String> getTelemetryDataMap() {
        Map<String, String> dataMap = new LinkedHashMap<>();
        dataMap.put("time", String.valueOf(getTime()));
        dataMap.put("position_x", String.valueOf(position.x));
        dataMap.put("position_y", String.valueOf(position.y));
        dataMap.put("position_z", String.valueOf(position.z));
        dataMap.put("velocity_x", String.valueOf(velocity.x));
        dataMap.put("velocity_y", String.valueOf(velocity.y));
        dataMap.put("velocity_z", String.valueOf(velocity.z));
        return dataMap;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TelemetryStatusImpl)) {
            return false;
        }
        TelemetryStatusImpl other = (TelemetryStatusImpl) o;

        return Double.compare(getTime(), other.getTime()) == 0 &&
            getPosition().equals(other.getPosition()) &&
            getVelocity().equals(other.getVelocity());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.valueOf(getTime()).hashCode();
        result = 31 * result + getPosition().hashCode();
        result = 31 * result + getVelocity().hashCode();
        return result;
    }

    @Override
    public List<String> getHeaders() {
        return new ArrayList<>(getTelemetryDataMap().keySet());
    }

    @Override
    public List<String> getValues() {
        return new ArrayList<>(getTelemetryDataMap().values());
    }
}
