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
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.TeamFightInfo;
import dh.project11.packs.simul.object.TeamInfo;
import dh.project11.packs.simul.object.TurnInfo;

@Component
public class SimulTurn {
	
	@Autowired
	CreateID createId;
	
	@Autowired
	Random random;
	
	@Autowired
	Fighting fighting;

	public Map<String, Object> turnRoop(TeamFightInfo teamFightInfo) {
		// TODO Auto-generated method stub
		String sid = teamFightInfo.getSid();
		String fid = teamFightInfo.getFid();
		TurnInfo turnInfo = new TurnInfo();
		turnInfo.setSid(sid);
		turnInfo.setFid(fid);
		ChampCondition champCondition = new ChampCondition();
		champCondition.setHpPer1(100);
		champCondition.setHpPer2(100);
		champCondition.setHpPer3(100);
		champCondition.setHpPer4(100);
		champCondition.setHpPer5(100);
		champCondition.setActive1(true);
		champCondition.setActive2(true);
		champCondition.setActive3(true);
		champCondition.setActive4(true);
		champCondition.setActive5(true);
		turnInfo.setChampConditionU(champCondition);
		System.out.println("컨디션 세팅 확인"+turnInfo.getChampConditionU().getHpPer1());
		turnInfo.setChampConditionE(champCondition);
		List<SimulLogMessage> turnLog = new ArrayList<>();
		return turn(1, turnInfo, teamFightInfo, turnLog);
	}
	
	private Map<String, Object> turn(int count, TurnInfo turnInfo, TeamFightInfo teamFightInfo,
			List<SimulLogMessage> turnLog) {
		String sid = teamFightInfo.getSid();
		String fid = teamFightInfo.getFid();
		String tid;
		List<BattlePower> battleU = new ArrayList<>();
		List<BattlePower> battleE = new ArrayList<>();
		if(count == 1) {
			tid = createId.createTID();
			turnInfo.setTid(tid);
			battleU = battlePowerSetting(turnInfo, 
					teamFightInfo.getUserTeamInfo(), turnInfo.getChampConditionU());
			battleE = battlePowerSetting(turnInfo, 
					teamFightInfo.getEnemyTeamInfo(), turnInfo.getChampConditionE());
			turnInfo.setBattlePowerU(battleU);
			turnInfo.setBattlePowerE(battleE);
			turnInfo.setTurnLog(turnLog);
			for(int i=0; i<battleU.size(); i++) {
				System.out.println("turn 적용체크 배틀파워"+
				    turnInfo.getBattlePowerU().get(i).getName()+
				    turnInfo.getBattlePowerU().get(i).getLevel());
				System.out.println("turn 변수체크 배틀파워"+
				    battleU.get(i).getName()+battleU.get(i).getLevel());
			}
		}else {
			tid = turnInfo.getTid();
			battleU = turnInfo.getBattlePowerU();
			battleE = turnInfo.getBattlePowerE();
		}
		turnInfo.setTurnCount(count);
		
		fighting.resetValue();
		fighting.FightingSetting(teamFightInfo.getBattleField(),
				battleU, battleE, teamFightInfo.getTowerU(), teamFightInfo.getTowerE(),
				turnInfo.getChampConditionU(),turnInfo.getChampConditionE(),turnInfo.getTurnLog());
		fighting.setSid(sid);
		fighting.setFid(fid);
		fighting.setTid(tid);
		fighting.initPhase();
		List<BattlePower> battleU2 = fighting.getBattleU();
		List<BattlePower> battleE2 = fighting.getBattleE();
		turnLog = fighting.getFightLog();
		turnInfo.setTurnLog(turnLog);
		teamFightInfo.setUserTeamInfo(growSetting(battleU2, teamFightInfo.getUserTeamInfo(), count));
		teamFightInfo.setEnemyTeamInfo(growSetting(battleE2, teamFightInfo.getEnemyTeamInfo(), count));
		turnInfo.setChampConditionU(conditionSetting(
				battleU, battleU2, teamFightInfo.getUserTeamInfo()));
		turnInfo.setChampConditionE(conditionSetting(
				battleE, battleE2, teamFightInfo.getEnemyTeamInfo()));
		if(checkChapmCondition(turnInfo)) {
			count = 3;
		}
		if(count==3) {
			 Map<String, Object> turnData = new HashMap<>();
			 turnData.put("TurnInfo",turnInfo);
			 turnData.put("TeamFightInfo",teamFightInfo);
			return turnData;
		}else {
			return turn(count+1, turnInfo, teamFightInfo, turnLog);
		}
	}

	private boolean checkChapmCondition(TurnInfo turnInfo) {
		// TODO Auto-generated method stub
		int flagU = 0;
		int flagE = 0;
		ChampCondition ccU = turnInfo.getChampConditionU();
		ChampCondition ccE = turnInfo.getChampConditionE();
		if(ccU.isActive1()) {
			flagU +=1;
		}
		if(ccU.isActive2()) {
			flagU +=1;
		}
		if(ccU.isActive3()) {
			flagU +=1;
		}
		if(ccU.isActive4()) {
			flagU +=1;
		}
		if(ccU.isActive5()) {
			flagU +=1;
		}
		if(ccE.isActive1()) {
			flagE +=1;
		}
		if(ccE.isActive2()) {
			flagE +=1;
		}
		if(ccE.isActive3()) {
			flagE +=1;
		}
		if(ccE.isActive4()) {
			flagE +=1;
		}
		if(ccE.isActive5()) {
			flagE +=1;
		}
		if(flagU<2 || flagE<2) {
			return true;
		}else {
			return false;
		}
	}

	public List<BattlePower> battlePowerSetting(
			TurnInfo turnInfo, TeamInfo teamInfo, ChampCondition champCondition) {
		// TODO Auto-generated method stub
		System.out.println("배틀파워세팅 시작");
		List<BattlePower> battlePowerList = new ArrayList<>();
		List<String> positionList = new ArrayList<>();
		positionList.add(teamInfo.getGrow1().getBuild());
		positionList.add(teamInfo.getGrow2().getBuild());
		positionList.add(teamInfo.getGrow3().getBuild());
		positionList.add(teamInfo.getGrow4().getBuild());
		positionList.add(teamInfo.getGrow5().getBuild());
		if(champCondition.isActive1()==true) {
			System.out.println("1번 생존");
			battlePowerList.add(setBattlePowerLogic(positionList, teamInfo.getGrow1(), 
					teamInfo.getChampName1(), champCondition.getHpPer1(),
					champCondition.isActive1(),teamInfo.getDragonStack()));
		}
		if(champCondition.isActive2()==true) {
			System.out.println("2번 생존");
			battlePowerList.add(setBattlePowerLogic(positionList, teamInfo.getGrow2(), 
					teamInfo.getChampName2(), champCondition.getHpPer2(),
					champCondition.isActive2(),teamInfo.getDragonStack()));
		}
		if(champCondition.isActive3()==true) {
			System.out.println("3번 생존");
			battlePowerList.add(setBattlePowerLogic(positionList, teamInfo.getGrow3(), 
					teamInfo.getChampName3(), champCondition.getHpPer3(),
					champCondition.isActive3(),teamInfo.getDragonStack()));
		}
		if(champCondition.isActive4()==true) {
			System.out.println("4번 생존");
			battlePowerList.add(setBattlePowerLogic(positionList, teamInfo.getGrow4(), 
					teamInfo.getChampName4(), champCondition.getHpPer4(),
					champCondition.isActive4(),teamInfo.getDragonStack()));
		}
		if(champCondition.isActive5()==true) {
			System.out.println("5번 생존");
			battlePowerList.add(setBattlePowerLogic(positionList, teamInfo.getGrow5(), 
					teamInfo.getChampName5(), champCondition.getHpPer5(), 
					champCondition.isActive5(), teamInfo.getDragonStack()));
		}
		for (int i=0; i<battlePowerList.size(); i++) {
            System.out.println("배틀파워리스트 설정확인"+battlePowerList.get(i).getName()+battlePowerList.get(i).getLevel());
        }
		return battlePowerList;
	}

	private BattlePower setBattlePowerLogic(List<String> positionList, 
			ChampGrow grow, String champName, int hpPer, boolean active, int dragonStack) {
		// TODO Auto-generated method stub
		BattlePower battlePower = new BattlePower(grow, champName);
		battlePower.setActive(active);
		System.out.println("셋 배틀파워로직 작동확인1"+battlePower.getName()+battlePower.getLevel());
		battlePower.checkDragonStack(dragonStack);
		System.out.println("셋 배틀파워로직 작동확인2"+battlePower.getName()+battlePower.getLevel());
		battlePower.checkBaronBuff(grow.isBaronBuff());
		System.out.println("셋 배틀파워로직 작동확인3"+battlePower.getName()+battlePower.getLevel());
		battlePower.checkElderBuff(grow.isElderBuff());
		System.out.println("셋 배틀파워로직 작동확인4"+battlePower.getName()+battlePower.getLevel());
		battlePower.checkHp(hpPer);
		System.out.println("셋 배틀파워로직 작동확인5"+battlePower.getName()+battlePower.getLevel());
		battlePower.checkUtilSupport(positionList);
		System.out.println("셋 배틀파워로직 작동확인6"+battlePower.getName()+
				battlePower.getLevel()+battlePower.isActive());
		return battlePower;
	}

	private TeamInfo growSetting(List<BattlePower> battle2, TeamInfo teaminfo, int turn) {
		// TODO Auto-generated method stub
		for(int i=0; i<battle2.size(); i++) {
			String name = battle2.get(i).getName();
			if(name.equals(teaminfo.getChampName1())) {
				teaminfo.getGrow1().setKill(battle2.get(i).getKill());
				teaminfo.getGrow1().setDeath(battle2.get(i).getDeath());
				teaminfo.getGrow1().setAssist(battle2.get(i).getAssist());
			}else if(name.equals(teaminfo.getChampName2())) {
				teaminfo.getGrow2().setKill(battle2.get(i).getKill());
				teaminfo.getGrow2().setDeath(battle2.get(i).getDeath());
				teaminfo.getGrow2().setAssist(battle2.get(i).getAssist());
			}else if(name.equals(teaminfo.getChampName3())) {
				teaminfo.getGrow3().setKill(battle2.get(i).getKill());
				teaminfo.getGrow3().setDeath(battle2.get(i).getDeath());
				teaminfo.getGrow3().setAssist(battle2.get(i).getAssist());
			}else if(name.equals(teaminfo.getChampName4())) {
				teaminfo.getGrow4().setKill(battle2.get(i).getKill());
				teaminfo.getGrow4().setDeath(battle2.get(i).getDeath());
				teaminfo.getGrow4().setAssist(battle2.get(i).getAssist());
			}else if(name.equals(teaminfo.getChampName5())) {
				teaminfo.getGrow5().setKill(battle2.get(i).getKill());
				teaminfo.getGrow5().setDeath(battle2.get(i).getDeath());
				teaminfo.getGrow5().setAssist(battle2.get(i).getAssist());
			}
		}
		return teaminfo;
	}

	private ChampCondition conditionSetting(
			List<BattlePower> battle, List<BattlePower> battle2, TeamInfo teaminfo) {
		// TODO Auto-generated method stub
		ChampCondition champCondition = new ChampCondition();
		champCondition.setHpPer1(0);
		champCondition.setHpPer2(0);
		champCondition.setHpPer3(0);
		champCondition.setHpPer4(0);
		champCondition.setHpPer5(0);
		champCondition.setActive1(false);
		champCondition.setActive2(false);
		champCondition.setActive3(false);
		champCondition.setActive4(false);
		champCondition.setActive5(false);
		for(int i=0; i<battle2.size(); i++) {
			String name = battle2.get(i).getName();
			if(name.equals(teaminfo.getChampName1())) {
				for(int j=0; j<battle.size(); j++) {
					String name2 = battle.get(j).getName();
					if(name.equals(name2) && battle.get(j).getHp()>0) {
						int hpPer = (int)((battle2.get(i).getHp()/battle.get(j).getHp())*100);
						champCondition.setHpPer1(hpPer);
						if(hpPer>15) {
							champCondition.setActive1(true);
						}
					}
				}
			}else if(name.equals(teaminfo.getChampName2())) {
				for(int j=0; j<battle.size(); j++) {
					String name2 = battle.get(j).getName();
					if(name.equals(name2)) {
						int hpPer = (int)((battle2.get(i).getHp()/battle.get(j).getHp())*100);
						champCondition.setHpPer2(hpPer);
						if(hpPer>15) {
							champCondition.setActive2(true);
						}
					}
				}
			}else if(name.equals(teaminfo.getChampName3())) {
				for(int j=0; j<battle.size(); j++) {
					String name2 = battle.get(j).getName();
					if(name.equals(name2)) {
						int hpPer = (int)((battle2.get(i).getHp()/battle.get(j).getHp())*100);
						champCondition.setHpPer3(hpPer);
						if(hpPer>15) {
							champCondition.setActive3(true);
						}
					}
				}
			}else if(name.equals(teaminfo.getChampName4())) {
				for(int j=0; j<battle.size(); j++) {
					String name2 = battle.get(j).getName();
					if(name.equals(name2)) {
						int hpPer = (int)((battle2.get(i).getHp()/battle.get(j).getHp())*100);
						champCondition.setHpPer4(hpPer);
						if(hpPer>15) {
							champCondition.setActive4(true);
						}
					}
				}
			}else if(name.equals(teaminfo.getChampName5())) {
				for(int j=0; j<battle.size(); j++) {
					String name2 = battle.get(j).getName();
					if(name.equals(name2)) {
						int hpPer = (int)((battle2.get(i).getHp()/battle.get(j).getHp())*100);
						champCondition.setHpPer5(hpPer);
						if(hpPer>15) {
							champCondition.setActive5(true);
						}
					}
				}
			}
		}
		return champCondition;
	}
}
