package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase{

    TalonFX armMotor = new TalonFX(6);
    private double currPosition = 0;


    private final MotionMagicVoltage positionRequest = new MotionMagicVoltage(0);

    public ArmSubsystem() {

        var talonFXConfigs = new TalonFXConfiguration();

        var slot0Configs = talonFXConfigs.Slot0;
        slot0Configs.kS = 0.14;
        slot0Configs.kV = 0.113;
        slot0Configs.kA = 0.002;
        slot0Configs.kG = 0.435;
        slot0Configs.kP = 10;

        var motionMagicConfigs = talonFXConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = 80;
        motionMagicConfigs.MotionMagicAcceleration = 160;
        motionMagicConfigs.MotionMagicJerk = 1600;

        armMotor.getConfigurator().apply(talonFXConfigs);
    }




    public void armUp(){
        currPosition -= 0.01;
        armMotor.setControl(positionRequest.withPosition(currPosition));
    }

    public void armDown(){
    currPosition += 0.01;
    armMotor.setControl(positionRequest.withPosition(currPosition));
    }
    

    public Command armUpCommand() {
        return run(() -> armUp());
    }

    public Command armDownCommand() {
    return run(() -> armDown());
    }
}
