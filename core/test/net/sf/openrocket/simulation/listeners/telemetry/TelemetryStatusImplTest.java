package net.sf.openrocket.simulation.listeners.telemetry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.util.Coordinate;
import org.junit.Test;

public class TelemetryStatusImplTest {

    @Test
    public void equalsShouldReturnTrueIfEverythingEqual() {

        double timeA = 1.23;
        Coordinate positionA = new Coordinate(1.0, 2.0, 3.0, 4.0);
        Coordinate velocityA = new Coordinate(3.0, 2.0, 1.0, 0.2);

        SimulationStatus statusA = mock(SimulationStatus.class);
        when(statusA.getSimulationTime()).thenReturn(timeA);
        when(statusA.getRocketPosition()).thenReturn(positionA);
        when(statusA.getRocketVelocity()).thenReturn(velocityA);

        double timeB = 1.23;
        Coordinate positionB = new Coordinate(1.0, 2.0, 3.0, 4.0);
        Coordinate velocityB = new Coordinate(3.0, 2.0, 1.0, 0.2);


        SimulationStatus statusB = mock(SimulationStatus.class);
        when(statusB.getSimulationTime()).thenReturn(timeB);
        when(statusB.getRocketPosition()).thenReturn(positionB);
        when(statusB.getRocketVelocity()).thenReturn(velocityB);

        assertEquals(
            new TelemetryStatusImpl(statusA),
            new TelemetryStatusImpl(statusB)
        );
    }

    @Test
    public void equalsShouldReturnFalseIfSomethingNotEqual() {

        double timeA = 1.23;
        Coordinate positionA = new Coordinate(1.0, 2.0, 3.0, 4.0);
        Coordinate velocityA = new Coordinate(3.0, 2.0, 1.0, 0.2);

        SimulationStatus statusA = mock(SimulationStatus.class);
        when(statusA.getSimulationTime()).thenReturn(timeA);
        when(statusA.getRocketPosition()).thenReturn(positionA);
        when(statusA.getRocketVelocity()).thenReturn(velocityA);

        double timeB = 1.23;
        Coordinate positionB = new Coordinate(1.0, 2.0, 3.0, 4.0);
        Coordinate velocityB = new Coordinate(3.0, 2.0, 1.0, 0.1);


        SimulationStatus statusB = mock(SimulationStatus.class);
        when(statusB.getSimulationTime()).thenReturn(timeB);
        when(statusB.getRocketPosition()).thenReturn(positionB);
        when(statusB.getRocketVelocity()).thenReturn(velocityB);

        assertNotEquals(
            new TelemetryStatusImpl(statusA),
            new TelemetryStatusImpl(statusB)
        );
    }

    @Test
    public void getTelemetryDataMapShouldReturnCorrectMap() {

        double timeA = 1.23;
        Coordinate positionA = new Coordinate(1.0, 2.0, 3.0, 4.0);
        Coordinate velocityA = new Coordinate(3.0, 2.0, 1.0, 0.2);

        SimulationStatus statusA = mock(SimulationStatus.class);
        when(statusA.getSimulationTime()).thenReturn(timeA);
        when(statusA.getRocketPosition()).thenReturn(positionA);
        when(statusA.getRocketVelocity()).thenReturn(velocityA);

        TelemetryStatusImpl status = new TelemetryStatusImpl(statusA);

        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("time", "1.23");
        expectedMap.put("position_x", "1");
        expectedMap.put("position_y", "2");
        expectedMap.put("position_z", "3");
        expectedMap.put("velocity_x", "3");
        expectedMap.put("velocity_y", "2");
        expectedMap.put("velocity_z", "1");

        assertEquals(
            expectedMap,
            status.getTelemetryDataMap()
        );
    }
}