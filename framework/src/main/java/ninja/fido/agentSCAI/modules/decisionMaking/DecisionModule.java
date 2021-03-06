/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI.modules.decisionMaking;

import ninja.fido.agentSCAI.UnitDecisionSetting;
import ninja.fido.agentSCAI.base.Agent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ninja.fido.agentSCAI.base.Commander;
import ninja.fido.agentSCAI.base.GameAPIModule;

/**
 *
 * @author F.I.D.O.
 */
public class DecisionModule implements GameAPIModule{
	private final Set<Class<? extends Agent>> registeredAgentsTypes;
	
	private final Map<Class<? extends Agent>,Map<DecisionTablesMapKey,DecisionTable>> decisionSettings;

	public DecisionModule() {
		decisionSettings = new HashMap<>();
		registeredAgentsTypes = new HashSet<>();
	}
	
		
	public Map<DecisionTablesMapKey,DecisionTable> getDecisionTablesMap(Class<? extends Agent> agentClass){
		return decisionSettings.get(agentClass);
	}
	
	public void addDecisionTablesMap(Class<? extends Agent> agentClass,
			Map<DecisionTablesMapKey,DecisionTable> decisionTablesMap){
		if(decisionSettings.containsKey(agentClass)){
			mergeDecisionTablesMaps(agentClass, decisionTablesMap);
		}
		else{
			decisionSettings.put(agentClass, decisionTablesMap);
		}
	}
	
	public void addDecisionTablesMap(UnitDecisionSetting unitDecisionSetting){
		addDecisionTablesMap(unitDecisionSetting.getAgentClass(), unitDecisionSetting.getDecisionTablesMap());
	}
	
	public void rewriteDecisionTablesMap(Class<? extends Agent> agentClass,
			Map<DecisionTablesMapKey,DecisionTable> decisionTablesMap){
		decisionSettings.put(agentClass, decisionTablesMap);
	}
	
	public boolean isDecisionMakingOn(Agent agent){
		return registeredAgentsTypes.contains(agent.getClass());
	}
	
	public void registerAgentClass(Agent agent){
		registeredAgentsTypes.add(agent.getClass());
		Map<DecisionTablesMapKey,DecisionTable> decisionTablesMap = agent.getDefaultDecisionTablesMap();
		if(decisionTablesMap != null){
			addDecisionTablesMap(agent.getClass(), decisionTablesMap);
		}
	}
	
	public void registerCommanderType(Class<? extends Commander> commanderClass, 
			Map<DecisionTablesMapKey,DecisionTable> decisionTablesMap){
		registeredAgentsTypes.add(commanderClass);
		if(decisionTablesMap != null){
			addDecisionTablesMap(commanderClass, decisionTablesMap);
		}
	}
	
	private void mergeDecisionTablesMaps(Class<? extends Agent> agentClass,
			Map<DecisionTablesMapKey,DecisionTable> decisionTablesMap){
		Map<DecisionTablesMapKey,DecisionTable> oldDecisionTablesMap = decisionSettings.get(agentClass);
		
		for (Map.Entry<DecisionTablesMapKey, DecisionTable> entry : decisionTablesMap.entrySet()) {
			DecisionTablesMapKey decisionTablesMapKey = entry.getKey();
			DecisionTable decisionTable = entry.getValue();
			oldDecisionTablesMap.put(decisionTablesMapKey, decisionTable);
		}
	}

	@Override
	public void onRun() {

	}

	@Override
	public void onEnd(boolean winner, int score) {
		
	}

	@Override
	public void onStart(int gameCount) {
		
	}

	@Override
	public List<Class<? extends GameAPIModule>> getDependencies() {
		return null;
	}

	
	
}
