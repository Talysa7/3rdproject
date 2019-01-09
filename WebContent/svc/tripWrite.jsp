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

<link rel="stylesheet" href="${project}googleAPI_style.css">

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

<!-- Map Search API -->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAoC3t0xr7YieFKGY9nIAH366PT6JyaiEg&libraries=places">
</script>
</head>
<body>
<%-- tripWrite submit 시 나오는 자료구조
	board.tag 와
	trip 은 배열 형태로, input 태그가 늘어나면 데이터 역시 자동으로 늘어나며 붙는다.
	cf) view 의 형태 때문에 위치가 제각각 떨어져 있으나, 기능상 문제는 없음.
		그러나 html 태그만 봐서는 한번에 알아보기 힘들기 때문에 참고할 수 있는 자료구조를 따로 첨부함.
	
{
	"boardDto":{
		"user_id":"user_id"
		"board_title":"board_title",
		"board_content":"board_content",
		"board_contact":"board_contact",
		"tagList":["tag_id1","tag_id2","tag_id3", ...]
	},
	"tripDtoList":[{
		"trip_member_count":"trip_member_count",
		"coordList":{
			"coord_name":"coord_name",
			"country_code":"country_code",
			"coord_long":"coord_long",
			"coord_lat":"coord_lat"
		},
		"start_date":"start_date",
		"end_date":"end_date",
		"coord_order":"coord_order"
	}]
}
 --%>
	<div class="container" style="width: 800px;">
		<form id="tripForm" class="form" method="POST" action="tripWritePro.go">	<!-- TODO : onsubmit에 writeCheck() 일단 무시함. 새로 제작 필요 -->
			<div class="input-box">	
				<div class="board_part">
					<input type="hidden" name="boardDto[]">
					<input type="hidden" name="boardDto[user_id]" value="${user_id}">
					<div class="form-group row">
						<input type="text" class="board_title col-12 form-control form-control-lg" name="boardDto[board_title]" placeholder="제목" maxlength="30">
					</div>
				</div>
				<div class="trip_part">
					<div class="trip_chips">
						<input type="hidden" name="tripDtoList[]">
						<div class="form-group row">
							<label for="trip_member_count" class="col-2 col-form-label">참여인원</label>
							<input type="number" class="trip_member_count col-2" name="tripDtoList[][trip_member_count]" min="1" placeholder="n명">
						</div>
						<div class="form-group row">
							<label for="trip_start_date" class="col-2 col-form-label">일정</label>
							<input type="text" class="trip_start_date" name="tripDtoList[][start_date]" placeholder="시작일">
							~
							<input type="text" class="trip_end_date" name="tripDtoList[][end_date]" placeholder="종료일">
							
						</div>
							
						<div class="form-group row">
							<label for="pac-input" class="col-2 col-form-label">장소</label>
							<input type="text" id="pac-input" class="pac-input" placeholder="장소를 입력하세요">
							<input type="hidden" name="tripDtoList[][coordList]">
								<input type="hidden" class="trip_coord_name" name="tripDtoList[][coordList][coord_name]">
								<input type="hidden" class="trip_country_code" name="tripDtoList[][coordList][country_code]">
								<input type="hidden" class="trip_long" name="tripDtoList[][coordList][coord_long]">
								<input type="hidden" class="trip_lat" name="tripDtoList[][coordList][coord_lat]">
							<input type="hidden" class="coord_order" name="tripDtoList[][coord_order]" value="1">
						</div>
					</div>
					<button id="add_btn">추가</button>
					<button id="del_btn">삭제</button>
				</div>

				<div class="googleMap">
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
						<textarea class="board_content" name="boardDto[board_content]" placeholder="내용을 입력하세요">
						</textarea>
					</div>
					<div class="form-group row">
						<label class="col-2 col-form-label">여행 태그</label>
						<c:if test="${styleTags.size() ne 0}">
							<c:forEach var="tagCnt" items="${styleTags}">
							<label class="btn btn-secondary"> 
								<input type="checkbox" class="board_tag" name="boardDto[tagList][]" value="${tagCnt.tag_id}">${tagCnt.tag_value}
							</label>
							</c:forEach>
						</c:if>
					</div>
					<div class="form-group row">
						<label for="board_contact" class="col-2 col-form-label">대화링크</label>
						<input type="text" class="board_contact col-10" name="boardDto[board_contact]">
					</div>
				</div>
				<input class="btn btn-dark btn-sm" type="button" id="submit" value="${trip_write}"> 
				<input class="btn btn-dark btn-sm" type="button" value="${btn_list}" onclick="location='tripList.go'">
			</div>
		</form>
		
	</div>
	
	
	
	
</body>
<script type="text/javascript">
	var infowindowContent = document.getElementById('infowindow-content');
	var zoom = 13;
	var after_zoom = 17;
	var map_opt = {
			center : {
				lat : 37.4864,
				lng : 127.0207
			},
			zoom : zoom
		};
	var map = new google.maps.Map(document.getElementById('map'), map_opt);
	var input = document.getElementsByClassName('pac-input').item(0);
	setAutoComplete(input, map);
	var infowindow = new google.maps.InfoWindow();
	infowindow.setContent(infowindowContent);

	var marker = new google.maps.Marker({
		map : map,
		anchorPoint : new google.maps.Point(0, -29)
	});

function setAutoComplete( item, map ){
	var autocomplete = new google.maps.places.Autocomplete(item);
	
	autocomplete.bindTo('bounds', map);
//	autocomplete.setFields([ 'address_components', 'formatted_phone_number', 'geometry', 'icon', 'name', 'photos', 'vicinity' ]);
	autocomplete.addListener('place_changed', function() {
		infowindow.close();
		marker.setVisible(false);
		var place = autocomplete.getPlace();
		if (!place.geometry) {
			window.alert("찾으시는 '"+ place.name + "' 에 대한 결과가 없습니다 \n"
			+ "다른 검색어를 입력 해주세요");
			return;
		}

		if (place.geometry.viewport) {
			map.fitBounds(place.geometry.viewport);
		} else {
			map.setCenter(place.geometry.location);
			map.setZoom(after_zoom); 
		}
		marker.setPosition(place.geometry.location);
		marker.setVisible(true);

		var address = '';
		if (place.address_components) {
			address = [
				(place.address_components[0]
					&& place.address_components[0].short_name || ''),
				(place.address_components[1]
					&& place.address_components[1].short_name || ''),
				(place.address_components[2]
					&& place.address_components[2].short_name || '') ]
				.join(' ');
		}
		var addr_comp = place.address_components;
		var lng = place.geometry.location.lng().toString();
		var lat = place.geometry.location.lat().toString();
		var country_code;
		var name = place.name;
		lng = lng.substring( 0, (parseInt(lng.indexOf('.')) + 7) );
		lat = lat.substring( 0, (parseInt(lat.indexOf('.')) + 7) );

		infowindowContent.children['place-name'].textContent = name;
//		infowindowContent.children['place-address'].textContent = address;
//		infowindowContent.children['place-location'].textContent = 
//			place.geometry.location.lat();
		
		for( var i in addr_comp){
			if(addr_comp[i].types[0] == 'country'){
				$(item).siblings('.trip_country_code').val(addr_comp[i].short_name);
				country_code = addr_comp[i].short_name;
			} 
		}
		var coordinate_json = {
			coord_name : name,
			coord_long : lng,
			coord_lat : lat,
			country_code : country_code
		}
		$.ajax({
			url: 'setCoordinate.go',
			type: 'POST',
			data: JSON.stringify(coordinate_json),
			dataType: 'json',
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(data){
				console.log('성공'+ data.result);
			},
			error: function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
		});
		console.log(coordinate_json);
		$(item).siblings('.trip_coord_name').val(place.name);
		$(item).siblings('.trip_long').val(lng);
		$(item).siblings('.trip_lat').val(lat);
		infowindow.open(map, marker);
	});
}


$(function(){
	var trip_cnt = 1;
	var start_end = '.trip_start_date, .trip_end_date';
	// datepicker 세팅
	var dp_opt = {
		minDate: 0,
		changeMonth: true,
		changeYear: true,
		showOtherMonths: true,
		selectOtherMonths: true,
		dateFormat: "yy-mm-dd"
	}
	$(document).on('focus', start_end, function(){
		$(this).datepicker(dp_opt);
	})
	
	
	// add_btn /  trip 추가
	$('#add_btn').on('click', function(event){
		
		var $last = $('.trip_chips:last');
		var $clone = $last.clone(true);
		$last.after($clone);
		$('.trip_chips:last input, .trip_chips:last select').val('');
		$clone.find('input.trip_start_date')
			.removeClass('hasDatepicker')
			.removeData('datepicker')
			.attr('id', 'change_id' + Math.random())
			.unbind()
			.datepicker(dp_opt);
		$clone.find('input.trip_end_date')
			.removeClass('hasDatepicker')
			.removeData('datepicker')
			.attr('id', 'change_id' + Math.random())
			.unbind()
			.datepicker(dp_opt);

		input = document.getElementsByClassName('pac-input').item(trip_cnt);
		setAutoComplete(input, map);
		
		trip_cnt += 1;		// coord_order 값 수정
		$('.coord_order')
			.last()
			.val(trip_cnt);
		return false;
	});
	// del_btn / trip 삭제
	$('#del_btn').on('click', function(){
		$('.trip_chips')
			.last()
			.remove();
		trip_cnt -= 1;		// del 에서는 add 에서처럼 coord_order 값 수정
		return false;
	});

	// 데이터 submit 
	$('#submit').on('click', function(){
//		event.preventDefault();		// 폼 처리를 완전히 jQuery 안에서 한다면, 페이지 다시 불러오기 방지
		var data_parse = JSON.parse($('form#tripForm').serializeJSON());
		console.log(data_parse);
		$.ajax({
			url: 'tripWritePro.go',
			type: 'POST',
			data: JSON.stringify(data_parse),
			dataType: 'json',
			contentType: 'application/json',
			mimeType: 'application/json',
			success: function(data){
				window.location.replace("tripList.go");
			},
			error: function(request, status, error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
		})
	});
});
</script>
</html>