/* 
 * AgentAI
 */
package ninja.fido.agentAI.base.exception;

import ninja.fido.agentAI.base.Agent;
import ninja.fido.agentAI.base.Order;

/**
 *
 * @author F.I.D.O.
 */
public class ChainOfCommandViolationException extends Exception{
	private final Agent violator;
	
	private final Order order;
	
	private final Agent target;

	public ChainOfCommandViolationException(Agent violator, Order order, Agent target) {
		this.violator = violator;
		this.order = order;
		this.target = target;
	}
	
	
	
	@Override
	public String getMessage() {
		return "Chain of command has been violated by " + violator.getClass() + ". " + target.getClass()
				+ " is not under it|s command (order: " + order + ").";
	}
}
