package frc.robot.subsystems;

import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import java.lang.Math;

public class twoMotorsTest extends SubsystemBase {
    private final TalonFX motor1 = new TalonFX(19, "rio");
    private final TalonFX motor2 = new TalonFX(17, "canivore");
    private final VelocityTorqueCurrentFOC motor1VelocityRequest = new VelocityTorqueCurrentFOC(0).withSlot(0);
    private final MotionMagicVoltage motor2PositionRequest = new MotionMagicVoltage(0).withSlot(0);
    public twoMotorsTest() {
        var motor1Config = new TalonFXConfiguration();
        motor1Config.Slot0.kS = 5.0;
        motor1Config.Slot0.kV = 0.175;
        motor1Config.Slot0.kP = 10.0;
        var motor2Config = new TalonFXConfiguration();
        motor2Config.Slot0.kS = 0.2;
        motor2Config.Slot0.kP = 5.0;
        motor2Config.MotionMagic.MotionMagicCruiseVelocity = 100.0;
        motor2Config.MotionMagic.MotionMagicAcceleration = 100.0;
        motor2Config.MotionMagic.MotionMagicJerk = 0.0;
        motor2Config.MotionMagic.MotionMagicExpo_kV = 0.12;
        motor2Config.MotionMagic.MotionMagicExpo_kA = 0.1;
        motor1.getConfigurator().apply(motor1Config);
        motor2.getConfigurator().apply(motor2Config);
    }
    public void setMotorVelocity(double velocity) {
        motor1.setControl(motor1VelocityRequest.withVelocity(velocity));
    }
    public void setMotorPosition(double position) {
        motor2.setControl(motor2PositionRequest.withPosition(position));
    }
    public Command stop() {
        return runOnce(() -> {
            setMotorVelocity(0.0);
            setMotorPosition(0.0);
        });
    }
    public Command start() {
        return runOnce(() -> {
            setMotorVelocity(50.0);
            setMotorPosition(50.0);
        });
    }
    public Command collective() {
        return startEnd(() -> {
            setMotorPosition(50.0);
            setMotorVelocity(50.0);
        },
        () -> {
            setMotorPosition(0.0);
            setMotorVelocity(0.0);
        });
    }
    public Command sequential() {
        return Commands.sequence(
            runOnce(() -> {setMotorPosition(50.0);}),
            Commands.waitUntil(() -> Math.abs(motor2.getPosition().getValueAsDouble() - 50.0) < 0.5),
            runOnce(() -> {setMotorVelocity(50.0);})
        );
    }
}
