package dh.project11.packs.admin;

import java.util.List;

public class UpdateChampsVo {
	
	private String champs_version;
	private String champs_cid;
	private String champs_name;
	private String champs_id;
	private List<String> champs_tags;
	
	public String getChamps_version() {
		return champs_version;
	}
	public void setChamps_version(String champs_version) {
		this.champs_version = champs_version;
	}
	public String getChamps_cid() {
		return champs_cid;
	}
	public void setChamps_cid(String champs_cid) {
		this.champs_cid = champs_cid;
	}
	public String getChamps_name() {
		return champs_name;
	}
	public void setChamps_name(String champs_name) {
		this.champs_name = champs_name;
	}
	public String getChamps_id() {
		return champs_id;
	}
	public void setChamps_id(String champs_id) {
		this.champs_id = champs_id;
	}
	public List<String> getChamps_tags() {
		return champs_tags;
	}
	public void setChamps_tags(List<String> champs_tags) {
		this.champs_tags = champs_tags;
	}
}
