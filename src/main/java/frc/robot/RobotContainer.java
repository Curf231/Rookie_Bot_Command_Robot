// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.reflect.GenericSignatureFormatError;

import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.RollerSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final RollerSubsystem m_rollerSubsystem = new RollerSubsystem();
  private final GripperSubsystem m_gripperSubsystem = new GripperSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandPS5Controller m_driverController =
    new CommandPS5Controller(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    configureBindings();
  }


  private void configureBindings() {

    m_driverController
        .R1()
        .whileTrue(m_armSubsystem.armNeutralCommand());
    m_driverController
        .cross()
        .whileTrue(m_armSubsystem.armDownCommand());
    m_driverController
        .square()
        .whileTrue(m_armSubsystem.armUpCommand());
    m_driverController
        .circle()
        .whileTrue(m_rollerSubsystem.rollerBackwardCommand())
        .onFalse(m_rollerSubsystem.stopRollerCommand());
    m_driverController
        .triangle()
        .whileTrue(m_rollerSubsystem.rollerForwardCommand())
        .onFalse(m_rollerSubsystem.stopRollerCommand());
    m_driverController
        .pov(90)
        .whileTrue(m_gripperSubsystem.gripperOpenCommand())
        .onFalse(m_gripperSubsystem.gripperServoStopCommand());
    m_driverController
        .pov(270)
        .whileTrue(m_gripperSubsystem.gripperCloseCommand())
        .onFalse(m_gripperSubsystem.gripperServoStopCommand());

  }
}
