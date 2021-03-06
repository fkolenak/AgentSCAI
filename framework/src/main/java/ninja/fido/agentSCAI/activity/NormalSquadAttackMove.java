/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI.activity;

import ninja.fido.agentSCAI.agent.unit.Marine;
import ninja.fido.agentSCAI.agent.SquadCommander;
import ninja.fido.agentSCAI.modules.decisionStorage.StorableDecisionModuleActivity;
import ninja.fido.agentSCAI.goal.SquadAttackMoveGoal;
import ninja.fido.agentSCAI.order.AttackMoveOrder;
import java.util.List;
import ninja.fido.agentSCAI.base.exception.ChainOfCommandViolationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author F.I.D.O.
 */
public class NormalSquadAttackMove extends SquadAttackMove<NormalSquadAttackMove>
		implements StorableDecisionModuleActivity<SquadCommander, SquadAttackMoveGoal, NormalSquadAttackMove>{
	
	private static final int DEFAULT_MIN_SQUAD_SIZE = 5;
	
	private final int minSquadSize;
	
	public NormalSquadAttackMove() {
		minSquadSize = DEFAULT_MIN_SQUAD_SIZE;
	}

	public NormalSquadAttackMove(int minSquadSize) {
		this.minSquadSize = minSquadSize;
	}

	public NormalSquadAttackMove(SquadCommander agent, SquadAttackMoveGoal goal) {
		super(agent, goal);
		minSquadSize = DEFAULT_MIN_SQUAD_SIZE;
	}
	
	public NormalSquadAttackMove(SquadCommander agent, SquadAttackMoveGoal goal, int minSquadSize) {
		super(agent, goal);
		this.minSquadSize = minSquadSize;
	}

	
	
//	public NormalSquadAttackMove(SquadCommander agent, Position attackTarget) {
//		super(agent, attackTarget);
//	}
	
	
	@Override
	protected void performAction() throws ChainOfCommandViolationException {
		List<Marine> marines = getCommandedAgents(Marine.class);
		if(marines.size() >= minSquadSize){
			for (Marine marine : marines) {
				if(marine.isIdle()){
					new AttackMoveOrder(marine, agent, attackTarget).issueOrder();
				}
			}
		}
	}

	@Override
	protected void init() {
//		minSquadSize = DEFAULT_MIN_SQUAD_SIZE;
	}
	
	@Override
	public Element getXml(Document document) {
		Element activity = document.createElement("normalSquadAttackMove");
		activity.setAttribute("minSquadSize", Integer.toString(minSquadSize));
		
		
		return activity;
	}
	
	@Override
	public String getId() {
		return "normalSquadAttackMove";
	}

	@Override
	public NormalSquadAttackMove create(SquadCommander agent, SquadAttackMoveGoal goal) {
		return new NormalSquadAttackMove(agent, goal, minSquadSize);
	}

	@Override
	public StorableDecisionModuleActivity getFromXml(Element activityElement) {
		int minSquadSize = Integer.parseInt(activityElement.getAttribute("minSquadSize"));
		return new NormalSquadAttackMove(minSquadSize);
	}
}
