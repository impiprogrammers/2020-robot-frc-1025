package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ClimberLoop extends ParallelCommandGroup {
	
	public ClimberLoop() {
		super(new ClimberWinchMove(), new ClimberShimmyMove());
	}
}