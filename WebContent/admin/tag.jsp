<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/>	
<article>
	<h3>|${str_tag_m}</h3>
	<section>
		<br>
		<input id="on" class="listbutton" type="button" value="${str_tag_v}">
	</section>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
	
			<div class="buttonarea">
				<input class="inputbutton" type="submit" value="${btn_delete}" onclick="deleteList('${page}')">
				<input class="inputbutton" type="button" value="${btn_modify}" onclick="goModTag()">
				<input class="inputbutton" type="button" value="${btn_add}" onclick="goAddTag()">
			</div>

	</section>
	<section>
		<form name="tagForm" id="tagForm">
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled" ></th>
					<th>${str_tagid}</th>
					<th>${str_tag}</th>
					<th>${str_type}</th>
				</tr>
				<c:if test="${count ne 0}">	
					<c:forEach var="tag" items="${tags}">
						<tr>
							<td class="check" align="center"><input type="checkbox" name="check1"></td>
							<td name="key">${tag.tag_id}</td>
							<td>${tag.tag_value}</td>
							<c:if test="${tag.tag_id ge 0 and tag.tag_id le 100}">
								<td>${str_tag_user}</td>
							</c:if>
							<c:if test="${tag.tag_id ge 101 and tag.tag_id le 1000}">
								<td>${str_tag_city}</td>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
			<br>
			<div id="page">
				<c:if test="${count ne 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="adminTag.go">[◀◀] </a>
						<a href="adminTag.go?pageNum=${startPage-pageBlock}">[◀] </a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i eq currentPage}">
							<p>[${i}]<p>
						</c:if>
						<c:if test="${i ne currentPage}">					
							<a href="adminTag.go?pageNum=${i}">[${i}] </a>
						</c:if>	
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href ="adminTag.go?pageNum=${startPage+pageBlock}">[▶]</a>
						<a href ="adminTag.go?pageNum=${pageCount}">[▶▶]</a>
					</c:if>	
				</c:if>
			</div>
	</section>
</article>