<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dh.project11.packs.simul.object.SimulListVo" %>
<%@ page import="dh.project11.packs.simul.object.SimulDataVo" %>
<%@ page import="dh.project11.packs.simul.object.SimulLogVo" %>
<%@ page import="dh.project11.packs.simul.object.ReturnData" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<title>결과페이지</title>

<style>
.flex{
    display:flex;
}
.absolute{
    position: absolute;
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
#centerMenu{
    width: 100%;
    justify-content: space-between;
}
#mapConsole{
    width: 600px;
    /* height: 1000px; */
}
#miniMap{
    background-image: url('/packs/resources/img/simul/map11.png');
    background-size: cover;
    position: relative;
    width: 600px;
    height: 600px;
}
#tale{
    /* height: 170px; */
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
.linkBox{
    margin-bottom: 10px; /* 아래쪽으로 10픽셀의 마진 */
    border-bottom: 3px solid #FFFF00; /* 검정색 3픽셀의 아래쪽 테두리 */
    background-color: rgba(190, 190, 190, 0.5);
    transition: background-color 0.3s;
    transition: color 0.3s;
    transition: border-bottom 0.3s;
    color: #666;
    font-size: 16px;
    line-height: 35px;
    font-weight: bold;
}
.linkBox *{
    margin-bottom: 0; /
    border-bottom: 0;
    background-color: none;
    justify-content: space-between;
}
.face img{
    width: 30px;
    height: 30px;
    border-radius: 30%;
}
.result{
    display: none;
}
.result.on{
    display: block;
}
.resultbox{
    width: 360px;
}
.resultMessage{
    width: 200px;
    line-height: 200px;
}
#controller{
    height:600px;
    overflow: auto;
}
.con{
    padding: 20px;
}
.con:hover{
    cursor: pointer;
    background-color: rgba(190, 190, 190, 0.5);
}
.con.on{
    background-color: rgba(190, 190, 190, 0.5);
}
#logBox{
    padding: 20px;
    height: 300px;
    overflow: auto;
}
.faceIcon{
    width: 30px;
    height: 30px;
    border-radius: 100%;
    position: absolute;
}
.faceIcon img {
    width: 30px;
    height: 30px;
    border-radius: 100%;
}
.objects{
    width: 600px;
    height: 600px;
    position: relative;
}
.objects div{
    width: 30px;
    height: 30px;
    border-radius: 100%;
    line-height: 30px;
    font-size: 25px;
    position: absolute;
    text-align: center;
}
.objects img{
    width: 30px;
    height: 30px;
}
.defaultChamp{
    width: 600px;
    height: 600px;
    position: relative;
}
.defaultChamp .c1u{
    top: 140px;
    left: 35px;
}
.defaultChamp .c2u{
    top: 270px;
    left: 140px;
}
.defaultChamp .c3u{
    top: 295px;
    left: 275px;
}
.defaultChamp .c4u{
    top: 500px;
    left: 455px;
}
.defaultChamp .c5u{
    top: 530px;
    left: 455px;
}
.defaultChamp .c1e{
    top: 37px;
    left: 140px;
}
.defaultChamp .c2e{
    top: 140px;
    left: 270px;
}
.defaultChamp .c3e{
    top: 270px;
    left: 300px;
}
.defaultChamp .c4e{
    top: 420px;
    left: 505px;
}
.defaultChamp .c5e{
    top: 420px;
    left: 535px;
}
.aliveU{
    color: green;
}
.aliveE{
    color: red;
}
.destroy{
    color: #666;
}
.nexusU{
    top: 530px;
    left: 35px;
}
.t1U{
    top: 170px;
    left: 35px;
}
.t2U{
    top: 285px;
    left: 35px;
}
.t3U{
    top: 390px;
    left: 35px;
}
.tiU{
    top: 430px;
    left: 35px;
}
.m1U{
    top: 325px;
    left: 245px;
}
.m2U{
    top: 380px;
    left: 190px;
}
.m3U{
    top: 425px;
    left: 140px;
}
.miU{
    top: 455px;
    left: 110px;
}
.b1U{
    top: 530px;
    left: 425px;
}
.b2U{
    top: 530px;
    left: 300px;
}
.b3U{
    top: 530px;
    left: 170px;
}
.biU{
    top: 530px;
    left: 135px;
}
.ltU{
    top: 500px;
    left: 45px;
}
.rtU{
    top: 520px;
    left: 65px;
}
.baron{
    top: 160px;
    left: 187px;
}
.dragon{
    top: 408px;
    left: 387px;
}
.nexusE{
    top: 37px;
    left: 535px;
}
.t1E{
    top: 37px;
    left: 170px;
}
.t2E{
    top: 37px;
    left: 290px;
}
.t3E{
    top: 37px;
    left: 395px;
}
.tiE{
    top: 37px;
    left: 430px;
}
.m1E{
    top: 240px;
    left: 330px;
}
.m2E{
    top: 190px;
    left: 380px;
}
.m3E{
    top: 140px;
    left: 430px;
}
.miE{
    top: 115px;
    left: 455px;
}
.b1E{
    top: 390px;
    left: 535px;
}
.b2E{
    top: 270px;
    left: 535px;
}
.b3E{
    top: 175px;
    left: 535px;
}
.biE{
    top: 140px;
    left: 535px;
}
.ltE{
    top: 47px;
    left: 505px;
}
.rtE{
    top: 67px;
    left: 525px;
}
.matchBox{
    position: relative;
    width: 120px;
    height: 120px;
}
.matchBox .c1u{
    top: 35px;
    left: 5px;
}
.matchBox .c2u{
    top: 65px;
    left: 5px;
}
.matchBox .c3u{
    top: 65px;
    left: 35px;
}
.matchBox .c4u{
    top: 95px;
    left: 35px;
}
.matchBox .c5u{
    top: 95px;
    left: 65px;
}
.matchBox .c1e{
    top: 5px;
    left: 35px;
}
.matchBox .c2e{
    top: 5px;
    left: 65px;
}
.matchBox .c3e{
    top: 35px;
    left: 65px;
}
.matchBox .c4e{
    top: 35px;
    left: 95px;
}
.matchBox .c5e{
    top: 65px;
    left: 95px;
}
.ualive{
    border: 5px solid green;
}
.ealive{
    border: 5px solid red;
}
.dead{
    border: 5px solid #666;
}
.topFirstTowerU{
    top: 115px;
    left: 0px;
}
.topSecondTowerU{
    top: 230px;
    left: 0px;
}
.topThirdTowerU{
    top: 335px;
    left: 0px;
}
.topInhibitorU{
    top: 375px;
    left: 0px;
}
.midFirstTowerU{
    top: 270px;
    left: 190px;
}
.midSecondTowerU{
    top: 325px;
    left: 135px;
}
.midThirdTowerU{
    top: 370px;
    left: 85px;
}
.midInhibitorU{
    top: 340px;
    left: 55px;
}
.botFirstTowerU{
    top: 460px;
    left: 370px;
}
.botSecondTowerU{
    top: 460px;
    left: 225px;
}
.botThirdTowerU{
    top: 460px;
    left: 115px;
}
.botInhibitorU{
    top: 460px;
    left: 80px;
}
.topFirstTowerE{
    top: 0px;
    left: 120px;
}
.topSecondTowerE{
    top: 0px;
    left: 240px;
}
.topThirdTowerE{
    top: 0px;
    left: 345px;
}
.topInhibitorE{
    top: 0px;
    left: 375px;
}
.midFirstTowerE{
    top: 185px;
    left: 275px;
}
.midSecondTowerE{
    top: 135px;
    left: 325px;
}
.midThirdTowerE{
    top: 85px;
    left: 375px;
}
.midInhibitorE{
    top: 60px;
    left: 400px;
}
.botFirstTowerE{
    top: 335px;
    left: 460px;
}
.botSecondTowerE{
    top: 215px;
    left: 460px;
}
.botThirdTowerE{
    top: 120px;
    left: 460px;
}
.botInhibitorE{
    top: 85px;
    left: 460px;
}
.midLine{
    top: 227px;
    left: 233px;
}
.topLine{
    top: 25px;
    left: 25px;
}
.bottomLine{
    top: 435px;
    left: 435px;
}
.baronNest{
    top: 105px;
    left: 132px;
}
.dragonNest{
    top: 353px;
    left: 332px;
}
.baseU{
    top: 435px;
    left: 25px;
}
.baseE{
    top: 25px;
    left: 435px;
}
#scoreBoard{
    width: 200px;
}
#scoreBoard div{
    font-size: 35px;
    line-hight: 35px;
    font-weight: bold;
}
#boardTop{
    height: 120px;
}
#boardTop div{
    height: 40px;
}
#boardBot{
    height: 120px;
}
#boardBot div{
    height: 40px;
}
#round{
    height: 40px;
}
#scoreBoard img{
    width: 35px;
    height: 35px;
}
#scoreU{
    justify-content: space-between;
}
#scoreE{
    justify-content: space-between;
}
</style>
</head>
<body>
<article id="background" class="background1">
<div id="ui">     
    <c:set var="tempID" value="${tempID}"/>
    <c:set var="userID" value="${userID}"/>
    <c:set var="list" value="${list}"/>
    <c:set var="data" value="${data}"/>
    <c:set var="rData" value="${rData}"/>
    <c:set var="logs" value="${logs}"/>
    <c:set var="error" value="${error}"/>
    <%
        SimulListVo list = (SimulListVo)pageContext.getAttribute("list");
        String error = (String)pageContext.getAttribute("error");
        if(error.equals("excess_exception")){
    %>
    <h1>접근 불가능</h1>
    <%
        }else{
    %>
    
    <a href="/packs/simul/list/${userID }/1">대전기록페이지로</a>
    <div class="linkBox">
    <div class="player">
        PLAYER : ${list.list_player }
    </div>
    <div class="flex">
        <div class="user">
            <div class="resultbox flex">
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ1 }.png">
                </div>
                <div>
                    ${data.data_user_level1 } 레벨
                </div>
                <div>
                    ${data.data_user_kill1 } 킬
                </div>
                <div>
                    ${data.data_user_death1 } 데스
                </div>
                <div>
                    ${data.data_user_assist1 } 어시스트
                </div>
            </div>
            <div class="resultbox flex">
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ2 }.png">
                </div>
                <div>
                    ${data.data_user_level2 } 레벨
                </div>
                <div>
                    ${data.data_user_kill2 } 킬
                </div>
                <div>
                    ${data.data_user_death2 } 데스
                </div>
                <div>
                    ${data.data_user_assist2 } 어시스트
                </div>
            </div>
            <div class="resultbox flex">
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ3 }.png">
                </div>
                <div>
                    ${data.data_user_level3 } 레벨
                </div>
                <div>
                    ${data.data_user_kill3 } 킬
                </div>
                <div>
                    ${data.data_user_death3 } 데스
                </div>
                <div>
                    ${data.data_user_assist3 } 어시스트
                </div>
            </div>
            <div class="resultbox flex">
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ4 }.png">
                </div>
                <div>
                    ${data.data_user_level4 } 레벨
                </div>
                <div>
                    ${data.data_user_kill4 } 킬
                </div>
                <div>
                    ${data.data_user_death4 } 데스
                </div>
                <div>
                    ${data.data_user_assist4 } 어시스트
                </div>
            </div>
            <div class="resultbox flex">
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ5 }.png">
                </div>
                <div>
                    ${data.data_user_level5 } 레벨
                </div>
                <div>
                    ${data.data_user_kill5 } 킬
                </div>
                <div>
                    ${data.data_user_death5 } 데스
                </div>
                <div>
                    ${data.data_user_assist5 } 어시스트
                </div>
            </div>
        </div>
        <div class="flex resultMessage">
        <c:choose>
            <c:when test="${list.list_winlose == 0 }">
                <div class="win">
                    유저 승리
                </div>
                <div class="lose">
                    AI 패배
                </div>
            </c:when>
            <c:when test="${list.list_winlose == 1 }">
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
            <div class="resultbox flex">
                <div>
                    ${data.data_enemy_level1 } 레벨
                </div>
                <div>
                    ${data.data_enemy_kill1 } 킬
                </div>
                <div>
                    ${data.data_enemy_death1 } 데스
                </div>
                <div>
                    ${data.data_enemy_assist1 } 어시스트
                </div>
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ1 }.png">
                </div>
            </div>
            <div class="resultbox flex">
                <div>
                    ${data.data_enemy_level2 } 레벨
                </div>
                <div>
                    ${data.data_enemy_kill2 } 킬
                </div>
                <div>
                    ${data.data_enemy_death2 } 데스
                </div>
                <div>
                    ${data.data_enemy_assist2 } 어시스트
                </div>
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ2 }.png">
                </div>
            </div>
            <div class="resultbox flex">
                <div>
                    ${data.data_enemy_level3 } 레벨
                </div>
                <div>
                    ${data.data_enemy_kill3 } 킬
                </div>
                <div>
                    ${data.data_enemy_death3 } 데스
                </div>
                <div>
                    ${data.data_enemy_assist3 } 어시스트
                </div>
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ3 }.png">
                </div>
            </div>
            <div class="resultbox flex">
                <div>
                    ${data.data_enemy_level4 } 레벨
                </div>
                <div>
                    ${data.data_enemy_kill4 } 킬
                </div>
                <div>
                    ${data.data_enemy_death4 } 데스
                </div>
                <div>
                    ${data.data_enemy_assist4 } 어시스트
                </div>
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ4 }.png">
                </div>
            </div>
            <div class="resultbox flex">
                <div>
                    ${data.data_enemy_level5 } 레벨
                </div>
                <div>
                    ${data.data_enemy_kill5 } 킬
                </div>
                <div>
                    ${data.data_enemy_death5 } 데스
                </div>
                <div>
                    ${data.data_enemy_assist5 } 어시스트
                </div>
                <div class="face">
                    <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ5 }.png">
                </div>
            </div>
        </div>
    </div>
    </div>
    <div id="centerMenu" class="flex">
        <div id="scoreBoard">
            <div id="boardTop">
                <div id="scoreU" class="flex">
                    <div id="killU" class="flex">
                        <div class="win">
                            <i class="fa-solid fa-khanda"></i>
                        </div>
                        <div id="killCountU">
                            <div class="result round0 on">0</div>
                            <c:forEach var="rData" items="${rData}">
                            <div class="result round${rData.round }">${rData.killU}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <div id="towerU" class="flex">
                        <div class="win">
                            <i class="fa-solid fa-chess-rook"></i>
                        </div>
                        <div id="towerCountU">
                            <div class="result round0 on">0</div>
                            <c:forEach var="rData" items="${rData}">
                            <div class="result round${rData.round }">${rData.towerU}</div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="buffBox flex">
                    <div id="elderU">
                        <c:forEach var="rData" items="${rData}">
                        <div class="result round${rData.round }">
                        <c:choose>
                            <c:when test="${rData.elderU > 0}">
                                <img src="${pageContext.request.contextPath}/resources/img/simul/elder.svg">
                            </c:when>
                        </c:choose>
                        </div>
                        </c:forEach>
                    </div>
                    <div id="baronU">
                        <c:forEach var="rData" items="${rData}">
                        <div class="result round${rData.round }">
                        <c:choose>
                            <c:when test="${rData.baronU > 0}">
                                <img src="${pageContext.request.contextPath}/resources/img/simul/baron.svg">
                            </c:when>
                        </c:choose>
                        </div>
                        </c:forEach>
                    </div>
                </div>
                <div id="dragonU">
                    <c:forEach var="rData" items="${rData}">
                        <div class="result round${rData.round }">
                            <c:forEach var="dData" items="${rData.dragonU}">
                            <img src="${pageContext.request.contextPath}/resources/img/simul/${dData }.svg">
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>  
            </div>
            <div id="round">round0</div>
            <div id="boardBot">
                <div id="dragonE">
                    <c:forEach var="rData" items="${rData}">
                        <div class="result round${rData.round }">
                            <c:forEach var="dData" items="${rData.dragonE}">
                            <img src="${pageContext.request.contextPath}/resources/img/simul/${dData }.svg">
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
                <div class="buffBox flex">
                    <div id="baronE">
                    <c:forEach var="rData" items="${rData}">
                        <div class="result round${rData.round }">
                        <c:choose>
                            <c:when test="${rData.baronE > 0}">
                                <img src="${pageContext.request.contextPath}/resources/img/simul/baron.svg">
                            </c:when>
                        </c:choose>
                        </div>
                    </c:forEach>
                    </div>
                    <div id="elderE">
                    <c:forEach var="rData" items="${rData}">
                        <div class="result round${rData.round }">
                        <c:choose>
                            <c:when test="${rData.elderE > 0}">
                                <img src="${pageContext.request.contextPath}/resources/img/simul/elder.svg">
                            </c:when>
                        </c:choose>
                        </div>
                    </c:forEach>
                    </div>
                </div>
                <div id="scoreE" class="flex">
                    <div id="killE" class="flex">
                        <div class="lose">
                            <i class="fa-solid fa-khanda"></i>
                        </div>
                        <div id="killCountE">
                            <div class="result round0 on">0</div>
                            <c:forEach var="rData" items="${rData}">
                            <div class="result round${rData.round }">${rData.killE}</div>
                            </c:forEach>
                        </div>
                    </div>
                    <div id="towerE" class="flex">
                        <div class="lose">
                            <i class="fa-solid fa-chess-rook"></i>
                        </div>
                        <div id="towerCountE">
                            <div class="result round0 on">0</div>
                            <c:forEach var="rData" items="${rData}">
                            <div class="result round${rData.round }">${rData.towerE}</div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="mapConsole">
            <div id="miniMap">
                <div class="result round0 absolute on">
                <div class="objects">
                    <div class="nexusU aliveU nexus"><i class="fa-solid fa-location-crosshairs"></i></div>
                    <div class="t1U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="t2U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="t3U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="tiU aliveU"><i class="fa-solid fa-bullseye"></i></div>
                    <div class="m1U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="m2U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="m3U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="miU aliveU"><i class="fa-solid fa-bullseye"></i></div>
                    <div class="b1U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="b2U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="b3U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="biU aliveU"><i class="fa-solid fa-bullseye"></i></div>
                    <div class="ltU aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="rtU aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="baron"></div>
                    <div class="dragon"></div>
                    <div class="nexusE aliveE nexus"><i class="fa-solid fa-location-crosshairs"></i></div>
                    <div class="t1E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="t2E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="t3E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="tiE aliveE"><i class="fa-solid fa-bullseye"></i></div>
                    <div class="m1E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="m2E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="m3E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="miE aliveE"><i class="fa-solid fa-bullseye"></i></div>
                    <div class="b1E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="b2E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="b3E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="biE aliveE"><i class="fa-solid fa-bullseye"></i></div>
                    <div class="ltE aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                    <div class="rtE aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                </div>
                </div>
                <c:forEach var="rData" items="${rData}">
                <div class="result round${rData.round} absolute">
                <div class="objects">
                    <c:choose>
                        <c:when test="${rData.nexusU == 0}">
                            <div class="nexusU aliveU nexus"><i class="fa-solid fa-location-crosshairs"></i></div>
                        </c:when>
                        <c:when test="${rData.nexusU == 1}">
                            <div class="nexusU destroy nexus"><i class="fa-solid fa-location-crosshairs"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.t1U == 0}">
                            <div class="t1U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.t1U == 1}">
                            <div class="t1U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.t2U == 0}">
                            <div class="t2U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.t2U == 1}">
                            <div class="t2U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.t3U == 0}">
                            <div class="t3U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.t3U == 1}">
                            <div class="t3U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.tiU == 0}">
                            <div class="tiU aliveU"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:when test="${rData.t3U == 1}">
                            <div class="tiU destroy"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.m1U == 0}">
                            <div class="m1U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.m1U == 1}">
                            <div class="m1U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.m2U == 0}">
                            <div class="m2U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.m2U == 1}">
                            <div class="m2U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.m3U == 0}">
                            <div class="m3U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.m3U == 1}">
                            <div class="m3U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.miU == 0}">
                            <div class="miU aliveU"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:when test="${rData.miU == 1}">
                            <div class="miU destroy"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.b1U == 0}">
                            <div class="b1U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.b1U == 1}">
                            <div class="b1U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.b2U == 0}">
                            <div class="b2U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.b2U == 1}">
                            <div class="b2U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.b3U == 0}">
                            <div class="b3U aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.b3U == 1}">
                            <div class="b3U destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.biU == 0}">
                            <div class="biU aliveU"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:when test="${rData.biU == 1}">
                            <div class="biU destroy"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.ltU == 0}">
                            <div class="ltU aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.ltU == 1}">
                            <div class="ltU destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.rtU == 0}">
                            <div class="rtU aliveU"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.rtU == 1}">
                            <div class="rtU destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.baronNest == 0}">
                            <div class="baron"></div>
                        </c:when>
                        <c:when test="${rData.baronNest == 1}">
                            <div class="baron"><img src="${pageContext.request.contextPath}/resources/img/simul/baron.svg"></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.dragonNest == 'x'}">
                            <div class="dragon"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="dragon"><img src="${pageContext.request.contextPath}/resources/img/simul/${rData.dragonNest }.svg"></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.nexusE == 0}">
                            <div class="nexusE aliveE nexus"><i class="fa-solid fa-location-crosshairs"></i></div>
                        </c:when>
                        <c:when test="${rData.nexusE == 1}">
                            <div class="nexusE destroy nexus"><i class="fa-solid fa-location-crosshairs"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.t1E == 0}">
                            <div class="t1E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.t1E == 1}">
                            <div class="t1E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.t2E == 0}">
                            <div class="t2E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.t2E == 1}">
                            <div class="t2E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.t3E == 0}">
                            <div class="t3E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.t3E == 1}">
                            <div class="t3E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.tiE == 0}">
                            <div class="tiE aliveE"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:when test="${rData.t3E == 1}">
                            <div class="tiE destroy"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.m1E == 0}">
                            <div class="m1E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.m1E == 1}">
                            <div class="m1E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.m2E == 0}">
                            <div class="m2E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.m2E == 1}">
                            <div class="m2E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.m3E == 0}">
                            <div class="m3E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.m3E == 1}">
                            <div class="m3E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.miE == 0}">
                            <div class="miE aliveE"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:when test="${rData.miE == 1}">
                            <div class="miE destroy"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.b1E == 0}">
                            <div class="b1E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.b1E == 1}">
                            <div class="b1E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.b2E == 0}">
                            <div class="b2E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.b2E == 1}">
                            <div class="b2E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.b3E == 0}">
                            <div class="b3E aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.b3E == 1}">
                            <div class="b3E destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.biE == 0}">
                            <div class="biE aliveE"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:when test="${rData.biE == 1}">
                            <div class="biE destroy"><i class="fa-solid fa-bullseye"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.ltE == 0}">
                            <div class="ltE aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.ltE == 1}">
                            <div class="ltE destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.rtE == 0}">
                            <div class="rtE aliveE"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:when test="${rData.rtE == 1}">
                            <div class="rtE destroy"><i class="fa-solid fa-chess-rook"></i></div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                </div>
                </div>
                </c:forEach>
                <div class="result round0 absolute on">
                <div class="defaultChamp">
                    <div class="faceIcon c1u ualive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ1 }.png">
                    </div>
                    <div class="faceIcon c2u ualive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ2 }.png">
                    </div>
                    <div class="faceIcon c3u ualive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ3 }.png">
                    </div>
                    <div class="faceIcon c4u ualive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ4 }.png">
                    </div>
                    <div class="faceIcon c5u ualive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ5 }.png">
                    </div>
                    <div class="faceIcon c1e ealive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ1 }.png">
                    </div>
                    <div class="faceIcon c2e ealive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ2 }.png">
                    </div>
                    <div class="faceIcon c3e ealive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ3 }.png">
                    </div>
                    <div class="faceIcon c4e ealive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ4 }.png">
                    </div>
                    <div class="faceIcon c5e ealive">
                        <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ5 }.png">
                    </div>
                </div>
                </div>
                <c:forEach var="rData" items="${rData}">
                    <div class="result ${rData.field } round${rData.round} absolute vs">
                    <c:choose>
                        <c:when test="${rData.field == 'freeze'}">
                        <div class="defaultChamp">
                        </c:when>
                        <c:otherwise>
                        <div class="matchBox">
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c1u == 0}">
                            <div class="faceIcon c1u dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ1 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c1u == 1}">
                            <div class="faceIcon c1u ualive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ1 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c2u == 0}">
                            <div class="faceIcon c2u dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ2 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c2u == 1}">
                            <div class="faceIcon c2u ualive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ2 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c3u == 0}">
                            <div class="faceIcon c3u dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ3 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c3u == 1}">
                            <div class="faceIcon c3u ualive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ3 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c4u == 0}">
                            <div class="faceIcon c4u dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ4 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c4u == 1}">
                            <div class="faceIcon c4u ualive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ4 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c5u == 0}">
                            <div class="faceIcon c5u dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ5 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c5u == 1}">
                            <div class="faceIcon c5u ualive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_user_champ5 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c1e == 0}">
                            <div class="faceIcon c1e dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ1 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c1e == 1}">
                            <div class="faceIcon c1e ealive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ1 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c2e == 0}">
                            <div class="faceIcon c2e dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ2 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c2e == 1}">
                            <div class="faceIcon c2e ealive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ2 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c3e == 0}">
                            <div class="faceIcon c3e dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ3 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c3e == 1}">
                            <div class="faceIcon c3e ealive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ3 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c4e == 0}">
                            <div class="faceIcon c4e dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ4 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c4e == 1}">
                            <div class="faceIcon c4e ealive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ4 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${rData.c5e == 0}">
                            <div class="faceIcon c5e dead">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ5 }.png">
                            </div>
                        </c:when>
                        <c:when test="${rData.c5e == 1}">
                            <div class="faceIcon c5e ealive">
                                <img src="${pageContext.request.contextPath}/resources/img/${list.list_version}/sqaure/${data.data_enemy_champ5 }.png">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div></div>
                        </c:otherwise>
                    </c:choose>
                    </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div id="controllerBox">
        <div id="controller">
            <div class="con round0 on">0턴</div>
            <c:forEach var="rData" items="${rData}">
                <div class="con round${rData.round }">${rData.round }턴</div>
            </c:forEach>
        </div>
        </div>
    </div>
    <div id="tale">
    <h3>로그</h3>
    <div id="logBox">
    <div class="result round0 logs on">
        <c:forEach var="log" items="${logs}">
        <c:choose>
            <c:when test="${log.logs_turncount == 0 }">
            <c:choose>
            <c:when test="${log.logs_type == 1}">
                <div class="lose">${log.logs_message }</div>
            </c:when>
            <c:when test="${log.logs_type == 0}">
                <div class="win">${log.logs_message }</div>
            </c:when>
            <c:otherwise>
                <div>${log.logs_message }</div>
            </c:otherwise>
            </c:choose>
            </c:when>
        </c:choose>
        </c:forEach>
    </div>
    <c:forEach var="rData2" items="${rData }">
    <div class="result round${rData2.round } logs">
    <c:forEach var="log" items="${logs}">
        <c:choose>
        <c:when test="${log.logs_turncount == rData2.round }">
        <c:choose>
            <c:when test="${log.logs_type == 1}">
                <div class="lose">${log.logs_message }</div>
            </c:when>
            <c:when test="${log.logs_type == 0}">
                <div class="win">${log.logs_message }</div>
            </c:when>
            <c:otherwise>
                <div>${log.logs_message }</div>
            </c:otherwise>
        </c:choose>
        </c:when>
        </c:choose>
    </c:forEach>
    </div>
    </c:forEach>
    </div>
    <%
        }
    %>
    </div>
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
    
    // controller div의 자식 con div를 클릭했을 때의 이벤트 핸들러
    jQ("#controller").on("click", ".con", function() {
    	// 클릭된 con div의 내용을 가져옵니다.
        var clickedText = jQ(this).text();
        console.log(clickedText)
        
        // "턴"이라는 글자를 제거하고 "round"를 앞에 붙입니다.
        var clickedClass = "round" + clickedText.replace("턴", " ");
        console.log(clickedClass)

        // 모든 con div에서 on 클래스를 제거합니다.
        jQ(".con").removeClass("on");
        jQ(".result").removeClass("on");

        // 클래스명이 clickedContent와 동일한 모든 요소에 on 클래스를 추가합니다.
        jQ("." + clickedClass).addClass("on");
        
        jQ("#round").empty();
        jQ("#round").text(clickedClass);
    });
    
</script>
</body>
</html>