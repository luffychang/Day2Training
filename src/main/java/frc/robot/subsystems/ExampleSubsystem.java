// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ExampleSubsystem extends SubsystemBase {
  private final TalonFX m_test_motor = new TalonFX(0, "rio");
  private final MotionMagicVoltage m_motorPositionRequest = new MotionMagicVoltage(0.0).withSlot(0);
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    var testMotorConfigs = new TalonFXConfiguration();

    testMotorConfigs.Slot0.kS = 4.7;
    testMotorConfigs.Slot0.kV = 0.09;
    testMotorConfigs.Slot0.kA = 1;
    testMotorConfigs.Slot0.kP = 4;
    testMotorConfigs.Slot0.kI = 0;
    testMotorConfigs.Slot0.kD = 0.25;
    testMotorConfigs.MotionMagic.MotionMagicAcceleration = 100;
    testMotorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200;
    testMotorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12;
    testMotorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1;
    testMotorConfigs.MotionMagic.MotionMagicJerk = 0;
  }
  public void setMotorPosition(double position) {
    m_test_motor.setControl(m_motorPositionRequest.withPosition(position));
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          setMotorPosition(10.0);
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
