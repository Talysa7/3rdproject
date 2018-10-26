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
				<c:if test="${isOwner eq 1}">
					<div>
						<input type="button" value="${btn_mod}"
							onclick="modifyBoard(${tb_no})" class="btn btn-sm"> <input
							type="button" value="${btn_delete}"
							onclick="deleteBoard(${tb_no})" class="btn btn-sm">
					</div>
				</c:if>
				<c:if test="${user_level eq 9}">
					<div>
						<input type="button" value="${btn_delete}" class="btn btn-sm"
							onclick="deleteBoard(${tb_no})"> <input type="button"
							value="${btn_back_admin}" class="btn btn-sm"
							onclick="goAdminPage()">
					</div>
				</c:if>
			</div>
			<!--  -->
			<br>
			<input type="hidden" value="${tbDto.tb_notice}" id="notice" />
			<!----- 공지 ----->
			<article>
				<section>
					<c:if test="${tbDto.tb_notice == 1}">
						<div style="font-size:32px;">
							<img class="mb-4" src="${project}img/logo_c.png" alt="" width="40" height="40"> <b>${trip_notice_2}</b>
						</div>
					</c:if>
					<div id="trip_title">
						<div class="row">
							<input type="text" name="trip_title" class="col-12 form-control form-control-lg"
								value="${tbDto.tb_title}" readonly="readonly">
						</div>
						<div class="row">
							<div class="text-muted">
								<i><b>With</b></i>&nbsp; ${tbDto.user_id}
							</div>
						</div>
						<c:forEach var="i" items="${locDtoList}">
							<c:set var="order" value="${i.coord_order}" />
							<form name="orderInfo">
								<input type="hidden" name="order_of_${i.td_trip_id}" value="${i.coord_order}">
							</form>
							<div class="container" style="width: 100%" onmouseover="focusMarker(${order},${i.coord_long},${i.coord_lat})">
								<div class="row">
									<label class="col-2">${trip_schedule}</label> <input
										type="text" class="col-3" value="${i.cal_start_date}"
										readonly="readonly" /> ~ <input type="text" class="col-3"
										value="${i.cal_end_date}" readonly="readonly" />
								</div>
								<!-- 날짜 일정 -->
								<div class="row">
									<div class="col-12 offset-2">
										<div class="loc" name="coord">
											<input type="text" name="trip_location${order}" id="address${order}" class="col-8 pt-3" readonly="readonly">
											<input type="hidden" name="coord_long" value="${i.coord_long}"> 
											<input type="hidden" name="coord_lat" value="${i.coord_lat}"> 
											<input type="hidden" id="country${order}" value="${i.country_name}">
										</div>
										<!-- 장소 -->
									</div>
									<!-- column -->
								</div>
								<!-- row -->
								<div class="row">
									<label class="col-2">${trip_m_num}</label>
									<div id="trip_button_${i.td_trip_id}">
										<c:if test="${sessionScope.user_id ne null}">
											<c:choose>
												<c:when test="${isOwner eq 1}">
													&nbsp; 
												</c:when>
												<c:when test="${isMember eq true}">
													<button onclick="absent(${i.td_trip_id})" class="btn btn-sm">불참</button>
												</c:when>
												<c:otherwise>
													<button onclick="attend(${i.td_trip_id})" class="btn btn-sm">참석</button>
												</c:otherwise>
											</c:choose>
										</c:if>
									</div>
									 &nbsp; 
									<div>
										<c:forEach var="memInfoList" items="${memInfoList}">
											<c:if test="${i.td_trip_id eq memInfoList.td_trip_id}">
												<div id="trip_member_list_${order}">
													${memInfoList.memNum}/${tbDto.tb_m_num}, ${memInfoList.members}</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:forEach>
							<!-- 일정 Container box-->
						
						<form name="trip_detail">
							<input type="hidden" name="user_id" value="${user_id}"> 
							<input type="hidden" name="m_num" value="${tbDto.tb_m_num}">
						</form>

						<div class="row pt-3 pb-1">
							<label class="col-2">${tb_talk}</label> <a
								href="${tbDto.tb_talk}" target="_blank">${tbDto.tb_talk}</a>
						</div>
						<div id="trip_content">
							<div class="row px-3">
								<textarea class="col-12" rows="8"
									style="border: dotted 1px grey" readonly>${tbDto.tb_content}</textarea>
							</div>
						</div>
						<!-- id: trip_content -->
					</div>
					<!-- id: trip_title -->
				</section>

				<br>
				<!--button 영역 -->
				<section>
					<button class="btn" onclick="showMap()">지도</button>
					<button class="btn" onclick="showAlbum()">사진</button>
				</section>

				<!--boardAlbum영역  -->
				<c:if test="${tab eq '0'}">
					<section id="albumTab" style="display: none">
				</c:if>
				<c:if test="${tab eq '1'}">
					<section id="albumTab">
				</c:if>
					<div class="row" id="album">
						<jsp:include
							page='boardAlbum.go?tb_no=${tb_no}&start=${start}&tab=${tab}'
							flush='false' />
					</div>
				</section>

				<!--ㅡMap영역  -->
				<c:if test="${tab eq 0}">
					<div id="mapTab">
				</c:if>
				<c:if test="${tab eq 1}">
					<div id="mapTab" style="display: none">
				</c:if>
				<div id="map">지도</div>
				<input type="hidden" value="${lat}" id="lat" /> <input
					type="hidden" value="${lng}" id="lng" />
		</div>
		</article>

		<!-- comment -->
		<c:if test="${sessionScope.user_id != null}">
			<div class="container">
				<label for="content">comment</label>
				<form name="commentInsertForm" method="post">
					<div class="input-group">
						<input type="hidden" name="tb_no" value="${tb_no}" /> <input
							type="hidden" name="session" value="${user_id}" /> <input
							type="text" class="input col-11" id="c_content" name="c_content"
							placeholder="${trip_entercontent}"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button"
								onclick="commentInsert()">등록</button>
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
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnBlipOjNesyFkAIAlXO9WkkIhfiqUIi4&callback=initMap">
</script>

<!-- Bootstrap core JavaScript
    ==================================================
    Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/vendor/holder.min.js"></script>