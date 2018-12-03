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
				<c:forEach var="best" items="${best}" varStatus="status">
				     <h6>멤버 : ${best.user_id}의 최고 평판</h6>	
				    	<label>평가자 : </label> ${best.reviewer_id }<br>
						<label>평판점수 : </label> <c:choose>
											<c:when test="${best.review_point eq 1}"> ★ </c:when>
											<c:when test="${best.review_point eq 2}"> ★★  </c:when>
											<c:when test="${best.review_point eq 3}"> ★★★  </c:when>
											<c:when test="${best.review_point eq 4}"> ★★★★    </c:when>
											<c:when test="${best.review_point eq 5}"> ★★★★★     </c:when>
										</c:choose><br>
						<label>평판내용 : </label>${best.review_comment}<br>
					<hr size="1px" color="black" noshade>
				</c:forEach>		
				<c:forEach var="recent" items="${recent}">
					<h6>멤버 : ${recent.user_id}의 최신 평판</h6>					
						<label>평가자 : </label> ${recent.reviewer_id }<br>
						<label>평판점수 : </label> <c:choose>
											<c:when test="${recent.review_point eq 1}"> ★ </c:when>
											<c:when test="${recent.review_point eq 2}"> ★★  </c:when>
											<c:when test="${recent.review_point eq 3}"> ★★★  </c:when>
											<c:when test="${recent.review_point eq 4}"> ★★★★    </c:when>
											<c:when test="${recent.review_point eq 5}"> ★★★★★     </c:when>
										</c:choose><br>
						<label>평판내용 : </label>${recent.review_comment}<br>
					<hr size="1px" color="black" noshade>					
				</c:forEach>
				<c:forEach var="wst" items="${wst}">
				<h6>멤버 : ${wst.user_id}의 최저평판</h6>					
					<label>평가자 : </label> ${wst.reviewer_id }<br>
					<label>평판점수 : </label> <c:choose>
										<c:when test="${wst.review_point eq 1}"> ★ </c:when>
										<c:when test="${wst.review_point eq 2}"> ★★  </c:when>
										<c:when test="${wst.review_point eq 3}"> ★★★  </c:when>
										<c:when test="${wst.review_point eq 4}"> ★★★★    </c:when>
										<c:when test="${wst.review_point eq 5}"> ★★★★★     </c:when>
									</c:choose><br>
					<label>평판내용 : </label>${wst.review_comment}<br>
				<hr size="1px" color="black" noshade>
				</c:forEach>			
									
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>