/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fido.dp.activity.protoss;

import bwapi.Position;
import com.fido.dp.agent.SquadCommander;
import com.fido.dp.agent.unit.HighTemplar;
import com.fido.dp.agent.unit.Zealot;
import com.fido.dp.base.CommandActivity;
import com.fido.dp.base.GameAPI;
import com.fido.dp.base.Goal;
import com.fido.dp.base.Info;
import com.fido.dp.base.UniversalGoalOrder;
import com.fido.dp.goal.GroupGuardGoal;
import com.fido.dp.goal.MoveGoal;
import com.fido.dp.info.GuardOnPositionInfo;
import java.util.ArrayList;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * @author F.I.D.O.
 */
public class FormationTestSquadFormationIndividual extends CommandActivity<SquadCommander, Goal>{
	
	private ArrayList<Zealot> zealotsOnPosition;
	
	private boolean zealotsOrdered;
	
	private Vector2D direction;
	

	public FormationTestSquadFormationIndividual(SquadCommander agent) {
		super(agent);
		zealotsOnPosition = new ArrayList<>();
		zealotsOrdered = false; 
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof FormationTestSquadFormationIndividual;
	}

	@Override
	protected void performAction() {
		ArrayList<Zealot> zealots = agent.getCommandedAgents(Zealot.class);
		HighTemplar highTemplar = agent.getCommandedAgent(HighTemplar.class);
		
		if(!zealotsOrdered && zealots.size() > 8){
			for (Zealot zealot : zealots) {
				new UniversalGoalOrder(
						zealot, agent, new GroupGuardGoal(zealot, null, highTemplar, zealots)).issueOrder();
			}
			zealotsOrdered = true;
		}
		if(GameAPI.getFrameCount() % 350 == 0){
			direction = new Vector2D(Math.random(), Math.random());
		}
		if(zealotsOnPosition.size() >= zealots.size()){
			Position highTemplarNewPosition = transferPosition(highTemplar.getUnit().getPosition(), direction);
			new UniversalGoalOrder(highTemplar, agent, new MoveGoal(highTemplar, null, 
							highTemplarNewPosition, 5)).issueOrder();
			zealotsOnPosition = new ArrayList<>();
		}
	}

	@Override
	protected void init() {
		direction = new Vector2D(1, 0);
	}

	@Override
	protected void processInfo(Info info) {
		if(info instanceof GuardOnPositionInfo){
			zealotsOnPosition.add((Zealot) info.getSender());
		}
	}
	
	private Position transferPosition(Position oldPosition, Vector2D direction) {
		int length = 20;
		direction = direction.scalarMultiply(length);
		int newX = oldPosition.getX() + (int) direction.getX();
		int newY = oldPosition.getY() + (int) direction.getY();
		return new Position(newX, newY);
	}
	
}