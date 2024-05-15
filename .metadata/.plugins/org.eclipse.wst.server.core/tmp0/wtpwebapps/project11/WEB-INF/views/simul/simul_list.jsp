<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dh.project11.packs.simul.object.SimulListDataVo" %>
<%@ page import="dh.project11.packs.simul.object.SimulListVo" %>
<%@ page import="dh.project11.packs.simul.object.SimulDataVo" %>
<%@ page import="dh.project11.packs.simul.object.SimulLogVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>대전목록</title>
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
    max-width: 1000px;
    margin-left: auto; 
    margin-right: auto;
    padding: 0 50px;
    background-color: rgba(255, 255, 255, 0.5);
}
#menuBar{
    padding:10px;
    font-size: 16px;
    font-weight: bold;
    text-overflow: ellipsis;
}
#tale{
    height: 170px;
}

.win{
    color: green;
}

.lose{
    color: red;
}
/* 스크롤바의 전체 스타일 */
::-webkit-scrollbar {
    width: 8px; /* 스크롤바의 너비 */
}

/* 스크롤바의 추적(트랙) 스타일 */
::-webkit-scrollbar-track {
    background-color: rgba(255, 255, 255, 0.5); /* 추적(트랙)의 배경색 */
}

/* 스크롤바의 슬라이더(쓰롯) 스타일 */
::-webkit-scrollbar-thumb {
    background-color: #888; /* 슬라이더(쓰롯)의 배경색 */
    border-radius: 3px; /* 슬라이더(쓰롯)의 둥근 모서리 */
}

/* 마우스 오버시 스크롤바의 슬라이더(쓰롯) 스타일 */
::-webkit-scrollbar-thumb:hover {
    background-color: #555;
}
#listList {
    height:600px;
    overflow: auto;
}
#listBox{
    width: 985px;
    padding: 3px;
}
.linkBox{
    margin-bottom: 10px; /* 아래쪽으로 10픽셀의 마진 */
    border-bottom: 3px solid #FFFF00; /* 검정색 3픽셀의 아래쪽 테두리 */
    background-color: rgba(190, 190, 190, 0.5);
    transition: background-color 0.3s;
    transition: color 0.3s;
    transition: border-bottom 0.3s;
    color: #666;
    font-size: 16px;
    line-height: 45px;
    font-weight: bold;
}
.linkBox:hover{
    background-color: rgba(120, 120, 120, 0.5);
    color: #fff;
}
.linkBox.winwin:hover{
    border-bottom: 3px solid green;
}
.linkBox.loselose:hover{
    border-bottom: 3px solid red;
}
.linkBox *{
    margin-bottom: 0; /
    border-bottom: 0;
    background-color: none;
    justify-content: space-between;
}
.face img{
    width: 40px;
    height: 40px;
    border-radius: 30%;
}
.result{
    width: 360px;
}
.resultMessage{
    width: 200px;
    line-height: 200px;
}
.pages{
    height: 100px;
    justify-content: center; /* 수평 중앙 정렬 */
    align-items: center; /* 수직 중앙 정렬 */
}
.pages div{
    padding: 8px;
    font-size: 20px;
    font-weight: bold;
    color: #666;
}
.pages a{
    padding: 8px;
    font-size: 20px;
    font-weight: bold;
}
.pages a:hover{
    color: #666;
}
</style>
</head>
<body>
<article id="background" class="background1">
<div id="ui">    
    <c:set var="list" value="${list}"/>
    <c:set var="page" value="${page}"/>    
    <c:set var="totalPage" value="${totalPage}"/>    
    <c:set var="id" value="${id}"/>
    
    <div id="menuBar">
        <h1>SIMUL RECORDS CONNECT USER : ${id}</h1>
        <h2>${page} PAGE OUT OF ${totalPage} PAGE</h2>
        <h3><a href="/packs">메뉴로</a></h3>
    </div>
    <div id="listList">
        <div id="listBox">
        <c:forEach var="list" items="${list}">
        <c:choose>
            <c:when test="${list.simulListVo.list_winlose == 0 }">
                <div class="linkBox winwin" style="padding: 0 10px;">
            </c:when>
            <c:when test="${list.simulListVo.list_winlose == 1 }">
                <div class="linkBox loselose" style="padding: 0 10px;">
            </c:when>
        </c:choose>
            <a href="/packs/simul/simulResult/${list.simulListVo.list_sid }/${id }" style="display: block;">
                <div class="player">
                    PLAYER : ${list.simulListVo.list_player }
                </div>
                <div class="flex">
                    <div class="user">
                        <div class="result flex">
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_user_champ1 }.png">
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_level1 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_kill1 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_death1 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_assist1 } 어시스트
                            </div>
                        </div>
                        <div class="result flex">
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_user_champ2 }.png">
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_level2 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_kill2 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_death2 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_assist2 } 어시스트
                            </div>
                        </div>
                        <div class="result flex">
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_user_champ3 }.png">
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_level3 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_kill3 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_death3 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_assist3 } 어시스트
                            </div>
                        </div>
                        <div class="result flex">
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_user_champ4 }.png">
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_level4 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_kill4 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_death4 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_assist4 } 어시스트
                            </div>
                        </div>
                        <div class="result flex">
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_user_champ5 }.png">
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_level5 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_kill5 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_death5 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_user_assist5 } 어시스트
                            </div>
                        </div>
                    </div>
                    <div class="flex resultMessage">
                    <c:choose>
                        <c:when test="${list.simulListVo.list_winlose == 0 }">
                            <div class="win">
                                유저 승리
                            </div>
                            <div class="lose">
                                AI 패배
                            </div>
                        </c:when>
                        <c:when test="${list.simulListVo.list_winlose == 1 }">
                            <div class="lose">
                                유저 패배
                            </div>
                            <div class="win">
                                AI 승리
                            </div>
                        </c:when>
                    </c:choose>
                    </div>
                    <div class="enemy">
                        <div class="result flex">
                            <div>
                                ${list.simulDataVo.data_enemy_level1 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_kill1 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_death1 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_assist1 } 어시스트
                            </div>
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_enemy_champ1 }.png">
                            </div>
                        </div>
                        <div class="result flex">
                            <div>
                                ${list.simulDataVo.data_enemy_level2 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_kill2 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_death2 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_assist2 } 어시스트
                            </div>
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_enemy_champ2 }.png">
                            </div>
                        </div>
                        <div class="result flex">
                            <div>
                                ${list.simulDataVo.data_enemy_level3 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_kill3 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_death3 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_assist3 } 어시스트
                            </div>
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_enemy_champ3 }.png">
                            </div>
                        </div>
                        <div class="result flex">
                            <div>
                                ${list.simulDataVo.data_enemy_level4 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_kill4 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_death4 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_assist4 } 어시스트
                            </div>
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_enemy_champ4 }.png">
                            </div>
                        </div>
                        <div class="result flex">
                            <div>
                                ${list.simulDataVo.data_enemy_level5 } 레벨
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_kill5 } 킬
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_death5 } 데스
                            </div>
                            <div>
                                ${list.simulDataVo.data_enemy_assist5 } 어시스트
                            </div>
                            <div class="face">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.simulListVo.list_version}/sqaure/${list.simulDataVo.data_enemy_champ5 }.png">
                            </div>
                        </div>
                    </div>
                </div>
            </a>
        </div>
        </c:forEach>
        </div>
    </div>
    <div id="tale">
        <div class="pages flex">
        <c:set var="start" value="0" />
        <c:set var="end" value="0" />
        
        <c:set var="pageCount" value="${page / 10}" />
        <c:choose>
            <c:when test="${pageCount < 1}">
                <c:set var="start" value="1" />
                <c:choose>
                    <c:when test="${page+10 >= totalPage}">
                        <c:set var="end" value="${totalPage}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="end" value="10" />
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <c:set var="start" value="${pageCount * 10 + 1}" />
                <c:choose>
                    <c:when test="${page+10 >= totalPage}">
                        <c:set var="end" value="${totalPage}" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="end" value="${pageCount * 10 + 10}" />
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        
        <c:if test="${start > 1}">
            <a href="/packs/simul/list/${id }/${start-1}">
                이전
            </a>
        </c:if>
        
        <c:forEach var="i" begin="${start}" end="${end}">
            <c:choose>
                <c:when test="${i == page}">
                    <div class="nowPage">
                        ${i}
                    </div>
                </c:when>
                <c:otherwise>
                    <a href="/packs/simul/list/${id }/${i}">
                        ${i}
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        
        <c:if test="${end < totalPage}">
            <a href="/packs/simul/list/${id }/${end+1}">
                다음
            </a>
        </c:if>
        </div>
    </div>
</div>
</article>    
    <script>
        var jQ = jQuery.noConflict();
        
        function goBack() {
           window.history.back();
        }
        
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