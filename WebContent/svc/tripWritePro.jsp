<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<c:if test="${result eq 0}">
	<script type="text/javascript">
		alert(write_error);
	</script>
</c:if>
<c:if test="${result ne 0}">
	<c:redirect url="trip.go?board_no=${board_no}"/>
</c:if>