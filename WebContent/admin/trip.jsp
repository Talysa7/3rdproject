<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<%@ include file="/svc/header.jsp" %>
<%-- <jsp:include page='list.jsp' flush="false"/> --%>	
 <br><br><br>
<article>
	<h3>|${str_list_m}</h3>
	<section>
		<br>
		<input id="on" class="listbutton" type="button" value="${str_content_v}">
		<input class="listbutton" type="button" value="${str_comment_v}" onclick="location='adminComment.go'">
	</section>
	<section>
		<p><input type="checkbox" id="checkAll">${str_select_all}</p>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_delete}" onclick="deleteList('${page}')">
			<input class="inputbutton" type="button" value="${btn_notice}" onclick="notice()">
		</div>
	</section>
	<section>
		<form id="tripForm">
			<table>
				<tr>
					<th class="check"><input type="checkbox" disabled="disabled"></th>
					<th>${str_notice}</th>
					<th>${str_num}</th>
					<th>${str_title}</th>
					<th>${str_writer}</th>
					<th>${str_content}</th>
					<th>${str_reg_date}</th>
					<th>${str_readcount}</th>
				</tr>
				<c:if test="${count ne 0}">	
					<c:forEach var="trip" items="${trips}">
						<tr>
							<td class="check" align="center">
								<input type="checkbox" name="check1">
							</td>
							<td>
								<c:if test="${trip.board_level eq 1}">
									<span name="notice" class="label label-primary">
										${btn_notice}
									</span>
								</c:if>
							</td>
							<td name="key">${trip.board_no}</td>
							<td>${trip.board_title}</td>
							<td>${trip.user_id}</td>
							<td><a href="trip.go?board_no=${trip.board_no}" title="${trip.board_content}">${trip.board_content}</a></td>
							<td><fmt:formatDate value="${trip.board_reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>${trip.board_view_count}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</form>
		<br>&nbsp;
		<div id="page">
			<c:if test="${count ne 0}">
				<c:if test="${startPage gt pageBlock}">
					<a href="adminTrip.go">[◀◀] </a>
					<a href="adminTrip.go?pageNum=${startPage-pageBlock}">[◀] </a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:if test="${i eq currentPage}">
						<p>[${i}]<p>
					</c:if>
					<c:if test="${i ne currentPage}">					
						<a href="adminTrip.go?pageNum=${i}">[${i}] </a>
					</c:if>	
				</c:forEach>
				<c:if test="${pageCount gt endPage}">
					<a href ="adminTrip.go?pageNum=${startPage+pageBlock}">[▶]</a>
					<a href ="adminTrip.go?pageNum=${pageCount}">[▶▶]</a>
				</c:if>	
			</c:if>
		</div>
	</section>
</article> 
