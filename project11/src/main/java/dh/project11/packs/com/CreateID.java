package dh.project11.packs.com;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CreateID {

	public String createUID() {
		// TODO Auto-generated method stub
		String uid = UUID.randomUUID().toString();
		String type = "UID_";
		return type+uid;
	}
	
	public String createSID() {
		// TODO Auto-generated method stub
		String uid = UUID.randomUUID().toString();
		String type = "SID_";
		return type+uid;
	}
	
	public String createFID() {
		// TODO Auto-generated method stub
		String uid = UUID.randomUUID().toString();
		String type = "FID_";
		return type+uid;
	}
	
	public String createTID() {
		// TODO Auto-generated method stub
		String uid = UUID.randomUUID().toString();
		String type = "TID_";
		return type+uid;
	}
	
	public String createTempID() {
		// TODO Auto-generated method stub
		String uid = UUID.randomUUID().toString();
		String type = "TempID_";
		return type+uid;
	}
}
