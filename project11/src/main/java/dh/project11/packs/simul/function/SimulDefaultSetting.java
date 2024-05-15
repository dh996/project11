package dh.project11.packs.simul.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dh.project11.packs.admin.AdminDao;
import dh.project11.packs.admin.UpdateChampsVo;
import dh.project11.packs.simul.control.SimulDao;
import dh.project11.packs.simul.object.ChampGrow;
import dh.project11.packs.simul.object.SimulInfo;
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.TeamInfo;

@Component
public class SimulDefaultSetting {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	SimulDao simulDao;
	
	@Autowired
	Random random;
	
	@Autowired
	SimulLogging simulLogging;

	public SimulInfo setUser(TeamInfo userTeam, String version, String sid) {
		// TODO Auto-generated method stub
		SimulInfo simulInfo = new SimulInfo();
		simulInfo.setSid(sid);
		simulLogging.setSid(sid);
		simulDao.createSimulList(version, sid);
		TeamInfo enemyTeam = generateEnemyTeam(version);
		simulInfo.setVersion(version);
		TeamInfo userTeamReset = positionBuilding(userTeam, version, true);
		TeamInfo enemyTeamReset = positionBuilding(enemyTeam, version, false);
		simulInfo.setUserTeam(userTeamReset);
		simulInfo.setEnemyTeam(enemyTeamReset);
		return simulInfo;
	}

	private TeamInfo generateEnemyTeam(String version) {
		TeamInfo enemyTeam = new TeamInfo();
		List<UpdateChampsVo> champList = adminDao.getChampList(version);
		List<UpdateChampsVo> pickChampList = new ArrayList<>();
		List<SimulLogMessage> enemyTeamLog = new ArrayList<>();
		int champCount = champList.size();
		for(int i=0; i<5; i++) {
			UpdateChampsVo pickChamp = champList.get(random.nextInt(champCount));
			boolean pickCheck = deplicateTestEnemyTeam(i, pickChampList, pickChamp);
			if(pickCheck==true) {
				pickChampList.add(pickChamp);
			}else {
				i--;
			}
		}
		enemyTeam.setTeam("AI",
				pickChampList.get(0).getChamps_cid(),
				pickChampList.get(1).getChamps_cid(), 	
				pickChampList.get(2).getChamps_cid(),
				pickChampList.get(3).getChamps_cid(),
				pickChampList.get(4).getChamps_cid(),
				pickChampList.get(0).getChamps_name(),
				pickChampList.get(1).getChamps_name(),
				pickChampList.get(2).getChamps_name(),
				pickChampList.get(3).getChamps_name(),
				pickChampList.get(4).getChamps_name());
		for(int i=0; i<pickChampList.size(); i++) {
			enemyTeamLog.add(simulLogging.pickChampLogging(
					pickChampList.get(i).getChamps_name(), false));
		}
		enemyTeam.setTeamLog(enemyTeamLog);
		return enemyTeam;
	}
	
	private boolean deplicateTestEnemyTeam(int i,
			List<UpdateChampsVo> pickChampList, UpdateChampsVo pickChamp) {
		int pickChampSize = pickChampList.size();
		for(int j=0; j<pickChampSize; j++) {
			if(pickChampList.get(j).equals(pickChamp)) {
				return false;
			}
		}
		boolean flag = false;
		List<String> champs_tags = pickChamp.getChamps_tags();
		switch(i) {
		    case 0: if(champs_tags.contains("Tank") || champs_tags.contains("Fighter")) {
		    	        flag = true;
		    	        break;
		            }else {
		            	flag = false;
		            	break;
		            }
		    case 1: if(champs_tags.contains("Fighter") || champs_tags.contains("Assassin")) {
		    	        flag = true;
		    	        break;
                    }else {
                    	flag = false;
                    	break;
                    }
		    case 2: if(champs_tags.contains("Mage")) {
                    	flag = true;
                    	break;
                    }else {
                    	flag = false;
                    	break;
                    }
		    case 3: if(champs_tags.contains("Marksman")) {
                    	flag = true;
                    	break;
                    }else {
                    	flag = false;
                    	break;
                    }
		    case 4: if(champs_tags.contains("Support")) {
                    	flag = true;
                    	break;
                    }else {
                    	flag = false;
                    	break;
                    }
		}
		return flag;
	}
	
	private TeamInfo positionBuilding(TeamInfo teamInfo, String version, boolean type){
		List<UpdateChampsVo> teamList = adminDao.callPositionBuilding(teamInfo, version);
		List<String> needPosition = new ArrayList<>();
		int containMageFlag = 0;
		int containTankerFlag = 0;
		int containSupporterFlag = 0;
		int containMarksmanFlag = 0;
		int containFighterFlag = 0;
		int containAssassinFlag = 0;
		for(int i=0; i<teamList.size(); i++) {
			List<String> champPosition = teamList.get(i).getChamps_tags();
			if(champPosition.contains("Marksman")) {
				containMarksmanFlag++;
			}
			if(champPosition.contains("Tank")) {
				containTankerFlag++;
			}
			if(champPosition.contains("Fighter")) {
				containFighterFlag++;
			}
			if(champPosition.contains("Assassin")) {
				containAssassinFlag++;
			}
			if(champPosition.contains("Mage")) {
				containMageFlag++;
			}
			if(champPosition.contains("Support")) {
				containSupporterFlag++;
			}
		}
		if((containMageFlag+containMarksmanFlag)==1){
			needPosition.add("UniqDeal");
		}
		if(containTankerFlag==1){
			needPosition.add("UniqTank");
		}
		if((containMageFlag+containMarksmanFlag+containAssassinFlag)>1 && containSupporterFlag==1) {
			needPosition.add("DealSupt");
		}
		if(containFighterFlag>0 && containSupporterFlag==1 && containTankerFlag>0) {
			needPosition.add("TankSupt");
		}
		if(containSupporterFlag==0) {
			needPosition.add("needSupt");
		}
		if(containFighterFlag>0 && containTankerFlag==0) {
			needPosition.add("FightTank");
		}
		TeamInfo resetPosition = resetPositionBuild(teamInfo, needPosition, teamList, type);
		if(needPosition.contains("needSupt")) {
			resetPosition = forcePositionChangeToSup(resetPosition, type);
		}
		for (String item : needPosition) {
            System.out.println(item);
        }
		return resetPosition;
	}
	
	private TeamInfo resetPositionBuild(TeamInfo teamInfo, 
			List<String> needPosition, List<UpdateChampsVo> teamList, boolean type) {
		List<SimulLogMessage> teamPositionLog = teamInfo.getTeamLog();
		for(int i=0; i<teamList.size(); i++) {
			List<String> champPosition = teamList.get(i).getChamps_tags();
			String build = "";
			if(champPosition.size()==1) {
				build = singlePositionBuilding(champPosition,needPosition);
				teamInfo = setGrowBuild(i, build, teamInfo);
			}else {
				build = multiPositionBuilding(champPosition,needPosition);
				teamInfo = setGrowBuild(i, build, teamInfo);
			}
			teamPositionLog.add(simulLogging.resetPositionLogging(
					teamList.get(i).getChamps_name(), build, type));
		}
	//	teamInfo.setTeamLog(teamPositionLog);
		return teamInfo;
	}
	
	private String singlePositionBuilding(List<String> champPosition, List<String> needPosition) {
		String build = "";
		if(champPosition.contains("Tank") && needPosition.contains("UniqTank")) {
			build = "FureTank";
		}
		if(champPosition.contains("Tank") && !needPosition.contains("UniqTank")) {
			build = "Tank";
		}
		if(champPosition.contains("Assassin")) {
			build = "Assassin";
		}
		if(champPosition.contains("Marksman") && needPosition.contains("UniqDeal")) {
			build = "FureADC";
		}
		if(champPosition.contains("Marksman") && !needPosition.contains("UniqDeal")) {
			build = "ADC";
		}
		if(champPosition.contains("Mage") && needPosition.contains("UniqDeal")) {
			build = "FureMage";
		}
		if(champPosition.contains("Mage") && !needPosition.contains("UniqDeal")) {
			build = "Mage";
		}
		if(champPosition.contains("Fighter") && needPosition.contains("FightTank")) {
			build = "FightTank";
		}
		if(champPosition.contains("Fighter") && !needPosition.contains("FightTank")) {
			build = "Fighter";
		}
		if(champPosition.contains("Support")){
			build = "Support";
		}
		return build;
	}
	
	private String multiPositionBuilding(List<String> champPosition, List<String> needPosition) {
		String build = "";
		String mainPosition = champPosition.get(0);
		String subPosition = champPosition.get(1);
		if(mainPosition.equals("Tank") && needPosition.contains("UniqTank")) {
			if(subPosition.equals("Mage") && needPosition.contains("UniqDeal")) {
				build = "MageTank";
			}
			if(subPosition.equals("Mage") && !needPosition.contains("UniqDeal")) {
				build = "FureTank";
			}
			if(subPosition.equals("Support") && needPosition.contains("TankSupt")) {
				build = "TankSupport";
			}
			if(subPosition.equals("Support") && !needPosition.contains("TankSupt")) {
				build = "FureTank";
			}
			if(subPosition.equals("Fighter")) {
				build = "FightTank";
			}
			if(build.equals("")) {
				build = "Tank";
			}
		}
		if(mainPosition.equals("Tank") && !needPosition.contains("UniqTank")) {
			if(subPosition.equals("Mage") && needPosition.contains("UniqDeal")) {
				build = "MageTank";
			}
			if(subPosition.equals("Mage") && !needPosition.contains("UniqDeal")) {
				build = "Tank";
			}
			if(subPosition.equals("Support") && needPosition.contains("TankSupt")) {
				build = "TankSupport";
			}
			if(subPosition.equals("Support") && !needPosition.contains("TankSupt")) {
				build = "Tank";
			}
			if(subPosition.equals("Fighter")) {
				build = "Fighter";
			}
			if(build.equals("")) {
				build = "Tank";
			}
		}
		if(mainPosition.equals("Marksman") && needPosition.contains("UniqDeal")) {
			build = "FureADC";
		}
		if(mainPosition.equals("Marksman") && !needPosition.contains("UniqDeal")) {
			if(subPosition.equals("Support") && needPosition.contains("DealSupt")) {
				build = "ADSupport";
			}else {
				build = "ADC";
			}
		}
		if(mainPosition.equals("Assassin") && needPosition.contains("UniqDeal")) {
			if(subPosition.equals("Marksman")) {
				build = "FureADC";
			}
			if(subPosition.equals("Mage")) {
				build = "FureMage";
			}
			if(build.equals("")) {
				build = "Assassin";
			}
		}
		if(mainPosition.equals("Assassin") && !needPosition.contains("UniqDeal")) {
			build = "Assassin";
		}
		if(mainPosition.equals("Mage") && needPosition.contains("UniqDeal")) {
			build = "FureMage";
		}
		if(mainPosition.equals("Mage") && !needPosition.contains("UniqDeal")) {
			if(subPosition.equals("Support") && needPosition.contains("DealSupt")) {
				build = "MageSupport";
			}else {
				build = "Mage";
			}
		}
		if(mainPosition.equals("Fighter") && needPosition.contains("FightTank")) {
			build = "FightTank";
		}
		if(mainPosition.equals("Fighter") && !needPosition.contains("FightTank")) {
			if(subPosition.equals("Mage") && !needPosition.contains("UniqDeal")) {
				build = "FureMage";
			}
			if(subPosition.equals("Mage") && !needPosition.contains("UniqDeal")) {
				build = "Mage";
			}
			if(subPosition.equals("Tank")) {
				build = "Fighter";
			}
			if(build.equals("")) {
				build = "Fighter";
			}
		}
		if(mainPosition.equals("Support")) {
			if(subPosition.equals("Mage") && needPosition.contains("UniqDeal")) {
				build = "MageSupport";
			}
			if(subPosition.equals("Mage") && !needPosition.contains("UniqDeal")) {
				build = "UtilSupport";
			}
			if(subPosition.equals("Marksman") && needPosition.contains("UniqDeal")) {
				build = "ADC";
			}
			if(subPosition.equals("Marksman") && !needPosition.contains("UniqDeal")) {
				build = "ADSupport";
			}
			if(subPosition.equals("Tank") && needPosition.contains("TankSupt")) {
				build = "TankSupport";
			}
			if(subPosition.equals("Tank") && !needPosition.contains("TankSupt")) {
				build = "Support";
			}
			if(build.equals("")) {
				build = "Support";
			}
		}
		return build;
	}
	
	private TeamInfo forcePositionChangeToSup(TeamInfo teamInfo, boolean type) {
		System.out.println("5픽은 서폿을 갈시간");
		String positionInfo = teamInfo.getGrow5().getBuild();
		List<SimulLogMessage> teamPositionLog = teamInfo.getTeamLog();
		String build = "";
		if(positionInfo.equals("FureTank")
				|| positionInfo.equals("Tank")
				) {
			build = "TankSupport";
		}
		if(positionInfo.equals("Assassin")
				|| positionInfo.equals("FightTank")
				|| positionInfo.equals("Fighter")
				|| positionInfo.equals("MageTank")
				) {
			build = "DealSupport";
		}
		if(positionInfo.equals("Mage")
				|| positionInfo.equals("FureMage")
				) {
			build = "MageSupport";
		}
		if(positionInfo.equals("ADC")
				|| positionInfo.equals("FureADC")
				) {
			build = "ADSupport";
		}
		if(build.equals("")) {
			build = "Support";
		}
		teamInfo = setGrowBuild(4, build, teamInfo);
		teamPositionLog.add(simulLogging.forcePositionLogging(
				build, teamInfo.getChampName5(), type));
		teamInfo.setTeamLog(teamPositionLog);
		return teamInfo;
	}
	
	private TeamInfo setGrowBuild(int i, String build, TeamInfo teamInfo) {
		ChampGrow champGrow = new ChampGrow();
		champGrow.setExp(0);
		champGrow.setLevel(1);
		champGrow.setKill(0);
		champGrow.setDeath(0);
		champGrow.setAssist(0);
		champGrow.setBuild(build);
		champGrow.setBaronBuff(false);
		champGrow.setElderBuff(false);
		switch(i){
		    case 0: teamInfo.setGrow1(champGrow);
		            break;
		    case 1: teamInfo.setGrow2(champGrow);
                    break;
		    case 2: teamInfo.setGrow3(champGrow);
                    break;
		    case 3: teamInfo.setGrow4(champGrow);
                    break;
		    case 4: teamInfo.setGrow5(champGrow);
                    break;
		}
		return teamInfo;
	}
	
	public SimulInfo setWinLose(SimulInfo simulInfo, boolean win) {
		simulInfo.setWin(win);
		SimulLogMessage simulResult = new SimulLogMessage();
		if(win=true) {
			simulResult.setMessage("플레이어의 '승리'");
			simulResult.setType(0);
		}else {
			simulResult.setMessage("플레이어의 '패배'");
			simulResult.setType(1);
		}
		simulInfo.setSimulResult(simulResult);
		return simulInfo;
	}
}
