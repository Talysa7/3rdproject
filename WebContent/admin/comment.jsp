<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<%@ include file="/svc/header.jsp" %>
<%-- <jsp:include page='head.jsp' flush="false"/>
<jsp:include page='list.jsp' flush="false"/> --%>
<br><br><br><br><br><br>
<article>
	<h3>|${str_list_m}</h3>
	<section>
		<br>
		<input class="listbutton" type="button" value="${str_content_v}" onclick="location='adminTrip.go'">
		<input id="on" class="listbutton" type="button" value="${str_comment_v}">
	</section>
	<section>
		<p><input type="checkbox" id="checkAll" >${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_delete}" onclick="deleteList('${page}')">
		</div>
	</section>
	<section>
		<form id="commentForm">
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled" onclick="deleteList('${page}')"></th>
					<th>${str_id}</th>
					<th>${str_comment}</th>
					<th>${str_reg_date}</th>
				</tr>
				<c:if test="${count ne 0}">	
					<c:forEach var="comment" items="${comments}">
						<tr>
							<td class="check" align="center">
								<input type="checkbox" name="check1">
								<input type="hidden" name="key"value="${comment.comment_id}"/>
							</td>
							<td >${comment.user_id}</td>
							<td><a title="${comment.comment_content}">${comment.comment_content}</a></td>
							<td><fmt:formatDate value="${comment.comment_reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
		<br>
			<div id="page">
				<c:if test="${count ne 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="adminComment.go">[◀◀] </a>
						<a href="adminComment.go?pageNum=${startPage-pageBlock}">[◀] </a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i eq currentPage}">
							<p>[${i}]<p>
						</c:if>
						<c:if test="${i ne currentPage}">					
							<a href="adminComment.go?pageNum=${i}">[${i}] </a>
						</c:if>	
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href ="adminComment.go?pageNum=${startPage+pageBlock}">[▶]</a>
						<a href ="adminComment.go?pageNum=${pageCount}">[▶▶]</a>
					</c:if>	
				</c:if>
			</div>
	</section>
</article>	