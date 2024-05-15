package dh.project11.packs.simul.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dh.project11.packs.simul.control.SimulDao;
import dh.project11.packs.simul.control.SimulService;
import dh.project11.packs.simul.object.SimulLogMessage;

@Component
public class SimulLogging {
	
	private String sid;
	private String fid;
	private String tid;
	
	@Autowired
	SimulService simulService;

	public SimulLogMessage pickChampLogging(String champs_name, boolean type) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀 "+champs_name+" 선택");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀 "+champs_name+" 선택");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 0, 0, "x", "x");
		return message;
	}
	
	public SimulLogMessage resetPositionLogging(String champs_name, String build, boolean type) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀 "+champs_name+" 챔피언 "+build+" 빌드 선택");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀 "+champs_name+" 챔피언 "+build+" 빌드 선택");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, 0, "x", "x");
		return message;
	}
	
	public SimulLogMessage forcePositionLogging(String build, String champName5, boolean type) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀 "+champName5+" 포지션 중복으로 "+build+" 빌드 선택");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀 "+champName5+" 포지션 중복으로 "+build+" 빌드 선택");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, 0, "x", "x");
		return message;
	}
	
	public SimulLogMessage battleFieldLogging(int count, String battleField) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		message.setMessage(count+"번째 전장: "+battleField);
		System.out.println("로그입니다   "+message.getMessage());
		message.setType(2);
		simulService.messageLogging(message, sid, fid, tid, 0, count, "field", battleField);
		return message;
	}

	public SimulLogMessage levelUpLogging(int level, String champName, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀 "+champName+" 레벨 "+level+"로 성장");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀 "+champName+" 레벨 "+level+"로 성장");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, turnCount, "x", "x");
		return message;
	}

	public SimulLogMessage initLogging(String name, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀 "+name+" 의 선제공격");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀 "+name+" 의 선제공격");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, turnCount, "x", "x");
		return message;
	}

	public SimulLogMessage champDead(String name, String killer, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("적군 팀 "+name+" 이(가) "+killer+" 에 의해 사망");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "e", name);
		}else {
			message.setMessage("아군 팀 "+name+" 이(가) "+killer+" 에 의해 사망");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "u", name);
		}
		return message;
	}

	public SimulLogMessage towerAttack(String name, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀의 타워가 "+name+" 을 공격 300만큼 의 피해를 입힘");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀의 타워가 "+name+" 을 공격 300만큼 의 피해를 입힘");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, turnCount, "x", "x");
		return message;
	}

	public SimulLogMessage champAttack(String name, String killer, int damage, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀의 "+killer+" 이(가) "+name+" 을 공격 "+damage+"만큼 의 피해를 입힘");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀의 "+killer+" 이(가) "+name+" 을 공격 "+damage+"만큼 의 피해를 입힘");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, turnCount, "x", "x");
		return message;
	}

	public SimulLogMessage spawnBaronLogging(int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		message.setMessage("내셔 남작이 생성되었습니다");
		System.out.println("로그입니다   "+message.getMessage());
		message.setType(2);
		simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "baron", "x");
		return message;
	}

	public SimulLogMessage spawnDragonLogging(int dragonCount, String elemental, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		message.setMessage(dragonCount+"번째 드래곤이 생성되었습니다 이번 드래곤은 " + elemental + " 드래곤입니다");
		System.out.println("로그입니다   "+message.getMessage());
		message.setType(2);
		simulService.messageLogging(message, sid, fid, tid, 0, turnCount, elemental, "x");
		return message;
	}

	public SimulLogMessage spawnElderLogging(int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		message.setMessage("장로 드래곤이 생성되었습니다");
		System.out.println("로그입니다   "+message.getMessage());
		message.setType(2);
		simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "elder", "x");
		return message;
	}

	public SimulLogMessage killElder(boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀이 장로 드래곤을 처치했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "u", "elder");
		}else {
			message.setMessage("적군 팀이 장로 드래곤을 처치했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "e", "elder");
		}
		return message;
	}

	public SimulLogMessage killBaron(boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀이 내셔 남작을 처치했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "u", "baron");
		}else {
			message.setMessage("적군 팀이 내셔 남작을 처치했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "e", "baron");
		}
		return message;
	}

	public SimulLogMessage killDragon(boolean type, String elemental, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀이 " + elemental + " 드래곤을 처치했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "u", "dragon");
		}else {
			message.setMessage("적군 팀이 " + elemental + " 드래곤을 처치했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "e", "dragon");
		}
		return message;
	}

	public SimulLogMessage destroyTower(String string, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀이 적의 "+string+" 을 파괴했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "e", string);
		}else {
			message.setMessage("적군 팀이 아군의 "+string+" 을 파괴했습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
			simulService.messageLogging(message, sid, fid, tid, 0, turnCount, "u", string);
		}
		return message;
	}

	public SimulLogMessage attackTower(String string, int i, boolean type, int turnCount) {
		// TODO Auto-generated method stub
		SimulLogMessage message = new SimulLogMessage();
		if(type==true) {
			message.setMessage("아군 팀이 적의 "+string+" 에 "+i+"의 데미지를 주었습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(0);
		}else {
			message.setMessage("적군 팀이 아군의 "+string+" 에 "+i+"의 데미지를 주었습니다");
			System.out.println("로그입니다   "+message.getMessage());
			message.setType(1);
		}
		simulService.messageLogging(message, sid, fid, tid, 1, turnCount, "x", "x");
		return message;
	}

//	public SimulLogMessage freePassLine(String string, boolean type) {
//		// TODO Auto-generated method stub
//		SimulLogMessage message = new SimulLogMessage();
//		if(type==true) {
//			message.setMessage("아군 팀이 적의 "+string+"라인의 억제기까지 파괴를 성공하였습니다");
//			System.out.println("로그입니다   "+message.getMessage());
//			message.setType(0);
//		}else {
//			message.setMessage("적군 팀이 아군의 "+string+"라인의 억제기까지 파괴를 성공하였습니다");
//			System.out.println("로그입니다   "+message.getMessage());
//			message.setType(1);
//		}
//		simulService.messageLogging(message, sid, fid, tid, 0);
//		return message;
//	}

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

}
