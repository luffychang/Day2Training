// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class ExampleSubsystem extends SubsystemBase {
  private final TalonFX m_test_motor = new TalonFX(17, "canivore");
  //private final MotionMagicVoltage m_motorPositionRequest = new MotionMagicVoltage(0.0).withSlot(0);
  private final VelocityTorqueCurrentFOC m_motorVelocityRequest = new VelocityTorqueCurrentFOC(0.0).withSlot(0);
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    var testMotorConfigs = new TalonFXConfiguration();
    // kS 0.2, kP 5 for pos
    // kS 5, kV 0.175, kP 10 for vel
    testMotorConfigs.Slot0.kS = 5;
    testMotorConfigs.Slot0.kV = 0.175;
    testMotorConfigs.Slot0.kA = 0;
    testMotorConfigs.Slot0.kP = 10;
    testMotorConfigs.Slot0.kI = 0;
    testMotorConfigs.Slot0.kD = 0;
    // testMotorConfigs.MotionMagic.MotionMagicAcceleration = 100;
    // testMotorConfigs.MotionMagic.MotionMagicCruiseVelocity = 200;
    // testMotorConfigs.MotionMagic.MotionMagicExpo_kV = 0.12;
    // testMotorConfigs.MotionMagic.MotionMagicExpo_kA = 0.1;
    // testMotorConfigs.MotionMagic.MotionMagicJerk = 0;

    m_test_motor.getConfigurator().apply(testMotorConfigs);
  }
  // public void setMotorPosition(double position) {
  //   m_test_motor.setControl(m_motorPositionRequest.withPosition(position));
  // }
  public void setMotorVelocity(double velocity) {
    m_test_motor.setControl(m_motorVelocityRequest.withVelocity(velocity));
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    // return runOnce(
    //     () -> {
    //       //setMotorPosition(10.0);
    //     });
    return Commands.none();
  }
  public Command resetVel() {
    return runOnce(() -> {
      setMotorVelocity(0.0);
    });
  }
  public Command setVel() {
    return runOnce(() -> {
      setMotorVelocity(50.0);
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
//notes
//一个子系统应该具备哪些东西
//1.该子系统涉及到的电机
//3.一些方法
//按键按下->触发command->setMotorPosition->电机运动