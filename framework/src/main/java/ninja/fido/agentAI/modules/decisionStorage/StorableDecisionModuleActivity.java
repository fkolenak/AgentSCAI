/* 
 * AgentAI
 */
package ninja.fido.agentAI.modules.decisionStorage;

import ninja.fido.agentAI.base.Activity;
import ninja.fido.agentAI.base.Agent;
import ninja.fido.agentAI.base.Goal;
import ninja.fido.agentAI.modules.decisionMaking.DecisionModuleActivity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author F.I.D.O.
 * @param <A> Agent
 * @param <G> Goal
 * @param <AC> Activity
 */
public interface StorableDecisionModuleActivity<A extends Agent,G extends Goal,AC extends Activity> 
		extends DecisionModuleActivity<A, G, AC>{
	
	public Element getXml(Document document);
	
	public String getId();

	public StorableDecisionModuleActivity getFromXml(Element activityElement);
}
