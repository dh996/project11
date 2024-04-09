package dh.project11.packs.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import dh.project11.packs.admin.UpdateData;
import dh.project11.packs.admin.UpdateData.ChampData;
import dh.project11.packs.simul.control.SimulService;
import dh.project11.packs.simul.object.SimulDataVo;
import dh.project11.packs.simul.object.SimulListDataVo;
import dh.project11.packs.simul.object.SimulListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin")
public class AdminMainController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	SimulService simulService;
	
	@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
	public String home() {
		System.out.println("admin/home");
		String nextPage = "/admin/main_controller";
		return nextPage;
	}
	
	@RequestMapping(value = "/versionup.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String versionUpDo(String version, Model model){
		//기본 다음페이지 버전로딩완료페이지로 세팅
		System.out.println("versionup.do");
		String nextPage = "/admin/include/versionup_success="+version;
		RestTemplate restTemplate = new RestTemplate();
		String apiUrl = "https://ddragon.leagueoflegends.com/cdn/"+version+"/data/ko_KR/champion.json";
		Map<String, Object> champsData = new HashMap<>();
		List<String> keyData = new ArrayList<>();
		model.addAttribute("version", version);
		
		try {
	        champsData = adminService.versionLoad(version);
	        model.addAttribute("champsData", champsData);
	    } catch (Exception e) {
	    	
	    	try {
	    		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
	            keyData = adminService.versionUpdate(response, version);
	            
	            String jsonString = response.getBody();
	            setUpdata(jsonString);
	            model.addAttribute("keyData", keyData);
	            //입력된 버전이 아니면 버전업데이트페이지로 세팅
	            nextPage = "/admin/include/versionup_success";
	    	}catch (Exception e2) {
	    		model.addAttribute("errorMessage", e2.getMessage());
		        nextPage = "/admin/include/error";
	    	}
	    }
		return nextPage;
	}
	/*
	 * @RequestMapping(value = "/simulListForAdmin", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public String listPageAdmin(Model model) {
	 * 
	 * List<SimulListDataVo> simulListVo = simulService.getAllSimulListForAdmin();
	 * model.addAttribute("list", simulListVo); model.addAttribute("id", "Admin");
	 * return "/simul/simul_list"; }
	 */
	
	private void setUpdata(String jsonString) {
		// TODO Auto-generated method stub
	    try {
	        // ObjectMapper 생성
	        ObjectMapper objectMapper = new ObjectMapper();
	        // JSON 문자열을 자바 객체로 변환
	        Map<String, Object> reData = objectMapper.readValue(
	        		jsonString, new TypeReference<Map<String, Object>>() {});
            String version = (String) reData.get("version");
            Map<String, Object> data = (Map<String, Object>) reData.get("data");
            List<String> keys = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            for(Map.Entry<String,Object> entry : data.entrySet()) {
            	keys.add(entry.getKey());
            	values.add(entry.getValue());
            }
            for(int i=0; i<keys.size(); i++) {
            	UpdateData updateData = new UpdateData();
            	updateData.setVersion(version);
            	ChampData champData = new ChampData();
                champData.setId(keys.get(i));
                Map<String, Object> valueMap = (Map<String, Object>) values.get(i);
                champData.setKey((String) valueMap.get("key"));
                champData.setName((String) valueMap.get("name"));
                champData.setTags((List<String>) valueMap.get("tags"));
                champData.setStats((Map<String, Float>) valueMap.get("stats"));
                updateData.setChampData(champData);

                try {
                    adminService.uploadChampData(updateData);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            
            for(int i=0; i<data.size(); i++) {
            	
            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@RequestMapping(value = "/versionup.start", method = {RequestMethod.GET, RequestMethod.POST})
	public String versionUpStart() {
		String nextPage = "/admin/main_controller?start";
		return nextPage;
	}
	
	@RequestMapping(value = "/versionup.upload", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> uploadChampData(@RequestBody Map<String, Object> requestData) {
		System.out.println("versionup.upload");
		try {
			adminService.uploadChampData(fromMap(requestData));
		    return ResponseEntity.ok("Data received successfully");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process data: " + e.getMessage());
		}
	}
	
	public static UpdateData fromMap(Map<String, Object> requestData) {
        UpdateData updateData = new UpdateData();
        updateData.setVersion((String) requestData.get("version"));
    
        Map<String, Object> champDataMap = (Map<String, Object>) requestData.get("data");
        ChampData champData = new ChampData();
        champData.setId((String) champDataMap.get("id"));
        champData.setKey((String) champDataMap.get("key"));
        champData.setName((String) champDataMap.get("name"));
        
        List<String> tagsList = (List<String>) champDataMap.get("tags");
        champData.setTags(tagsList);
    
        // Skin 데이터 매핑
        List<Map<String, Object>> skinsMapList = (List<Map<String, Object>>) champDataMap.get("skins");
        List<ChampData.Skin> skins = skinsMapList.stream()
                .map(skinMap -> {
                    ChampData.Skin skin = new ChampData.Skin();
                    skin.setId((String) skinMap.get("id"));
                    skin.setNum((int) skinMap.get("num"));
                    skin.setName((String) skinMap.get("name"));
                    return skin;
                })
                .collect(Collectors.toList());
    
        //champData.setSkins(skins);
    
        // Stats 데이터 매핑
        Map<String, Float> statsMap = (Map<String, Float>) champDataMap.get("stats");
        champData.setStats(statsMap);
    
        updateData.setChampData(champData);
    
        return updateData;
    }
	
	//@RequestMapping(value = "/versionup.img", method = {RequestMethod.GET, RequestMethod.POST})
	//@ResponseBody
	//public void updateChampImg(@RequestBody Map<String, Object> requestData, HttpServletResponse response) {
	//	String folderPath = "C:\\Dev\\workspaces\\project11\\project11\\src\\main\\webapp\\resources\\img\\";
	//	String champImgCode = (String)requestData.get("champImgCode");
	//	String champSkinCode = (String)requestData.get("champSkinCode");
	//    String version = (String)requestData.get("version");
	//    String imgFileName = champImgCode + "_"+champSkinCode+".jpg";
	//	String imgUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + imgFileName;
    //	String imgFilePath = folderPath + version + "\\" + imgFileName;
    //	File folder = new File(folderPath + version);
    //    if (!folder.exists()) {
    //        folder.mkdirs();
    //    }
    //    
    //    if(champSkinCode.equals("0")) {
    //    	String iconUrl = "https://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+champImgCode+".png";
    //    	String iconFileName = champImgCode+"_icon.png";
    //    	String iconFilePath = folderPath + version + "\\" + iconFileName;
    //    	String boxUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/"+imgFileName;
    //    	String boxFileName = champImgCode+"_box.jpg";
    //    	String boxFilePath = folderPath + version + "\\" + boxFileName;
    //    	saveImg(iconFileName, iconUrl, iconFilePath, response);
    //    	saveImg(boxFileName, boxUrl, boxFilePath, response);
    //    }
    //    saveImg(imgFileName, imgUrl, imgFilePath, response);
	//}
	//
	//public void saveImg(String fileName, String imgUrl, String localFilePath, HttpServletResponse response) {
	//	try {
    //        // 이미지 다운로드 및 저장
    //        URL url = new URL(imgUrl);
    //        try (InputStream in = url.openStream();
    //             OutputStream out = new FileOutputStream(localFilePath)) {
    //            byte[] b = new byte[2048];
    //            int length;
    //            while ((length = in.read(b)) != -1) {
    //                out.write(b, 0, length);
    //            }
    //        }
    //        String mimeType = Files.probeContentType(Paths.get(localFilePath));
    //        response.setContentType(mimeType);
    //
    //        // 로컬 파일을 응답으로 전송
    //        try (InputStream fileInputStream = new FileInputStream(new File(localFilePath));
    //             OutputStream responseOutputStream = response.getOutputStream()) {
    //            byte[] buffer = new byte[4096];
    //            int bytesRead = -1;
    //            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
    //                responseOutputStream.write(buffer, 0, bytesRead);
    //            }
    //        }
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
	//}
}
