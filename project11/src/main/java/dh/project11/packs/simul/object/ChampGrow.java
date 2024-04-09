package dh.project11.packs.simul.object;

import org.springframework.stereotype.Component;

@Component
public class ChampGrow {

	private int exp;
	private int level;
	private int kill;
	private int death;
	private int assist;
	private String build;
	private boolean baronBuff;
	private boolean elderBuff;
	
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
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public boolean isBaronBuff() {
		return baronBuff;
	}
	public void setBaronBuff(boolean baronBuff) {
		this.baronBuff = baronBuff;
	}
	public boolean isElderBuff() {
		return elderBuff;
	}
	public void setElderBuff(boolean elderBuff) {
		this.elderBuff = elderBuff;
	}
}
