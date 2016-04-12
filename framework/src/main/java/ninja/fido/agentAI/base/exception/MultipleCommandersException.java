/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.base.exception;

import ninja.fido.agentAI.base.Commander;

/**
 *
 * @author F.I.D.O.
 */
public class MultipleCommandersException extends Exception{
	private final Class<? extends Commander> oldCommanderClass;
	
	private final Class<? extends Commander> newCommanderClass;
	
	
	

	public MultipleCommandersException(Class<? extends Commander> oldCommanderClass, Class<? extends Commander> newCommanderClass) {
		this.oldCommanderClass = oldCommanderClass;
		this.newCommanderClass = newCommanderClass;
	}
	
	
	@Override
	public String getMessage() {
		return newCommanderClass + ": commander cannot be created because another commander has been already created "
				+ "in this game (" + oldCommanderClass + ").";
	}
}