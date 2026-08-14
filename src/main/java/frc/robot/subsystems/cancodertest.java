package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import java.lang.Math;
import java.util.concurrent.CancellationException;

public class cancodertest {
    private final TalonFX testMotor = new TalonFX(1, "rio");
    private final CANcoder testCancoder = new CANcoder(2, "rio");
    public cancodertest() {
        var testCancoderConfig = new CANcoderConfiguration();
        testCancoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
        testCancoderConfig.MagnetSensor.MagnetOffset = 0;
        testCancoder.getConfigurator().apply(testCancoderConfig);

        var testMotorConfig = new TalonFXConfiguration();
        testMotorConfig.Slot0.kS = 0;
        testMotorConfig.Slot0.kV = 0;
        testMotorConfig.Slot0.kA = 0;
        testMotorConfig.Slot0.kP = 0;
        testMotorConfig.Slot0.kI = 0;
        testMotorConfig.Slot0.kD = 0;
        testMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        testMotorConfig.MotionMagic.MotionMagicCruiseVelocity = 200;
        testMotorConfig.MotionMagic.MotionMagicAcceleration = 100;

        testMotorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.FusedCANcoder;
        testMotorConfig.Feedback.FeedbackRemoteSensorID = testCancoder.getDeviceID();
        testMotorConfig.Feedback.RotorToSensorRatio = 1;
        testMotorConfig.Feedback.SensorToMechanismRatio = 1;
        testMotor.getConfigurator().apply(testMotorConfig);
    }
}
// steps for a cancoder
// import com.ctre.phoenix6.configs.CANcoderConfiguration;
// 1. declare cancoder object (like a motor) as 属性/配置
// 2. configure cancoder in constructor
// 3. connect motor feedback source to cancoder
// config e.g.testCancoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
//            testCancoderConfig.MagnetSensor.MagnetOffset = 0;
