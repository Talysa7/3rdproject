<%@page import="db.UserDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<script src="${project}script.js"></script>

<h2> ${page_delete} </h2>

<c:if test="${resultCheck eq 1}">
	<c:if test="${result eq 0}">
			<script type="text/javascript">
				<!--
				alert( deleteerror );
				//-->
			</script>
			<meta http-equiv="refresh" content="0; url=login.go">
	</c:if>
	<c:if test="${result eq 1}">
		${sessionScope.user_id = null}
		<c:redirect url="login.go"/>
	</c:if>		
</c:if>		
<c:if test="${resultCheck eq 0}">
	<script type="text/javascript">
		<!--
		erroralert( loginpasswderror );	
		//-->			
	</script>
</c:if>		










