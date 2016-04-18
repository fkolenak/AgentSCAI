/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.activity;

import ninja.fido.agentAI.base.CommandActivity;
import ninja.fido.agentAI.agent.ResourceCommand;
import ninja.fido.agentAI.agent.unit.SCV;
import ninja.fido.agentAI.order.HarvestMineralsOrder;
import ninja.fido.agentAI.goal.HarvestMineralsGoal;
import java.util.List;
import java.util.Objects;
import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentAI.goal.HarvestGoal;

/**
 *
 * @author david_000
 * @param <A> Agent
 */
public class ManageHarvest<A extends ResourceCommand> extends CommandActivity<A,HarvestGoal,ManageHarvest>{
	
	private final double mineralShare;

	
	
	
	public ManageHarvest() {
		mineralShare = 0;
	}

    public ManageHarvest(A agent, HarvestGoal goal) {
        super(agent);
        this.mineralShare = goal.getMineralShare();
    }

    @Override
    public void performAction() throws ChainOfCommandViolationException {
		List<SCV> scvs = getAgent().getCommandedAgents(SCV.class);
        for (SCV scv : scvs) {
			if(!(scv.getGoal() instanceof HarvestMineralsGoal)){
				new HarvestMineralsOrder(scv, this.getAgent()).issueOrder();
			}
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ManageHarvest other = (ManageHarvest) obj;
        if (!Objects.equals(this.mineralShare, other.mineralShare)) {
            return false;
        }
        return true;
    }

	@Override
	protected void init() {
		
	}

	@Override
	public ManageHarvest create(A agent, HarvestGoal goal) {
		return new ManageHarvest(agent, goal);
	}
    
}
