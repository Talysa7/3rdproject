<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/svc/setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
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

<body class="trip">
	<div class="container" style="width: 800px;">
		<div>
			<div id=button_area class="d-flex justify-content-end">
				<!-- 목록 button -->
				<div>
					<input type="button" value="${btn_list}"
						onclick="location='tripList.go'" class="btn btn-sm">&nbsp;
				</div>
				<!--수정/삭제 button -->
				<c:if test="${boardDto.user_id eq user_id}">
					<div>
						<input type="button" value="${btn_mod}"
							onclick="modifyBoard(${boardDto.board_no})" class="btn btn-sm">
						<input type="button" value="${btn_delete}"
							onclick="deleteBoard(${boardDto.board_no})" class="btn btn-sm">
					</div>
				</c:if>
				<c:if test="${user_level eq 9}">
					<div>
						<input type="button" value="${btn_delete}" class="btn btn-sm"
							onclick="deleteBoard(${board_no})"> <input type="button"
							value="${btn_back_admin}" class="btn btn-sm"
							onclick="goAdminPage()">
					</div>
				</c:if>
			</div>
			<!--  -->
			<br>
			<!----- 공지 ----->
			<article>
				<section>
					<c:if test="${boardDto.board_level eq 1}">
						<div style="font-size: 32px;">
							<img class="mb-4" src="${project}img/logo_c.png" alt=""
								width="40" height="40"> <b>${trip_notice_2}</b>
						</div>
					</c:if>
					<!-- Post -->
					<div id="trip_title">
						<div class="row">
							<input type="text" name="trip_title"
								class="col-12 form-control form-control-lg"
								value="${boardDto.board_title}" readonly="readonly">
						</div>
						<div class="row">
							<div class="text-muted">
								<i><b>With</b></i>&nbsp; ${boardDto.user_name}
							</div>
						</div>

						<!-- start schedule -->
						<nav class="navbar bg-dark navbar-dark">
							<div class="container">
								<ul class="nav navbar-nav">
									<c:forEach var="trip" items="${boardDto.tripLists}">
										<a href="#" class="navbar-brand">
											<li>${trip.coord_order}</li>
										</a>
									</c:forEach>
								</ul>
							</div>
						</nav>
						<c:forEach var="trip" items="${boardDto.tripLists}">
							<div id="trip_${trip.coord_order}">
								<form name="orderInfo">
									<input type="hidden" name="order_of_${trip.trip_id}"
										value="${trip.coord_order}"> <input type="hidden"
										name="member_count_${trip.trip_id}"
										value="${trip.trip_member_count}">
								</form>
								<div class="container" style="width: 100%"
									onmouseover="focusMarker(${order},${trip.coordinate.coord_long},${trip.coordinate.coord_lat})">
									<div class="row">
										<label class="col-2">${trip_schedule}</label> <input
											type="text" class="col-3" value="${trip.start_date}"
											readonly="readonly" /> ~ <input type="text" class="col-3"
											value="${trip.end_date}" readonly="readonly" />
									</div>
									<div class="row">
										<div class="col-12 offset-2">
											<div class="loc" name="coord">
												<input type="text" name="trip_location_${trip.coord_order}"
													id="address${trip.coord_order}" class="col-8 pt-3"
													readonly="readonly"> <input type="hidden"
													name="coord_long" value="${trip.coordinate.coord_long}">
												<input type="hidden" name="coord_lat"
													value="${trip.coordinate.coord_lat}"> <input
													type="hidden" id="country${trip.coord_order}"
													value="${trip.coordinate.country_name}">
											</div>
											<!-- 장소 -->
										</div>
										<!-- column -->
									</div>
									<!-- row -->
									<div class="row">
										<label class="col-2">${trip_member_count}</label>
										<div id="trip_button_${trip.trip_id}">
											<c:if test="${sessionScope.user_id ne null}">
												<c:choose>
													<c:when test="${session.user_id eq boardDto.user_id}">
													&nbsp; 
												</c:when>
													<c:when test="${isMember eq true}">
														<button onclick="absent(${trip.trip_id})"
															class="btn btn-sm">불참</button>
													</c:when>
													<c:otherwise>
														<button onclick="attend(${trip.trip_id})"
															class="btn btn-sm">참석</button>
													</c:otherwise>
												</c:choose>
											</c:if>
										</div>
										&nbsp;
										<div>
											<div id="trip_member_list_${trip.coord_order}">
												${trip_members.size}/${trip.trip_member_count}, &nbsp;
												<c:forEach var="member" items="${trip.trip_members}">
												${member.user_name}
											</c:forEach>
											</div>
										</div>
									</div>
								</div>

								<!--button 영역 -->
								<button class="btn" onclick="showMap()">${trip_map}</button>
								<button class="btn" onclick="showAlbum()">${trip_photo}</button>

								<!-- tripAlbum -->
								<div id="albumTab">
									<jsp:include page='boardAlbum.go?trip_id=${trip.trip_id}' />
								</div>
								<!--- tripMap -->
								<input type="hidden" value="${trip.coordinate.coord_lat}"
									id="lat" /> <input type="hidden"
									value="${trip.coordinate.coord_lng}" id="lng" />
								<div id="mapTab" style="display: none"></div>
							</div>
					</div>
					</c:forEach>
					<!-- 일정 Container box-->

					<div class="row pt-3 pb-1">
						<label class="col-2">${boardDto.board_contact}</label> <a
							href="${boardDto.board_contact}" target="_blank">${boardDto.board_contact}</a>
					</div>
					<div id="trip_content">
						<div class="row px-3">
							<textarea class="col-12" rows="8" style="border: dotted 1px grey"
								readonly>${boardDto.board_content}</textarea>
						</div>
					</div>
					<!-- id: trip_content -->
		</div>
		<!-- id: trip_title -->
		</section>

		<br>

		<!-- End of Post -->
		</article>

		<!-- comment -->
		<c:if test="${sessionScope.user_id != null}">
			<div class="container">
				<label for="content">comment</label>
				<form name="commentInsertForm" method="post">
					<div class="input-group">
						<input type="hidden" name="board_no" value="${boardDto.board_no}" />
						<input type="hidden" name="session" value="${boardDto.user_id}" />
						<input type="text" class="input col-11" id="c_content"
							name="c_content" placeholder="${trip_entercontent}"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button"
								onclick="commentInsert()">${trip_write}</button>
						</span>
					</div>
				</form>
			</div>
		</c:if>
		<div class="commentList"></div>
		<!-- comment -->
	</div>
	</div>
</body>

<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCTHrzKi3xuXtFRo_fm9dGO2cC--hrLBpo&callback=initMap">
</script>

<!-- Bootstrap core JavaScript
    ==================================================
    Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/vendor/holder.min.js"></script>