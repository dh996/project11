package dh.project11.packs.simul.object;

import org.springframework.stereotype.Component;

@Component
public class SimulInfo {

	private String sid;
	private TeamInfo userTeam;
	private TeamInfo enemyTeam;
	private String version;
	private boolean win;
	private SimulLogMessage simulResult;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public TeamInfo getUserTeam() {
		return userTeam;
	}
	public void setUserTeam(TeamInfo userTeam) {
		this.userTeam = userTeam;
	}
	public TeamInfo getEnemyTeam() {
		return enemyTeam;
	}
	public void setEnemyTeam(TeamInfo enemyTeam) {
		this.enemyTeam = enemyTeam;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	
	 public SimulLogMessage getSimulResult() { return simulResult; } public void
	 setSimulResult(SimulLogMessage simulResult) { this.simulResult = simulResult;
	 }

}
