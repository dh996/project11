<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 컨트롤러</title>
</head>
<body>
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
</body>
</html>