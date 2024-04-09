<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="champsData" value="${champsData }"/>
<c:set var="version" value="${version }"/>
    <div id="successConsole">
        <p>성공! 버전: ${version }</p>
    </div>
    
<div><a href="/packs/">메인으로</a></div>
</body>
</html>