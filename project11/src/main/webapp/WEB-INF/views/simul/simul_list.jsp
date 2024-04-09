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
<title>대전목록</title>
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
div{
    margin: 2px;
}
</style>
</head>
<body>
    
    <c:set var="list" value="${list}"/>
    <c:set var="page" value="${page}"/>    
    <c:set var="totalPage" value="${totalPage}"/>    
    <c:set var="id" value="${id}"/>
    
    <h1>시뮬 목록 페이지 사용자 : ${id } ${page }페이지</h1>
    <h2>토탈페이지 확인용 : ${totalPage}</h2>
    <h3><a href="#" onclick="goBack()">뒤로가기</a></h3>
    
    <c:forEach var="list" items="${list}">
        <a href="/packs/simul/simulResult/${list.simulListVo.list_sid }/${id }" style="display: block;">
            <div class="flex linkBox">
                <div class="name">
                    ${list.simulListVo.list_player }
                </div>
                <c:choose>
                    <c:when test="${list.simulListVo.list_winlose == 0 }">
                        <div class="win">
                            승리
                        </div>
                    </c:when>
                    <c:when test="${list.simulListVo.list_winlose == 1 }">
                        <div class="lose">
                            패배
                        </div>
                    </c:when>
                </c:choose>
                <div class="user">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_user_champ1 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_user_champ2 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_user_champ3 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_user_champ4 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_user_champ5 }.png">
                </div>
                <div>VS</div>
                <div class="enemy">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_enemy_champ1 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_enemy_champ2 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_enemy_champ3 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_enemy_champ4 }.png">
                    <img src="https://ddragon.leagueoflegends.com/cdn/14.6.1/img/champion/${list.simulDataVo.data_enemy_champ5 }.png">
                </div>
            </div>
        </a>
    </c:forEach>
    
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
    
    <script>
        function goBack() {
           window.history.back();
        }
    </script>
</body>
</html>