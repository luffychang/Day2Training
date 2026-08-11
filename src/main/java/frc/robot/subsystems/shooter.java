package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class shooter extends SubsystemBase {
    private final TalonFX shooter_1 = new TalonFX(2, "rio");
    private final TalonFX shooter_2 = new TalonFX(3, "rio");
    private final MotionMagicVoltage motorPositionRequest_1 = new MotionMagicVoltage(0.0).withSlot(0);
    private final MotionMagicVoltage motorPositionRequest_2 = new MotionMagicVoltage(0.0).withSlot(0);
    public shooter() {
        var shooterMotorConfig = new TalonFXConfiguration();

        shooterMotorConfig.Slot0.kS = 0.0;
        shooterMotorConfig.Slot0.kV = 0.0;
        shooterMotorConfig.Slot0.kA = 0.0;
        shooterMotorConfig.Slot0.kP = 0.0;
        shooterMotorConfig.Slot0.kI = 0.0;
        shooterMotorConfig.Slot0.kD = 0.0;

        shooter_1.getConfigurator().apply(shooterMotorConfig);
        shooter_2.getConfigurator().apply(shooterMotorConfig);
    }
    public void setMotorPosition(double position) {
        shooter_1.setControl(motorPositionRequest_1.withPosition(position));
        shooter_2.setControl(motorPositionRequest_2.withPosition(position));
    }
    public Command turnToPosition() {
        return runOnce(() -> {
            setMotorPosition(1000.0);
        });
    }
    public Command returnToPosition() {
        return runOnce(() -> {
            setMotorPosition(0.0);
        });
    }
}
