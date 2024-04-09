package dh.project11.packs.simul.object;

import java.util.List;

import dh.project11.packs.admin.UpdateChampsVo;

public class SimulDataVo {

	private String data_sid;
	private String data_user_champ1;
	private int data_user_level1;
	private int data_user_kill1;
	private int data_user_death1;
	private int data_user_assist1;
	private String data_user_champ2;
	private int data_user_level2;
	private int data_user_kill2;
	private int data_user_death2;
	private int data_user_assist2;
	private String data_user_champ3;
	private int data_user_level3;
	private int data_user_kill3;
	private int data_user_death3;
	private int data_user_assist3;
	private String data_user_champ4;
	private int data_user_level4;
	private int data_user_kill4;
	private int data_user_death4;
	private int data_user_assist4;
	private String data_user_champ5;
	private int data_user_level5;
	private int data_user_kill5;
	private int data_user_death5;
	private int data_user_assist5;
	private String data_enemy_champ1;
	private int data_enemy_level1;
	private int data_enemy_kill1;
	private int data_enemy_death1;
	private int data_enemy_assist1;
	private String data_enemy_champ2;
	private int data_enemy_level2;
	private int data_enemy_kill2;
	private int data_enemy_death2;
	private int data_enemy_assist2;
	private String data_enemy_champ3;
	private int data_enemy_level3;
	private int data_enemy_kill3;
	private int data_enemy_death3;
	private int data_enemy_assist3;
	private String data_enemy_champ4;
	private int data_enemy_level4;
	private int data_enemy_kill4;
	private int data_enemy_death4;
	private int data_enemy_assist4;
	private String data_enemy_champ5;
	private int data_enemy_level5;
	private int data_enemy_kill5;
	private int data_enemy_death5;
	private int data_enemy_assist5;
	
	public SimulDataVo() {
        // 기본 생성자 내용
		this.data_sid = null;
		this.data_user_champ1 = null;
		this.data_user_level1 = 0;
		this.data_user_kill1 = 0;
		this.data_user_death1 = 0;
		this.data_user_assist1 = 0;
		this.data_user_champ2 = null;
		this.data_user_level2 = 0;
		this.data_user_kill2 = 0;
		this.data_user_death2 = 0;
		this.data_user_assist2 = 0;
		this.data_user_champ3 = null;
		this.data_user_level3 = 0;
		this.data_user_kill3 = 0;
		this.data_user_death3 = 0;
		this.data_user_assist3 = 0;
		this.data_user_champ4 = null;
		this.data_user_level4 = 0;
		this.data_user_kill4 = 0;
		this.data_user_death4 = 0;
		this.data_user_assist4 = 0;
		this.data_user_champ5 = null;
		this.data_user_level5 = 0;
		this.data_user_kill5 = 0;
		this.data_user_death5 = 0;
		this.data_user_assist5 = 0;
		this.data_enemy_champ1 = null;
		this.data_enemy_level1 = 0;
		this.data_enemy_kill1 = 0;
		this.data_enemy_death1 = 0;
		this.data_enemy_assist1 = 0;
		this.data_enemy_champ2 = null;
		this.data_enemy_level2 = 0;
		this.data_enemy_kill2 = 0;
		this.data_enemy_death2 = 0;
		this.data_enemy_assist2 = 0;
		this.data_enemy_champ3 = null;
		this.data_enemy_level3 = 0;
		this.data_enemy_kill3 = 0;
		this.data_enemy_death3 = 0;
		this.data_enemy_assist3 = 0;
		this.data_enemy_champ4 = null;
		this.data_enemy_level4 = 0;
		this.data_enemy_kill4 = 0;
		this.data_enemy_death4 = 0;
		this.data_enemy_assist4 = 0;
		this.data_enemy_champ5 = null;
		this.data_enemy_level5 = 0;
		this.data_enemy_kill5 = 0;
		this.data_enemy_death5 = 0;
		this.data_enemy_assist5 = 0;
    }
	
	public SimulDataVo(SimulInfo simulInfo) {
		// TODO Auto-generated constructor stub
		TeamInfo userTeam = simulInfo.getUserTeam();
		ChampGrow uGrow1 = userTeam.getGrow1();
		ChampGrow uGrow2 = userTeam.getGrow2();
		ChampGrow uGrow3 = userTeam.getGrow3();
		ChampGrow uGrow4 = userTeam.getGrow4();
		ChampGrow uGrow5 = userTeam.getGrow5();
		TeamInfo enemyTeam = simulInfo.getEnemyTeam();
		ChampGrow eGrow1 = enemyTeam.getGrow1();
		ChampGrow eGrow2 = enemyTeam.getGrow2();
		ChampGrow eGrow3 = enemyTeam.getGrow3();
		ChampGrow eGrow4 = enemyTeam.getGrow4();
		ChampGrow eGrow5 = enemyTeam.getGrow5();
		this.data_sid = simulInfo.getSid();
		this.data_user_champ1 = userTeam.getChampName1();
		this.data_user_level1 = uGrow1.getLevel();
		this.data_user_kill1 = uGrow1.getKill();
		this.data_user_death1 = uGrow1.getDeath();
		this.data_user_assist1 = uGrow1.getAssist();
		this.data_user_champ2 = userTeam.getChampName2();
		this.data_user_level2 = uGrow2.getLevel();
		this.data_user_kill2 = uGrow2.getKill();
		this.data_user_death2 = uGrow2.getDeath();
		this.data_user_assist2 = uGrow2.getAssist();
		this.data_user_champ3 = userTeam.getChampName3();
		this.data_user_level3 = uGrow3.getLevel();
		this.data_user_kill3 = uGrow3.getKill();
		this.data_user_death3 = uGrow3.getDeath();
		this.data_user_assist3 = uGrow3.getAssist();
		this.data_user_champ4 = userTeam.getChampName4();
		this.data_user_level4 = uGrow4.getLevel();
		this.data_user_kill4 = uGrow4.getKill();
		this.data_user_death4 = uGrow4.getDeath();
		this.data_user_assist4 = uGrow4.getAssist();
		this.data_user_champ5 = userTeam.getChampName5();
		this.data_user_level5 = uGrow5.getLevel();
		this.data_user_kill5 = uGrow5.getKill();
		this.data_user_death5 = uGrow5.getDeath();
		this.data_user_assist5 = uGrow5.getAssist();
		this.data_enemy_champ1 = enemyTeam.getChampName1();
		this.data_enemy_level1 = eGrow1.getLevel();
		this.data_enemy_kill1 = eGrow1.getKill();
		this.data_enemy_death1 = eGrow1.getDeath();
		this.data_enemy_assist1 = eGrow1.getAssist();
		this.data_enemy_champ2 = enemyTeam.getChampName2();
		this.data_enemy_level2 = eGrow2.getLevel();
		this.data_enemy_kill2 = eGrow2.getKill();
		this.data_enemy_death2 = eGrow2.getDeath();
		this.data_enemy_assist2 = eGrow2.getAssist();
		this.data_enemy_champ3 = enemyTeam.getChampName3();
		this.data_enemy_level3 = eGrow3.getLevel();
		this.data_enemy_kill3 = eGrow3.getKill();
		this.data_enemy_death3 = eGrow3.getDeath();
		this.data_enemy_assist3 = eGrow3.getAssist();
		this.data_enemy_champ4 = enemyTeam.getChampName4();
		this.data_enemy_level4 = eGrow4.getLevel();
		this.data_enemy_kill4 = eGrow4.getKill();
		this.data_enemy_death4 = eGrow4.getDeath();
		this.data_enemy_assist4 = eGrow4.getAssist();
		this.data_enemy_champ5 = enemyTeam.getChampName5();
		this.data_enemy_level5 = eGrow5.getLevel();
		this.data_enemy_kill5 = eGrow5.getKill();
		this.data_enemy_death5 = eGrow5.getDeath();
		this.data_enemy_assist5 = eGrow5.getAssist();
	}
	public String getData_sid() {
		return data_sid;
	}
	public void setData_sid(String data_sid) {
		this.data_sid = data_sid;
	}
	public String getData_user_champ1() {
		return data_user_champ1;
	}
	public void setData_user_champ1(String data_user_champ1) {
		this.data_user_champ1 = data_user_champ1;
	}
	public int getData_user_level1() {
		return data_user_level1;
	}
	public void setData_user_level1(int data_user_level1) {
		this.data_user_level1 = data_user_level1;
	}
	public int getData_user_kill1() {
		return data_user_kill1;
	}
	public void setData_user_kill1(int data_user_kill1) {
		this.data_user_kill1 = data_user_kill1;
	}
	public int getData_user_death1() {
		return data_user_death1;
	}
	public void setData_user_death1(int data_user_death1) {
		this.data_user_death1 = data_user_death1;
	}
	public int getData_user_assist1() {
		return data_user_assist1;
	}
	public void setData_user_assist1(int data_user_assist1) {
		this.data_user_assist1 = data_user_assist1;
	}
	public String getData_user_champ2() {
		return data_user_champ2;
	}
	public void setData_user_champ2(String data_user_champ2) {
		this.data_user_champ2 = data_user_champ2;
	}
	public int getData_user_level2() {
		return data_user_level2;
	}
	public void setData_user_level2(int data_user_level2) {
		this.data_user_level2 = data_user_level2;
	}
	public int getData_user_kill2() {
		return data_user_kill2;
	}
	public void setData_user_kill2(int data_user_kill2) {
		this.data_user_kill2 = data_user_kill2;
	}
	public int getData_user_death2() {
		return data_user_death2;
	}
	public void setData_user_death2(int data_user_death2) {
		this.data_user_death2 = data_user_death2;
	}
	public int getData_user_assist2() {
		return data_user_assist2;
	}
	public void setData_user_assist2(int data_user_assist2) {
		this.data_user_assist2 = data_user_assist2;
	}
	public String getData_user_champ3() {
		return data_user_champ3;
	}
	public void setData_user_champ3(String data_user_champ3) {
		this.data_user_champ3 = data_user_champ3;
	}
	public int getData_user_level3() {
		return data_user_level3;
	}
	public void setData_user_level3(int data_user_level3) {
		this.data_user_level3 = data_user_level3;
	}
	public int getData_user_kill3() {
		return data_user_kill3;
	}
	public void setData_user_kill3(int data_user_kill3) {
		this.data_user_kill3 = data_user_kill3;
	}
	public int getData_user_death3() {
		return data_user_death3;
	}
	public void setData_user_death3(int data_user_death3) {
		this.data_user_death3 = data_user_death3;
	}
	public int getData_user_assist3() {
		return data_user_assist3;
	}
	public void setData_user_assist3(int data_user_assist3) {
		this.data_user_assist3 = data_user_assist3;
	}
	public String getData_user_champ4() {
		return data_user_champ4;
	}
	public void setData_user_champ4(String data_user_champ4) {
		this.data_user_champ4 = data_user_champ4;
	}
	public int getData_user_level4() {
		return data_user_level4;
	}
	public void setData_user_level4(int data_user_level4) {
		this.data_user_level4 = data_user_level4;
	}
	public int getData_user_kill4() {
		return data_user_kill4;
	}
	public void setData_user_kill4(int data_user_kill4) {
		this.data_user_kill4 = data_user_kill4;
	}
	public int getData_user_death4() {
		return data_user_death4;
	}
	public void setData_user_death4(int data_user_death4) {
		this.data_user_death4 = data_user_death4;
	}
	public int getData_user_assist4() {
		return data_user_assist4;
	}
	public void setData_user_assist4(int data_user_assist4) {
		this.data_user_assist4 = data_user_assist4;
	}
	public String getData_user_champ5() {
		return data_user_champ5;
	}
	public void setData_user_champ5(String data_user_champ5) {
		this.data_user_champ5 = data_user_champ5;
	}
	public int getData_user_level5() {
		return data_user_level5;
	}
	public void setData_user_level5(int data_user_level5) {
		this.data_user_level5 = data_user_level5;
	}
	public int getData_user_kill5() {
		return data_user_kill5;
	}
	public void setData_user_kill5(int data_user_kill5) {
		this.data_user_kill5 = data_user_kill5;
	}
	public int getData_user_death5() {
		return data_user_death5;
	}
	public void setData_user_death5(int data_user_death5) {
		this.data_user_death5 = data_user_death5;
	}
	public int getData_user_assist5() {
		return data_user_assist5;
	}
	public void setData_user_assist5(int data_user_assist5) {
		this.data_user_assist5 = data_user_assist5;
	}
	public String getData_enemy_champ1() {
		return data_enemy_champ1;
	}
	public void setData_enemy_champ1(String data_enemy_champ1) {
		this.data_enemy_champ1 = data_enemy_champ1;
	}
	public int getData_enemy_level1() {
		return data_enemy_level1;
	}
	public void setData_enemy_level1(int data_enemy_level1) {
		this.data_enemy_level1 = data_enemy_level1;
	}
	public int getData_enemy_kill1() {
		return data_enemy_kill1;
	}
	public void setData_enemy_kill1(int data_enemy_kill1) {
		this.data_enemy_kill1 = data_enemy_kill1;
	}
	public int getData_enemy_death1() {
		return data_enemy_death1;
	}
	public void setData_enemy_death1(int data_enemy_death1) {
		this.data_enemy_death1 = data_enemy_death1;
	}
	public int getData_enemy_assist1() {
		return data_enemy_assist1;
	}
	public void setData_enemy_assist1(int data_enemy_assist1) {
		this.data_enemy_assist1 = data_enemy_assist1;
	}
	public String getData_enemy_champ2() {
		return data_enemy_champ2;
	}
	public void setData_enemy_champ2(String data_enemy_champ2) {
		this.data_enemy_champ2 = data_enemy_champ2;
	}
	public int getData_enemy_level2() {
		return data_enemy_level2;
	}
	public void setData_enemy_level2(int data_enemy_level2) {
		this.data_enemy_level2 = data_enemy_level2;
	}
	public int getData_enemy_kill2() {
		return data_enemy_kill2;
	}
	public void setData_enemy_kill2(int data_enemy_kill2) {
		this.data_enemy_kill2 = data_enemy_kill2;
	}
	public int getData_enemy_death2() {
		return data_enemy_death2;
	}
	public void setData_enemy_death2(int data_enemy_death2) {
		this.data_enemy_death2 = data_enemy_death2;
	}
	public int getData_enemy_assist2() {
		return data_enemy_assist2;
	}
	public void setData_enemy_assist2(int data_enemy_assist2) {
		this.data_enemy_assist2 = data_enemy_assist2;
	}
	public String getData_enemy_champ3() {
		return data_enemy_champ3;
	}
	public void setData_enemy_champ3(String data_enemy_champ3) {
		this.data_enemy_champ3 = data_enemy_champ3;
	}
	public int getData_enemy_level3() {
		return data_enemy_level3;
	}
	public void setData_enemy_level3(int data_enemy_level3) {
		this.data_enemy_level3 = data_enemy_level3;
	}
	public int getData_enemy_kill3() {
		return data_enemy_kill3;
	}
	public void setData_enemy_kill3(int data_enemy_kill3) {
		this.data_enemy_kill3 = data_enemy_kill3;
	}
	public int getData_enemy_death3() {
		return data_enemy_death3;
	}
	public void setData_enemy_death3(int data_enemy_death3) {
		this.data_enemy_death3 = data_enemy_death3;
	}
	public int getData_enemy_assist3() {
		return data_enemy_assist3;
	}
	public void setData_enemy_assist3(int data_enemy_assist3) {
		this.data_enemy_assist3 = data_enemy_assist3;
	}
	public String getData_enemy_champ4() {
		return data_enemy_champ4;
	}
	public void setData_enemy_champ4(String data_enemy_champ4) {
		this.data_enemy_champ4 = data_enemy_champ4;
	}
	public int getData_enemy_level4() {
		return data_enemy_level4;
	}
	public void setData_enemy_level4(int data_enemy_level4) {
		this.data_enemy_level4 = data_enemy_level4;
	}
	public int getData_enemy_kill4() {
		return data_enemy_kill4;
	}
	public void setData_enemy_kill4(int data_enemy_kill4) {
		this.data_enemy_kill4 = data_enemy_kill4;
	}
	public int getData_enemy_death4() {
		return data_enemy_death4;
	}
	public void setData_enemy_death4(int data_enemy_death4) {
		this.data_enemy_death4 = data_enemy_death4;
	}
	public int getData_enemy_assist4() {
		return data_enemy_assist4;
	}
	public void setData_enemy_assist4(int data_enemy_assist4) {
		this.data_enemy_assist4 = data_enemy_assist4;
	}
	public String getData_enemy_champ5() {
		return data_enemy_champ5;
	}
	public void setData_enemy_champ5(String data_enemy_champ5) {
		this.data_enemy_champ5 = data_enemy_champ5;
	}
	public int getData_enemy_level5() {
		return data_enemy_level5;
	}
	public void setData_enemy_level5(int data_enemy_level5) {
		this.data_enemy_level5 = data_enemy_level5;
	}
	public int getData_enemy_kill5() {
		return data_enemy_kill5;
	}
	public void setData_enemy_kill5(int data_enemy_kill5) {
		this.data_enemy_kill5 = data_enemy_kill5;
	}
	public int getData_enemy_death5() {
		return data_enemy_death5;
	}
	public void setData_enemy_death5(int data_enemy_death5) {
		this.data_enemy_death5 = data_enemy_death5;
	}
	public int getData_enemy_assist5() {
		return data_enemy_assist5;
	}
	public void setData_enemy_assist5(int data_enemy_assist5) {
		this.data_enemy_assist5 = data_enemy_assist5;
	}

	public void setChampNameToEng(List<UpdateChampsVo> champData) {
		// TODO Auto-generated method stub
		for(int i=0; i<champData.size(); i++) {
			String champStrKor = champData.get(i).getChamps_name();
			String champStrEng = champData.get(i).getChamps_id();
			if(this.data_user_champ1.equals(champStrKor)) {
				this.data_user_champ1 = champStrEng;
			}
			if(this.data_user_champ2.equals(champStrKor)) {
				this.data_user_champ2 = champStrEng;
			}
			if(this.data_user_champ3.equals(champStrKor)) {
				this.data_user_champ3 = champStrEng;
			}
			if(this.data_user_champ4.equals(champStrKor)) {
				this.data_user_champ4 = champStrEng;
			}
			if(this.data_user_champ5.equals(champStrKor)) {
				this.data_user_champ5 = champStrEng;
			}
			if(this.data_enemy_champ1.equals(champStrKor)) {
				this.data_enemy_champ1 = champStrEng;
			}
			if(this.data_enemy_champ2.equals(champStrKor)) {
				this.data_enemy_champ2 = champStrEng;
			}
			if(this.data_enemy_champ3.equals(champStrKor)) {
				this.data_enemy_champ3 = champStrEng;
			}
			if(this.data_enemy_champ4.equals(champStrKor)) {
				this.data_enemy_champ4 = champStrEng;
			}
			if(this.data_enemy_champ5.equals(champStrKor)) {
				this.data_enemy_champ5 = champStrEng;
			}
		}
	}
}
