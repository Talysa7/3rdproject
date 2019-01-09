<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>
<h2>평판처리페이지</h2>

<c:if test="${result eq 1}">
	<c:redirect url="myPage.go"/>
</c:if>
<c:if test="${result eq 0}">
	alert("평판이 작성되지 않았습니다");
	history.back();
</c:if>
