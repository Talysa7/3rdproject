<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>
<h2>장소평가 처리페이지</h2>

<c:if test="${result eq 1}">
	<c:redirect url="myPage.go"/>
</c:if>
