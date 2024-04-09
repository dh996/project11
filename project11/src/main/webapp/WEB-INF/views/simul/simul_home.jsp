<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>시뮬레이션 페이지</title>
<style>

.flex{
    display:flex;
}
#simulMainBanner{
    justify-content: space-around;
}
#pickChamps{
    height: 200px;
}
#champList {
    flex-wrap: wrap;
    justify-content: space-around;
}

.champ {
    flex: 0 0 calc(10% - 20px); /* 10% 너비, 간격 20px */
    margin: 3px; /* 플렉스 아이템 간격 조절 */
    border: 1px solid #333;
}
.cid {
    display: none;
}
.champ.on{
    border: 1px solid #ff0000;
}
.tagName {
    padding: 3px;
}
</style>
</head>
<body>
    <div id="simulMainBanner" class="flex">
        <div>현재버전 ${version}</div>
        <div>사용자 ${id}</div>
        <div id="start" class="ready">시작하기</div>
        <div><a href="/packs">메뉴로</a></div>
    </div>
    <div id="pickChamps" class="flex">
    </div>
    <div id="champList" class="flex">
        <c:forEach var="champion" items="${champList}">
        <div class="champ">
            <div class="name">${champion.champs_name}</div>
            <div class="cid">${champion.champs_cid}</div>
            <div class="tags flex">
                <c:forEach var="tags" items="${champion.champs_tags}">
                <div class="tagName">${tags}</div>
                </c:forEach>
            </div>
        </div>
        </c:forEach>
    </div>
    
    <script>
        var jQ = jQuery.noConflict();
        var id = '${id}'
        console.log(id)
        
        var selectCount = 0;
        jQ('.champ').click(function(){
        	if(jQ(this).hasClass('on')){
        		jQ(this).removeClass('on');
        		selectCount --;
        	}else{
        		if(selectCount<5){
        			jQ(this).addClass('on');
        			selectCount ++;
        		}
        	}
        	selectChampSetting();
        })
        
        function selectChampSetting(){
        	// on 클래스를 가지고 있는 .champ 요소들을 선택
            var selectedChamps = jQ('.champ.on');

            // 기존에 있던 #pickChamps의 자식들을 제거
            jQ('#pickChamps').empty();

            // 각 선택된 .champ 요소를 복제하여 새로운 .champ를 생성하고, #pickChamps의 자식으로 추가
            selectedChamps.each(function () {
                var clonedChamp = jQ(this).clone();
                clonedChamp.removeClass('on'); // 'on' 클래스 제거
                jQ('#pickChamps').append(clonedChamp);
            });
        }
        
        function startAjax() {
            var selectedChampData = [];

            jQ('.champ.on').each(function () {
                var champName = jQ(this).find('.name').text();
                var champId = jQ(this).find('.cid').text();

                selectedChampData.push({
                    name: champName,
                    id: champId
                });
            });

            // Ajax 요청
            jQ.ajax({
                type: 'POST',
                url: '/packs/simul/simulStart.do',  // 실제 엔드포인트에 맞게 변경
                contentType: 'application/json',
                data: JSON.stringify({
                    version: '${version}',
                    selectedChamps: selectedChampData,
                    userID: id
                }),
                success: function (response) {
                    // 성공 시 동작
                    //console.log('Ajax request succeeded:', response);
                    console.log(response);
                    createResultBanner(response)
                    //simulResult(response, '${id}');
                },
                error: function (error) {
                    // 실패 시 동작
                    console.error('Ajax request failed:', error);
                }
            });
        }
        
        function createResultBanner(tempId){
        	var resultLink = '<a href="/packs/simul/simulResult/'+tempId+'/'+id+'">결과확인</a>'
        	jQ('#start').empty()
        	jQ('#start').append(resultLink)
        }
        
        /* window.addEventListener('beforeunload', function (event) {
            // Ajax 요청 보내기
            $.ajax({
                type: 'POST',
                url: '/tempReset/'+${id},  // 로그아웃을 처리하는 엔드포인트 URL
                async: false,   // 동기적으로 요청을 처리하려면 async 옵션을 false로 설정
                success: function (response) {
                    // 서버로부터 성공적인 응답을 받았을 때 수행할 작업
                    console.log('tempReset successful');
                },
                error: function (xhr, status, error) {
                    // 서버 요청이 실패했을 때 수행할 작업
                    console.error('tempReset failed:', error);
                }
            });
        }); */
        
        /* function simulResult(tempID, userID){
        	jQ.ajax({
                type: 'POST',
                url: '/packs/simul/simulResult',  // 실제 엔드포인트에 맞게 변경
                contentType: 'application/json',
                data: JSON.stringify({
                	tempID: tempID,
                    userID: userID
                }),
                success: function (response) {
                    // 성공 시 동작
                    console.log('Ajax request succeeded:', response);
                    
                },
                error: function (error) {
                    // 실패 시 동작
                    console.error('Ajax request failed:', error);
                }
            });
        } */
        
        jQ("#start").click(function(){
        	if(selectCount==5 && jQ("#start").hasClass("ready")){
        		startAjax();
        		jQ("#start").removeClass("ready");
        	}else if(selectCount<5 && jQ("#start").hasClass("ready")){
        		alert("5개를 골라주세요");
        	}
        })
    </script>
</body>
</html>