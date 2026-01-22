
package com.akrmlib.ankangrobotics.lib.hardware;

import com.akrmlib.ankangrobotics.lib.math.Conversions;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class TalonFX_Controller {
    public TalonFX m_motor;
    private PositionVoltage m_voltagePosition = new PositionVoltage(0);
    private MotionMagicVoltage m_MMVPosition = new MotionMagicVoltage(0);
    private VelocityVoltage m_VelocityVoltage = new VelocityVoltage(0);
    private VoltageOut m_voltageOut = new VoltageOut(0);
    private TalonFXConfiguration m_configs = new TalonFXConfiguration();
    private MotionMagicVelocityVoltage m_MMVVelocity = new MotionMagicVelocityVoltage(0);
    private int deviceID = 0;
    private InvertedValue inverted = InvertedValue.CounterClockwise_Positive;
    private NeutralModeValue neutralmodevalue = NeutralModeValue.Brake;
    private double SupplyCurrentLimit = 40;

    public static class TanlonConfig {
        public double kP, kI, kD, kS, kV, kA, kG;

        public TanlonConfig(
                double kP, double kI, double kD, double kS, double kV, double kA, double kG) {
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
            this.kS = kS;
            this.kV = kV;
            this.kA = kA;
            this.kG = kG;
        }
    }

    public static class TanlonMotionConfig {
        public double PeakForwardVoltage,
                PeakReverseVoltage,
                Velocity,
                Acceleration,
                Jerk,
                Expo_kV,
                Expo_kA;

        public TanlonMotionConfig(
                double PeakForwardVoltage,
                double PeakReverseVoltage,
                double Velocity,
                double Acceleration,
                double Jerk,
                double Expo_kV,
                double Expo_kA) {

            this.PeakForwardVoltage = PeakForwardVoltage;
            this.PeakReverseVoltage = PeakReverseVoltage;
            this.Acceleration = Acceleration;
            this.Velocity = Velocity;
            this.Jerk = Jerk;
            this.Expo_kV = Expo_kV;
            this.Expo_kA = Expo_kA;
        }
    }

    public TalonFX_Controller build() {
        this.m_motor = new TalonFX(deviceID);
        this.m_configs.CurrentLimits.SupplyCurrentLimit = SupplyCurrentLimit;
        this.m_configs.MotorOutput.Inverted = inverted;

        applyConfigurations();

        this.m_motor.setPosition(0);
        this.m_motor.setNeutralMode(neutralmodevalue);

        return this;
    }

    /*
        public TalonFX_Controller buildAsFollower(int MasterID, boolean OpposeMasterDirection, double UpdateFreqHz) {
            this.m_motor = new TalonFX(deviceID);
            this.m_motor.setControl(new Follower(MasterID, OpposeMasterDirection).withUpdateFreqHz(UpdateFreqHz));
            return this;
        }
    */
    public TalonFX_Controller with_config(InvertedValue inverted) {
        this.inverted = inverted;
        return this;
    }

    public TalonFX_Controller with_config(double SupplyCurrentLimit) {
        this.SupplyCurrentLimit = SupplyCurrentLimit;
        return this;
    }

    public TalonFX_Controller with_config(NeutralModeValue neutralmodevalue) {
        this.neutralmodevalue = neutralmodevalue;
        return this;
    }

    public TalonFX_Controller with_motion_config(TanlonMotionConfig config) {
        this.m_configs.MotionMagic.MotionMagicAcceleration = config.Acceleration;
        this.m_configs.MotionMagic.MotionMagicCruiseVelocity = config.Velocity;
        this.m_configs.Voltage.PeakForwardVoltage = config.PeakForwardVoltage;
        this.m_configs.Voltage.PeakReverseVoltage = config.PeakReverseVoltage;
        this.m_configs.MotionMagic.MotionMagicJerk = config.Jerk;
        this.m_configs.MotionMagic.MotionMagicExpo_kA = config.Expo_kA;
        this.m_configs.MotionMagic.MotionMagicExpo_kV = config.Expo_kV;
        return this;
    }

    public TalonFX_Controller with_ID(int deviceID) {
        this.deviceID = deviceID;
        return this;
    }

    public TalonFX_Controller with_config_slot0(TanlonConfig config) {
        this.m_configs.Slot0.kP = config.kP;
        this.m_configs.Slot0.kI = config.kI;
        this.m_configs.Slot0.kD = config.kD;
        this.m_configs.Slot0.kA = config.kA;
        this.m_configs.Slot0.kV = config.kV;
        this.m_configs.Slot0.kS = config.kS;
        this.m_configs.Slot0.kG = config.kG;
        return this;
    }

    public TalonFX_Controller with_config_slot1(TanlonConfig config) {
        this.m_configs.Slot1.kP = config.kP;
        this.m_configs.Slot1.kI = config.kI;
        this.m_configs.Slot1.kD = config.kD;
        this.m_configs.Slot1.kA = config.kA;
        this.m_configs.Slot1.kV = config.kV;
        this.m_configs.Slot1.kS = config.kS;
        this.m_configs.Slot1.kG = config.kG;
        return this;
    }

    public TalonFX_Controller with_config_slot2(TanlonConfig config) {
        this.m_configs.Slot2.kP = config.kP;
        this.m_configs.Slot2.kI = config.kI;
        this.m_configs.Slot2.kD = config.kD;
        this.m_configs.Slot2.kA = config.kA;
        this.m_configs.Slot2.kV = config.kV;
        this.m_configs.Slot2.kS = config.kS;
        this.m_configs.Slot2.kG = config.kG;
        return this;
    }

    public void set(double power) {
        m_motor.set(power);
    }

    public void setPosition(double degree, int slot) {
        m_motor.setControl(
                m_voltagePosition.withEnableFOC(true).withPosition(degree / 360).withSlot(slot));
    }

    public void setVoltage(double voltage) {
        m_motor.setControl(m_voltageOut.withEnableFOC(true).withOutput(voltage));
    }

    public void setSpeed(double speed, int slot) {
        m_motor.setControl(
                m_VelocityVoltage
                        .withEnableFOC(true)
                        .withVelocity(Conversions.RPMTORPS(speed))
                        .withSlot(slot));
    }

    public void setMotionPosition(double degree, int slot) {
        m_motor.setControl(
                m_MMVPosition.withEnableFOC(true).withPosition(degree / 360).withSlot(slot));
    }

    public void setMotionVelocity(double speed, int slot) {
        m_motor.setControl(
                m_MMVVelocity
                        .withEnableFOC(true)
                        .withVelocity(Conversions.RPMTORPS(speed))
                        .withSlot(slot));
    }

    public void resetPosition(double degree) {
        m_motor.setPosition(degree / 360);
    }

    public double getPosition() {
        return m_motor.getPosition().getValueAsDouble() * 360;
    }

    public double getVelocity() {
        return Conversions.RPSTORPM(m_motor.getVelocity().getValueAsDouble());
    }

    public double getSupplyCurrent() {
        return m_motor.getSupplyCurrent().getValueAsDouble();
    }

    public double getDutyCycle() {
        return m_motor.getDutyCycle().getValueAsDouble();
    }

    public double getTorqueCurrent() {
        return m_motor.getTorqueCurrent().getValueAsDouble();
    }

    public void stop() {
        m_motor.stopMotor();
    }

    private void applyConfigurations() {
        StatusCode status = StatusCode.StatusCodeNotInitialized;
        for (int i = 0; i < 10; ++i) {
            status = m_motor.getConfigurator().apply(m_configs);
            if (status.isOK()) break;
        }
        if (!status.isOK()) {
            System.out.println("Could not apply configs, error code: " + status.toString());
        }
    }
}
