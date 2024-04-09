package dh.project11.packs.simul.object;

public class SimulLogVo {

	private String logs_sid;
	private String logs_fid;
	private String logs_tid;
	private String logs_message;
	private int logs_type;
	private int logs_visible;
	
	public SimulLogVo(){
		
	}
	public String getLogs_sid() {
		return logs_sid;
	}
	public void setLogs_sid(String logs_sid) {
		this.logs_sid = logs_sid;
	}
	public String getLogs_fid() {
		return logs_fid;
	}
	public void setLogs_fid(String logs_fid) {
		this.logs_fid = logs_fid;
	}
	public String getLogs_tid() {
		return logs_tid;
	}
	public void setLogs_tid(String logs_tid) {
		this.logs_tid = logs_tid;
	}
	public String getLogs_message() {
		return logs_message;
	}
	public void setLogs_message(String logs_message) {
		this.logs_message = logs_message;
	}
	public int getLogs_type() {
		return logs_type;
	}
	public void setLogs_type(int logs_type) {
		this.logs_type = logs_type;
	}
	public int getLogs_visible() {
		return logs_visible;
	}
	public void setLogs_visible(int logs_visible) {
		this.logs_visible = logs_visible;
	}
}
