/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.akrmlib.ankangrobotics.lib.util;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private final String LLNTname;

   
    public Limelight(String LLNTname) {
        this.LLNTname = "limelight-" + LLNTname;
    }

   
    public Rotation2d getRetroTx() {
        return Rotation2d.fromDegrees(
                -NetworkTableInstance.getDefault().getTable(LLNTname).getEntry("tx").getDouble(0));
    }

   
    public Rotation2d getRetroTy() {
        return Rotation2d.fromDegrees(
                NetworkTableInstance.getDefault().getTable(LLNTname).getEntry("ty").getDouble(0));
    }

   
    public boolean hasRetroTarget() {
        boolean hasTarget =
                NetworkTableInstance.getDefault().getTable(LLNTname).getEntry("tv").getDouble(0)
                        == 1;
        return hasTarget;
    }

    
    public double getLatency() {
        double pipelineLatency =
                NetworkTableInstance.getDefault().getTable(LLNTname).getEntry("tl").getDouble(0)
                        / 1000;
        double captureLatency =
                NetworkTableInstance.getDefault().getTable(LLNTname).getEntry("cl").getDouble(0)
                        / 1000;
        return (pipelineLatency + captureLatency);
    }

  
    public Pose2d getBotPose2d() {
        double[] botPose =
                NetworkTableInstance.getDefault()
                        .getTable(LLNTname)
                        .getEntry("botpose")
                        .getDoubleArray(new double[6]);
        Pose2d botPose2d =
                new Pose2d(
                        new Translation2d(botPose[0], botPose[1]),
                        Rotation2d.fromDegrees(botPose[5]));
        return botPose2d;
    }

   
    public Pose3d getBotPose3d() {
        double[] botPose =
                NetworkTableInstance.getDefault()
                        .getTable(LLNTname)
                        .getEntry("botpose")
                        .getDoubleArray(new double[6]);
        Pose3d botPose3d =
                new Pose3d(
                        new Translation3d(botPose[0], botPose[1], botPose[2]),
                        new Rotation3d(
                                Units.degreesToRadians(botPose[3]),
                                Units.degreesToRadians(botPose[4]),
                                Units.degreesToRadians(botPose[5])));
        return botPose3d;
    }

   
    public Pose2d getBlueBotPose2d() {
        double[] botPose =
                NetworkTableInstance.getDefault()
                        .getTable(LLNTname)
                        .getEntry("botpose_wpiblue")
                        .getDoubleArray(new double[6]);
        Pose2d botPose2d =
                new Pose2d(
                        new Translation2d(botPose[0], botPose[1]),
                        Rotation2d.fromDegrees(botPose[5]));
        return botPose2d;
    }


    public Pose3d getBlueBotPose3d() {
        double[] botPose =
                NetworkTableInstance.getDefault()
                        .getTable(LLNTname)
                        .getEntry("botpose_wpiblue")
                        .getDoubleArray(new double[6]);
        Pose3d botPose3d =
                new Pose3d(
                        new Translation3d(botPose[0], botPose[1], botPose[2]),
                        new Rotation3d(
                                Units.degreesToRadians(botPose[3]),
                                Units.degreesToRadians(botPose[4]),
                                Units.degreesToRadians(botPose[5])));
        return botPose3d;
    }


    public Pose2d getRedBotPose2d() {
        double[] botPose =
                NetworkTableInstance.getDefault()
                        .getTable(LLNTname)
                        .getEntry("botpose_wpired")
                        .getDoubleArray(new double[6]);
        Pose2d botPose2d =
                new Pose2d(
                        new Translation2d(botPose[0], botPose[1]),
                        Rotation2d.fromDegrees(botPose[5]));
        return botPose2d;
    }

    public Pose3d getRedBotPose3d() {
        double[] botPose =
                NetworkTableInstance.getDefault()
                        .getTable(LLNTname)
                        .getEntry("botpose_wpired")
                        .getDoubleArray(new double[6]);
        Pose3d botPose3d =
                new Pose3d(
                        new Translation3d(botPose[0], botPose[1], botPose[2]),
                        new Rotation3d(
                                Units.degreesToRadians(botPose[3]),
                                Units.degreesToRadians(botPose[4]),
                                Units.degreesToRadians(botPose[5])));
        return botPose3d;
    }

   
    public void ledState(ledStates ledState) {
        NetworkTableInstance.getDefault()
                .getTable(LLNTname)
                .getEntry("ledMode")
                .setNumber(ledState.value);
    }

 
    public void camMode(camModes camMode) {
        NetworkTableInstance.getDefault()
                .getTable(LLNTname)
                .getEntry("camMode")
                .setNumber(camMode.value);
    }

  
    public void setPipeline(int pipeline) {
        NetworkTableInstance.getDefault()
                .getTable(LLNTname)
                .getEntry("pipeline")
                .setNumber(pipeline);
    }

   
    public enum ledStates {
        pipeline(0),
        off(1),
        blink(2),
        on(3);

        public final int value;

        ledStates(int value) {
            this.value = value;
        }
    }

   
    public enum camModes {
        vision(0),
        driver(1);

        public final int value;

        camModes(int value) {
            this.value = value;
        }
    }
}
