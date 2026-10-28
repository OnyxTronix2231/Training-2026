
package frc.robot.subsystems.Conveyance;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

import frc.robot.lib.OnyxMotorOutputs;
import frc.robot.lib.VisualizationLib;
import frc.robot.visualization.VisualizedSubsystem;

import static frc.robot.subsystems.Conveyance.ConveyanceConstants.*;

public class ConveyanceIOSimulation implements ConveyanceIO {

    private final TalonFX motor;
    private final DCMotorSim simulatedMotor;

    private final OnyxMotorOutputs masterMotorOutputs;
    private final OnyxMotorOutputs followerMotorOutputs;

    public ConveyanceIOSimulation() {
        motor = new TalonFX(SIMULATION_CONVEYANCE_MASTER_MOTOR_ID);

        DCMotor gearbox =
                DCMotor.getKrakenX60(SIMULATION_CONVEYANCE_NUM_OF_MOTORS);

        simulatedMotor = new DCMotorSim(
                LinearSystemId.createDCMotorSystem(
                        gearbox,
                        SingleJointedArmSim.estimateMOI(
                                SIMULATION_CONVEYANCE_LENGTH_METERS,
                                SIMULATION_CONVEYANCE_MASS_KG
                        ),
                        1.0
                ),
                gearbox
        );
        masterMotorOutputs = new OnyxMotorOutputs(
                motor,
                CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_MASTER_MOTOR_NAME, OnyxMotorOutputs.CanBus.RIO;
        );
        followerMotorOutputs = new OnyxMotorOutputs();
        motor.getConfigurator().apply(getTalonFXConfiguration());
    }
    private TalonFXConfiguration getTalonFXConfiguration() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted =
                InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        return config;
    }
    public class ConveyanceVisualization extends VisualizedSubsystem {
        private final VisualizationLib.Wheel wheel;
        public ConveyanceVisualization() {
            wheel = new VisualizationLib.Wheel(CONVEYANCE_ROOT, CONVEYANCE_SUBSYSTEM_NAME, CONVEYANCE_LIGAMENT_COLOR;

        }
        @Override
        protected void updateVisualization() {
        }
    }
}
