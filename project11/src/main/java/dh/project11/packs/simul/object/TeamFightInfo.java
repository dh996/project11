package dh.project11.packs.simul.object;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TeamFightInfo {

	private String sid;
	private String fid;
	private String battleField;
	private TeamInfo userTeamInfo;
	private TeamInfo enemyTeamInfo;
	private int teamFightCount;
	private TowerCondition towerU;
	private TowerCondition towerE;
	private List<SimulLogMessage> teamFightLog;
	private boolean spawnBaron;
	private boolean spawnDragon;
	private boolean spawnElder;
	private int spawnBaronCount;
	private int spawnDragonCount;
	private int spawnElderCount;
	private List<String> dragonTypeList;
	private String dragonType;
	
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
	public String getBattleField() {
		return battleField;
	}
	public void setBattleField(String battleField) {
		this.battleField = battleField;
	}
	public TeamInfo getUserTeamInfo() {
		return userTeamInfo;
	}
	public void setUserTeamInfo(TeamInfo userTeamInfo) {
		this.userTeamInfo = userTeamInfo;
	}
	public TeamInfo getEnemyTeamInfo() {
		return enemyTeamInfo;
	}
	public void setEnemyTeamInfo(TeamInfo enemyTeamInfo) {
		this.enemyTeamInfo = enemyTeamInfo;
	}
	public int getTeamFightCount() {
		return teamFightCount;
	}
	public void setTeamFightCount(int teamFightCount) {
		this.teamFightCount = teamFightCount;
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
	public List<SimulLogMessage> getTeamFightLog() { return teamFightLog; }
	public void setTeamFightLog(List<SimulLogMessage> teamFightLog) {
	this.teamFightLog = teamFightLog; }

	public boolean isSpawnBaron() {
		return spawnBaron;
	}
	public void setSpawnBaron(boolean spawnBaron) {
		this.spawnBaron = spawnBaron;
	}
	public boolean isSpawnDragon() {
		return spawnDragon;
	}
	public void setSpawnDragon(boolean spawnDragon) {
		this.spawnDragon = spawnDragon;
	}
	public boolean isSpawnElder() {
		return spawnElder;
	}
	public void setSpawnElder(boolean spawnElder) {
		this.spawnElder = spawnElder;
	}
	public int getSpawnBaronCount() {
		return spawnBaronCount;
	}
	public void setSpawnBaronCount(int spawnBaronCount) {
		this.spawnBaronCount = spawnBaronCount;
	}
	public int getSpawnDragonCount() {
		return spawnDragonCount;
	}
	public void setSpawnDragonCount(int spawnDragonCount) {
		this.spawnDragonCount = spawnDragonCount;
	}
	public int getSpawnElderCount() {
		return spawnElderCount;
	}
	public void setSpawnElderCount(int spawnElderCount) {
		this.spawnElderCount = spawnElderCount;
	}
	public List<String> getDragonTypeList() {
		return dragonTypeList;
	}
	public void setDragonTypeList(List<String> dragonTypeList) {
		this.dragonTypeList = dragonTypeList;
	}
	public String getDragonType() {
		return dragonType;
	}
	public void setDragonType(String dragonType) {
		this.dragonType = dragonType;
	}
}
