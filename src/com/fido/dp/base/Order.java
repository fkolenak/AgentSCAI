/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fido.dp.base;

/**
 *
 * @author F.I.D.O.
 */
public abstract class Order {
	
	protected final CommandAgent commandAgent;
	
	private final Agent target;

	
	
	public Order(Agent target, CommandAgent commandAgent) {
		this.target = target;
		this.commandAgent = commandAgent;
	}
	
	
	
	protected abstract void execute();
	
	public final void issueCommand(){
		target.addToCommandQueue(this);
	}
	
	public void reportCompleted(){
		commandAgent.reportOrderCompleted(this);
	}
	
	protected <T> T getTarget(){
		return (T) target;
	}
}
