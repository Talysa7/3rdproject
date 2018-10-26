<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h2> ${page_modify} </h2>
<c:if test="${result eq 0}">
	<script type="text/javascript">
		<!--
		alert( modifyerror );
		//-->
	</script>		
	<meta http-equiv="refresh" content="0; url=trip.go?tb_no=${tb_no}">
</c:if>
<c:if test="${result eq 1}">
	<c:redirect url="trip.go?tb_no=${tb_no}"/>
</c:if>	
</body>
</html>