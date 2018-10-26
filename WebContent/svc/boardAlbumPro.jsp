<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
<script src="${project}script.js"></script>
<h2>albumPro</h2>
<c:if test="${fileResult eq 1}">
	<script type="text/javascript">
		<!--
			<c:redirect url="trip.go?tb_no=${tb_no}&tab=1"/>
		//-->
	</script>
</c:if>
<c:if test="${fileResult eq -1}">
	<script type="text/javascript">
		<!--
			erroralert(extensionerror);
		//-->
	</script>
</c:if>
<c:if test="${fileResult eq -2}">
	<script type="text/javascript">
		<!--
			erroralert(sizeerror);
		//-->
	</script>
</c:if>