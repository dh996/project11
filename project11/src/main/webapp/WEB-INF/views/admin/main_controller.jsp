<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 컨트롤러</title>
<style>
.flex{
    display:flex;
}
* {
    cursor: default;
}
a {
    color: inherit; /* 링크의 색을 부모 요소에서 상속 */
    text-decoration: none; /* 링크의 밑줄 제거 */
    cursor: pointer;
}
#background{
    width: 100%;
    height: 940px;
    background-size: cover; /* 이미지가 요소에 맞게 확장되도록 설정합니다. */
    background-position: center; /* 이미지를 가운데 정렬합니다. */
    background-repeat: no-repeat; /* 이미지 반복을 비활성화합니다. */
    transition: background-image 1s ease-in-out;
}
#background.background1 {
    background-image: url('/packs/resources/img/main/back1.jpg');
}
#background.background2 {
    background-image: url('/packs/resources/img/main/back2.jpg');
}
#background.background3 {
    background-image: url('/packs/resources/img/main/back3.jpg');
}
#background.background4 {
    background-image: url('/packs/resources/img/main/back4.jpg');
}
#background.background5 {
    background-image: url('/packs/resources/img/main/back5.jpg');
}
#background.background6 {
    background-image: url('/packs/resources/img/main/back6.jpg');
}
#ui{
    width: 300px;
    height: 200px;
    margin: auto;
    padding: 50px;
    background-color: rgba(255, 255, 255, 0.5);
}
#blank{
    height: 200px;
}
</style>
</head>
<body>
<article id="background" class="background1">
<div id="blank"></div>
<div id="ui">
    <header>
        <div id="mcHeader">
             <div id="settingVersion"></div>
             <div id="mcBanner"></div>
             <div id="changeVersion">
                 <form action="versionup.do" method="post" name="versionForm">
                     <input type="text" name="version" placeholder="패치 버전을 입력해주세요">
                     <button type="submit">적용하기</button>
                 </form><!-- versionForm -->
             </div><!-- changeVersion -->
        </div><!-- mcHeader -->
    </header>
    <div><a href="/packs/">로그아웃</a></div>
    <article id="mcConsole">
    <%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<%
    // 현재 요청의 쿼리 파라미터를 얻음
    String queryString = request.getQueryString();
    Map<String, String> queryParams = new HashMap<>();

    // 쿼리 파라미터를 Map으로 변환
    if (queryString != null && !queryString.isEmpty()) {
        String[] params = queryString.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                queryParams.put(keyValue[0], keyValue[1]);
            }
        }
    }
%>

<%
    if (queryParams.containsKey("versionup_start")) {
        %>
        <jsp:include page="include/versionup_start.jsp" />
        <%
    } else if (queryParams.containsKey("error")) {
        %>
        <jsp:include page="include/error.jsp" />
        <%
    } else if (queryParams.containsKey("versionup_success")) {
        String version = queryParams.get("version");
        %>
        <jsp:include page="include/versionup_success.jsp">
            <jsp:param name="version" value="<%= version %>" />
        </jsp:include>
        <%
    } else {
        %>
        <jsp:include page="include/blank.jsp" />
        <%
    }
%>
    </article>
</div>
</article>
<script>
        var jQ = jQuery.noConflict();
        
        var nowImg = 1;
        function changeBack(){
        	var backImg = Math.floor(Math.random() * 6) + 1;
        	if(backImg != nowImg){
            	setBack(backImg);
        	}else{
        		console.log("배경이미지 중복탐지");
        		changeBack();
        	}
        }
        
        function setBack(backImg){
        	nowImg = backImg;
        	jQ("#background").removeClass();
        	jQ("#background").addClass("background" + backImg);
        }
        setInterval(changeBack, 5000);
    </script>
</body>
</html>