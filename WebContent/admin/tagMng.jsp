<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<script src="${project}script.js"></script>
<h2>adminTagMng</h2>
<!--tag add  -->
<c:if test="${state eq 1}">
	<c:if test="${result eq 0}">
		<script type="text/javascript">
			<!--
				erroralert(taginserterror);
				
			//-->
		</script>
	</c:if>
	<c:if test="${result eq 1}">
		<script type="text/javascript">
			<!--
				window.opener.location.href=window.opener.document.URL;
				self.close();
			//-->
		</script>	
	</c:if>
</c:if>
<!--tag modify -->
<c:if test="${state eq 2}">
	<c:if test="${result eq 0}">
		<script type="text/javascript">
			<!--
				erroralert(taginserterror);
				
			//-->
		</script>
	</c:if>
	<c:if test="${result eq 1}">
		<script type="text/javascript">
			<!--
				window.opener.location.href=window.opener.document.URL;
				self.close();
			//-->
		</script>	
	</c:if>
</c:if>