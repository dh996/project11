package dh.project11.packs.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	AdminDao adminDao;

	public Map<String, Object> versionLoad(String version) {
		Map<String, Object> data = adminDao.versionLoad(version);
		return data;
	}

	public List<String> versionUpdate(ResponseEntity<String> response, String version) {
		String jsonString = response.getBody();
		adminDao.versionSetting(version);
		List<String> keyData = adminDao.versionUpdate(jsonString);
		return keyData;
	}

	public void uploadChampData(UpdateData data) {
		// TODO Auto-generated method stub
		adminDao.uploadChampData(data);
	}

}
