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
</head>
<body>
<h1>dd</h1>
<script>
    var jQ = jQuery.noConflict();
    var="tempID" value="${tempID}
    var="userID" value="${userID}
    
    function startAjax() {
        // Ajax 요청
        jQ.ajax({
            type: 'POST',
            url: '/packs/simul/simulResult',  // 실제 엔드포인트에 맞게 변경
            contentType: 'application/json',
            data: JSON.stringify({
            	tempID: '${tempID}',
                userID: '${userID}'
            }),
            success: function (response) {
                // 성공 시 동작
                console.log('Ajax request succeeded:', response);
                
            },
            error: function (error) {
                // 실패 시 동작
                console.error('Ajax request failed:', error);
            }
        });
    }
    startAjax();
</script>
</body>
</html>