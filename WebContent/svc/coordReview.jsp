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
				<c:when test="${review.size() eq 0}">
					<center>
						<h6>${search_no_result}</h6>
						<br>
						<img src="${project}img/paori.png">
					</center>
				</c:when>
				<c:otherwise>
				<c:forEach var="review" items="${review}">
					<!-- left row start -->
				<div class="row">
					<!-- col-md-11 start -->
					<div class="col-md-11">
						<!-- left card start -->
						<div class="card flex-md-row mb-3 shadow-sm h-md-250">
							<!-- card body start -->
							<div class="card-body">
								<div class="mb-1 text-muted text-right">
									<i><b>장소</b></i>&nbsp; ${coord.coord_name}
								</div>
								<strong class="d-inline-block mb-2">
						<label>평가점수 : </label>${review.review_point }<br>
								</strong>
								<h3 class="mb-0">
						<label>평가 내용: </label> ${review.review_comment}<br>
								</h3>
							</div>
						</div>
					</div>
				</div>		
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
<div id="trace">
		</div>
		<div id="page">
				<c:if test="${count ne 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="coordReview.go?coord_id=${review.coord_id}">[◀◀] </a>
						<a href="coordReview.go?coord_id=${review.coord_id}&pageNum=${startPage-pageBlock}">[◀] </a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i eq currentPage}">
							<p>[${i}]<p>
						</c:if>
						<c:if test="${i ne currentPage}">					
							<a href="coordReview.go?coord_id=${review.coord_id}&pageNum=${i}">[${i}] </a>
						</c:if>	
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href ="coordReview.go?coord_id=${review.coord_id}&pageNum=${startPage+pageBlock}">[▶]</a>
						<a href ="coordReview.go?coord_id=${review.coord_id}&pageNum=${pageCount}">[▶▶]</a>
					</c:if>	
				</c:if>
			<div class="mx-auto">
				<button type="button" class="btn btn-secondary btn-sm" onclick="goback()">이전페이지로</button>
			</div>
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
