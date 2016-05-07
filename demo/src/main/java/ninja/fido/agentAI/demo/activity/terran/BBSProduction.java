/* 
 * AgentAI - Demo
 */
package ninja.fido.agentAI.demo.activity.terran;

import ninja.fido.agentAI.ResourceDeficiencyException;
import ninja.fido.agentAI.ResourceType;
import ninja.fido.agentAI.agent.unit.Barracks;
import ninja.fido.agentAI.base.CommandActivity;
import ninja.fido.agentAI.agent.ProductionCommand;
import ninja.fido.agentAI.base.Goal;
import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentAI.goal.AutomaticProductionGoal;
import ninja.fido.agentAI.order.AutomaticProductionOrder;

/**
 *
 * @author F.I.D.O.
 */
public class BBSProduction extends CommandActivity<ProductionCommand,Goal,BBSProduction>{

	public BBSProduction() {
	}
	
	public BBSProduction(ProductionCommand agent) {
		super(agent);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof BBSProduction;
	}
	
	

	@Override
	protected void performAction() throws ChainOfCommandViolationException {
		for (Barracks barracks : agent.getBarracks()) {
			if(!(barracks.getGoal() instanceof AutomaticProductionGoal)){
				new AutomaticProductionOrder(barracks, agent).issueOrder();
			}
			if(barracks.isMineralsMissing() && barracks.getMissingMinerals() <= getOwnedMinerals()
					&& barracks.isSupplyMissing() && barracks.getMissingSupply() <= getOwnedSupply()){
				try {
					giveResource(barracks, ResourceType.MINERALS, barracks.getMissingMinerals());
					giveResource(barracks, ResourceType.SUPPLY, barracks.getMissingSupply());
				} catch (ResourceDeficiencyException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void init() {
		
	}

	@Override
	public BBSProduction create(ProductionCommand agent, Goal goal) {
		return new BBSProduction(agent);
	}
	
}
