package net.sf.openrocket.simulation.listeners.telemetry;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.util.Coordinate;
import org.junit.Test;

public class TelemetryDataRowImplTest {

    @Test
    public void getHeadersShouldReturnHeadersInCorrectOrder() {
        double timeA = 1.23;
        Coordinate positionA = new Coordinate(1.0, 2.0, 3.0, 4.0);
        Coordinate velocityA = new Coordinate(3.0, 2.0, 1.0, 0.2);

        SimulationStatus statusA = mock(SimulationStatus.class);
        when(statusA.getSimulationTime()).thenReturn(timeA);
        when(statusA.getRocketPosition()).thenReturn(positionA);
        when(statusA.getRocketVelocity()).thenReturn(velocityA);

        TelemetryStatus status = new TelemetryStatusImpl(statusA);
        TelemetryAerodynamicForces forces = mock(TelemetryAerodynamicForces.class);
        TelemetryFlightConditions conditions = mock(TelemetryFlightConditions.class);

        TelemetryDataRow dataRow = new TelemetryDataRowImpl(
            status,
            forces,
            conditions
        );

        assertEquals(
            Arrays.asList(
                "time",
                "position_x",
                "position_y",
                "position_z",
                "velocity_x",
                "velocity_y",
                "velocity_z"
            ),
            dataRow.getHeaders()
        );

    }
}