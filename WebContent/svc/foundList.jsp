<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">

<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
	<!-- Category & Contents Box -->
	<div class="body-box">
		<div class="board-list" id="board-list">
			<c:choose>
				<c:when test="${count ne 0}">
					<h6>${keyword} ${search_result}</h6>
					<c:forEach var="post" items="${foundList}">
						<div class="row">
							<div class="col-md-12">
								<div class="card flex-md-row mb-3 shadow-sm h-md-250">
									<div class="card-body d-flex flex-column align-items-start">
										<strong class="d-inline-block mb-2"> 
										<c:forEach var="trip" items="${post.tripLists}">
					              			${trip.coord_name}
					              		</c:forEach>
										</strong>
										<h3 class="mb-0">
											<a class="text-dark" href="trip.go?board_no=${post.board_no}">${post.board_title}</a>
										</h3>
										<div class="mb-1 text-muted text-right">
											<i><b>With</b></i>&nbsp; ${post.user_name}
										</div>
										<hr size="1px" color="black" noshade>
										<p class="card-text mb-auto">${post.board_content}</p>
										<hr style="width: 100%">
										<div class="d-flex justify-content-center">
											<div class="p-2">조회수:${post.board_view_count}</div>
											&nbsp;
											<div class="p-2">
												<c:forEach var="tag" items="${post.board_tags}">
													<label class="btn taglist"> # ${tag.tag_value} </label>
												</c:forEach>
											</div>
										</div>
										<!-- card-footer -->
									</div>
									<!-- card-body -->
								</div>
							</div>
							<!-- col-md-12 -->
						</div>
						<!-- 1 row -->
					</c:forEach>
				</c:when>
				<c:otherwise>
					<center>
						<h6>${keyword} ${search_no_result}</h6>
						<br>
						<img src="${project}img/paori.png">
					</center>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- board list -->
	</div>
	<!-- body box -->
</body>
</html>
