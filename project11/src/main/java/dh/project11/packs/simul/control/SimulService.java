package dh.project11.packs.simul.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dh.project11.packs.admin.AdminDao;
import dh.project11.packs.admin.UpdateChampsVo;
import dh.project11.packs.simul.object.SimulDataVo;
import dh.project11.packs.simul.object.SimulInfo;
import dh.project11.packs.simul.object.SimulListDataVo;
import dh.project11.packs.simul.object.SimulListVo;
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.SimulLogVo;

@Service
public class SimulService {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	SimulDao simulDao;

	public String getLatestVersion() {
		// TODO Auto-generated method stub
		return adminDao.getLatestVersion();
	}

	public List<UpdateChampsVo> getChampList(String version) {
		// TODO Auto-generated method stub
		return adminDao.getChampList(version);
	}

	public void createSimulData(SimulInfo simulInfo, String userID) {
		// TODO Auto-generated method stub
		simulDao.createSimulData(simulInfo, userID);
	}

	public SimulListVo getSimulList(String sid) {
		// TODO Auto-generated method stub
		return simulDao.getSimulList(sid);
	}

	public SimulDataVo getSimulData(String sid) {
		// TODO Auto-generated method stub
		return simulDao.getSimulData(sid);
	}

	public List<SimulLogVo> getSimulLogs(String sid) {
		// TODO Auto-generated method stub
		return simulDao.getSimulLogs(sid);
	}
	
	public void messageLogging(SimulLogMessage message, String sid, String fid, String tid, int i) {
		// TODO Auto-generated method stub
		simulDao.messageLogging(message, sid, fid, tid, i);
	}

	public String createTempID(String sid, String userID) {
		// TODO Auto-generated method stub
		String tempID = simulDao.createTempID(sid, userID);
		return tempID;
	}

	public String getSid(String tempID, String userID) {
		// TODO Auto-generated method stub
		String sid = simulDao.getSid(tempID, userID);
		return sid;
	}

	public List<SimulListDataVo> getAllSimulListForUser(String id) {
		// TODO Auto-generated method stub
		if(id.equals("Admin")) {
			return simulDao.getAllSimulListForAdmin();
		}else {
			return simulDao.getAllSimulListForUser(id);
		}
	}

	public void delTempId(String userID) {
		// TODO Auto-generated method stub
		simulDao.delTempId(userID);
	}

}
