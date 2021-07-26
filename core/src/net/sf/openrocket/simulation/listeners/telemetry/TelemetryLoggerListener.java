package net.sf.openrocket.simulation.listeners.telemetry;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

/**
 * Class that logs out telemetry to a CSV file every step of the simulation.
 */
public class TelemetryLoggerListener extends AbstractSimulationListener {

    private TelemetryFacade telemetryFacade;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    @Override
    public void startSimulation(SimulationStatus status) throws
        SimulationException {
        System.out.println("Beginning Simulation...");
        telemetryFacade = new TelemetryFacadeImpl(
            status.getSimulationConditions().getAerodynamicCalculator()
        );
    }

    @Override
    public void endSimulation(
        SimulationStatus status,
        SimulationException exception
    ) {
        System.out.println("Ending Simulation.");

        try {
            System.out.println("Printing Telemetry to CSV...");
            telemetryFacade.printTelemetry(
                String.format(
                    "%s_%s_%s_%s",
                    dateFormat.format(new Date()),
                    status.getFlightConfiguration().getName(),
                    telemetryFacade.getAerodynamicCalculator().toString(),
                    telemetryFacade.getAerodynamicEquationsName()
                )
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(
            String.format(
                "Finished printing Telemetry to %s",
                telemetryFacade.getTelemetryFile().getAbsolutePath()
            )
        );
    }

//    @Override
//    public boolean preStep(SimulationStatus status) throws
//        SimulationException {
//        return false;
//    }

    @Override
    public void postStep(SimulationStatus status) throws
        SimulationException {
    }

    @Override
    public AerodynamicForces postAerodynamicCalculation(SimulationStatus status, AerodynamicForces forces) throws SimulationException {
        telemetryFacade
            .getTelemetryList()
            .addTelemetryAerodynamicForces(
                new TelemetryStatusImpl(status),
                new TelemetryAerodynamicForcesImpl(forces)
            );
        return null;
    }

    @Override
    public FlightConditions postFlightConditions(SimulationStatus status, FlightConditions flightConditions) throws SimulationException {
        telemetryFacade
            .getTelemetryList()
            .addTelemetryFlightConditions(
                new TelemetryStatusImpl(status),
                new TelemetryFlightConditionsImpl(flightConditions)
            );
        return null;
    }

}
