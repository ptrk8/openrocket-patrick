package net.sf.openrocket.simulation.listeners.telemetry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TelemetryListImpl implements TelemetryList {

    private final Map<TelemetryStatus, TelemetryBasket> telemetryBasketMap = new LinkedHashMap<>();

    public TelemetryListImpl() {
    }

    @Override
    public Map<TelemetryStatus, TelemetryBasket> getTelemetryBasketMap() {
        return telemetryBasketMap;
    }

    @Override
    public void addTelemetryAerodynamicForces(
        TelemetryStatus status,
        TelemetryAerodynamicForces forces
    ) {
        TelemetryBasket basket = telemetryBasketMap.get(status);
        if (basket != null) {
            basket.setTelemetryAerodynamicForces(forces);
        } else {
            telemetryBasketMap.put(
                status,
                new TelemetryBasketImpl(forces)
            );
        }
    }

    @Override
    public void addTelemetryFlightConditions(
        TelemetryStatus status,
        TelemetryFlightConditions conditions
    ) {
        TelemetryBasket basket = telemetryBasketMap.get(status);
        if (basket != null) {
            basket.setTelemetryFlightConditions(conditions);
        } else {
            telemetryBasketMap.put(
                status,
                new TelemetryBasketImpl(conditions)
            );
        }
    }

    @Override
    public List<TelemetryListRow> getList() {
        List<TelemetryListRow> telemetryDataRowList = new ArrayList<>();
        for (Map.Entry<TelemetryStatus, TelemetryBasket> entry : telemetryBasketMap.entrySet()) {
            TelemetryStatus status = entry.getKey();
            TelemetryBasket basket = entry.getValue();
            telemetryDataRowList.add(
                new TelemetryListRowImpl(
                    status,
                    basket
                )
            );
        }
        return telemetryDataRowList;
    }



}
