package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.ArrayList;
import java.util.List;

public class TelemetryListRowImpl implements TelemetryListRow {

    private WriterRowComponent status;
    private WriterRowComponent basket;

    public TelemetryListRowImpl(
        WriterRowComponent status,
        WriterRowComponent basket
    ) {
        this.status = status;
        this.basket = basket;
    }

    @Override
    public List<String> getHeaders() {
        List<String> headers = new ArrayList<>(status.getHeaders());
        headers.add("");
        headers.addAll(basket.getHeaders());
        return headers;
    }

    @Override
    public List<String> getValues() {
        List<String> values = new ArrayList<>(status.getValues());
        values.add("");
        values.addAll(basket.getValues());
        return values;
    }
}
