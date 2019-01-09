<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<h3>|${str_tag_mod}</h3>
<form method="post" action="adminTagMng.go">
	<input type="hidden" value="${state}" name="state"/>
	<section>
			<div class="buttonarea">
				<input class="inputbutton" type="button" value="${btn_cancel}" onclick="setCancel()">
				<input class="inputbutton" type="submit" value="${btn_check}">
			</div>
	</section>
	<section>
			<table>
				<tr> 
					<th>${str_tagid}</th>
					<th>${str_tag}</th>
					<th>${str_mod_tag}</th>
				</tr>
				<c:set var="i" value="0"/>
				<c:forEach var="tag" items="${tags}"> 
						<c:set var="i" value="${i+1}"/>
						<input type="hidden" value="${tag.tag_id}" name="tag_id${i}"/>
						<tr>
							<td>${tag.tag_id}</td>
							<td>${tag.tag_value}</td>
							<td><input class="input" type="text" value="${tag.tag_value}" name="tag_value${i}" autofocus="autofocus" maxlength="10"/></td>
						</tr>
 				</c:forEach> 
			</table>
			<input type="hidden" value="${i}" name="num"/>
	</section>
</form>