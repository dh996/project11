package dh.project11.packs.simul.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dh.project11.packs.com.CreateID;
import dh.project11.packs.simul.object.BattlePower;
import dh.project11.packs.simul.object.ChampCondition;
import dh.project11.packs.simul.object.ChampGrow;
import dh.project11.packs.simul.object.SimulInfo;
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.TeamFightInfo;
import dh.project11.packs.simul.object.TeamInfo;
import dh.project11.packs.simul.object.TowerCondition;
import dh.project11.packs.simul.object.TowerCondition.AttackRoute;
import dh.project11.packs.simul.object.TurnInfo;

@Component
public class SimulTeamFight {
	
	@Autowired
	CreateID createID;
	
	@Autowired
	SimulLogging simulLogging;
	
	@Autowired
	Random random;
	
	@Autowired
	SimulTurn simulTurn;

	List<SimulLogMessage> towerLog = new ArrayList<>();
	
	public boolean startGame(SimulInfo simulInfo, String sid) {
		// TODO Auto-generated method stub
		TeamFightInfo teamFightInfo = new TeamFightInfo();
		teamFightInfo.setSid(sid);
		simulLogging.setSid(sid);
		teamFightInfo.setUserTeamInfo(simulInfo.getUserTeam());
		teamFightInfo.setEnemyTeamInfo(simulInfo.getEnemyTeam());
		teamFightInfo.setTeamFightCount(0);
		teamFightInfo.setTowerU(defaultTowerSetting());
		teamFightInfo.setTowerE(defaultTowerSetting());
		teamFightInfo.setSpawnBaron(false);
		teamFightInfo.setSpawnDragon(false);
		teamFightInfo.setSpawnElder(false);
		teamFightInfo.setSpawnBaronCount(0);
		teamFightInfo.setSpawnDragonCount(0);
		teamFightInfo.setSpawnElderCount(0);
		List<String> dragonType = new ArrayList<>();
		dragonType.add("화염");
		dragonType.add("대지");
		dragonType.add("바다");
		dragonType.add("바람");
		dragonType.add("마법공학");
		dragonType.add("화학공학");
		teamFightInfo.setDragonTypeList(dragonType);
		return teamFightRoop(teamFightInfo, 1);
	}
	
	private TowerCondition defaultTowerSetting() {
		TowerCondition towerCondition = new TowerCondition();
		AttackRoute attackRoute = new AttackRoute();
		towerCondition.setTop(attackRoute);
		towerCondition.getTop().setFristTower(1000);
		towerCondition.getTop().setSecondTower(1000);
		towerCondition.getTop().setThirdTower(1000);
		towerCondition.getTop().setInhibitor(1000);
		towerCondition.setMid(attackRoute);
		towerCondition.getMid().setFristTower(1000);
		towerCondition.getMid().setSecondTower(1000);
		towerCondition.getMid().setThirdTower(1000);
		towerCondition.getMid().setInhibitor(1000);
		towerCondition.setBot(attackRoute);
		towerCondition.getBot().setFristTower(1000);
		towerCondition.getBot().setSecondTower(1000);
		towerCondition.getBot().setThirdTower(1000);
		towerCondition.getBot().setInhibitor(1000);
		towerCondition.setTwinTowerLeft(1000);
		towerCondition.setTwinTowerRight(1000);
		towerCondition.setNexus(1000);
		return towerCondition;
	}

	private boolean teamFightRoop(TeamFightInfo teamFightInfo, int count) {
		teamFightInfo = teamFight(count, teamFightInfo);
		int gameSet = checkNexus(teamFightInfo);
		if(gameSet==1){
			return true;
		}else if(gameSet==2){
			return false;
		}else {
			return teamFightRoop(teamFightInfo, count+1);
		}
	}
	
	private TeamFightInfo teamFight(int count, TeamFightInfo teamFightInfo) {
		String fid = createID.createFID();
		simulLogging.setFid(fid);
		teamFightInfo.setFid(fid);
		teamFightInfo.setTeamFightCount(count);
		List<SimulLogMessage> teamFightLog = new ArrayList<>();
		teamFightInfo.setTeamFightLog(teamFightLog);
		AboutFieldLogic battleFieldLogic = new AboutFieldLogic(teamFightInfo.getTowerU(), teamFightInfo.getTowerE());
		String battleField = battleFieldLogic.battleFieldSetting(
				teamFightInfo.isSpawnBaron(), teamFightInfo.isSpawnDragon(), teamFightInfo.isSpawnElder());
		teamFightInfo.setBattleField(battleField);
		teamFightLog.add(simulLogging.battleFieldLogging(count, battleField));
		teamFightInfo.setTeamFightLog(teamFightLog);
		teamFightInfo = setChampGrow(teamFightInfo);
		TeamFightInfo teamFightInfo2 = new TeamFightInfo();
		if(battleField.equals("freeze")) {
			teamFightInfo2 = teamFightInfo;
		}else {
			Map<String, Object> turnData = new HashMap<>();
			turnData = simulTurn.turnRoop(teamFightInfo);
			TurnInfo turnInfo = (TurnInfo) turnData.get("TurnInfo");
			teamFightInfo2 = (TeamFightInfo) turnData.get("TeamFightInfo");
			boolean winner = checkFightWinner(turnInfo);
			List<String> checkAlive = checkAlive(turnInfo, winner);
			teamFightInfo2 = takeAdvantage(winner, teamFightInfo2, turnInfo, count, checkAlive);
			teamFightInfo2 = failTeamObject(winner, teamFightInfo2);
		}
		teamFightInfo = spawnObjectLogic(teamFightInfo);
		return teamFightInfo2;
	}
	
	private int checkNexus(TeamFightInfo teamFightInfo) {
		// TODO Auto-generated method stub
		if(teamFightInfo.getTowerU().getNexus()==0) {
			return 1;
		}else if(teamFightInfo.getTowerE().getNexus()==0) {
			return 2;
		}else {
			return 3;
		}
	}

	private TeamFightInfo failTeamObject(boolean winner, TeamFightInfo teamFightInfo2) {
		// TODO Auto-generated method stub
		if(winner==true) {
			TeamInfo resetObjBuff = resetObjectBuff(teamFightInfo2.getEnemyTeamInfo());
			teamFightInfo2.setEnemyTeamInfo(resetObjBuff);
		}else {
			TeamInfo resetObjBuff = resetObjectBuff(teamFightInfo2.getUserTeamInfo());
			teamFightInfo2.setUserTeamInfo(resetObjBuff);
		}
		return teamFightInfo2;
	}

	private List<String> checkAlive(TurnInfo turnInfo, boolean winner) {
		// TODO Auto-generated method stub
		List<String> alive = new ArrayList<>();
		ChampCondition champCondition = new ChampCondition();
		if(winner==true) {
			champCondition = turnInfo.getChampConditionU();
			if(champCondition.isActive1()==true) {
				alive.add("1");
			}
			if(champCondition.isActive2()==true) {
				alive.add("2");
			}
			if(champCondition.isActive3()==true) {
				alive.add("3");
			}
			if(champCondition.isActive4()==true) {
				alive.add("4");
			}
			if(champCondition.isActive5()==true) {
				alive.add("5");
			}
		}else {
			champCondition = turnInfo.getChampConditionE();
			if(champCondition.isActive1()==true) {
				alive.add("1");
			}
			if(champCondition.isActive2()==true) {
				alive.add("2");
			}
			if(champCondition.isActive3()==true) {
				alive.add("3");
			}
			if(champCondition.isActive4()==true) {
				alive.add("4");
			}
			if(champCondition.isActive5()==true) {
				alive.add("5");
			}
		}
		return alive;
	}

	private TeamFightInfo takeAdvantage(boolean winner, TeamFightInfo teamFightInfo2, 
			TurnInfo turnInfo, int count, List<String> checkAlive) {
		// TODO Auto-generated method stub
		List<BattlePower> battlePower = new ArrayList<>();
		List<SimulLogMessage> teamFightLog = teamFightInfo2.getTeamFightLog();
		boolean chanceToWin = false;
		if(winner==true) {
			battlePower = simulTurn.battlePowerSetting(
					turnInfo, teamFightInfo2.getUserTeamInfo(), turnInfo.getChampConditionU());
			chanceToWin = checkTowerHp(teamFightInfo2.getTowerE(), teamFightInfo2.getBattleField());
			TeamInfo resetObjBuff = resetObjectBuff(teamFightInfo2.getUserTeamInfo());
			teamFightInfo2.setUserTeamInfo(resetObjBuff);
		}else {
			battlePower = simulTurn.battlePowerSetting(
					turnInfo, teamFightInfo2.getEnemyTeamInfo(), turnInfo.getChampConditionE());
			chanceToWin = checkTowerHp(teamFightInfo2.getTowerU(), teamFightInfo2.getBattleField());
			TeamInfo resetObjBuff = resetObjectBuff(teamFightInfo2.getEnemyTeamInfo());
			teamFightInfo2.setEnemyTeamInfo(resetObjBuff);
		}
		int battlePowerSum = 0;
		for(int i=0; i<battlePower.size(); i++) {
			battlePowerSum += battlePower.get(i).getBattlePower();
		}
		boolean spawnBaron = teamFightInfo2.isSpawnBaron();
		boolean spawnDragon = teamFightInfo2.isSpawnDragon();
		boolean spawnElder = teamFightInfo2.isSpawnElder();
		if(chanceToWin==false) {
			if(spawnElder==true && battlePowerSum>260) {
				teamFightInfo2.setSpawnElder(false);
				teamFightLog.add(simulLogging.killElder(winner, count));
				teamFightInfo2 = getElderBuff(teamFightInfo2, turnInfo, winner);
			}else if(spawnBaron==true && battlePowerSum>300) {
				teamFightInfo2.setSpawnBaron(false);
				teamFightLog.add(simulLogging.killBaron(winner, count));
				teamFightInfo2 = getBaronBuff(teamFightInfo2, turnInfo, winner);
			}else if(spawnDragon==true && battlePowerSum>120) {
				teamFightInfo2.setSpawnDragon(false);
				teamFightLog.add(simulLogging.killDragon(winner, teamFightInfo2.getDragonType(), count));
				teamFightInfo2 = getDragonBuff(teamFightInfo2, winner);
			}else {
				teamFightInfo2 = attackOnBase(battlePowerSum, teamFightInfo2, winner, count);
			}
		}else {
			teamFightInfo2 = attackOnBase(battlePowerSum, teamFightInfo2, winner, count);
		}
		return teamFightInfo2;
	}

	private TeamFightInfo attackOnBase(int battlePowerSum, 
			TeamFightInfo teamFightInfo2, boolean winner, int count) {
		// TODO Auto-generated method stub
		List<SimulLogMessage> teamFightLog = teamFightInfo2.getTeamFightLog();
		String battleField = teamFightInfo2.getBattleField();
		int damage = battlePowerSum*(10+count);
		damage /= 10;
		TowerCondition towerHp = new TowerCondition();
		if(winner==true) {
			towerHp = teamFightInfo2.getTowerE();
		}else {
			towerHp = teamFightInfo2.getTowerU();
		}
		if(battleField.equals("topLine") ||
				battleField.equals("topFirstTowerU") ||
				battleField.equals("topFirstTowerE") ||
				battleField.equals("topSecondTowerU") ||
				battleField.equals("topSecondTowerE") ||
				battleField.equals("topThirdTowerU") ||
				battleField.equals("topThirdTowerE")) {
			int lineHp = towerHp.getTop().getFristTower()+
					towerHp.getTop().getSecondTower()+
					towerHp.getTop().getThirdTower()+
					towerHp.getTop().getInhibitor();
			if(lineHp>damage) {
				int afterHp = lineHp-damage;
				towerHp = towerAttack("top", "top", afterHp, towerHp, winner, count);
			}else if(lineHp>0){
				towerHp = towerAttack("top", "top", 0, towerHp, winner, count);
				int afterHp = damage-lineHp;
				towerHp = towerAttack("top", "base", afterHp, towerHp, winner, count);
			}else {
				int afterHp = damage-lineHp;
				towerHp = towerAttack("top", "base", afterHp, towerHp, winner, count);
			}
		}else if(battleField.equals("midLine") ||
				battleField.equals("midFirstTowerU") ||
				battleField.equals("midFirstTowerE") ||
				battleField.equals("midSecondTowerU") ||
				battleField.equals("midSecondTowerE") ||
				battleField.equals("midThirdTowerU") ||
				battleField.equals("midThirdTowerE")) {
			int lineHp = towerHp.getMid().getFristTower()+
					towerHp.getMid().getSecondTower()+
					towerHp.getMid().getThirdTower()+
					towerHp.getMid().getInhibitor();
			if(lineHp>damage) {
				int afterHp = lineHp-damage;
				towerHp = towerAttack("mid", "mid", afterHp, towerHp, winner, count);
			}else if(lineHp>0){
				towerHp = towerAttack("mid", "mid", 0, towerHp, winner, count);
				int afterHp = damage-lineHp;
				towerHp = towerAttack("mid", "base", afterHp, towerHp, winner, count);
			}else {
				int afterHp = damage-lineHp;
				towerHp = towerAttack("mid", "base", afterHp, towerHp, winner, count);
			}
		}else if(battleField.equals("bottomLine") ||
				battleField.equals("botFirstTowerU") ||
				battleField.equals("botFirstTowerE") ||
				battleField.equals("botSecondTowerU") ||
				battleField.equals("botSecondTowerE") ||
				battleField.equals("botThirdTowerU") ||
				battleField.equals("botThirdTowerE")) {
			int lineHp = towerHp.getBot().getFristTower()+
					towerHp.getBot().getSecondTower()+
					towerHp.getBot().getThirdTower()+
					towerHp.getBot().getInhibitor();
			if(lineHp>damage) {
				int afterHp = lineHp-damage;
				towerHp = towerAttack("bot", "bot", afterHp, towerHp, winner, count);
			}else if(lineHp>0){
				towerHp = towerAttack("bot", "bot", 0, towerHp, winner, count);
				int afterHp = damage-lineHp;
				towerHp = towerAttack("bot", "base", afterHp, towerHp, winner, count);
			}else {
				int afterHp = damage-lineHp;
				towerHp = towerAttack("bot", "base", afterHp, towerHp, winner, count);
			}
		}else {
			int topHp = towerHp.getTop().getFristTower()+
					towerHp.getTop().getSecondTower()+
					towerHp.getTop().getThirdTower()+
					towerHp.getTop().getInhibitor();
			int midHp = towerHp.getMid().getFristTower()+
					towerHp.getMid().getSecondTower()+
					towerHp.getMid().getThirdTower()+
					towerHp.getMid().getInhibitor();
			int botHp = towerHp.getBot().getFristTower()+
					towerHp.getBot().getSecondTower()+
					towerHp.getBot().getThirdTower()+
					towerHp.getBot().getInhibitor();
			int target = random.nextInt(topHp+midHp+botHp);
			if(topHp==0) {
				int lineHp = topHp;
				if(lineHp>damage) {
					int afterHp = lineHp-damage;
					towerHp = towerAttack("top", "top", afterHp, towerHp, winner, count);
				}else {
					int afterHp = damage-lineHp;
					towerHp = towerAttack("top", "base", afterHp, towerHp, winner, count);
				}
			}else if(midHp==0) {
				int lineHp = midHp;
				if(lineHp>damage) {
					int afterHp = lineHp-damage;
					towerHp = towerAttack("mid", "mid", afterHp, towerHp, winner, count);
				}else {
					int afterHp = damage-lineHp;
					towerHp = towerAttack("mid", "base", afterHp, towerHp, winner, count);
				}
			}else if(botHp==0) {
				int lineHp = botHp;
				if(lineHp>damage) {
					int afterHp = lineHp-damage;
					towerHp = towerAttack("bot", "bot", afterHp, towerHp, winner, count);
				}else {
					int afterHp = damage-lineHp;
					towerHp = towerAttack("bot", "base", afterHp, towerHp, winner, count);
				}
			}else if(target<topHp) {
				int lineHp = topHp;
				if(lineHp>damage) {
					int afterHp = lineHp-damage;
					towerHp = towerAttack("top", "top", afterHp, towerHp, winner, count);
				}else if(lineHp>0){
					towerHp = towerAttack("top", "top", 0, towerHp, winner, count);
					int afterHp = damage-lineHp;
					towerHp = towerAttack("top", "base", afterHp, towerHp, winner, count);
				}else {
					int afterHp = damage-lineHp;
					towerHp = towerAttack("top", "base", afterHp, towerHp, winner, count);
				}
			}else if(topHp<=target && target<midHp) {
				int lineHp = midHp;
				if(lineHp>damage) {
					int afterHp = lineHp-damage;
					towerHp = towerAttack("mid", "mid", afterHp, towerHp, winner, count);
				}else if(lineHp>0){
					towerHp = towerAttack("mid", "mid", 0, towerHp, winner, count);
					int afterHp = damage-lineHp;
					towerHp = towerAttack("mid", "base", afterHp, towerHp, winner, count);
				}else {
					int afterHp = damage-lineHp;
					towerHp = towerAttack("mid", "base", afterHp, towerHp, winner, count);
				}
			}else {
				int lineHp = botHp;
				if(lineHp>damage) {
					int afterHp = lineHp-damage;
					towerHp = towerAttack("bot", "bot", afterHp, towerHp, winner, count);
				}else if(lineHp>0){
					towerHp = towerAttack("bot", "bot", 0, towerHp, winner, count);
					int afterHp = damage-lineHp;
					towerHp = towerAttack("bot", "base", afterHp, towerHp, winner, count);
				}else {
					int afterHp = damage-lineHp;
					towerHp = towerAttack("bot", "base", afterHp, towerHp, winner, count);
				}
			}
		}
		if(winner==true) {
			teamFightInfo2.setTowerE(towerHp);
		}else {
			teamFightInfo2.setTowerU(towerHp);
		}
		for(int i=0; i<towerLog.size(); i++) {
			teamFightLog.add(towerLog.get(i));
		}
		teamFightInfo2.setTeamFightLog(teamFightLog);
		towerLog.clear();
		return teamFightInfo2;
	}

	private TowerCondition towerAttack(String from, String to, 
			int afterHp, TowerCondition towerHp, boolean winner, int count) {
		// TODO Auto-generated method stub
		if(to.equals("top")) {
			if(towerHp.getTop().getFristTower()>0) {
				if(afterHp<=3000) {
					towerLog.add(simulLogging.destroyTower("TopFirstTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(1000);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setTop(line);
				}else {
					towerLog.add(simulLogging.attackTower("TopFirstTower", 4000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(4000-afterHp);
					line.setSecondTower(1000);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setTop(line);
				}
			}
			if(towerHp.getTop().getFristTower()==0 && towerHp.getTop().getSecondTower()>0) {
				if(afterHp<=2000) {
					towerLog.add(simulLogging.destroyTower("TopSecondTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setTop(line);
				}else {
					towerLog.add(simulLogging.attackTower("TopSecondTower", 3000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(3000-afterHp);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setTop(line);
				}
			}
			if(towerHp.getTop().getSecondTower()==0 && towerHp.getTop().getThirdTower()>0) {
				if(afterHp<=1000) {
					towerLog.add(simulLogging.destroyTower("TopThirdTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(1000);
					towerHp.setTop(line);
				}else {
					towerLog.add(simulLogging.attackTower("TopThirdTower", 2000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(2000-afterHp);
					line.setInhibitor(1000);
					towerHp.setTop(line);
				}
			}
			if(towerHp.getTop().getThirdTower()==0 && towerHp.getTop().getInhibitor()>0) {
				if(afterHp<=0) {
					towerLog.add(simulLogging.destroyTower("TopInhibitor", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(0);
					towerHp.setTop(line);
				}else {
					towerLog.add(simulLogging.attackTower("TopInhibitor", 1000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(1000-afterHp);
					towerHp.setTop(line);
				}
			}
		}else if(to.equals("mid")) {
			if(towerHp.getMid().getFristTower()>0) {
				if(afterHp<=3000) {
					towerLog.add(simulLogging.destroyTower("MidFirstTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(1000);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setMid(line);
				}else {
					towerLog.add(simulLogging.attackTower("MidFirstTower", 4000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(4000-afterHp);
					line.setSecondTower(1000);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setMid(line);
				}
			}
			if(towerHp.getMid().getFristTower()==0 && towerHp.getMid().getSecondTower()>0) {
				if(afterHp<=2000) {
					towerLog.add(simulLogging.destroyTower("MidSecondTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setMid(line);
				}else {
					towerLog.add(simulLogging.attackTower("MidSecondTower", 3000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(3000-afterHp);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setMid(line);
				}
			}
			if(towerHp.getMid().getSecondTower()==0 && towerHp.getMid().getThirdTower()>0) {
				if(afterHp<=1000) {
					towerLog.add(simulLogging.destroyTower("MidThirdTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(1000);
					towerHp.setMid(line);
				}else {
					towerLog.add(simulLogging.attackTower("MidThirdTower", 2000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(2000-afterHp);
					line.setInhibitor(1000);
					towerHp.setMid(line);
				}
			}
			if(towerHp.getMid().getThirdTower()==0 && towerHp.getMid().getInhibitor()>0) {
				if(afterHp<=0) {
					towerLog.add(simulLogging.destroyTower("MidInhibitor", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(0);
					towerHp.setMid(line);
				}else {
					towerLog.add(simulLogging.attackTower("MidInhibitor", 1000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(1000-afterHp);
					towerHp.setMid(line);
				}
			}
		}else if(to.equals("bot")) {
			if(towerHp.getBot().getFristTower()>0) {
				if(afterHp<=3000) {
					towerLog.add(simulLogging.destroyTower("BotFirstTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(1000);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setBot(line);
				}else {
					towerLog.add(simulLogging.attackTower("BotFirstTower", 4000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(4000-afterHp);
					line.setSecondTower(1000);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setBot(line);
				}
			}
			if(towerHp.getBot().getFristTower()==0 && towerHp.getBot().getSecondTower()>0) {
				if(afterHp<=2000) {
					towerLog.add(simulLogging.destroyTower("BotSecondTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setBot(line);
				}else {
					towerLog.add(simulLogging.attackTower("BotSecondTower", 3000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(3000-afterHp);
					line.setThirdTower(1000);
					line.setInhibitor(1000);
					towerHp.setBot(line);
				}
			}
			if(towerHp.getBot().getSecondTower()==0 && towerHp.getBot().getThirdTower()>0) {
				if(afterHp<=1000) {
					towerLog.add(simulLogging.destroyTower("BotThirdTower", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(1000);
					towerHp.setBot(line);
				}else {
					towerLog.add(simulLogging.attackTower("BotThirdTower", 2000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(2000-afterHp);
					line.setInhibitor(1000);
					towerHp.setBot(line);
				}
			}
			if(towerHp.getBot().getThirdTower()==0 && towerHp.getBot().getInhibitor()>0) {
				if(afterHp<=0) {
					towerLog.add(simulLogging.destroyTower("BotInhibitor", winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(0);
					towerHp.setBot(line);
				}else {
					towerLog.add(simulLogging.attackTower("BotInhibitor", 1000-afterHp, winner, count));
					AttackRoute line = new AttackRoute();
					line.setFristTower(0);
					line.setSecondTower(0);
					line.setThirdTower(0);
					line.setInhibitor(1000-afterHp);
					towerHp.setBot(line);
				}
			}
		}else {
//			if(from.equals("top")) {
//				if(towerHp.getTop().getInhibitor()>0) {
//					towerLog.add(simulLogging.freePassLine("Top", winner));
//					towerHp.getTop().setFristTower(0);
//					towerHp.getTop().setSecondTower(0);
//					towerHp.getTop().setThirdTower(0);
//					towerHp.getTop().setInhibitor(0);
//				}
//			}else if(from.equals("mid")) {
//				if(towerHp.getMid().getInhibitor()>0) {
//					towerLog.add(simulLogging.freePassLine("Mid", winner));
//					towerHp.getMid().setFristTower(0);
//					towerHp.getMid().setSecondTower(0);
//					towerHp.getMid().setThirdTower(0);
//					towerHp.getMid().setInhibitor(0);
//				}
//			}else{
//				if(towerHp.getBot().getInhibitor()>0) {
//					towerLog.add(simulLogging.freePassLine("Bot", winner));
//					towerHp.getBot().setFristTower(0);
//					towerHp.getBot().setSecondTower(0);
//					towerHp.getBot().setThirdTower(0);
//					towerHp.getBot().setInhibitor(0);
//				}
//			}
			int baseHp = towerHp.getNexus()+towerHp.getTwinTowerLeft()+towerHp.getTwinTowerRight();
			int baseLateHp = baseHp-afterHp;
			if(towerHp.getTwinTowerLeft()>0) {
				if(baseLateHp<=2000) {
					towerLog.add(simulLogging.destroyTower("TwinTowerLeft", winner, count));
					towerHp.setTwinTowerLeft(0);
					baseLateHp -= 1000;
				}else {
					towerLog.add(simulLogging.attackTower("TwinTowerLeft", 3000-baseLateHp, winner, count));
					towerHp.setTwinTowerLeft(3000-baseLateHp);
				}
			}
			if(towerHp.getTwinTowerLeft()==0 && towerHp.getTwinTowerRight()>0) {
				if(baseLateHp<=1000) {
					towerLog.add(simulLogging.destroyTower("TwinTowerRight", winner, count));
					towerHp.setTwinTowerRight(0);
					baseLateHp -= 1000;
				}else {
					towerLog.add(simulLogging.attackTower("TwinTowerRight", 2000-baseLateHp, winner, count));
					towerHp.setTwinTowerRight(2000-baseLateHp);
				}
			}
			if(towerHp.getTwinTowerRight()==0 && towerHp.getNexus()>0) {
				if(baseLateHp<=0) {
					towerLog.add(simulLogging.destroyTower("Nexus", winner, count));
					towerHp.setNexus(0);
					baseLateHp -= 1000;
				}else {
					towerLog.add(simulLogging.attackTower("Nexus", 1000-baseLateHp, winner, count));
					towerHp.setNexus(1000-baseLateHp);
				}
			}
		}
		return towerHp;
	}

	private TeamInfo resetObjectBuff(TeamInfo teamInfo) {
		// TODO Auto-generated method stub
		teamInfo.getGrow1().setBaronBuff(false);
		teamInfo.getGrow2().setBaronBuff(false);
		teamInfo.getGrow3().setBaronBuff(false);
		teamInfo.getGrow4().setBaronBuff(false);
		teamInfo.getGrow5().setBaronBuff(false);
		teamInfo.getGrow1().setElderBuff(false);
		teamInfo.getGrow2().setElderBuff(false);
		teamInfo.getGrow3().setElderBuff(false);
		teamInfo.getGrow4().setElderBuff(false);
		teamInfo.getGrow5().setElderBuff(false);
		return teamInfo;
	}

	private TeamFightInfo getElderBuff(TeamFightInfo teamFightInfo2, 
			TurnInfo turnInfo, boolean winner) {
		// TODO Auto-generated method stub
		TeamInfo teamInfo = new TeamInfo();
		ChampCondition condition = new ChampCondition();
		if(winner==true) {
			teamInfo = teamFightInfo2.getUserTeamInfo();
			condition = turnInfo.getChampConditionU();
		}else {
			teamInfo = teamFightInfo2.getEnemyTeamInfo();
			condition = turnInfo.getChampConditionE();
		}
		if(condition.isActive1()==true) {
			teamInfo.getGrow1().setElderBuff(true);;
		}
		if(condition.isActive2()==true) {
			teamInfo.getGrow2().setElderBuff(true);;
		}
		if(condition.isActive3()==true) {
			teamInfo.getGrow3().setElderBuff(true);;
		}
		if(condition.isActive4()==true) {
			teamInfo.getGrow4().setElderBuff(true);;
		}
		if(condition.isActive5()==true) {
			teamInfo.getGrow5().setElderBuff(true);;
		}
		if(winner==true) {
			teamFightInfo2.setUserTeamInfo(teamInfo);
		}else {
			teamFightInfo2.setEnemyTeamInfo(teamInfo);
		}
		return teamFightInfo2;
	}

	private TeamFightInfo getBaronBuff(TeamFightInfo teamFightInfo2, 
			TurnInfo turnInfo, boolean winner) {
		// TODO Auto-generated method stub
		TeamInfo teamInfo = new TeamInfo();
		ChampCondition condition = new ChampCondition();
		if(winner==true) {
			teamInfo = teamFightInfo2.getUserTeamInfo();
			condition = turnInfo.getChampConditionU();
		}else {
			teamInfo = teamFightInfo2.getEnemyTeamInfo();
			condition = turnInfo.getChampConditionE();
		}
		if(condition.isActive1()==true) {
			teamInfo.getGrow1().setBaronBuff(true);;
		}
		if(condition.isActive2()==true) {
			teamInfo.getGrow2().setBaronBuff(true);;
		}
		if(condition.isActive3()==true) {
			teamInfo.getGrow3().setBaronBuff(true);;
		}
		if(condition.isActive4()==true) {
			teamInfo.getGrow4().setBaronBuff(true);;
		}
		if(condition.isActive5()==true) {
			teamInfo.getGrow5().setBaronBuff(true);;
		}
		if(winner==true) {
			teamFightInfo2.setUserTeamInfo(teamInfo);
		}else {
			teamFightInfo2.setEnemyTeamInfo(teamInfo);
		}
		return teamFightInfo2;
	}

	private TeamFightInfo getDragonBuff(TeamFightInfo teamFightInfo2, boolean winner)  {
		// TODO Auto-generated method stub
		TeamInfo teamInfo = new TeamInfo();
		if(winner==true) {
			teamInfo = teamFightInfo2.getUserTeamInfo();
		}else {
			teamInfo = teamFightInfo2.getEnemyTeamInfo();
		}
		int stack = teamInfo.getDragonStack();
		if(stack<4) {
			stack ++;
		}
		teamInfo.setDragonStack(stack);
		if(winner==true) {
			teamFightInfo2.setUserTeamInfo(teamInfo);
		}else {
			teamFightInfo2.setEnemyTeamInfo(teamInfo);
		}
		return teamFightInfo2;
	}

	private boolean checkTowerHp(TowerCondition towerHp, String battleField) {
		// TODO Auto-generated method stub
		if(battleField.equals("topLine") ||
				battleField.equals("topFirstTowerU") ||
				battleField.equals("topFirstTowerE") ||
				battleField.equals("topSecondTowerU") ||
				battleField.equals("topSecondTowerE") ||
				battleField.equals("topThirdTowerU") ||
				battleField.equals("topThirdTowerE")) {
			if(towerHp.getTop().getThirdTower()<100) {
				return true;
			}else {
				return false;
			}
		}else if(battleField.equals("midLine") ||
				battleField.equals("midFirstTowerU") ||
				battleField.equals("midFirstTowerE") ||
				battleField.equals("midSecondTowerU") ||
				battleField.equals("midSecondTowerE") ||
				battleField.equals("midThirdTowerU") ||
				battleField.equals("midThirdTowerE")) {
			if(towerHp.getMid().getThirdTower()<100) {
				return true;
			}else {
				return false;
			}
		}else if(battleField.equals("bottomLine") ||
				battleField.equals("botFirstTowerU") ||
				battleField.equals("botFirstTowerE") ||
				battleField.equals("botSecondTowerU") ||
				battleField.equals("botSecondTowerE") ||
				battleField.equals("botThirdTowerU") ||
				battleField.equals("botThirdTowerE")) {
			if(towerHp.getTop().getThirdTower()<100) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}	
	}

	private boolean checkFightWinner(TurnInfo turnInfo) {
		// TODO Auto-generated method stub
		int hpU = turnInfo.getChampConditionU().getHpPer1()+
				turnInfo.getChampConditionU().getHpPer2()+
				turnInfo.getChampConditionU().getHpPer3()+
				turnInfo.getChampConditionU().getHpPer4()+
				turnInfo.getChampConditionU().getHpPer5();
		int hpE = turnInfo.getChampConditionE().getHpPer1()+
				turnInfo.getChampConditionE().getHpPer2()+
				turnInfo.getChampConditionE().getHpPer3()+
				turnInfo.getChampConditionE().getHpPer4()+
				turnInfo.getChampConditionE().getHpPer5();
		if(hpU>=hpE) {
			return true;
		}else {
			return false;
		}
	}

	private TeamFightInfo spawnObjectLogic(TeamFightInfo teamFightInfo) {
		boolean spawnBaron = teamFightInfo.isSpawnBaron();
		boolean spawnDragon = teamFightInfo.isSpawnDragon();
		boolean spawnElder = teamFightInfo.isSpawnElder();
		boolean elderSwitch = false;
		int spawnBaronCount = teamFightInfo.getSpawnBaronCount();
		int spawnDragonCount = teamFightInfo.getSpawnDragonCount();
		int spawnElderCount = teamFightInfo.getSpawnElderCount();
		int userDragonStack = teamFightInfo.getUserTeamInfo().getDragonStack();
		int enemyDragonStack = teamFightInfo.getEnemyTeamInfo().getDragonStack();
		List<SimulLogMessage> teamFightLog = teamFightInfo.getTeamFightLog();
		if(userDragonStack==4 || enemyDragonStack==4) {
			elderSwitch = true;
		}
		if(spawnBaronCount==7) {
			spawnBaron = true;
			spawnBaronCount = 2;
			teamFightLog.add(simulLogging.spawnBaronLogging(teamFightInfo.getTeamFightCount()));
			teamFightInfo.setSpawnBaronCount(spawnBaronCount);
			teamFightInfo.setSpawnBaron(spawnBaron);
		}
		if(spawnDragonCount==2) {
			spawnDragon = true;
			spawnDragonCount = 0;
			int dragonCount = userDragonStack+enemyDragonStack+1;
			if(dragonCount<3) {
				List<String>dragonList = teamFightInfo.getDragonTypeList();
				int dragonIndex = random.nextInt(dragonList.size());
				String elemental = dragonList.get(dragonIndex);
				dragonList.remove(dragonIndex);
				teamFightLog.add(simulLogging.spawnDragonLogging(dragonCount, elemental, teamFightInfo.getTeamFightCount()));
				teamFightInfo.setDragonTypeList(dragonList);
				teamFightInfo.setSpawnDragonCount(spawnDragonCount);
				teamFightInfo.setSpawnDragon(spawnDragon);
				teamFightInfo.setDragonType(elemental);
			}else {
				List<String>dragonList = teamFightInfo.getDragonTypeList();
				int dragonIndex = random.nextInt(dragonList.size());
				String elemental = dragonList.get(dragonIndex);
				List<String> dragonElemantal = new ArrayList<>();
				dragonElemantal.add(elemental);
				teamFightLog.add(simulLogging.spawnDragonLogging(dragonCount, elemental, teamFightInfo.getTeamFightCount()));
				teamFightInfo.setDragonTypeList(dragonElemantal);
				teamFightInfo.setSpawnDragonCount(spawnDragonCount);
				teamFightInfo.setSpawnDragon(spawnDragon);
				teamFightInfo.setDragonType(elemental);
			}
			
		}
		if(spawnElderCount==3) {
			spawnElder = true;
			spawnElderCount = 0;
			teamFightLog.add(simulLogging.spawnElderLogging(teamFightInfo.getTeamFightCount()));
			teamFightInfo.setSpawnElderCount(spawnElderCount);
			teamFightInfo.setSpawnElder(spawnElder);
		}
		if(spawnBaron==false) {
			spawnBaronCount ++;
			teamFightInfo.setSpawnBaronCount(spawnBaronCount);
		}
		if(spawnDragon==false && elderSwitch==false) {
			spawnDragonCount ++;
			teamFightInfo.setSpawnDragonCount(spawnDragonCount);
		}
		if(spawnElder==false && elderSwitch==true) {
			spawnElderCount ++;
			teamFightInfo.setSpawnElderCount(spawnElderCount);
		}
		return teamFightInfo;
	}
	
	private TeamFightInfo setChampGrow(TeamFightInfo teamFightInfo) {
		for(int i=1; i<6; i++) {
			teamFightInfo = growLogic(teamFightInfo, i);
		}
		return teamFightInfo;
	}
	
	private TeamFightInfo growLogic(TeamFightInfo teamFightInfo, int i) {
		TeamInfo userTeamInfo = teamFightInfo.getUserTeamInfo();
		TeamInfo enemyTeamInfo = teamFightInfo.getEnemyTeamInfo();
		List<SimulLogMessage> teamFightLog = teamFightInfo.getTeamFightLog();
		ChampGrow growU = new ChampGrow();
		ChampGrow growE = new ChampGrow();
		
		switch(i) {
		case 1: growU = userTeamInfo.getGrow1();
		        growE = enemyTeamInfo.getGrow1();
		        growU = levelUp(growU);
		        growE = levelUp(growE);
		        userTeamInfo.setGrow1(growU);
		        enemyTeamInfo.setGrow1(growE);
		        teamFightLog.add(simulLogging.levelUpLogging(
		        		growU.getLevel(), userTeamInfo.getChampName1(), true, teamFightInfo.getTeamFightCount()));
		        teamFightLog.add(simulLogging.levelUpLogging(
		        		growE.getLevel(), enemyTeamInfo.getChampName1(), false, teamFightInfo.getTeamFightCount()));
		        break;
		case 2: growU = userTeamInfo.getGrow2();
                growE = enemyTeamInfo.getGrow2();
                growU = levelUp(growU);
		        growE = levelUp(growE);
                userTeamInfo.setGrow2(growU);
                enemyTeamInfo.setGrow2(growE);
                teamFightLog.add(simulLogging.levelUpLogging(
                		growU.getLevel(), userTeamInfo.getChampName2(), true, teamFightInfo.getTeamFightCount()));
                teamFightLog.add(simulLogging.levelUpLogging(
                		growE.getLevel(), enemyTeamInfo.getChampName2(), false, teamFightInfo.getTeamFightCount()));
                break;
		case 3: growU = userTeamInfo.getGrow3();
                growE = enemyTeamInfo.getGrow3();
                growU = levelUp(growU);
		        growE = levelUp(growE);
                userTeamInfo.setGrow3(growU);
                enemyTeamInfo.setGrow3(growE);
                teamFightLog.add(simulLogging.levelUpLogging(
                		growU.getLevel(), userTeamInfo.getChampName3(), true, teamFightInfo.getTeamFightCount()));
                teamFightLog.add(simulLogging.levelUpLogging(
                		growE.getLevel(), enemyTeamInfo.getChampName3(), false, teamFightInfo.getTeamFightCount()));
                break;
		case 4: growU = userTeamInfo.getGrow4();
                growE = enemyTeamInfo.getGrow4();
                growU = levelUp(growU);
		        growE = levelUp(growE);
                userTeamInfo.setGrow4(growU);
                enemyTeamInfo.setGrow4(growE);
                teamFightLog.add(simulLogging.levelUpLogging(
                		growU.getLevel(), userTeamInfo.getChampName4(), true, teamFightInfo.getTeamFightCount()));
                teamFightLog.add(simulLogging.levelUpLogging(
                		growE.getLevel(), enemyTeamInfo.getChampName4(), false, teamFightInfo.getTeamFightCount()));
                break;
		case 5: growU = userTeamInfo.getGrow5();
                growE = enemyTeamInfo.getGrow5();
                growU = levelUp(growU);
		        growE = levelUp(growE);
                userTeamInfo.setGrow5(growU);
                enemyTeamInfo.setGrow5(growE);
                teamFightLog.add(simulLogging.levelUpLogging(
                		growU.getLevel(), userTeamInfo.getChampName5(), true, teamFightInfo.getTeamFightCount()));
                teamFightLog.add(simulLogging.levelUpLogging(
                		growE.getLevel(), enemyTeamInfo.getChampName5(), false, teamFightInfo.getTeamFightCount()));
                break;
		}

		return teamFightInfo;
	}
	
	private ChampGrow levelUp(ChampGrow grow) {
		int exp = grow.getExp();
		int level = grow.getLevel();
		int kill = grow.getKill();
		int death = grow.getDeath();
		int assist = grow.getAssist();
		String build = grow.getBuild();
		int getExp = random.nextInt(2000);
		getExp += 6000;
		getExp += (kill*50);
		getExp -= (death*20);
		getExp += (assist*10);
		if(build.equals("Support") || build.equals("ADSupport")
				|| build.equals("TankSupport") || build.equals("MageSupport")
				|| build.equals("DealSupport") || build.equals("UtilSupport")) {
			getExp *= 0.7;
		}
		if(build.equals("FureADC") || build.equals("FureMage")) {
			getExp *= 1.2;
		}
		level = growLevel(exp+getExp);
		grow.setLevel(level);
		grow.setExp(exp+getExp);
		return grow;
	}
	
	private int growLevel(int exp) {
		if(exp>=0 && exp<=5000) {
			return 1;
		}else if(exp>5000 && exp<=10000) {
			return 2;
		}else if(exp>10000 && exp<=15000) {
			return 3;
		}else if(exp>15000 && exp<=22000) {
			return 4;
		}else if(exp>22000 && exp<=29000) {
			return 5;
		}else if(exp>29000 && exp<=36000) {
			return 6;
		}else if(exp>36000 && exp<=44000) {
			return 7;
		}else if(exp>44000 && exp<=51000) {
			return 8;
		}else if(exp>51000 && exp<=60000) {
			return 9;
		}else if(exp>60000 && exp<=71000) {
			return 10;
		}else if(exp>71000 && exp<=85000) {
			return 11;
		}else if(exp>85000 && exp<=100000) {
			return 12;
		}else if(exp>100000 && exp<=116000) {
			return 13;
		}else if(exp>116000 && exp<=133000) {
			return 14;
		}else if(exp>133000 && exp<=150000) {
			return 15;
		}else if(exp>150000 && exp<=167000) {
			return 16;
		}else if(exp>167000 && exp<=185000) {
			return 17;
		}else{
			return 18;
		}
	}
}
