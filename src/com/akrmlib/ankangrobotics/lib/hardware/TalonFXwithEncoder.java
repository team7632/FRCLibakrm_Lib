
package com.akrmlib.ankangrobotics.lib.hardware;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class TalonFXwithEncoder extends TalonFX_Controller {

    private DutyCycleEncoder encoder;
    private int encoderPortg = 1;

    private double gearRatio = 1.0;

    private double distancePerRotation = 360.0;

    private double offset = 0.0;

    private boolean synced = false;

    public TalonFXwithEncoder withEncoderPort(int port) {
        this.encoderPort = port;
        return this;
    }

    public TalonFXwithEncoder withGearRatio(double ratio) {
        this.gearRatio = ratio;
        return this;
    }

    public TalonFXwithEncoder withDistancePerRotation(double distance) {
        this.distancePerRotation = distance;
        return this;
    }

    public TalonFXwithEncoder withOffset(double offset) {
        this.offset = offset;
        return this;
    }

    public double getOffset() {
        return offset;
    }

    public double getEncoderPosition() {
        return encoder.get() * distancePerRotation;
    }

    public TalonFXwithEncoder build() {
        super.build();

        encoder = new DutyCycleEncoder(encoderPort);

        return this;
    }

    public double getAbsolutePosition() {
        return getEncoderPosition() - offset;
    }

    public void syncToAbsoluteEncoder() {
        if (!encoder.isConnected() || synced) return;

        double absPos = getAbsolutePosition();
        double motorDegree = absPos * gearRatio;

        resetPosition(motorDegree);
        synced = true;
    }

    public void setMechanismPosition(double position, int slot, boolean enablemotionmagic) {
        if (encoder.isConnected()) {
            double motorDegree = position * gearRatio;
            if (enablemotionmagic) {
                setMotionPosition(motorDegree, slot);
            } else {
                setPosition(motorDegree, slot);
            }
        } else {
            return;
        }
    }

    public double getMechanismPosition() {
        double motorDegree = getPosition();
        return motorDegree / gearRatio;
    }

    public double getMechanismVelocity() {
        double motorRPM = getVelocity();
        return (motorRPM) / gearRatio * distancePerRotation;
    }

    public boolean isSynced() {
        return synced;
    }
}
