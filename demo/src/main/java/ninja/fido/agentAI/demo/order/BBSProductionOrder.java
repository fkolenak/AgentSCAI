/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.demo.order;

import ninja.fido.agentAI.agent.ProductionCommand;
import ninja.fido.agentAI.base.CommandAgent;
import ninja.fido.agentAI.base.GoalOrder;
import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentAI.demo.goal.BBSProductionGoal;

/**
 *
 * @author F.I.D.O.
 */
public class BBSProductionOrder extends GoalOrder<ProductionCommand> {

	public BBSProductionOrder(ProductionCommand target, CommandAgent commandAgent) 
			throws ChainOfCommandViolationException {
		super(target, commandAgent);
	}

	@Override
	protected void execute() {
		setGoal(new BBSProductionGoal(getTarget(), this));
	}
	
}
