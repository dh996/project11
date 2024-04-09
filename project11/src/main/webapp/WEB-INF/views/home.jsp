<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>시작페이지</title>
<body>
<h1>
	시작페이지
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form action="submit" method="POST">
    <label for="username">사용자명 입력:</label><br>
    <input type="text" id="id" name="id"><br>

    <input type="submit" value="입장">
</form>

</body>
</html>
