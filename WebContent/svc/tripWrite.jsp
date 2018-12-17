<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="./jquery-3.3.1.js"></script>
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
<script src="./jquery.serialize-object.min.js"></script>
</head>
<body>
<%-- 	
<c:set var="i" value="1" />

	<div class="container" style="width: 800px;">
		<form id="tripForm" class="form-horizontal" method="post" action="tripWritePro.go" onsubmit="return writeCheck()">
			<h4>${page_write}</h4>
			<hr size="1px" color="black">
			<div class="input-box"> <!-- input-box 클래스는 css 먹일 때 쓰이는 용도로 확인 -->
				<input type="hidden" name="user_name" value="${user_name}">
				<div class="form-group row">
					<input type="text" name="trip_title" class="col-12 form-control form-control-lg" maxlength="30"
						placeholder="${trip_title}" autofocus required>
				</div>
				
				<div class="form-group row">
					<label for="trip_m_num" class="col-2 col-form-label">${trip_m_num}</label>
					<input type="number" name="trip_member_num${i}" class="col-2" min="1">
				</div>
				<div id="schedule" class="form-group row">
					<input type="hidden" name="num_counter" value="${i}">
					<label for="cal_date" name="schedule" class="col-2 col-form-label">${trip_schedule} ${i}</label> 
					<input type="text" name="start${i}" id="start${i}" maxlength="10" class="col-2" autofocus autocomplete="off" />
					 ~ 
					<input type="text" name="end${i}" id="end${i}" maxlength="10" class="col-2" autofocus autocomplete="off" /> 
					&nbsp;&nbsp; 
					<input name="place${i}" id="place${i}" type="text" readonly="readonly" placeholder="${trip_location}"> 
					
					<button id="btn${i}" class="btn_plus" type="button" onclick="addSchedule(${i})">
						<i class="fas fa-plus-circle"></i>
						<i class="fas fa-minus-circle"></i>
						${btn_add_trip}
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
					
					<label for="trip_location" class="col-2 col-form-label">${trip_location}</label>
					<div id="floating-panel" class="col-10">
						<input id="address" type="text" autocomplete=off /> 
						<input id="addSubmit" type="button" class="btn btn-dark btn-sm" value="${btn_search}" />
					</div>
					
					<!--/////////// google MAP API 파트 ///////////-->
					<div class="pac-card" id="pac-card">
						<div id="pac-container">
							<input id="pac-input" type="text" placeholder="Enter a location">
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
				<input type="hidden" name="schedulenum" value="1">
			</div>
			<!-- input box -->
		</form>
	</div>
	<!-- container --> 
--%>
<%-- tripWrite submit 시 나오는 자료구조
	board.tag 와
	trip 은 배열 형태로 늘어나서 붙는다.
{
	"board":{
		"title":"title",
		"content":"content",
		"contact":"contact",
		"tag":["tag1","tag2","tag3", ...]
	},
	"trip":[{
		"member_count":"member_count",
		"coord_id":{
			"place_name":"place_name",
			"country_code":"country_code",
			"long":"long",
			"lat":"lat"
		},
		"start_date":"start_date",
		"end_date":"end_date",
		"trip_order":"trip_order"
	}]
}
 --%>
	<div class="container" style="width: 800px;">
		<form id="tripForm" class="form" method="post" action="tripWritePro.go">	<!-- TODO : onsubmit에 writeCheck() 일단 무시함. 새로 제작 필요 -->
			<div class="input-box">	
				<div class="board_part">
					<input type="hidden" name="board[]">
					<div class="form-group row">
						<input type="text" class="board_title col-12 form-control form-control-lg" name="board[title]" placeholder="제목" maxlength="30">
					</div>
				</div>
				<div class="trip_part">
					<div class="trip_chips">
						<input type="hidden" name="trip[]">
						<div class="form-group row">
							<label for="trip_member_count" class="col-2 col-form-label">참여인원</label>
							<input type="number" class="trip_member_count col-2" name="trip[][member_count]" min="1" placeholder="n명">
						</div>
						<div class="form-group row">
							<label for="trip_start_date" class="col-2 col-form-label">일정</label>
							<input type="text" class="trip_start_date" name="trip[][start_date]" placeholder="시작일">
							~
							<input type="text" class="trip_end_date" name="trip[][end_date]" placeholder="종료일">
							
						</div>
							<input type="hidden" name="trip[][coord_id]">
								<input type="hidden" class="trip_place_name" name="trip[][coord_id][place_name]">
								<input type="hidden" class="trip_country_code" name="trip[][coord_id][country_code]">
								<input type="hidden" class="trip_long" name="trip[][coord_id][long]">
								<input type="hidden" class="trip_lat" name="trip[][coord_id][lat]">
							<input type="hidden" class="trip_order" name="trip[][trip_order]" value="1">
					</div>
					<button id="add_btn">추가</button>
					<button id="del_btn">삭제</button>
				</div>

				<div class="googleMap">
					<div class="pac-card" id="pac-card">
						<input id="pac-input" type="text" placeholder="장소를 입력하세요">
					</div>
					<div id="map"></div>
					<div id="infowindow-content">
						<span id="place-name" class="title"></span><br> 
						<span id="place-address"></span><br> 
						<span id="place-location"></span><br>
					</div>
				</div>

				<div class="board_part">	
					<div class="form-group row">
						<label for="board_content" class="col-2 col-form-label">글내용</label>
						<textarea class="board_content" name="board[content]" placeholder="내용을 입력하세요">
						</textarea>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">여행 태그</label>
						<c:if test="${styleTags.size() ne 0}">
							<c:forEach var="tagCnt" items="${styleTags}">
							<label class="btn btn-secondary"> 
								<input type="checkbox" class="board_tag" name="board[tag][]" value="${tagCnt.tag_id}">${tagCnt.tag_value}
							</label>
							</c:forEach>
						</c:if>
					</div>
					<div class="form-group row">
						<label for="board_contact" class="col-2 col-form-label">대화링크</label>
						<input type="text" class="board_contact col-10" name="board[contact]">
					</div>
				</div>
				<input class="btn btn-dark btn-sm" type="submit" value="${trip_write}"> 
				<input class="btn btn-dark btn-sm" type="button" value="${btn_list}" onclick="location='tripList.go'">
			</div>
		</form>
		
	</div>
	
	
	
	
</body>
	var start_end = '.trip_start_date, .trip_end_date';
	$(document).on('focus', start_end, function(){
		$(this).datepicker({
			minDate: 0,
			changeMonth: true,
			changeYear: true,
			showOtherMonths: true,
			selectOtherMonths: true
		});
	})
		
	// add_btn /  trip 추가
	$('#add_btn').on('click', function(){
		var $last = $('.trip_chips:last');
		var $clone = $last.clone(false);
		$last.after($clone);
		$('.trip_chips:last input, .trip_chips:last select').val('');
		$clone.find('input.trip_start_date')
			.removeClass('hasDatepicker')
			.removeData('datepicker')
			.attr('id', 'change_id' + Math.random())
			.unbind()
			.datepicker();
		$clone.find('input.trip_end_date')
			.removeClass('hasDatepicker')
			.removeData('datepicker')
			.attr('id', 'change_id' + Math.random())
			.unbind()
			.datepicker();
		trip_cnt += 1;		// trip_order 값 수정
		$('.trip_order')
			.last()
			.val(trip_cnt);
	});
	// del_btn / trip 삭제
	$('#del_btn').on('click', function(){
		$('.trip_chips')
			.last()
			.remove();
		
		trip_cnt -= 1;		// del 에서는 add 에서처럼 trip_order 값 수정
	});










