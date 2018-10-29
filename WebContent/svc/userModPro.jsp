<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<script src="${project}script.js"></script>
<c:if test="${result ne 1}">
	<script type="text/javascript">
		erroralert(${userMod_updateError});
	</script>
	<meta http-equiv="refresh" content="0; url=myPage.go">
</c:if>
<c:if test="${result eq 1}">
	<c:redirect url="myPage.go"/>	
</c:if>