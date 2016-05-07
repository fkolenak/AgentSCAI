/* 
 * AgentAI
 */
package ninja.fido.agentAI.order;

import ninja.fido.agentAI.base.GoalOrder;
import bwapi.TilePosition;
import bwapi.UnitType;
import ninja.fido.agentAI.base.CommandAgent;
import ninja.fido.agentAI.agent.unit.Worker;
import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentAI.goal.ConstructBuildingGoal;

/**
 *
 * @author F.I.D.O.
 */
public class ConstructBuildingOrder extends GoalOrder<Worker>{
	
	private final UnitType buildingType;
	
	private final TilePosition placeToBuildOn;

	public ConstructBuildingOrder(Worker target, CommandAgent commandAgent, UnitType buildingType, 
			TilePosition placeToBuildOn) throws ChainOfCommandViolationException {
		super(target, commandAgent);
		this.buildingType = buildingType;
		this.placeToBuildOn = placeToBuildOn;
	}

	@Override
	protected void execute() {
		setGoal(new ConstructBuildingGoal(getTarget(), this, buildingType, placeToBuildOn));
	}
	
}
