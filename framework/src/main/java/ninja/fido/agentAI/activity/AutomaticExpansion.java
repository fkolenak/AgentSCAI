/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.activity;

import bwapi.UnitType;
import java.util.logging.Level;
import java.util.logging.Logger;
import ninja.fido.agentAI.agent.ExpansionCommand;
import ninja.fido.agentAI.agent.FullCommander;
import ninja.fido.agentAI.agent.unit.Worker;
import ninja.fido.agentAI.base.CommandActivity;
import ninja.fido.agentAI.base.Order;
import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentAI.base.exception.CommanderNotCreatedException;
import ninja.fido.agentAI.modules.decisionMaking.DecisionModuleActivity;
import ninja.fido.agentAI.goal.AutomaticExpansionGoal;
import ninja.fido.agentAI.request.ExpansionInfoRequest;

/**
 *
 * @author F.I.D.O.
 */
public class AutomaticExpansion extends CommandActivity<ExpansionCommand, AutomaticExpansionGoal> 
		implements DecisionModuleActivity<ExpansionCommand, AutomaticExpansionGoal, AutomaticExpansion>{
	
	private UnitType expansionBuildingType;
	
	private boolean expansionRequestSended;

	
	
	
	public AutomaticExpansion() {
	}

	public AutomaticExpansion(ExpansionCommand agent, UnitType expansionBuilding) {
		super(agent);
		this.expansionBuildingType = expansionBuilding;
		expansionRequestSended = false;
	}

	public AutomaticExpansion(ExpansionCommand agent, AutomaticExpansionGoal goal) {
		super(agent);
		this.expansionBuildingType = goal.getExpansionBuildingType();
		expansionRequestSended = false;
	}
	
	
	

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof AutomaticExpansion;
	}

	@Override
	protected void performAction() throws ChainOfCommandViolationException {
		Worker worker;
		if(agent.getNextExpansionPosition() == null && !expansionRequestSended){
			try {
				new ExpansionInfoRequest(FullCommander.get().explorationCommand, agent).send();
			} catch (CommanderNotCreatedException ex) {
				ex.printStackTrace();
			}
			expansionRequestSended = true;
		}
		else if(agent.getNextExpansionPosition() != null && (worker = agent.getWorker()) != null){
			agent.startExpansion(expansionBuildingType, worker);
			expansionRequestSended = false;
		}
	}

	@Override
	protected void init() {
		
	}
	
	

	@Override
	protected void handleCompletedOrder(Order order) {
		
	}

	@Override
	public AutomaticExpansion create(ExpansionCommand agent, AutomaticExpansionGoal goal) {
		return new AutomaticExpansion(agent, goal);
	}
	
	
}