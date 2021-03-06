/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI.bwapiCommandInterface;

import bwapi.UnitCommand;
import ninja.fido.agentSCAI.base.GameAgent;

/**
 *
 * @author F.I.D.O.
 */
final class GeneralCommand extends BwapiCommad{
	
	private final UnitCommand unitCommand;

	public UnitCommand getUnitCommand() {
		return unitCommand;
	}
	
	
	
	
	public GeneralCommand(GameAgent agent, UnitCommand unitCommand) {
		super(agent);
		this.unitCommand = unitCommand;
	}

	@Override
	public String getType() {
		return unitCommand.getUnitCommandType().toString();
	}
	
}
