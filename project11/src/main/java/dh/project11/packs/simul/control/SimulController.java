package dh.project11.packs.simul.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dh.project11.packs.admin.AdminService;
import dh.project11.packs.admin.UpdateChampsVo;
import dh.project11.packs.com.CreateID;
import dh.project11.packs.simul.function.SimulDefaultSetting;
import dh.project11.packs.simul.function.SimulTeamFight;
import dh.project11.packs.simul.object.ReturnData;
import dh.project11.packs.simul.object.SimulDataVo;
import dh.project11.packs.simul.object.SimulInfo;
import dh.project11.packs.simul.object.SimulListDataVo;
import dh.project11.packs.simul.object.SimulListVo;
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.SimulLogVo;
import dh.project11.packs.simul.object.TeamInfo;

@Controller
@RequestMapping("/simul")
public class SimulController {
	
	//@Autowired
	//SimulService simulService;
	
	@Autowired
	SimulDefaultSetting simulDefaultSetting;
	
	@Autowired
	SimulTeamFight simulTeamFight;
	
	@Autowired
	CreateID createId;
	
	@Autowired
	SimulService simulService;
	
	@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
	public String home(String id, Model model) {
		System.out.println("simul/home");
		String nextPage = "/simul/simul_home";
		String version = simulService.getLatestVersion();
		model.addAttribute("version", version);
		List<UpdateChampsVo> champList = simulService.getChampList(version);
		model.addAttribute("champList", champList);
		model.addAttribute("id", id);
		return nextPage;
	}
	
	@RequestMapping(value = "/simulStart.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String startDo(@RequestBody Map<String, Object> requestData, Model model) {
		TeamInfo userTeam = new TeamInfo();
		List<SimulLogMessage> teamLog = new ArrayList<>();
		String version = (String) requestData.get("version");
		String userID = (String) requestData.get("userID");
		List<Map<String, String>> selectedChamps = (List<Map<String, String>>) requestData.get("selectedChamps");
		userTeam.setTeam("uid",
				selectedChamps.get(0).get("id"),
				selectedChamps.get(1).get("id"),
				selectedChamps.get(2).get("id"),
				selectedChamps.get(3).get("id"),
				selectedChamps.get(4).get("id"),
				selectedChamps.get(0).get("name"),
				selectedChamps.get(1).get("name"),
				selectedChamps.get(2).get("name"),
				selectedChamps.get(3).get("name"),
				selectedChamps.get(4).get("name")
				);
		System.out.println(selectedChamps.get(0).get("id")+
				selectedChamps.get(1).get("id")+
				selectedChamps.get(2).get("id")+
				selectedChamps.get(3).get("id")+
				selectedChamps.get(4).get("id")+
				selectedChamps.get(0).get("name")+
				selectedChamps.get(1).get("name")+
				selectedChamps.get(2).get("name")+
				selectedChamps.get(3).get("name")+
				selectedChamps.get(4).get("name"));
		userTeam.setTeamLog(teamLog);
		SimulInfo simulInfo = new SimulInfo();
		String sid = createId.createSID();
		simulInfo = simulDefaultSetting.setUser(userTeam, version, sid);
		simulInfo.setSid(sid);
		boolean win = simulTeamFight.startGame(simulInfo, sid);
		simulInfo = simulDefaultSetting.setWinLose(simulInfo,win);
		simulService.createSimulData(simulInfo, userID);
		//String nextPage = "simul/simul_channel";
		String tempID = simulService.createTempID(sid, userID);
		model.addAttribute("tempID",tempID);
		model.addAttribute("userID",userID);
		//model.addAttribute("nextPage",nextPage);
		return tempID;
	}
	@RequestMapping(value = "/simulResult/{tempID}/{userID}", method = {RequestMethod.GET})
	public String resultPage(@PathVariable String tempID, @PathVariable String userID, Model model) {
	
		model.addAttribute("tempID",tempID);
		model.addAttribute("userID",userID);
		String sid = simulService.getSid(tempID, userID);
		if(sid.equals("excess_exception")) {
			model.addAttribute("error", "excess_exception");
			model.addAttribute("list", "excess_exception");
			model.addAttribute("data", "excess_exception");
			model.addAttribute("logs", "excess_exception");
		}else {
			model.addAttribute("error", "none");
			SimulListVo simulListVo = simulService.getSimulList(sid);
			simulListVo.setList_sid(tempID);
			model.addAttribute("list", simulListVo);
			SimulDataVo simulDataVo = simulService.getSimulData(sid);
			simulDataVo.setData_sid(tempID);
			model.addAttribute("data", simulDataVo);
			List<SimulLogVo> simulLogVos = simulService.getSimulLogs(sid);
			for(SimulLogVo log : simulLogVos) {
			    log.setLogs_sid(tempID);
			}
			int turnCount = simulLogVos.get(simulLogVos.size()-1).getLogs_turncount();
			model.addAttribute("turnCount", turnCount);
			model.addAttribute("logs", simulLogVos);
			List<ReturnData> rDatas = new ArrayList<>();
			rDatas = packData(simulListVo, simulDataVo, simulLogVos, turnCount);
			model.addAttribute("rData", rDatas);
			simulService.delTempId(userID);
		}		
		String nextPage = "/simul/simul_result";
		return nextPage;
	}

	@RequestMapping(value = "/list/{userId}/{page}", method = {RequestMethod.GET, RequestMethod.POST})
	public String listPageUser(@PathVariable String userId, @PathVariable String page, Model model) {
		
		simulService.delTempId(userId);
		List<SimulListDataVo> simulListVo = new ArrayList<>();
		simulListVo = simulService.getAllSimulListForUser(userId);
		int listSize = simulListVo.size();
		int totalPage = (listSize/10)+1;
		String totPageStr = Integer.toString(totalPage);
		int pageInt = Integer.parseInt(page);
		int start = (pageInt*10)-10;
		int end = (pageInt*10);
		List<SimulListDataVo> returnData = new ArrayList<>();
		//List<UpdateChampsVo> champData = simulService.getChampList(simulService.getLatestVersion());
		if(end>listSize) {
			for(int i = start; i<listSize; i++) {
				returnData.add(simulListVo.get(i));
			}
		}else {
			for(int i = start; i<end; i++) {
				returnData.add(simulListVo.get(i));
			}
		}
		for(int i=0; i<returnData.size(); i++) {
			SimulListVo listVo = returnData.get(i).getSimulListVo();
			SimulDataVo dataVo = returnData.get(i).getSimulDataVo();
			String sid = listVo.getList_sid();
			String tempID = simulService.createTempID(sid, userId);
		//	dataVo.setChampNameToEng(champData);
			listVo.setList_sid(tempID);
			dataVo.setData_sid(tempID);
			returnData.get(i).setSimulListVo(listVo);
			returnData.get(i).setSimulDataVo(dataVo);
		}
		model.addAttribute("list", returnData);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totPageStr);
		model.addAttribute("id", userId);
		return "/simul/simul_list";
	}
	
	private List<ReturnData> packData(SimulListVo simulListVo, SimulDataVo simulDataVo, List<SimulLogVo> simulLogVos,
			int turnCount) {
		// TODO Auto-generated method stub
		List<ReturnData> rDatas = new ArrayList<>();
		String champ1U = simulDataVo.getData_user_champ1();
		String champ2U = simulDataVo.getData_user_champ2();
		String champ3U = simulDataVo.getData_user_champ3();
		String champ4U = simulDataVo.getData_user_champ4();
		String champ5U = simulDataVo.getData_user_champ5();
		String champ1E = simulDataVo.getData_enemy_champ1();
		String champ2E = simulDataVo.getData_enemy_champ2();
		String champ3E = simulDataVo.getData_enemy_champ3();
		String champ4E = simulDataVo.getData_enemy_champ4();
		String champ5E = simulDataVo.getData_enemy_champ5();
		List<String> dU = new ArrayList<>();
		List<String> dE = new ArrayList<>();
		int tU = 0;
		int tE = 0;
		int kU = 0;
		int kE = 0;
		int eU = 0;
		int eE = 0;
		int bU = 0;
		int bE = 0;
		int dcU = 0;
		int dcE = 0;
		String DragonNest = "x";
		ReturnData rData = new ReturnData();
		rData.setDragonNest("x");
		for(int i=1; i<turnCount+1; i++) {
			//ReturnData rData = new ReturnData(rDataM);
			//rData.setDragonNest(DragonNest);
			rData.resetChamp();
			rData.setRound(i);
			if(eE == 1) {
				eE = 2;
				rData.setElderE(1);
			}else if(eE == 2) {
				eE = 0;
			}else {
				rData.setElderE(0);
			}
			if(eU == 1) {
				eU = 2;
				rData.setElderU(1);
			}else if(eU == 2) {
				eU = 0;
			}else {
				rData.setElderU(0);
			}
			if(bE == 1) {
				bE = 2;
				rData.setBaronE(1);
			}else if(bE == 2) {
				bE = 0;
			}else {
				rData.setBaronE(0);
			}
			if(bU == 1) {
				bU = 2;
				rData.setBaronU(1);
			}else if(bU == 2) {
				bU = 0;
			}else {
				rData.setBaronU(0);
			}
			for(int j=0; j<simulLogVos.size(); j++) {
				if(simulLogVos.get(j).getLogs_turncount() == i) {
					if(simulLogVos.get(j).getLogs_attacker().equals("field")) {
						rData.setField(simulLogVos.get(j).getLogs_deffender());
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ1U)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							kE ++;
							rData.setKillE(kE);
							rData.setC1u(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ2U)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							kE ++;
							rData.setKillE(kE);
							rData.setC2u(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ3U)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							kE ++;
							rData.setKillE(kE);
							rData.setC3u(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ4U)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							kE ++;
							rData.setKillE(kE);
							rData.setC4u(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ5U)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							kE ++;
							rData.setKillE(kE);
							rData.setC5u(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ1E)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							kU ++;
							rData.setKillU(kU);
							rData.setC1e(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ2E)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							kU ++;
							rData.setKillU(kU);
							rData.setC2e(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ3E)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							kU ++;
							rData.setKillU(kU);
							rData.setC3e(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ4E)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							kU ++;
							rData.setKillU(kU);
							rData.setC4e(0);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals(champ5E)) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							kU ++;
							rData.setKillU(kU);
							rData.setC5e(0);
						}
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("baron")) {
						rData.setBaronNest(1);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("화염")) {
						DragonNest ="fire";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("대지")) {
						DragonNest ="earth";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("바다")) {
						DragonNest ="ocean";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("바람")) {
						DragonNest ="wind";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("마법공학")) {
						DragonNest ="hexatech";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("화학공학")) {
						DragonNest ="chemical";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_attacker().equals("elder")) {
						DragonNest ="elder";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("elder")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							eE = 1;
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							eU = 1;
						}
						DragonNest ="x";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("baron")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							bE = 1;
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							bU = 1;
						}
						rData.setBaronNest(0);
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("dragon")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							//List<String> dE = rData.getDragonE();
							dE.add(rData.getDragonNest());
							dcE ++;
							//rData.setDragonE(dE);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							//List<String> dU = rData.getDragonU();
							dU.add(rData.getDragonNest());
							dcU ++;
							//rData.setDragonU(dU);
						}
						DragonNest ="x";
						rData.setDragonNest(DragonNest);
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("TopFirstTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setT1E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setT1U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("TopSecondTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setT2E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setT2U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("TopThirdTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setT3E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setT3U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("TopInhibitor")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setTiE(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setTiU(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("MidFirstTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setM1E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setM1U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("MidSecondTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setM2E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setM2U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("MidThirdTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setM3E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setM3U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("MidInhibitor")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setMiE(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setMiU(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("BotFirstTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setB1E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setB1U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("BotSecondTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setB2E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setB2U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("BotThirdTower")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setB3E(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setB3U(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("BotInhibitor")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setBiE(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setBiU(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("TwinTowerLeft")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setLtE(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setLtU(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("TwinTowerRight")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setRtE(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setRtU(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("Nexus")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							rData.setNexusE(1);
							tU ++;
							rData.setTowerU(tU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							rData.setNexusU(1);
							tE ++;
							rData.setTowerE(tE);
						}
					}
					if(simulLogVos.get(j).getLogs_deffender().equals("c")) {
						if(simulLogVos.get(j).getLogs_attacker().equals("e")) {
							kU ++;
							rData.setKillU(kU);
						}else if(simulLogVos.get(j).getLogs_attacker().equals("u")) {
							kE ++;
							rData.setKillE(kE);
						}
					}
				}
			}
			List<String> drU = new ArrayList<>();
			List<String> drE = new ArrayList<>();
			if(dcU>0) {
				for(int j=0; j<dcU; j++) {
				    String drg = dU.get(j);
				    drU.add(drg);
			    }
			}
			if(dcE>0) {
				for(int j=0; j<dcE; j++) {
			        String drg = dE.get(j);
					drE.add(drg);
				}
		    }
			ReturnData rDataM = new ReturnData(rData, drU, drE);
			rDatas.add(rDataM);
		}
		return rDatas;
	}
	
	/*
	 * @RequestMapping(value = "/tempReset/{userId}", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public void listPageUser(@PathVariable String userId) {
	 * simulService.delTempId(userId); }
	 */
}
