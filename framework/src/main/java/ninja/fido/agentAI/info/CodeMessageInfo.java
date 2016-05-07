/* 
 * AgentAI
 */
package ninja.fido.agentAI.info;

import ninja.fido.agentAI.base.Agent;
import ninja.fido.agentAI.base.Info;

/**
 *
 * @author F.I.D.O.
 */
public class CodeMessageInfo extends Info{
	
	public enum Code{
		OVERLORD_MORPHED
	}
	
	private final Code code;

	
	
	
	public Code getCode() {
		return code;
	}

	
	
	
	public CodeMessageInfo(Code code, Agent recipient, Agent sender) {
		super(recipient, sender);
		this.code = code;
	}

	
}
