<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${project}script.js"></script>

<link rel="stylesheet" href="${project}googleAPI_style.css">
<script src="${project}googleAPI.js"></script>

<!-- Custom style for this template (Font API & Our UI)-->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">
<!-- Calendar API -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<c:set var="i" value="1" />

	<div class="container" style="width: 800px;">
		<form id="tripForm" class="form-horizontal" method="post" action="tripWritePro.go" onsubmit="return writeCheck()">
			<h4>${page_write}</h4>
			<hr size="1px" color="black">
			<div class="input-box">
				<input type="hidden" name="user_name" value="${user_name}">
				<input type="hidden" name="schedulenum" value="1">
				
				<div class="form-group row">
					<input type="text" name="trip_title" class="col-12 form-control form-control-lg" maxlength="30"
						placeholder="${trip_title}" autofocus required>
				</div>
				
				<div class="form-group row">
					<label for="trip_m_num" class="col-2 col-form-label">${trip_m_num}</label>
					<input type="number" name="trip_member_num${i}" class="col-2" min="1">
				</div>
				
				<div id="schedule" class="form-group row">
					<label for="cal_date" name="schedule" class="col-2 col-form-label">${trip_schedule} ${i}</label> 
					<input type="text" name="start${i}" id="start${i}" maxlength="10" value="yyyy-MM-dd" class="col-2" autofocus autocomplete="off" />
					 ~ 
					<input type="text" name="end${i}" id="end${i}" maxlength="10" value="yyyy-MM-dd" class="col-2" autofocus autocomplete="off" /> 
					&nbsp;&nbsp; 
					<input name="place${i}" id="place${i}" type="text" readonly="readonly" placeholder="${trip_location}"> 
					
					<button id="btn${i}" class="btn_plus" type="button" onclick="addSchedule(${i})">
						<img class="btn_img" src="${project}img/addbutton.png">${btn_add_trip}
					</button>
					<div id="coordinfo${i}">
					</div>
				</div>
				<div id="schedulediv"></div>
				<div class="form-group row">
					<label for="trip_talklink" class="col-2 col-form-label">${trip_talklink}</label>
					<input type="text" name="trip_talklink" class="col-10">
				</div>

				<div class="form-group row">
					<%-- 
					<label for="trip_location" class="col-2 col-form-label">${trip_location}</label>
					<div id="floating-panel" class="col-10">
						<input id="address" type="text" autocomplete=off /> 
						<input id="addSubmit" type="button" class="btn btn-dark btn-sm" value="${btn_search}" />
					</div>
					 --%>
					<!--/////////// google MAP API 파트 ///////////-->
					<div class="pac-card" id="pac-card">
						<div id="pac-container">
							<input id="pac-input" type="text" placeholder="Enter a location">
							<div id="strict-bounds-selector" class="pac-controls">
								<label for="use-strict-bounds">Strict Bounds</label> 
								<input type="checkbox" id="use-strict-bounds" value="">
							</div>
						</div>
					</div>
					<div id="map"></div>
					<div id="infowindow-content">
						<img src="" width="16" height="16" id="place-icon"> 
						<span id="place-name" class="title"></span><br> 
						<span id="place-address"></span><br> 
						<span id="place-location"></span><br>
					</div>
					<!--/////////// google MAP API 파트 ///////////-->
				</div>
				<br>
				<hr>

				<div class="form-group row">
					<textarea name="content" class="col-12" rows="10" maxlength="1300"
						placeholder="${trip_entercontent}" autofocus required></textarea>
				</div>
				<hr>

				<div class="form-group row">
					<label for="trip_tag" class="col-2 col-form-label">${trip_tag}</label>
					
					<c:if test="${styleTags.size() ne 0}">
					<c:forEach var="tagCnt" items="${styleTags}">
					<label class="btn btn-secondary"> 
					<input type="checkbox" name="tag" value="${tagCnt.tag_id}">${tagCnt.tag_value}
					</label>
					</c:forEach>
					</c:if>
					
				</div>

				<input class="btn btn-dark btn-sm" type="submit" value="${trip_write}"> 
				<input class="btn btn-dark btn-sm" type="button" value="${btn_list}" onclick="location='tripList.go'">
			</div>
			<div id="schedulenum">
				
			</div>
			<!-- input box -->
		</form>
	</div>
	<!-- container -->
</body>

<!-- Map Search API -->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAoC3t0xr7YieFKGY9nIAH366PT6JyaiEg&libraries=places&callback=initMap"
	async defer></script>
</html>