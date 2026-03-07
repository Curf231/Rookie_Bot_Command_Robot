// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.RollerSubsystem;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import com.ctre.phoenix6.controls.MotionMagicVoltage;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private double currPosition = 0;
  private final MotionMagicVoltage positionRequest = new MotionMagicVoltage(0);
  private final RollerSubsystem m_rollerSubsystem = new RollerSubsystem();



  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandPS5Controller m_driverController =
      new CommandPS5Controller(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    TalonFX rollerMotor = new TalonFX(9);
    TalonFX armMotor = new TalonFX(6);

    Trigger armUpTrigger = new Trigger(m_driverController.cross());
    armUpTrigger.whileTrue(m_armSubsystem.armUpCommand());
    Trigger armDownTrigger = new Trigger(m_driverController.square());
    armDownTrigger.whileTrue(m_armSubsystem.armDownCommand());

    Trigger rollerForwardTrigger = new Trigger(m_driverController.circle());
    rollerForwardTrigger.whileTrue(m_rollerSubsystem.rollerForwardCommand());

    Trigger rollerBackwardTrigger = new Trigger(m_driverController.triangle());
    rollerBackwardTrigger.whileTrue(m_rollerSubsystem.rollerBackwardCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

}
