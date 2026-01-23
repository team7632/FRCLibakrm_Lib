package com.akrmlib.ankangrobotics.lib.util;

import java.util.Optional;

import org.littletonrobotics.junction.Logger;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.DriverStation;

public class PhotoCamera {

    public PhotonCamera camera;
    private AprilTagFieldLayout aprilTagFieldLayout;
    private AprilTagFields field;
    private Transform3d cameraTransform3d;
    private String cameraName;
    private Pose2d pose2d = new Pose2d();


    public PhotoCamera setName(String cameraName) {
        this.cameraName = cameraName;
        return this;
    }

    public PhotoCamera setAprilTagFieldLayout(AprilTagFields fields) {
        this.field = fields;
        return this;
    }
    public PhotoCamera setCameraTransform3d(Transform3d cameraTransform3d) {
        this.cameraTransform3d = cameraTransform3d;
        return this;
    }

    public PhotoCamera build() {
        this.camera = new PhotonCamera(cameraName);
        this.aprilTagFieldLayout = AprilTagFieldLayout.loadField(field);

        return this;

    }

    public AprilTagResult aprilTagProcess(double multi_LIMIT, double single_LIMIT) {

        PhotonPipelineResult result = this.camera.getLatestResult();

        double TimestampSeconds = result.getTimestampSeconds();
        boolean useMutitag = true;
        boolean done = false;
        boolean Update = false;
        boolean useGyro = false;
        Optional<Pose3d> robotPose = Optional.ofNullable(new Pose3d());

        if (result.getTargets().toArray().length < 3) {
            for (PhotonTrackedTarget tar : result.getTargets()) {
                if (tar.getBestCameraToTarget().getTranslation().getDistance(new Translation3d()) > multi_LIMIT)
                    useMutitag = false;
            }
        }

        // ================================ MutiTag ================================
        if (result.multitagResult.isPresent() && useMutitag) {

            Transform3d multitagResult = result.multitagResult.get().estimatedPose.best;
            Translation3d translation = multitagResult.getTranslation();
            Rotation3d rotation = multitagResult.getRotation();

            Pose3d campose = new Pose3d(translation, rotation);
            robotPose = Optional.ofNullable(campose.plus(this.cameraTransform3d));
            Update = true;
            Logger.recordOutput("VirtualPose/(" + camera.getName() + ")MutiTag_RobotPose", robotPose.get());

            done = true;
        }

        // ================================ SingTag ================================
        if (!done) {
            for (PhotonTrackedTarget target : result.getTargets()) {
                if (target.getBestCameraToTarget().getTranslation().getDistance(new Translation3d()) > single_LIMIT)
                    continue;

                int tagId = target.getFiducialId();

                Optional<Pose3d> tagposition = aprilTagFieldLayout.getTagPose(tagId);
                if (!tagposition.isPresent()) {
                    continue;
                }
                Pose3d E_campose = tagposition.get().plus(target.getBestCameraToTarget().inverse());

                robotPose = Optional.ofNullable(E_campose.plus(this.cameraTransform3d));
                if (Math.abs(robotPose.get().getZ()) > 0.025)
                    continue;
                Update = true;
                Logger.recordOutput("VirtualPose/(" + camera.getName() + ")SingTag_RobotPose", robotPose.get());
            }
        }

        // ================================ Update ================================v

        if (Update) {
            Pose3d updatePose = robotPose.get();

            if (DriverStation.isAutonomous()) {
                if (!Constants.AutoAngleUseCamera) {
                    useGyro = true;
                }
            } else {
                if (!Constants.TeleAngleUseCamera) {
                    useGyro = true;
                }
            }

            if (!useMutitag || useGyro) {
                updatePose = new Pose3d(robotPose.get().getTranslation(),
                        new Rotation3d());
            }

            this.pose2d = new Pose2d(updatePose.getX(), updatePose.getY(),
                    new Rotation2d(updatePose.getRotation().getZ()));
        }
        return new AprilTagResult(pose2d, TimestampSeconds, Update);
    }

}
