package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {

  private final TalonFX armMotor = new TalonFX(6);
  private final MotionMagicVoltage positionRequest = new MotionMagicVoltage(0);

  private StatusSignal<Angle> position = armMotor.getPosition();

  public ArmSubsystem() {
    var talonFXConfigs = new TalonFXConfiguration();

    var slot0Configs = talonFXConfigs.Slot0;
    slot0Configs.kS = 0.14;
    slot0Configs.kV = 0.113;
    slot0Configs.kA = 0.002;
    slot0Configs.kG = 0.435;
    slot0Configs.kP = 28;
    slot0Configs.kD = 0.2;

    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = 80;
    motionMagicConfigs.MotionMagicAcceleration = 160;
    motionMagicConfigs.MotionMagicJerk = 1600;

    armMotor.getConfigurator().apply(talonFXConfigs);
  }

  public void armUp(double pos) {
    armMotor.setControl(positionRequest.withPosition(pos));
  }

  public void armDown(double pos) {
    armMotor.setControl(positionRequest.withPosition(pos));
  }

  public void armNeutral() {
    armMotor.setControl(positionRequest.withPosition(0));
  }

  public Command armDownCommand() {
    return run(
        () -> {
          position.refresh();
          armUp(position.getValueAsDouble() + 0.1);
        });
  }

  public Command armUpCommand() {
    return run(
        () -> {
          position.refresh();
          armDown(position.getValueAsDouble() - 0.1);
        });
  }

  public Command armNeutralCommand() {
    return run(() -> armNeutral());
  }
}
