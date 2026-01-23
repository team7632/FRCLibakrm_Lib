[![AkLib Logo](https://github.com/team7632/FRCLibakrm_Lib/blob/main/picture/254167164.png)](https://github.com/team7632/FRCLibakrm_Lib/blob/main/picture/254167164.png)
[![FRC Team 7632](https://img.shields.io/badge/FRC-7632Event-0055FF)](https://frc-events.firstinspires.org/team/7632)
[![JitPack Version](https://jitpack.io/v/team7632/FRCLibakrm_Lib.svg)](https://jitpack.io/#team7632/FRCLibakrm_Lib)  
[![Instagram](https://img.shields.io/badge/Instagram-FRC%207632-E4405F?logo=instagram&logoColor=purple)](https://www.instagram.com/frc_team_7632/)
[![Facebook](https://img.shields.io/badge/Facebook-FRC%207632-1877F2?logo=facebook&logoColor=blue)](https://www.facebook.com/FRCTeam7632)


---

# What is AkLib?

**AkLib** is a custom FRC library developed by Team 7632.  
It provides utilities for common robotics programming tasks, including:

- Gamepad input handling  
- Sensor fusion and filtering (e.g., Limelight + Encoder)  
- Math utilities  
- NetworkTables helpers  
- Other utility classes for simplifying robot code

---


# How to Use AkLib

### Step 0: Setup Gradle
Make sure your `plugins` block includes GradleRIO:
```groovy
plugins {
    id "java"
    id "edu.wpi.first.GradleRIO" version "2026.1.1"
}
```
Step 1. Add the JitPack repository to your `build.gradle`

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

 - Step 2. Add the dependency to `build.gradle`
 
 [![Jitpack Website](https://jitpack.io/v/StuyPulse/StuyLib.svg)](https://jitpack.io/#team7632/FRCLibakrm_Lib)
```groovy
dependencies {
    // check tag above for most recent version
   implementation 'com.github.team7632:FRCLibakrm_Lib:v2026.1.01'
}
```
