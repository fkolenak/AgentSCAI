/* 
 * AgentAI - Demo
 */
package ninja.fido.agentAI.demo.goal;

import ninja.fido.agentAI.base.Agent;
import ninja.fido.agentAI.base.Goal;
import ninja.fido.agentAI.base.GoalOrder;

/**
 *
 * @author F.I.D.O.
 */
public class FormationTestStrategyGoal extends Goal{

	public FormationTestStrategyGoal(Agent agent, GoalOrder order) {
		super(agent, order);
	}

	@Override
	public boolean isCompleted() {
		return false;
	}
	
}
