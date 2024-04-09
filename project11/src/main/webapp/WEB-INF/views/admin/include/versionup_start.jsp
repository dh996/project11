<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>Insert title here</title>
</head>
<body>
    <div id="upgogogo">
      클릭
    </div>
    <div id="startConsole" class="flex">
        <div id="updateInformation">
            <div id="nowUpdate">
            </div><!-- #nowUpdate -->
            <div id="updateBar">
            </div><!-- #updateBar -->
        </div><!-- #updateInformation -->
        <div id="updateRate">
            <div id="updateCounts"></div>
            <div id="updateRates"></div>
        </div><!-- #updateRate -->
    </div><!-- #startConsole -->
    
    <script>
        var jQ = jQuery.noConflict();
        var version = "${version}"
        var keyData = "${keyData}"
        var keyDataArray = keyData.replace(/[\[\]"]+/g, '').split(', ');
        var updateCount = 1
        var checkUpdate = false;
        
        jQ("#upgogogo").click(function(){
        	setChampUpdate(0);
        })
        
        function setChampUpdate(index) {
            if (index < keyData.length) {
                var champ = keyDataArray[index];
                var setChampUpdateTimeout;

                jQ.ajax({
                    type: 'GET',
                    url: 'https://ddragon.leagueoflegends.com/cdn/' + version + '/data/ko_KR/champion/' + champ + '.json',
                    dataType: 'json',
                    success: function (rdata) {
                        data = rdata.data[champ];
                        showConsole(data, updateCount);
                        var champSkin = data.skins;
                    	var champImgCode = data.id;
                        //var imgCheck = updateChampImg(0, version, champSkin, champImgCode);
                        var dBCheck = updateChampDB(data, version);
                        updateCount++;

                        setChampUpdateTimeout = setTimeout(function (dBCheck) {
                        	if(dBCheck===true){
                                function cancelSetChampUpdateTimeout() {
                                    clearTimeout(setChampUpdateTimeout);
                                }
                                cancelChampUpdateTimeout();
                                setChampUpdate(index + 1);
                        	}
                        }, 3000);
                    }
                });//if-ajax
            }else{
            	jQ('#updateBar').removeClass('on')
            	jQ('#startConsole').removeClass('on')
            	checkUpdate = false;
            	jQ.ajax({
                    url: "/versionup.do",
                    type: "GET",
                    success: function(response) {
                    	jQ("#result").text("Response from server: " + response);
                    },
                    error: function(error) {
                        console.log("Error:", error);
                    }
                });//else-ajax
            }//if
        }//setChampUpdate

        function showConsole(data, updateCount){
        	var upRate = (updateCount/keyData.length)*100
        	
        	jQ('#nowUpdate').text(data)
        	if(checkUpdate === false){
        		jQ('#updateBar').addClass('on')
        		jQ('#startConsole').addClass('on')
        	}
        	jQ('#updateCounts').text(updateCount+"/"+keyData.length)
        	jQ('#updateRates').text(upRate+"%")
        }//showConsole
        
        function updateChampDB(data, version){
        	
        	var sendDbData = {
        	        data: data,
        	        version: version
        	    };
        	
        	jQ.ajax({
                url: "/versionup.upload",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(sendDbData),
                success: function(response) {
                    return true;
                },
                error: function(error) {
                    console.log("Error:", error);
                    return false;
                }
            });
        }//updateChampDB
        
        //function updateChampImg(index, version, champSkin, champImgCode){
        //	if(index < champSkin.length){
        //		var champSkinCode = champSkin[index].num;
        //		var updateChampImgTimeout;
        //    	var sendImgData = {
        //    			champSkinCode: champSkinCode,
        //    			champImgCode: champImgCode,
        //    	        version: version
        //    	    };
        //    	
        //    	jQ.ajax({
        //    	    url: '/versionup.img',
        //    	    type: "POST",
        //            contentType: "application/json",
        //            data: JSON.stringify(sendImgData),
        //    	    success: function (response) {
        //    	    	if(index==0){
        //    	    		setConsoleImg(champImgCode);
        //    	    	}
        //    	    	updateChampImgTimeout = setTimeout(function () {
        //                    function cancelUpdateChampImgTimeout() {
        //                        clearTimeout(updateChampImgTimeout);
        //                    }
        //                    cancelUpdateChampImgTimeout();
        //                	updateChampImg(index + 1, version, champSkin, champImgCode);
        //                }, 3000);
        //    	    }
        //    	});
        //	}else{
        //		return true;
        //	}
        //}//updateChampImg
        
        //function setConsoleImg(champImgCode){
        //	var imageUrl = 'resources/img/'+champImgCode'_0.jpg';
        //	JQ('#startConsole').css('background-image', 'url(' + imageUrl + ')');
        //}//setConsoleImg
        //11.22:이제 데이터베이스에 저장하는거 만들고 css대충짠담에 메인페이지와 연결 지금까지하거 잗돌아가나 체크
      
    </script>
</body>
</html>