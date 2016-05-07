/* 
 * AgentAI
 */
package ninja.fido.agentAI.goal;

import bwapi.Position;
import ninja.fido.agentAI.base.Goal;
import ninja.fido.agentAI.base.GoalOrder;
import ninja.fido.agentAI.base.GameAgent;

/**
 *
 * @author F.I.D.O.
 */
public class AttackMoveGoal extends Goal{
	
	private final Position attackTarget;
	
	public AttackMoveGoal(GameAgent agent, GoalOrder order, Position attackTarget) {
		super(agent, order);
		this.attackTarget = attackTarget;
	}

	public Position getAttackTarget() {
		return attackTarget;
	}

	@Override
	public boolean isCompleted() {
		return false;
	}
	
}
