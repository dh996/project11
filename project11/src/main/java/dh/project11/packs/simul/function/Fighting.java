package dh.project11.packs.simul.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dh.project11.packs.simul.object.BattlePower;
import dh.project11.packs.simul.object.ChampCondition;
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.TeamFightInfo;
import dh.project11.packs.simul.object.TeamInfo;
import dh.project11.packs.simul.object.TowerCondition;
import dh.project11.packs.simul.object.TurnInfo;

@Component
public class Fighting {
	
	@Autowired
	Random random;

	@Autowired
	SimulLogging simulLogging;
	
	private String sid;
	private String fid;
	private String tid;
	private List<BattlePower> battleU;
	private List<BattlePower> battleE;
	private List<BattlePower> battle1;
	private List<BattlePower> battle2;
	private TowerCondition towerU;
	private TowerCondition towerE;
	private ChampCondition champConditionU;
	private ChampCondition champConditionE;
	private String battleField;
	private List<SimulLogMessage> fightLog;
	private int towerDive;
	private int target1_1;
	private int target1_2;
	private int target2_1;
	private int target2_2;
	private int target1_1Falg;
	private int target1_2Falg;
	private int target2_1Falg;
	private int target2_2Falg;
	private int target1CountMax;
	private int target2CountMax;
	private int target1CountMin;
	private int target2CountMin;

	public void FightingSetting(String battleField, List<BattlePower> battleU, List<BattlePower> battleE,
			TowerCondition towerU, TowerCondition towerE,
			ChampCondition champConditionU, ChampCondition champConditionE,
			List<SimulLogMessage> fightLog) {
		this.battleField = battleField;
		this.battleU = battleU;
		System.out.println("파이팅 인수체크 배틀필드"+battleField);
		System.out.println("파이팅 인수체크 배틀파워"+battleU.size());
		System.out.println("파이팅 변수체크 배틀필드"+this.battleField);
		System.out.println("파이팅 인수체크 배틀파워"+this.battleU.size());
		for(int i=0; i<this.battleU.size(); i++) {
			System.out.println("Fighting 인수체크 배틀파워"+
				battleU.get(i).getName()+
			    battleU.get(i).getLevel());
			System.out.println("Fighting 변수체크 배틀파워"+
			    battleU.get(i).getName()+this.battleU.get(i).getLevel());
		}
		this.battleE = battleE;
		this.towerU = towerU;
		this.towerE = towerE;
		this.champConditionU = champConditionU;
		this.champConditionE = champConditionE;
		this.fightLog = fightLog;
		this.towerDive = checkTowerDive(this.battleField, this.towerU, this.towerE);
		System.out.println("파이팅 변수체크 타워다이브"+this.towerDive);
	}

	private boolean initSetting(List<BattlePower> battleU, List<BattlePower> battleE) {
		// TODO Auto-generated method stub
		int initPowerU = 0;
		int initPowerE = 0;
		for(int i=0; i<this.battleU.size(); i++) {
			initPowerU += this.battleU.get(i).getInitiatingPower();
			System.out.println("이니시세팅 설정확인U"+initPowerU);
		}
		for(int i=0; i<this.battleE.size(); i++) {
			initPowerE += this.battleE.get(i).getInitiatingPower();
			System.out.println("이니시세팅 설정확인E"+initPowerE);
		}
		if(initPowerU>initPowerE) {
			return true;
		}else if(initPowerU<initPowerE) {
			return false;
		}else {
			int flag = random.nextInt(5);
			if(flag<3) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	public void initPhase() {
		simulLogging.setSid(sid);
		simulLogging.setFid(fid);
		simulLogging.setTid(tid);
		boolean initiating = initSetting(this.battleU, this.battleE);
		if(initiating==true) {
			this.battle1 = this.battleU;
			for(int i=0; i<battle1.size(); i++) {
				System.out.println("이닛페이즈 변수확인1"+battle1.get(i).getName());
			}
			this.battle2 = this.battleE;
			for(int i=0; i<battle2.size(); i++) {
				System.out.println("이닛페이즈 변수확인2"+battle2.get(i).getName());
			}
			startFight(true);
		}else {
			this.battle1 = this.battleE;
			for(int i=0; i<battle1.size(); i++) {
				System.out.println("이닛페이즈 변수확인1"+battle1.get(i).getName());
			}
			this.battle2 = this.battleU;
			for(int i=0; i<battle2.size(); i++) {
				System.out.println("이닛페이즈 변수확인2"+battle2.get(i).getName());
			}
			startFight(false);
		}
	}
	
	private void startFight(boolean type) {
		// TODO Auto-generated method stub
		System.out.println("스타트파이팅 인수확인"+type);
		int initiator = computationInitPower(this.battle1);
		int battlePattern = random.nextInt(5);
		battlePhase(type, battlePattern, initiator);
		
	}

	private void battlePhase(boolean type, int battlePattern, int initiator) {
		// TODO Auto-generated method stub
		boolean type2;
		if(type==true) {
			type2=false;
		}else {
			type2=true;
		}
		setTarget();
		switch(battlePattern) {
		
		case 0: attackInit(initiator, target1_1, battle1, battle2, type, 1);
		        attackADC(target1_1, battle1, battle2, type, 1);
		        attackTank(target2_2, battle2, battle1, type2, 2);
		        if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_1, target2_2, this.towerDive, type);
		        }
		        attackADC(target2_1, battle2, battle1, type2, 2);
		        attackFight(target1_1, battle1, battle2, type, 1);
		        setTarget();
		        attackFight(target2_1, battle2, battle1, type2, 2);
		        attackADC(target1_1, battle1, battle2, type, 1);
		        attackADC(target2_1, battle2, battle1, type2, 2);
		        attackMage(target1_2, battle1, battle2, type, 1);
		        attackFight(target2_1, battle2, battle1, type2, 2);
		        attackTank(target1_2, battle1, battle2, type, 1);
		        attackAssassin(target1_2, battle1, battle2, type, 1);
		        attackADC(target1_1, battle1, battle2, type, 1);
		        setTarget();
		        if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_1, target2_2, this.towerDive, type);
		        }
		        attackMage(target2_2, battle2, battle1, type2, 2);
		        attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
		        attackAssassin(target2_2, battle2, battle1, type2, 2);
		        attackFight(target1_1, battle1, battle2, type, 1);
		        break;
		        
		case 1: attackInit(initiator, target1_1, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackTank(target2_2, battle2, battle1, type2, 2);
                attackADC(target2_1, battle2, battle1, type2, 2);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackAssassin(target1_2, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                setTarget();
                attackMage(target1_2, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackTank(target1_2, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackAssassin(target2_2, battle2, battle1, type2, 2);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackMage(target2_2, battle2, battle1, type2, 2);
                break;
                
		case 2: attackInit(initiator, target1_1, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackMage(target1_2, battle1, battle2, type, 1);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackAssassin(target1_2, battle1, battle2, type, 1);
                attackTank(target2_2, battle2, battle1, type2, 2);
                setTarget();
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackTank(target1_2, battle1, battle2, type, 1);
                attackAssassin(target2_2, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackMage(target2_2, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                setTarget();
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                break;
        
		case 3: attackInit(initiator, target1_1, battle1, battle2, type, 1);
                attackMage(target2_2, battle2, battle1, type2, 2);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackTank(target2_2, battle2, battle1, type2, 2);
                attackADC(target2_1, battle2, battle1, type2, 2);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackMage(target1_2, battle1, battle2, type, 1);
                setTarget();
                attackTank(target1_2, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackAssassin(target1_2, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackAssassin(target2_2, battle2, battle1, type2, 2);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                break;
                
		case 4: attackInit(initiator, target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                setTarget();
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackTank(target2_2, battle2, battle1, type2, 2);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackMage(target2_2, battle2, battle1, type2, 2);
                attackFight(target1_1, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                setTarget();
                attackAssassin(target2_2, battle2, battle1, type2, 2);
                attackMage(target1_2, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackTank(target1_2, battle1, battle2, type, 1);
                attackAssassin(target1_2, battle1, battle2, type, 1);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackFight(target1_1, battle1, battle2, type, 1);
                break;
                
		case 5: attackInit(initiator, target1_1, battle1, battle2, type, 1);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackTank(target2_2, battle2, battle1, type2, 2);
                attackADC(target2_1, battle2, battle1, type2, 2);
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackADC(target2_1, battle2, battle1, type2, 2);
                setTarget();
                attackFight(target1_1, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackMage(target2_2, battle2, battle1, type2, 2);
                attackAssassin(target2_2, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackADC(target1_1, battle1, battle2, type, 1);
                attackADC(target2_1, battle2, battle1, type2, 2);
                attackMage(target1_2, battle1, battle2, type, 1);
                attackFight(target2_1, battle2, battle1, type2, 2);
                setTarget();
                if(this.towerDive==1 || this.towerDive==2) {
		        	attackTower(target1_2, target2_1, this.towerDive, type);
		        }
                attackFight(target1_1, battle1, battle2, type, 1);
                attackTank(target1_2, battle1, battle2, type, 1);
                attackAssassin(target1_2, battle1, battle2, type, 1);
                break;
		}
		checkAttackResult(type);
	}

	private void checkAttackResult(boolean type) {
		// TODO Auto-generated method stub
		if(type==true) {
			setBattleU(battle1);
			for(int i=0; i<battle1.size(); i++) {
				System.out.println("셋 배틀 1확인"+battle1.get(i).getName());
			}
			setBattleE(battle2);
			for(int i=0; i<battle2.size(); i++) {
				System.out.println("셋 배틀 2확인"+battle2.get(i).getName());
			}
		}else {
			setBattleE(battle1);
			for(int i=0; i<battle1.size(); i++) {
				System.out.println("셋 배틀 1확인"+battle1.get(i).getName());
			}
			setBattleU(battle2);
			for(int i=0; i<battle2.size(); i++) {
				System.out.println("셋 배틀 2확인"+battle2.get(i).getName());
			}
		}
	}

	private void attackTower(int target1_1, int target2_2, int towerDive, boolean type) {
		// TODO Auto-generated method stub
		if(towerDive==1) {
			if(type==true) {
				int hp = battle2.get(target1_1).getHp();
				hp -= 300;
				if(hp>0) {
					this.battle2.get(target1_1).setHp(hp);
					fightLog.add(simulLogging.towerAttack(battle2.get(target1_1).getName(), true));
				}else {
					this.battle2.get(target1_1).setHp(0);
					fightLog.add(simulLogging.towerAttack(battle2.get(target1_1).getName(), true));
					fightLog.add(simulLogging.champDead(battle2.get(target1_1).getName(), battleField, true));
					this.battle2.remove(target1_1);
					setTarget();
				}
			}else {
				int hp = battle1.get(target2_2).getHp();
				hp -= 300;
				if(hp>0) {
					this.battle1.get(target2_2).setHp(hp);
					fightLog.add(simulLogging.towerAttack(battle1.get(target2_2).getName(), true));
				}else {
					this.battle1.get(target2_2).setHp(0);
					fightLog.add(simulLogging.towerAttack(battle1.get(target2_2).getName(), true));
					fightLog.add(simulLogging.champDead(battle1.get(target2_2).getName(), battleField, true));
					this.battle1.remove(target2_2);
					setTarget();
				}
			}
		}else if(towerDive==2) {
			if(type==false) {
				int hp = battle2.get(target1_1).getHp();
				hp -= 300;
				if(hp>0) {
					this.battle2.get(target1_1).setHp(hp);
					fightLog.add(simulLogging.towerAttack(battle2.get(target1_1).getName(), false));
				}else {
					this.battle2.get(target1_1).setHp(0);
					fightLog.add(simulLogging.towerAttack(battle2.get(target1_1).getName(), false));
					fightLog.add(simulLogging.champDead(battle2.get(target1_1).getName(), battleField, false));
					this.battle2.remove(target1_1);
					setTarget();
				}
			}else {
				int hp = battle1.get(target2_2).getHp();
				hp -= 300;
				if(hp>0) {
					this.battle1.get(target2_2).setHp(hp);
					fightLog.add(simulLogging.towerAttack(battle1.get(target2_2).getName(), false));
				}else {
					this.battle1.get(target2_2).setHp(0);
					fightLog.add(simulLogging.towerAttack(battle1.get(target2_2).getName(), false));
					fightLog.add(simulLogging.champDead(battle1.get(target2_2).getName(), battleField, false));
					this.battle1.remove(target2_2);
					setTarget();
				}
			}
		}	
	}
	
	private void attackLogic(int targetIndex, int targetType, int hp, int damage, int i, 
			List<BattlePower> battleF, List<BattlePower> battleL, boolean type, int attackTeam) {
		if(hp<1) {
			
		}else {
		    hp -= damage;
		    if(hp>0) {
		    	if(attackTeam==1) {
		    		this.battle2.get(targetIndex).setHp(hp);
		    	}else if(attackTeam==2) {
		    		this.battle1.get(targetIndex).setHp(hp);
		    	}
		    	fightLog.add(simulLogging.champAttack(
		    			battleL.get(targetIndex).getName(), battleF.get(i).getName(), damage, type));
		    }else {
		    	fightLog.add(simulLogging.champAttack(
		    			battleL.get(targetIndex).getName(), battleF.get(i).getName(), damage, type));
		    	fightLog.add(simulLogging.champDead(battleL.get(targetIndex).getName(), battleF.get(i).getName(), type));
		    	if(attackTeam==1) {
		    		int kills = battleF.get(i).getKill()+1;
		    		battle1.get(i).setKill(kills);
		    		this.battle2.get(targetIndex).setHp(hp);
		    		this.battle2.get(targetIndex).setActive(false);
		    		int deaths = battle2.get(targetIndex).getDeath()+1;
		    		battle2.get(targetIndex).setDeath(deaths);
		    		for(int j=0; j<battleF.size(); j++) {
		    			if(battleF.get(j).isActive()==true && i!=j) {
		    				int assists = battleF.get(j).getAssist()+1;
		    				battle1.get(j).setAssist(assists);
		    			}
		    		}
		    		setTarget();
		    	}else if(attackTeam==2) {
		    		int kills = battleF.get(i).getKill()+1;
		    		battle2.get(i).setKill(kills);
		    		this.battle1.get(targetIndex).setHp(hp);
		    		this.battle1.get(targetIndex).setActive(false);
		    		int deaths = battle1.get(targetIndex).getDeath()+1;
		    		battle1.get(targetIndex).setDeath(deaths);
		    		for(int j=0; j<battleF.size(); j++) {
		    			if(battleF.get(j).isActive()==true && i!=j) {
		    				int assists = battleF.get(j).getAssist()+1;
		    				battle2.get(j).setAssist(assists);
		    			}
		    		}
		    		setTarget();
		    	}
		    }
		}
	}

	private void attackAssassin(int target1_2, List<BattlePower> battleF, List<BattlePower> battleL, 
			boolean type, int attackTeam) {
		// TODO Auto-generated method stub
		int hp = battleL.get(target1_2).getHp();
		float dmgRate = (float) (2+((random.nextInt(9))*0.1));
		for(int i=0; i<battleF.size(); i++) {
			if(battleF.get(i).isActive()==true)
			if(battleF.get(i).getPosition().equals("Assassin")) {
				System.out.println("공격력:"+battleF.get(i).getAttackPower()+"계수:"+dmgRate);
				int damage = (int) ((battleF.get(i).getAttackPower())*dmgRate);
				attackLogic(target1_2, 2, hp, damage, i, battleF, battleL, type, attackTeam);
			}
		}
	}

	private void attackMage(int target1_2, List<BattlePower> battleF, List<BattlePower> battleL, 
			boolean type, int attackTeam) {
		// TODO Auto-generated method stub
		int hp = battleL.get(target1_2).getHp();
		float dmgRate = (float) (3+((random.nextInt(5))*0.1));
		for(int i=0; i<battleF.size(); i++) {
			if(battleF.get(i).isActive()==true)
			if(battleF.get(i).getPosition().equals("FureMage") || 
					battleF.get(i).getPosition().equals("Mage") ||
					battleF.get(i).getPosition().equals("MageSupport") ||
					battleF.get(i).getPosition().equals("MageTank")) {
				System.out.println("공격력:"+battleF.get(i).getAttackPower()+"계수:"+dmgRate);
				int damage = (int) ((battleF.get(i).getAttackPower())*dmgRate);
				attackLogic(target1_2, 2, hp, damage, i, battleF, battleL, type, attackTeam);
			}
		}
	}

	private void attackFight(int target1_1, List<BattlePower> battleF, List<BattlePower> battleL, 
			boolean type, int attackTeam) {
		// TODO Auto-generated method stub
		int hp = battleL.get(target1_1).getHp();
		float dmgRate = (float) (2+((random.nextInt(5))*0.1));
		for(int i=0; i<battleF.size(); i++) {
			if(battleF.get(i).isActive()==true)
			if(battleF.get(i).getPosition().equals("Fighter") ||
					battleF.get(i).getPosition().equals("FightTank") ||
					battleF.get(i).getPosition().equals("DealSupport")) {
				System.out.println("공격력:"+battleF.get(i).getAttackPower()+"계수:"+dmgRate);
				int damage = (int) ((battleF.get(i).getAttackPower())*dmgRate);
				attackLogic(target1_1, 1, hp, damage, i, battleF, battleL, type, attackTeam);
			}
		}
	}

	private void attackTank(int target2_2, List<BattlePower> battleF, List<BattlePower> battleL, 
			boolean type, int attackTeam) {
		// TODO Auto-generated method stub
		int hp = battleL.get(target2_2).getHp();
		float dmgRate = (float) (1+((random.nextInt(5))*0.1));
		for(int i=0; i<battleF.size(); i++) {
			if(battleF.get(i).isActive()==true)
			if(battleF.get(i).getPosition().equals("Tank") ||
					battleF.get(i).getPosition().equals("FureTank") ||
					battleF.get(i).getPosition().equals("TankSupport")) {
				System.out.println("공격력:"+battleF.get(i).getAttackPower()+"계수:"+dmgRate);
				int damage = (int) ((battleF.get(i).getAttackPower())*dmgRate);
				attackLogic(target2_2, 2, hp, damage, i, battleF, battleL, type, attackTeam);
			}
		}
	}

	private void attackADC(int target1_1, List<BattlePower> battleF, List<BattlePower> battleL, 
			boolean type, int attackTeam) {
		// TODO Auto-generated method stub
		int hp = battleL.get(target1_1).getHp();
		float dmgRate = (float) (1+((random.nextInt(9))*0.1));
		for(int i=0; i<battleF.size(); i++) {
			if(battleF.get(i).isActive()==true)
			if(battleF.get(i).getPosition().equals("ADC") ||
					battleF.get(i).getPosition().equals("FureADC") ||
					battleF.get(i).getPosition().equals("ADSupport")) {
				System.out.println("공격력:"+battleF.get(i).getAttackPower()+"계수:"+dmgRate);
				int damage = (int) ((battleF.get(i).getAttackPower())*dmgRate);
				attackLogic(target1_1, 1, hp, damage, i, battleF, battleL, type, attackTeam);
			}
		}
	}

	private void attackInit(int initiator, int target1_1, 
			List<BattlePower> battleF, List<BattlePower> battleL, boolean type, int attackTeam) {
		// TODO Auto-generated method stub
		String name = battleF.get(initiator).getName();
		System.out.println("어택이닛 인수확인"+name+type);
		this.fightLog.add(simulLogging.initLogging(name, type));
		int hp = battleL.get(target1_1).getHp();
		float dmgRate = (float) (1+((random.nextInt(9))*0.1));
		int damage = (int) ((battleF.get(initiator).getAttackPower())*dmgRate);
		System.out.println("공격력:"+battleF.get(initiator).getAttackPower()+"계수:"+dmgRate);
		attackLogic(target1_1, 1, hp, damage, initiator, battleF, battleL, type, attackTeam);
	}

	private void setTarget() {
		// TODO Auto-generated method stub
		int aggroGauge1 = 0;
		for(int i=0; i<battle1.size(); i++) {
			if(battle1.get(i).isActive()==true)
			aggroGauge1 += battle1.get(i).getAggroGauge();
		}
		int aggroGauge2 = 0;
		for(int i=0; i<battle2.size(); i++) {
			if(battle2.get(i).isActive()==true)
			aggroGauge2 += battle2.get(i).getAggroGauge();
		}
		this.target1_1 = 0;
		this.target1_2 = 0;
		this.target2_1 = 0;
		this.target2_2 = 0;
		if(aggroGauge2>0) {
		    this.target1_1Falg = random.nextInt(aggroGauge2);
		    this.target1_2Falg = random.nextInt(aggroGauge2);
		}
		if(aggroGauge1>0) {
		    this.target2_1Falg = random.nextInt(aggroGauge1);
		    this.target2_2Falg = random.nextInt(aggroGauge1);
		}
		this.target1CountMax = 0;
		this.target2CountMax = 0;
		this.target1CountMin = 0;
		this.target2CountMin = 0;
		for(int i=0; i<battle1.size(); i++) {
			this.target2CountMin = target2CountMax;
			if(battle1.get(i).isActive()==true) {
				this.target2CountMax += battle1.get(i).getAggroGauge();
				if(i==0 && target2_1Falg<=target2CountMax) {
					this.target2_1 = i;
				}else if(target2_1Falg>target2CountMin && target2_1Falg<=target2CountMax) {
					this.target2_1 = i;
				}
				if(i==0 && target2_2Falg<=target2CountMax) {
					this.target2_2= i;
				}else if(target2_2Falg>target2CountMin && target2_2Falg<=target2CountMax) {
					this.target2_2 = i;
				}
			}
		}
		for(int i=0; i<battle2.size(); i++) {
			this.target1CountMin = target1CountMax;
			if(battle2.get(i).isActive()==true) {
				this.target1CountMax += battle2.get(i).getAggroGauge();
				if(i==0 && target1_1Falg<=target1CountMax) {
					this.target1_1 = i;
				}else if(target1_1Falg>target1CountMin && target1_1Falg<=target1CountMax) {
					this.target1_1 = i;
				}
				if(i==0 && target1_2Falg<=target1CountMax) {
					this.target1_2= i;
				}else if(target1_2Falg>target1CountMin && target1_2Falg<=target1CountMax) {
					this.target1_2 = i;
				}
			}
		}
	}

	private int computationInitPower(List<BattlePower> battle1) {
		// TODO Auto-generated method stub
		int initPower = 0;
		for(int i=0; i<this.battle1.size(); i++) {
			System.out.println("이니시에이터 결정함수 플래그 체크"+i+"번쩨"+
		        this.battle1.get(i).isActive());
			if(this.battle1.get(i).isActive()==true)
			initPower += this.battle1.get(i).getInitiatingPower();
		}
		int initiator = 0;
		System.out.println("initPower"+initPower);
		if(initPower>0) {
			int initFlag = random.nextInt(initPower);
			int initCountMax = 0;
			int initCountMin = 0;
			for(int i=0; i<this.battle1.size(); i++) {
				initCountMin = initCountMax;
				if(this.battle1.get(i).isActive()==true) {
					initCountMax += this.battle1.get(i).getInitiatingPower();
					if(i==0 && initFlag<=initCountMax) {
						initiator = i;
					}else if(initFlag>initCountMin && initFlag<=initCountMax) {
						initiator = i;
					}
				}
			}
		}
		return initiator;
	}

	private int checkTowerDive(String battleField, TowerCondition towerU, TowerCondition towerE) {
		// TODO Auto-generated method stub
	if(battleField != null) {
		if(battleField.equals("topFirstTowerU") && towerU.getTop().getFristTower()>10) {
			return 1;
		}else if(battleField.equals("topSecondTowerU") && towerU.getTop().getSecondTower()>10){
			return 1;
		}else if(battleField.equals("topThirdTowerU") && towerU.getTop().getThirdTower()>10){
			return 1;
		}else if(battleField.equals("midFirstTowerU") && towerU.getMid().getFristTower()>10){
			return 1;
		}else if(battleField.equals("midSecondTowerU") && towerU.getMid().getSecondTower()>10){
			return 1;
		}else if(battleField.equals("midThirdTowerU") && towerU.getMid().getThirdTower()>10){
			return 1;
		}else if(battleField.equals("botFirstTowerU") && towerU.getBot().getFristTower()>10){
			return 1;
		}else if(battleField.equals("botSecondTowerU") && towerU.getBot().getSecondTower()>10){
			return 1;
		}else if(battleField.equals("botThirdTowerU") && towerU.getBot().getThirdTower()>10){
			return 1;
		}else if(battleField.equals("baseU") && towerU.getTwinTowerLeft()>10){
			return 1;
		}else if(battleField.equals("baseU") && towerU.getTwinTowerRight()>10){
			return 1;
		}else if(battleField.equals("topFirstTowerE") && towerE.getTop().getFristTower()>10) {
			return 2;
		}else if(battleField.equals("topSecondTowerE") && towerE.getTop().getSecondTower()>10){
			return 2;
		}else if(battleField.equals("topThirdTowerE") && towerE.getTop().getThirdTower()>10){
			return 2;
		}else if(battleField.equals("midFirstTowerE") && towerE.getMid().getFristTower()>10){
			return 2;
		}else if(battleField.equals("midSecondTowerE") && towerE.getMid().getSecondTower()>10){
			return 2;
		}else if(battleField.equals("midThirdTowerE") && towerE.getMid().getThirdTower()>10){
			return 2;
		}else if(battleField.equals("botFirstTowerE") && towerE.getBot().getFristTower()>10){
			return 2;
		}else if(battleField.equals("botSecondTowerE") && towerE.getBot().getSecondTower()>10){
			return 2;
		}else if(battleField.equals("botThirdTowerE") && towerE.getBot().getThirdTower()>10){
			return 2;
		}else if(battleField.equals("baseE") && towerE.getTwinTowerLeft()>10){
			return 2;
		}else if(battleField.equals("baseE") && towerE.getTwinTowerRight()>10){
			return 2;
		}else{
			return 0;
		}
	}else
		return 0;
	}

	public List<BattlePower> getBattleU() {
		return battleU;
	}
	public void setBattleU(List<BattlePower> battleU) {
		this.battleU = battleU;
	}
	public List<BattlePower> getBattleE() {
		return battleE;
	}
	public void setBattleE(List<BattlePower> battleE) {
		this.battleE = battleE;
	}
	public TowerCondition getTowerU() {
		return towerU;
	}
	public void setTowerU(TowerCondition towerU) {
		this.towerU = towerU;
	}
	public TowerCondition getTowerE() {
		return towerE;
	}
	public void setTowerE(TowerCondition towerE) {
		this.towerE = towerE;
	}
	public ChampCondition getChampConditionU() {
		return champConditionU;
	}
	public void setChampConditionU(ChampCondition champConditionU) {
		this.champConditionU = champConditionU;
	}
	public ChampCondition getChampConditionE() {
		return champConditionE;
	}
	public void setChampConditionE(ChampCondition champConditionE) {
		this.champConditionE = champConditionE;
	}
	public String getBattleField() {
		return battleField;
	}
	public void setBattleField(String battleField) {
		this.battleField = battleField;
	}
	public List<SimulLogMessage> getFightLog() {
		return fightLog;
	}
	public void setFightLog(List<SimulLogMessage> fightLog) {
		this.fightLog = fightLog;
	}

	public List<BattlePower> getBattle1() {
		return battle1;
	}

	public void setBattle1(List<BattlePower> battle1) {
		this.battle1 = battle1;
	}

	public List<BattlePower> getBattle2() {
		return battle2;
	}

	public void setBattle2(List<BattlePower> battle2) {
		this.battle2 = battle2;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void resetValue() {
		// TODO Auto-generated method stub
		this.sid = "";
		this.fid = "";
		this.tid = "";
		this.battleU = new ArrayList<>();
		this.battleE = new ArrayList<>();
		this.battle1 = new ArrayList<>();
		this.battle2 = new ArrayList<>();
		this.towerU = new TowerCondition();
		this.towerE = new TowerCondition();
		this.champConditionU = new ChampCondition();
		this.champConditionE = new ChampCondition();
		this.battleField = "";
		this.fightLog = new ArrayList<>();
		this.towerDive = 0;
		this.target1_1 = 0;
		this.target1_2 = 0;
		this.target2_1 = 0;
		this.target2_2 = 0;
		this.target1_1Falg = 0;
		this.target1_2Falg = 0;
		this.target2_1Falg = 0;
		this.target2_2Falg = 0;
		this.target1CountMax = 0;
		this.target2CountMax = 0;
		this.target1CountMin = 0;
		this.target2CountMin = 0;
	}
}
