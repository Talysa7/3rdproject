<%@ page language="java" contentType="text/html; charset=UTF-8"
   session="true"
    buffer="10kb"
    autoFlush="true"
    isThreadSafe="true"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<aside>
	<h1><a href="adminTrip.go">${str_list_m}</a></h1>
		<h3><a href="adminTrip.go">${str_content_v}</a></h3>
		<h3><a href="adminComment.go">${str_comment_v}</a></h3>
		<br>
	<h1><a href="adminUser.go">${str_member_m}</a></h1>
		<h3><a href="adminUser.go">${str_member_v}</a></h3>
		<br>
	<h1><a href="adminTag.go">${str_tag_m}</a></h1>
		<h3><a href="adminTag.go">${str_tag_v}</a></h3>
		<br>
	<h1><a href="adminAlbum.go">${str_photo_m}</a></h1>
		<h3><a href="adminAlbum.go">${str_photo_v}</a></h3>
		<br>
</aside>