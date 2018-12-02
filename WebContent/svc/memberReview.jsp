<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Custom styles for boarAlbum template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
	<div class="container">
		<c:choose>
			<c:when test="${myTrips.size() eq 0}">
				<center>
					<h6>${search_no_result}</h6>
					<br>
					<img src="${project}img/paori.png">
				</center>
			</c:when>
			<c:otherwise>
			<c:forEach var="member" items="${review}">
				<c:if test="${review ne null}">
				<c:if test="${member.user_id ne user }">
				<h6>멤버 : ${member.user_id}</h6>					
					<label>평가자 : </label> ${member.reviewer_id }<br>
					<label>평판점수 : </label> <c:choose>
										<c:when test="${member.review_point eq 1}"> ★ </c:when>
										<c:when test="${member.review_point eq 2}"> ★★  </c:when>
										<c:when test="${member.review_point eq 3}"> ★★★  </c:when>
										<c:when test="${member.review_point eq 4}"> ★★★★    </c:when>
										<c:when test="${member.review_point eq 5}"> ★★★★★     </c:when>
									</c:choose><br>
					<label>평판내용 : </label>${member.review_comment}<br>
				<hr size="1px" color="black" noshade>			
				</c:if>
				</c:if>
			</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>