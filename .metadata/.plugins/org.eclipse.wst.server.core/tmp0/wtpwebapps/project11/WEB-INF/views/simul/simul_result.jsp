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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과페이지</title>
<style>
.flex{
    display: flex;
}

.win{
    color: green;
}

.lose{
    color: red;
}

#scoreBoard{
    padding: 10px;
    border: 2px solid black;
    justify-content: space-between;
}
.boxx{
    padding: 2px;
    width: 100px;
}
</style>
</head>
<body>
    
    <c:set var="tempID" value="${tempID}"/>
    <c:set var="userID" value="${userID}"/>
    <c:set var="list" value="${list}"/>
    <c:set var="data" value="${data}"/>
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
    
    <h1>플레이어 ${userID}</h1>
    <a href="/packs/simul/list/${userID }/1">대전기록페이지로</a>
    <%
        int winlose = list.getList_winlose();
        if(winlose == 0){
    %>
    <h2 id="result" class="win">승리</h2>
    <%
        }else{
    %>
    <h2 id="result" class="lose">패배</h2>
    <%
        }
    %>
    <div id="scoreBoard" class="flex">
        <div id="userTeam">
            <div class="flex">
                <div class="boxx">${data.data_user_champ1 }</div>
                <div class="boxx">레벨 : ${data.data_user_level1 }</div>
                <div class="boxx">킬 : ${data.data_user_kill1 }</div>
                <div class="boxx">데스 : ${data.data_user_death1 }</div>
                <div class="boxx">어시스트 : ${data.data_user_assist1 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_user_champ2 }</div>
                <div class="boxx">레벨 : ${data.data_user_level2 }</div>
                <div class="boxx">킬 : ${data.data_user_kill2 }</div>
                <div class="boxx">데스 : ${data.data_user_death2 }</div>
                <div class="boxx">어시스트 : ${data.data_user_assist2 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_user_champ3 }</div>
                <div class="boxx">레벨 : ${data.data_user_level3 }</div>
                <div class="boxx">킬 : ${data.data_user_kill3 }</div>
                <div class="boxx">데스 : ${data.data_user_death3 }</div>
                <div class="boxx">어시스트 : ${data.data_user_assist3 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_user_champ4 }</div>
                <div class="boxx">레벨 : ${data.data_user_level4 }</div>
                <div class="boxx">킬 : ${data.data_user_kill4 }</div>
                <div class="boxx">데스 : ${data.data_user_death4 }</div>
                <div class="boxx">어시스트 : ${data.data_user_assist4 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_user_champ5 }</div>
                <div class="boxx">레벨 : ${data.data_user_level5 }</div>
                <div class="boxx">킬 : ${data.data_user_kill5 }</div>
                <div class="boxx">데스 : ${data.data_user_death5 }</div>
                <div class="boxx">어시스트 : ${data.data_user_assist5 }</div>
            </div>
        </div>
        <div id="enemyTeam">
            <div class="flex">
                <div class="boxx">${data.data_enemy_champ1 }</div>
                <div class="boxx">레벨 : ${data.data_enemy_level1 }</div>
                <div class="boxx">킬 : ${data.data_enemy_kill1 }</div>
                <div class="boxx">데스 : ${data.data_enemy_death1 }</div>
                <div class="boxx">어시스트 : ${data.data_enemy_assist1 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_enemy_champ2 }</div>
                <div class="boxx">레벨 : ${data.data_enemy_level2 }</div>
                <div class="boxx">킬 : ${data.data_enemy_kill2 }</div>
                <div class="boxx">데스 : ${data.data_enemy_death2 }</div>
                <div class="boxx">어시스트 : ${data.data_enemy_assist2 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_enemy_champ3 }</div>
                <div class="boxx">레벨 : ${data.data_enemy_level3 }</div>
                <div class="boxx">킬 : ${data.data_enemy_kill3 }</div>
                <div class="boxx">데스 : ${data.data_enemy_death3 }</div>
                <div class="boxx">어시스트 : ${data.data_enemy_assist3 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_enemy_champ4 }</div>
                <div class="boxx">레벨 : ${data.data_enemy_level4 }</div>
                <div class="boxx">킬 : ${data.data_enemy_kill4 }</div>
                <div class="boxx">데스 : ${data.data_enemy_death4 }</div>
                <div class="boxx">어시스트 : ${data.data_enemy_assist4 }</div>
            </div>
            <div class="flex">
                <div class="boxx">${data.data_enemy_champ5 }</div>
                <div class="boxx">레벨 : ${data.data_enemy_level5 }</div>
                <div class="boxx">킬 : ${data.data_enemy_kill5 }</div>
                <div class="boxx">데스 : ${data.data_enemy_death5 }</div>
                <div class="boxx">어시스트 : ${data.data_enemy_assist5 }</div>
            </div>
        </div>
    </div>
    <h3>로그</h3>
    <c:forEach var="log" items="${logs}">
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
    </c:forEach>

    <%
        }
    %>
    
    <script>
    </script>
</body>
</html>