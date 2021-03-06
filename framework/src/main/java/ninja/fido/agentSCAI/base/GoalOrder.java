/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI.base;

import ninja.fido.agentSCAI.base.exception.ChainOfCommandViolationException;

/**
 * Represets goal order - order that results in changing target's goal
 * @author F.I.D.O.
 * @param <T> Command target type. When you subclass goalOrder, you should restrict the target type as much as it 
 * is possible.
 */
public abstract class GoalOrder<T extends Agent> extends Order<T> {

	/**
	 * Constructor
	 * @param target Target of the order.
	 * @param commandAgent Command agent issuing the order.
	 * @throws ChainOfCommandViolationException If the agent send order to unit that is not under it's direct command.
	 */
	public GoalOrder(T target, CommandAgent commandAgent) throws ChainOfCommandViolationException {
		super(target, commandAgent);
	}
	
	/**
	 * Sets target agents goal to new goal.
	 * @param goal New goal.
	 */
	protected final void setGoal(Goal goal){
		Agent target = getTarget();
		target.setGoal(goal);
	}
}
