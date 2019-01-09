<%@page import="db.UserDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="setting.jsp" %>
<script src="${project}script.js"></script>

<h2> ${page_login} </h2>

<c:if test="${result eq 0}">
	<script type="text/javascript">
		<!--
		erroralert( loginiderror );
		//-->
	</script>
</c:if>
<c:if test="${result eq -1}">
	<script type="text/javascript">
		<!--
		erroralert( loginpasswderror );
		//-->
	</script>
</c:if>
<c:if test="${result eq 1}">
	${sessionScope.user_id = id}
	${sessionScope.user_name = user_name}
	<c:if test="${userType eq 9}">
		${sessionScope.user_level = 9}
		<c:redirect url="adminTrip.go"/>
	</c:if>
	${sessionScope.user_level = 1}
	<c:redirect url="tripList.go"/>
</c:if>