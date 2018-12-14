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
		<!-- mid area row start -->
			<div class="row">
			
				<!-- left area start -->
				<div id="left">
				<div class="coordReview-list" id="coordReview-list">
				<c:choose>
				<c:when test="${coord.size() eq 0}">
					<center>
						<h6>${search_no_result}</h6>
						<br>
						<img src="${project}img/paori.png">
					</center>
				</c:when>
				<c:otherwise>
				<c:forEach var="coord" items="${coord}" varStatus="var">
					<!-- left row start -->
				<div class="row">
					<!-- col-md-11 start -->
					<div class="col-md-11">
						<!-- left card start -->
						<div class="card flex-md-row mb-3 shadow-sm h-md-250">
							<!-- card body start -->
							<div class="card-body">
								
								<strong class="d-inline-block mb-2">
						
						<label>장소 : </label>${coord.coord_name}<br>
								</strong>
								<h6 class="mb-0">
						<c:forEach var="tags" items="${coord.boardtags}" varStatus="stat">
						<label>태그 : ${tags.tag_value}</label><br>
						</c:forEach>

						<c:forEach var="review" items="${coord.coordReview}" varStatus="status" begin="0" end="2">
						<label>${status.count}번째 평가</label><br>
						<label>평가점수 : </label><c:choose>
											<c:when test="${review.review_point eq 1}"> ★ </c:when>
											<c:when test="${review.review_point eq 2}"> ★★  </c:when>
											<c:when test="${review.review_point eq 3}"> ★★★  </c:when>
											<c:when test="${review.review_point eq 4}"> ★★★★    </c:when>
											<c:when test="${review.review_point eq 5}"> ★★★★★     </c:when>
										</c:choose><br>
						<label>평가 내용: </label> ${review.review_comment}<br>
						<c:if test="${status.index ge 3 }">
							<a href="coordReview.go?coord_id=${coord.coord_id}">평판보기</a>
						</c:if>
						</c:forEach>
								</h6>
							</div>
						</div>
					</div>
				</div>		
								
			</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>

	<!-- body box -->
	</div>
	</div>
	
			</div>
	<!-- Footer -->
	<footer class="board-footer">
		<p>
			<a href="">Back to top</a>
		</p>
	</footer>
</body>
</html>
