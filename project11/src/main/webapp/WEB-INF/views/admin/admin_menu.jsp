<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>Insert title here</title>
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
</head>
<body>
    <div>사용자 Admin</div>
<form action="admin/home" method="POST">
    <input type="text" id="id" name="id" value="${id}" style="display: none;"><br>
    <input type="submit" value="버전 업데이트 페이지로">
</form>
    <a href="/packs/simul/list/Admin/1">
	        전체 대전기록 보기
	</a>
<div><a href="/packs/">로그아웃</a></div>
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