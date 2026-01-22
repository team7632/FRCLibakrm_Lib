package com.akrmlib.ankangrobotics.lib.util;
// Phoenix 6 is in the com.ctre.phoenix6.* packages
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.sim.TalonFXSimState;

public class TalonFXController {
    private TalonFX m_talonFX ;

    private TalonFXSimState m_talonFXSim = m_talonFX.getSimState();

    private DutyCycleOut m_talonFXOut = new DutyCycleOut(0);

    private TalonFXConfiguration m_talonFXConfig = new TalonFXConfiguration();

    InvertedValue m_talonFXInverted = InvertedValue.CounterClockwise_Positive;
}
