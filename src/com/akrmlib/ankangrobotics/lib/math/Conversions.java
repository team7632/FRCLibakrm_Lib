package frc.lib.math;

public class Conversions {

    /**
     * @param wheelRPS Wheel Velocity: (in Rotations per Second)
     * @param circumference Wheel Circumference: (in Meters)
     * @return Wheel Velocity: (in Meters per Second)
     * 將輪子的每秒轉數轉換為每秒米數
     */
    public static double RPSToMPS(double wheelRPS, double circumference){
        double wheelMPS = wheelRPS * circumference;
        return wheelMPS;
    }

    /**
     * @param wheelMPS Wheel Velocity: (in Meters per Second)
     * @param circumference Wheel Circumference: (in Meters)
     * @return Wheel Velocity: (in Rotations per Second)
     * 將輪子的每秒米數轉換為每秒轉數
     */
    public static double MPSToRPS(double wheelMPS, double circumference){
        double wheelRPS = wheelMPS / circumference;
        return wheelRPS;
    }
    public static double RPSTORPM(double value){
        return value * 60;
    }
    public static double RPMTORPS(double value){
        return value / 60;
    }

    /**
     * @param wheelRotations Wheel Position: (in Rotations)
     * @param circumference Wheel Circumference: (in Meters)
     * @return Wheel Distance: (in Meters)
     * 將輪子的轉數轉換為米數
     */
    public static double rotationsToMeters(double wheelRotations, double circumference){
        double wheelMeters = wheelRotations * circumference;
        return wheelMeters;
    }

    /**
     * @param wheelMeters Wheel Distance: (in Meters)
     * @param circumference Wheel Circumference: (in Meters)
     * @return Wheel Position: (in Rotations)
     * 將輪子的米數轉換為轉數
     */
    public static double metersToRotations(double wheelMeters, double circumference){
        double wheelRotations = wheelMeters / circumference;
        return wheelRotations;
    }
}