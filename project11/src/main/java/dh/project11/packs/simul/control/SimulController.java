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
			model.addAttribute("logs", simulLogVos);
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
		List<UpdateChampsVo> champData = simulService.getChampList(simulService.getLatestVersion());
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
			dataVo.setChampNameToEng(champData);
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
	
	/*
	 * @RequestMapping(value = "/tempReset/{userId}", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public void listPageUser(@PathVariable String userId) {
	 * simulService.delTempId(userId); }
	 */
}
