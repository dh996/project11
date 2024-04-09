package dh.project11.packs.simul.object;

import org.springframework.stereotype.Component;

@Component
public class SimulLogMessage {
	
	private String message;
	private int type;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
