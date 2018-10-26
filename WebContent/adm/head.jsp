<%@ page language="java" contentType="text/html; charset=UTF-8"
   session="true"
    buffer="10kb"
    autoFlush="true"
    isThreadSafe="true"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<header>
	<h2>ADMIN PAGE</h2>
	<div id="logout">
		<h4><a href="adminLogout.go">${str_logout}</a></h4>
	</div>
</header>