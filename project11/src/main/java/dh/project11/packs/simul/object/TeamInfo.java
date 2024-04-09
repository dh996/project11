package dh.project11.packs.simul.object;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TeamInfo {

	private String uid;
	private String champCid1;
	private String champCid2;
	private String champCid3;
	private String champCid4;
	private String champCid5;
	private ChampGrow grow1;
	private ChampGrow grow2;
	private ChampGrow grow3;
	private ChampGrow grow4;
	private ChampGrow grow5;
	private int dragonStack;
	private String champName1;
	private String champName2;
	private String champName3;
	private String champName4;
	private String champName5;
	private List<SimulLogMessage> teamLog;
	
	public void setTeam(String uid, String cid1, String cid2, String cid3, String cid4, String cid5,
			String name1, String name2, String name3, String name4, String name5) {
		this.uid = uid;
		this.champCid1 = cid1;
		this.champCid2 = cid2;
		this.champCid3 = cid3;
		this.champCid4 = cid4;
		this.champCid5 = cid5;
		this.champName1 = name1;
		this.champName2 = name2;
		this.champName3 = name3;
		this.champName4 = name4;
		this.champName5 = name5;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getChampCid1() {
		return champCid1;
	}
	public void setChampCid1(String champCid1) {
		this.champCid1 = champCid1;
	}
	public String getChampCid2() {
		return champCid2;
	}
	public void setChampCid2(String champCid2) {
		this.champCid2 = champCid2;
	}
	public String getChampCid3() {
		return champCid3;
	}
	public void setChampCid3(String champCid3) {
		this.champCid3 = champCid3;
	}
	public String getChampCid4() {
		return champCid4;
	}
	public void setChampCid4(String champCid4) {
		this.champCid4 = champCid4;
	}
	public String getChampCid5() {
		return champCid5;
	}
	public void setChampCid5(String champCid5) {
		this.champCid5 = champCid5;
	}

	public ChampGrow getGrow1() {
		return grow1;
	}
	public void setGrow1(ChampGrow grow1) {
		this.grow1 = grow1;
	}
	public ChampGrow getGrow2() {
		return grow2;
	}
	public void setGrow2(ChampGrow grow2) {
		this.grow2 = grow2;
	}
	public ChampGrow getGrow3() {
		return grow3;
	}
	public void setGrow3(ChampGrow grow3) {
		this.grow3 = grow3;
	}
	public ChampGrow getGrow4() {
		return grow4;
	}
	public void setGrow4(ChampGrow grow4) {
		this.grow4 = grow4;
	}
	public ChampGrow getGrow5() {
		return grow5;
	}
	public void setGrow5(ChampGrow grow5) {
		this.grow5 = grow5;
	}
	public int getDragonStack() {
		return dragonStack;
	}

	public void setDragonStack(int dragonStack) {
		this.dragonStack = dragonStack;
	}
	public String getChampName1() {
		return champName1;
	}
	public void setChampName1(String champName1) {
		this.champName1 = champName1;
	}
	public String getChampName2() {
		return champName2;
	}
	public void setChampName2(String champName2) {
		this.champName2 = champName2;
	}
	public String getChampName3() {
		return champName3;
	}
	public void setChampName3(String champName3) {
		this.champName3 = champName3;
	}
	public String getChampName4() {
		return champName4;
	}
	public void setChampName4(String champName4) {
		this.champName4 = champName4;
	}
	public String getChampName5() {
		return champName5;
	}
	public void setChampName5(String champName5) {
		this.champName5 = champName5;
	}
	public List<SimulLogMessage> getTeamLog() {
		return teamLog;
	}
	public void setTeamLog(List<SimulLogMessage> teamLog) {
		this.teamLog = teamLog;
	}
}
