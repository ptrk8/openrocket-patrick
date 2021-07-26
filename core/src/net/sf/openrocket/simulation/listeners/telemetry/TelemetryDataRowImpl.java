package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.ArrayList;
import java.util.List;

public class TelemetryDataRowImpl implements TelemetryDataRow {

    private TelemetryDataSegment status;
    private TelemetryDataSegment forces;
    private TelemetryDataSegment conditions;

    public TelemetryDataRowImpl(
        TelemetryDataSegment status,
        TelemetryDataSegment forces,
        TelemetryDataSegment conditions
    ) {
        if (status == null || forces == null || conditions == null) {
            throw new IllegalArgumentException("Telemetry data row has a null element");
        }
        this.status = status;
        this.forces = forces;
        this.conditions = conditions;
    }

    @Override
    public TelemetryDataSegment getStatus() {
        return status;
    }

    @Override
    public TelemetryDataSegment getForces() {
        return forces;
    }

    @Override
    public TelemetryDataSegment getConditions() {
        return conditions;
    }

    @Override
    public List<String> getHeaders() {
        List<String> statusHeaders = new ArrayList<>(status.getTelemetryDataMap().keySet());
        List<String> forcesHeaders = new ArrayList<>(forces.getTelemetryDataMap().keySet());
        List<String> conditionsHeaders = new ArrayList<>(conditions.getTelemetryDataMap().keySet());

        List<String> headers = new ArrayList<>();
        headers.addAll(statusHeaders);
        headers.add("");
        headers.addAll(forcesHeaders);
        headers.add("");
        headers.addAll(conditionsHeaders);
        return headers;
    }

    @Override
    public List<String> getValues() {
        List<String> statusValues = new ArrayList<>(status.getTelemetryDataMap().values());
        List<String> forcesValues = new ArrayList<>(forces.getTelemetryDataMap().values());
        List<String> conditionsValues = new ArrayList<>(conditions.getTelemetryDataMap().values());

        List<String> values = new ArrayList<>();
        values.addAll(statusValues);
        values.add("");
        values.addAll(forcesValues);
        values.add("");
        values.addAll(conditionsValues);
        return values;
    }
}
