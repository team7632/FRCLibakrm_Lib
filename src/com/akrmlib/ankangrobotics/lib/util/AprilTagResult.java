
package com.akrmlib.ankangrobotics.lib.util;
import edu.wpi.first.math.geometry.Pose2d;

public class AprilTagResult {
    public final Pose2d pose2d;
    public final double timestamp;
    public final boolean update;

    public AprilTagResult(Pose2d pose2d, double timestamp, boolean update) {
        this.pose2d = pose2d;
        this.timestamp = timestamp;
        this.update = update;
    }
}