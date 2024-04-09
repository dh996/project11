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
    <div>사용자 ${id}</div>
<form action="simul/home" method="POST">
    <input type="text" id="id" name="id" value="${id}" style="display: none;"><br>
    <input type="submit" value="시뮬페이지로">
</form>
    <a href="/packs/simul/list/${id }/1">
	        대전기록보기
	</a>
<div><a href="/packs/">로그아웃</a></div>
</body>
</html>