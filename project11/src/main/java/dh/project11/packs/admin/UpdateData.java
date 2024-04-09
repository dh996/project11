package dh.project11.packs.admin;

import java.util.List;
import java.util.Map;

public class UpdateData {

	private String version;
	private ChampData champData;
	
	public static class ChampData{
		
		private String id;
		private String key;
		private String name;
		private List<String> tags;
		//private List<Skin> skins;
		private Map<String, Float> stats;
		
		public static class Skin{
			
			private String id;
			private int num;
			private String name;
			
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public int getNum() {
				return num;
			}
			public void setNum(int num) {
				this.num = num;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

		//public List<Skin> getSkins() {
		//	return skins;
		//}

		//public void setSkins(List<Skin> skins) {
		//	this.skins = skins;
		//}

		public Map<String, Float> getStats() {
			return stats;
		}

		public void setStats(Map<String, Float> stats) {
			this.stats = stats;
		}
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ChampData getChampData() {
		return champData;
	}

	public void setChampData(ChampData champData) {
		this.champData = champData;
	}
}
