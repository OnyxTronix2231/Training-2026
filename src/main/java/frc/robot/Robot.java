// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.lib.SignalManager;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.conveyance.ConveyanceIO;
import frc.robot.subsystems.conveyance.ConveyanceIOSimulation;
import frc.robot.visualization.ConveyanceVisualization;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import static frc.robot.subsystems.conveyance.ConveyanceConstants.ConveyanceSimulationConstants.CONVEYANCE_DUTY_CYCLE_SPEED;
import static frc.robot.visualization.VisualizedSubsystem.updateVisualizations;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends LoggedRobot {

    private ConveyanceIOSimulation conveyanceIOSimulation;
    private ConveyanceIO.ConveyanceOutputs conveyanceOutputs;
    @Override
    public void robotInit() {
        initializeLogger();
        Superstructure.init();
        conveyanceOutputs = new ConveyanceIO.ConveyanceOutputs();
        conveyanceIOSimulation = new ConveyanceIOSimulation();
        new ConveyanceVisualization(conveyanceIOSimulation);

    }

    /**
     * This function is run once each time the robot enters autonomous mode.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This function is called once each time the robot enters teleoperated mode.
     */
    @Override
    public void teleopInit() {
        Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.DEFAULT_STATE);
    }

    @Override
    public void disabledInit() {
        Superstructure.getInstance().setWantedSuperState(Superstructure.WantedSuperState.STOPPED);
    }

    /**
     * This function is called periodically during teleoperated mode.
     */
    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void robotPeriodic() {
        SignalManager.refreshAll();
        CommandScheduler.getInstance().run();

        conveyanceIOSimulation.setDutyCycle(CONVEYANCE_DUTY_CYCLE_SPEED);
        conveyanceIOSimulation.updateOutputs(conveyanceOutputs);

        if (isSimulation()) {
            updateVisualizations();
        }
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    @Override
    public void testInit() {
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }

    private void initializeLogger() {
        Logger.recordMetadata("JackInTheOnyx", "MyProject"); // Set a metadata value

        if (isReal()) {
            Logger.addDataReceiver(new WPILOGWriter("u/kit_logs"));
            new PowerDistribution(1, PowerDistribution.ModuleType.kRev); // Enables power distribution logging
        } else
            Logger.addDataReceiver(new WPILOGWriter());

        Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables

        Logger.start();
    }

    public static final Joystick joystick = new Joystick(0);
}
