package dh.project11.packs.simul.object;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TurnInfo {

	private String sid;
	private String fid;
	private String tid;
	private int turnCount;
	private ChampCondition champConditionU;
	private ChampCondition champConditionE;
	private List<SimulLogMessage> turnLog;
	private List<BattlePower> battlePowerU;
	private List<BattlePower> battlePowerE;
	
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
	public int getTurnCount() {
		return turnCount;
	}
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
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
	public List<SimulLogMessage> getTurnLog() {
		return turnLog;
	}
	public void setTurnLog(List<SimulLogMessage> turnLog) {
		this.turnLog = turnLog;
	}
	public List<BattlePower> getBattlePowerU() {
		return battlePowerU;
	}
	public void setBattlePowerU(List<BattlePower> battlePowerU) {
		this.battlePowerU = battlePowerU;
	}
	public List<BattlePower> getBattlePowerE() {
		return battlePowerE;
	}
	public void setBattlePowerE(List<BattlePower> battlePowerE) {
		this.battlePowerE = battlePowerE;
	}
}
