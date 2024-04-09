package dh.project11.packs.simul.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dh.project11.packs.com.CreateID;
import dh.project11.packs.com.Encryption;
import dh.project11.packs.simul.object.SimulDataVo;
import dh.project11.packs.simul.object.SimulInfo;
import dh.project11.packs.simul.object.SimulListDataVo;
import dh.project11.packs.simul.object.SimulListVo;
import dh.project11.packs.simul.object.SimulLogMessage;
import dh.project11.packs.simul.object.SimulLogVo;
import dh.project11.packs.simul.object.TeamInfo;
import dh.project11.packs.simul.object.TempVo;

@Component
@Repository
public class SimulDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private CreateID createID;
	
	@Autowired
	private Encryption encryption;

	public void messageLogging(SimulLogMessage message, String sid, String fid, String tid, int visible) {
		// TODO Auto-generated method stub
		SimulLogVo simulLogVo = new SimulLogVo();
		simulLogVo.setLogs_sid(sid);
		if(fid != null) {
			simulLogVo.setLogs_fid(fid);
		}
		if(tid != null) {
			simulLogVo.setLogs_tid(tid);
		}
		simulLogVo.setLogs_message(message.getMessage());
		simulLogVo.setLogs_type(message.getType());
		simulLogVo.setLogs_visible(visible);
		sqlSession.insert("mapper.SimulMapper.insertSimulLog", simulLogVo);
	}

	public void createSimulList(String version, String sid) {
		// TODO Auto-generated method stub
		SimulListVo simulListVo = new SimulListVo();
		simulListVo.setList_sid(sid);
		simulListVo.setList_player("Dummy");
		simulListVo.setList_winlose(1);
		simulListVo.setList_version(version);
		sqlSession.insert("mapper.SimulMapper.createSimulList", simulListVo);
	}

	public void createSimulData(SimulInfo simulInfo, String userID) {
		// TODO Auto-generated method stub
		SimulDataVo simulDataVo = new SimulDataVo(simulInfo);
		sqlSession.insert("mapper.SimulMapper.createSimulData", simulDataVo);
		SimulListVo simulListVo = new SimulListVo();
		simulListVo.setList_sid(simulInfo.getSid());
		simulListVo.setList_player(userID);
		boolean winLose = simulInfo.isWin();
		if(winLose == true) {
			simulListVo.setList_winlose(1);
		}else {
			simulListVo.setList_winlose(0);
		}
		sqlSession.update("mapper.SimulMapper.modiftSimulListForResult", simulListVo);
	}

	public SimulListVo getSimulList(String sid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.SimulMapper.getSimulList", sid);
	}

	public SimulDataVo getSimulData(String sid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.SimulMapper.getSimulData", sid);
	}

	public List<SimulLogVo> getSimulLogs(String sid) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.SimulMapper.getSimulLogs", sid);
	}

	public List<SimulListDataVo> getAllSimulListForAdmin() {
		// TODO Auto-generated method stub
		List<SimulListDataVo> returnList = new ArrayList<>();
		List<SimulListVo> listList = sqlSession.selectList("mapper.SimulMapper.getAllSimulListForAdmin");
		List<SimulDataVo> dataList = sqlSession.selectList("mapper.SimulMapper.getAllSimulData");
		returnList = packingListDataVos(listList, dataList);
		return returnList;
	}
	
	public List<SimulListDataVo> getAllSimulListForUser(String userID) {
		// TODO Auto-generated method stub
		List<SimulListDataVo> returnList = new ArrayList<>();
		List<SimulListVo> listList = sqlSession.selectList("mapper.SimulMapper.getAllSimulListForUser", userID);
		List<SimulDataVo> dataList = sqlSession.selectList("mapper.SimulMapper.getAllSimulData");
		returnList = packingListDataVos(listList, dataList);
		return returnList;
	}

	private List<SimulListDataVo> packingListDataVos(List<SimulListVo> listList, List<SimulDataVo> dataList) {
		// TODO Auto-generated method stub
		List<SimulListDataVo> returnList = new ArrayList<>();
		for(int i=0; i<listList.size(); i++) {
			SimulListDataVo listDataVo = new SimulListDataVo();
			SimulListVo listVo = listList.get(i);
			listDataVo.setSimulListVo(listVo);
			for(int j=0; j<dataList.size(); j++) {
				SimulDataVo dataVo = dataList.get(j);
				if(dataVo.getData_sid().equals(listVo.getList_sid())) {
					listDataVo.setSimulDataVo(dataVo);
					break;
				}
			}
			returnList.add(listDataVo);
		}
		return returnList;
	}

	public String createTempID(String sid, String userID) {
		// TODO Auto-generated method stub
		String tempID = createID.createTempID();
		String ip = "";
		try {
			ip = encryption.encrypt(userID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TempVo tempVo = new TempVo();
		tempVo.setTemp_tempid(tempID);
		tempVo.setTemp_sid(sid);
		tempVo.setTemp_ip(ip);
		sqlSession.insert("mapper.SimulMapper.createTempID", tempVo);
		return tempID;
	}

	public String getSid(String tempID, String userID) {
		// TODO Auto-generated method stub
		TempVo tempVo = new TempVo();
		String ip = "";
		try {
			ip = encryption.encrypt(userID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tempVo = sqlSession.selectOne("mapper.SimulMapper.getSid", tempID);
		String user = tempVo.getTemp_ip();
		if(user.equals(ip) || userID.equals("Admin")) {
		    return tempVo.getTemp_sid();
		}else {
			return "excess_exception";
		}
	}

	public void delTempId(String userID) {
		// TODO Auto-generated method stub
		String ip = "";
		try {
			ip = encryption.encrypt(userID);
			sqlSession.delete("mapper.SimulMapper.delTempId", ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
