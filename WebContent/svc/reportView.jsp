<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${project}style_member.css">
<script src="${project}script.js"></script>
	<body>
		<form>
			
		</form>		
	</body>	

<c:if test="${sessionScope.user_id ne null}">	
	<c:forEach items="${coords}" var="coord" >  
	  <c:out value="${coord.coord_name}"/> 
	</c:forEach> 
</c:if>
<c:if test="${sessionScope.user_id eq null}">	
	나오냐
</c:if>
	