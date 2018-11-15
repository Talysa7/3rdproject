<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>
<table id="${nickname}">
	<div>닉네임</div>
	<span id="a">${nickname}</span>
	<span id="b"><c:choose>
		<c:when test="${grade eq 1}"> ★</c:when>
		<c:when test="${grade eq 2}"> ★★</c:when>
		<c:when test="${grade eq 3}"> ★★★</c:when>
		<c:when test="${grade eq 4}"> ★★★★</c:when>
		<c:when test="${grade eq 5}"> ★★★★★</c:when>
	</c:choose></span>
</table>