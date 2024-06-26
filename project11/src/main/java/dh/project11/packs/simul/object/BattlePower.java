package dh.project11.packs.simul.object;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BattlePower {

	private String name;
	private String position;
	private int hp;
	private int attackPower;
	private int initiatingPower;
	private int battlePower;
	private int aggroGauge;
	private int exp;
	private int level;
	private int kill;
	private int death;
	private int assist;
	private boolean active;
	
	public BattlePower(ChampGrow grow, String champName) {
		this.name = champName;
		this.level = grow.getLevel();
		this.position = grow.getBuild();
		System.out.println("배틀파워 생성자 생성확인"+name);
		System.out.println("배틀파워 생성자 생성확인"+position);
		this.kill = grow.getKill();
		this.death = grow.getDeath();
		this.assist = grow.getAssist();
		if(position !=null) {
		if(position.equals("FureTank")) {
			this.hp = 1300;
			this.attackPower = 70;
			this.initiatingPower = 130;
			this.battlePower = 130;
			this.aggroGauge = 100;
		}else if(position.equals("Tank")) {
			this.hp = 1100;
			this.attackPower = 90;
			this.initiatingPower = 130;
			this.battlePower = 120;
			this.aggroGauge = 100;
		}else if(position.equals("MageTank")) {
			this.hp = 1000;
			this.attackPower = 180;
			this.initiatingPower = 110;
			this.battlePower = 150;
			this.aggroGauge = 100;
		}else if(position.equals("FightTank")) {
			this.hp = 1000;
			this.attackPower = 100;
			this.initiatingPower = 110;
			this.battlePower = 150;
			this.aggroGauge = 100;
		}else if(position.equals("Fighter")) {
			this.hp = 800;
			this.attackPower = 120;
			this.initiatingPower = 90;
			this.battlePower = 150;
			this.aggroGauge = 80;
		}else if(position.equals("Assassin")) {
			this.hp = 400;
			this.attackPower = 150;
			this.initiatingPower = 70;
			this.battlePower = 160;
			this.aggroGauge = 30;
		}else if(position.equals("ADC")) {
			this.hp = 450;
			this.attackPower = 130;
			this.initiatingPower = 20;
			this.battlePower = 180;
			this.aggroGauge = 30;
		}else if(position.equals("FureADC")) {
			this.hp = 400;
			this.attackPower = 150;
			this.initiatingPower = 20;
			this.battlePower = 200;
			this.aggroGauge = 30;
		}else if(position.equals("Mage")) {
			this.hp = 550;
			this.attackPower = 130;
			this.initiatingPower = 60;
			this.battlePower = 170;
			this.aggroGauge = 30;
		}else if(position.equals("FureMage")) {
			this.hp = 500;
			this.attackPower = 150;
			this.initiatingPower = 50;
			this.battlePower = 190;
			this.aggroGauge = 30;
		}else if(position.equals("Support")) {
			this.hp = 600;
			this.attackPower = 70;
			this.initiatingPower = 70;
			this.battlePower = 80;
			this.aggroGauge = 50;
		}else if(position.equals("TankSupport")) {
			this.hp = 800;
			this.attackPower = 50;
			this.initiatingPower = 100;
			this.battlePower = 80;
			this.aggroGauge = 80;
		}else if(position.equals("ADSupport")) {
			this.hp = 450;
			this.attackPower = 100;
			this.initiatingPower = 30;
			this.battlePower = 120;
			this.aggroGauge = 50;
		}else if(position.equals("MageSupport")) {
			this.hp = 550;
			this.attackPower = 100;
			this.initiatingPower = 60;
			this.battlePower = 120;
			this.aggroGauge = 50;
		}else if(position.equals("UtilSupport")) {
			this.hp = 550;
			this.attackPower = 70;
			this.initiatingPower = 70;
			this.battlePower = 80;
			this.aggroGauge = 50;
		}else if(position.equals("DealSupport")) {
			this.hp = 650;
			this.attackPower = 100;
			this.initiatingPower = 70;
			this.battlePower = 110;
			this.aggroGauge = 50;
		}
		}else {
			this.hp = 1;
			this.attackPower = 1;
			this.initiatingPower = 1;
			this.battlePower = 1;
			this.aggroGauge = 1;
		}
		int level = grow.getLevel();
		int kill = grow.getKill();
		int death = grow.getDeath();
		int assist = grow.getAssist();
		int scale = (30+(level*15)+(kill)+(assist/8));
		if(scale<=450) {
			this.hp *= (scale/10);
			this.attackPower *= (scale/10);
			this.battlePower *= (scale/10);
			this.aggroGauge *= (scale/10);
		}else {
			this.hp *= 45;
			this.attackPower *= 45;
			this.battlePower *= 45;
			this.aggroGauge *= 45;
		}
		
		this.hp /= 10;
		this.attackPower /= 15;
		this.battlePower /= 30;
		this.aggroGauge /= 10;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttackPower() {
		return attackPower;
	}
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	public int getInitiatingPower() {
		return initiatingPower;
	}
	public void setInitiatingPower(int initiatingPower) {
		this.initiatingPower = initiatingPower;
	}
	public int getBattlePower() {
		return battlePower;
	}
	public void setBattlePower(int battlePower) {
		this.battlePower = battlePower;
	}
	public int getAggroGauge() {
		return aggroGauge;
	}
	public void setAggroGauge(int aggroGauge) {
		this.aggroGauge = aggroGauge;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void checkDragonStack(int dragonStack) {
		// TODO Auto-generated method stub
		this.hp *= 1+(dragonStack*0.03);
		this.attackPower *= 1+(dragonStack*0.02);
		this.battlePower *= 1+(dragonStack*0.02);
		this.aggroGauge *= 1+(dragonStack*0.01);
	}

	public void checkBaronBuff(boolean baronBuff) {
		// TODO Auto-generated method stub
		if(baronBuff) {
			this.hp *= 1.2;
			this.attackPower *= 1.1;
			this.initiatingPower *= 1.1;
			this.battlePower *= 1.1;
			this.aggroGauge *= 1.1;
		}
	}

	public void checkElderBuff(boolean elderBuff) {
		// TODO Auto-generated method stub
		if(elderBuff) {
			this.hp *= 1.1;
			this.attackPower *= 1.3;
			this.initiatingPower *= 1.1;
		}
	}

	public void checkHp(int hpPer) {
		// TODO Auto-generated method stub
		if(hpPer<30) {
			this.attackPower *= 0.9;
			this.initiatingPower *= 0.8;
			this.aggroGauge *= 1.4;
		}
	}

	public void checkUtilSupport(List<String> positionList) {
		// TODO Auto-generated method stub
		if(positionList.contains("UtilSupport")) {
			if(this.position.equals("FureADC") ||
					this.position.equals("ADC")) {
				this.hp *= 1.1;
				this.attackPower *= 1.1;
			}
		}
	}
}
