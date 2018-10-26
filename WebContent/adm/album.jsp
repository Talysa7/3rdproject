<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>

<jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/>	
<article>
	<h3>|${str_photo_m}</h3>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_delete}" onclick="deletePhoto()">
		</div>
	</section>
	<section>
		<form id="albumForm">
			<c:if test="${count ne 0}">	
				<c:forEach var="photo" items="${album}"> 
					<div class="photoarea">
						<input type="checkbox" name='check1'>
						<input type="hidden" value="${photo.photo_id}" name="key">
						<span class="photo">
							<a href="${photo.photo_url}" target="_blank"><img class="img" src="${photo.photo_url}"/></a>
						</span>
					</div>
				</c:forEach>
			</c:if>
		</form>
	</section>
</article>