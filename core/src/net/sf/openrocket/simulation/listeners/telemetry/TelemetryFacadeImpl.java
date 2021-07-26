package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sf.openrocket.aerodynamics.AerodynamicCalculator;
import net.sf.openrocket.aerodynamics.LookupCalculator;

public class TelemetryFacadeImpl implements TelemetryFacade {

    private final TelemetryList telemetryList;
    private String filePath;
    private final AerodynamicCalculator aerodynamicCalculator;

    public TelemetryFacadeImpl(AerodynamicCalculator aerodynamicCalculator) {
        telemetryList = new TelemetryListImpl();
        this.aerodynamicCalculator = aerodynamicCalculator;
    }

    @Override
    public TelemetryList getTelemetryList() {
        return telemetryList;
    }

    @Override
    public String getAerodynamicEquationsName() {
        if (aerodynamicCalculator instanceof LookupCalculator) {
            LookupCalculator lookupCalculator = (LookupCalculator) aerodynamicCalculator;
            return lookupCalculator.getAeroForceEquations().toString();
        }
        return "";
    }

    @Override
    public AerodynamicCalculator getAerodynamicCalculator() {
        return aerodynamicCalculator;
    }

    private List<TelemetryDataRow> getTelemetryDataList() {
        List<TelemetryDataRow> telemetryDataRowList = new ArrayList<>();
        for (Map.Entry<TelemetryStatus, TelemetryBasket> entry : telemetryList.getTelemetryBasketMap().entrySet()) {
            TelemetryStatus status = entry.getKey();
            TelemetryBasket basket = entry.getValue();
            telemetryDataRowList.add(
                new TelemetryDataRowImpl(
                    status,
                    basket.getTelemetryAerodynamicForces(),
                    basket.getTelemetryFlightConditions()
                )
            );
        }
        return telemetryDataRowList;
    }

    private List<List<String>> getCsvRows(List<TelemetryDataRow> telemetryDataRowList) {
        List<List<String>> csvRows = new ArrayList<>();
        int i = 0;
        for (TelemetryDataRow dataRow : telemetryDataRowList) {
            if (i == 0) {
                csvRows.add(dataRow.getHeaders());
            }
            csvRows.add(dataRow.getValues());
            i++;
        }
        return csvRows;
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void printToCsvFile(String fileName) throws
        FileNotFoundException {

        List<List<String>> csvRows = getCsvRows(getTelemetryDataList());

        String downloadsDir = String.format("%s/Downloads", System.getProperty("user.home"));
        String fileNameWithExtension = String.format("%s.csv", fileName);
        setFilePath(String.format("%s/%s", downloadsDir, fileNameWithExtension));

        File file = new File(getFilePath());
        PrintWriter printWriter = new PrintWriter(file);
        csvRows
            .stream()
            .map(row -> String.join(",", row))
            .forEach(printWriter::println);
    }
}
