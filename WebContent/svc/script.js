/* 회원 관리 */
var emailerror = "이메일 형식에 맞지 않습니다";
var confirmerror = "아이디 중복확인 해 주세요";
var gendererror = "성별을 선택해 주세요";

var emailconfirmerror = "이메일 인증에 실패하였습니다."
var inputerror = "회원가입에 실패했습니다.\n잠시 후 다시 시도하세요.";
var loginiderror = "입력하신 아이디가 없습니다.\n아이디를 다시 확인하세요.";
var loginpasswderror = "입력하신 비밀번호가 다릅니다.\n비밀번호를 다시 확인하세요.";
var deleteerror = "회원탈퇴에 실패했습니다.\n잠시 후 다시 시도하세요.";

/* 게시물 관리 */
var trip_tileerror = "글제목을 입력해주세요";
var contenterror = "글내용을 입력해주세요";

var boarddeleteerror="게시물 삭제에 실패했습니다.\n잠시후 다시 시도하세요";
var photodeleteerror="사진 삭제에 실패했습니다.\n잠시후 다시 시도하세요";
var extensionerror="jpg, gif, png 확장자만 업로드 가능합니다.";
var sizeerror="이미지 용량은 5M이하만 가능합니다.";

var nocheckerror="다운로드 받을 사진을 선택하세요";
var locationerror="장소를 선택하세요";
var maxschedule=5;
var schedulesizeerror="일정은 최대 "+maxschedule+"개 입니다.";
var noscheduleerror="일정을 먼저 입력해 주세요";
var noplaceerror="장소를 먼저 검색해주세요";

var noplaceresult="장소를 찾을 수 없습니다.";

var filesize=5*1024*1024;


$(document).ready(function(){
	var tb_no=$('input[name=tb_no]').val();
	if(tb_no){
		commentList(tb_no); //페이지 로딩시 댓글 목록 출력 
	}
	var num=$('label[name=schedule]').length;//일정 개수 
	if(num){
	loadCal(num);
	}
});
	
function erroralert( msg ) {
	alert( msg );
	history.back();
}
//Initialize and add the map
var boardmarkers=[];
var boardmarker;
var boardmap;
var coord_lats=[];
var coord_lngs=[];
var country_codes=[];
//Map for board
function initMap() {//trip.jsp에서 좌표로 마커 표시
	var coord=$('div[name=coord]');
	var coord_lat=[];
	var coord_long=[];
	var centerLatSum=0;
	var centerLngSum=0;
	var location=[];
	 coord.each(function(i){
		 coord_lat[i]=parseFloat(coord.eq(i).find('input[name=coord_lat]').val());
		 coord_long[i]=parseFloat(coord.eq(i).find('input[name=coord_long]').val());
		 
		 centerLatSum+=coord_lat[i];
		 centerLngSum+=coord_long[i];
		//location
		 location[i]= {lat: coord_lat[i], lng: coord_long[i]};
	 });
	var centerLat = centerLatSum / coord.length ; 
    var centerLng = centerLngSum / coord.length ;   
	var center={lat:centerLat,lng:centerLng};
	  // The map, centered at allPlace
	boardmap = new google.maps.Map(
      document.getElementById('map'), {zoom: 3, center:center});
   for(var i=0;i<coord.length;i++){
	  addMarker(location[i],i,boardmap);  
   }
   if(isSameCountry()==1)boardmap.setZoom(6); 
}
//Adds a marker to the map.
function addMarker(location, num,boardmap) {
	num++;
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'location': location}, function(results, status) {
	   if (status === 'OK') {
	     if (results[0]) {
	    	 var address=results[0].formatted_address;
	       	 boardmarker = new google.maps.Marker({
	         position: location,
	         map: boardmap,
	         title:address,
	 	     label:''+num+'',
	         animation:google.maps.Animation.DROP,
	       });
	       	//input에 주소 붙이기   		
	       $('#address'+num+'').val(address);
	     } else {
	       window.alert(noplaceresult);
	     }
	   } else {
	   }
	});
}
function isSameCountry(){
	var result=1;
	num=$('div[name=coord]').length;
	for(var i=2;i<=num;i++){
		if($('#country1').val()!=$('#country'+i+'').val()){
			result=0;break;
		}
	}
	return result;
}
function focusMarker(order,lng,lat){
	boardmap.setZoom(12);
	boardmap.setCenter({lat:parseFloat(lat),lng:parseFloat(lng)});
}
//Map for writing
//지도 주소검색
var map;
function searchMap() {
	map = new google.maps.Map(document.getElementById('searchmap'), {
      zoom: 8,
      center: {lat: -34.397, lng: 150.644}
    });
    var geocoder = new google.maps.Geocoder();
    
    document.getElementById('submit').addEventListener('click', function() {
      geocodeAddress(geocoder, map);
    });
  }
//주소로 좌표 표시
var markers=[];
var marker;
function geocodeAddress(geocoder, resultsMap) {
  var address = document.getElementById('address').value;
  geocoder.geocode({'address': address}, function(results, status) {
    if (status === 'OK') {
      resultsMap.setCenter(results[0].geometry.location);

      //국가-jason 값 가져오기
      var country=results[0].address_components.filter(
      		function(component){
      			return component.types[0]=="country"
      		});
      var country_code=country[0].short_name;
      var country_name=country[0].long_name;
      var full_address=results[0].formatted_address;
      
      var searchmarker = new google.maps.Marker({
        map: resultsMap,
        position: results[0].geometry.location,
        title:full_address,
      });	
      
      //좌표 받기
      var lat=searchmarker.position.lat();//위도 
      var lng=searchmarker.position.lng();//경도
      
      var infowindow = new google.maps.InfoWindow;
       
      geocodeLatLng({lat: lat, lng: lng},geocoder, resultsMap,infowindow); 
      searchmarker.setMap(null);  
      showPlace(country_code,full_address,lat,lng);
    } else {
      alert(locationerror);
    }
  });
}
//좌표로 주소 띄우기(coordinate->address)
function geocodeLatLng(latlng,geocoder, map,infowindow) {
 geocoder.geocode({'location': latlng}, function(results, status) {
   if (status === 'OK') {
     if (results[0]) {
       map.setZoom(8);
       marker = new google.maps.Marker({
         position: latlng,
         map: map,
         title:results[0].formatted_address,
         animation:google.maps.Animation.DROP,
       });
       var num=$('#schedulenum').find('input[name=schedulenum]').val();
       updateMarker(marker,num);
     } else {
       window.alert(noPlaceresult);
     }
   } else {
   }
 });
}
//push marker to the array.
function updateMarker(marker,num){
	markers.push(marker);
	marker.setLabel(''+num+'');
	deleteMarkers(num);
}
// Removes the markers 
function deleteMarkers(num) {
	 for (var i = 0; i < markers.length-1; i++) {
		    markers[i].setMap(null);
		    }	
}
//trip view-button event-map
function showMap(){
	$('#albumTab').hide();
	$('#mapTab').show();
}
//trip view-button event-boardAlbum
function showAlbum(){
	$('#albumTab').show();
	$('#mapTab').hide();
}
//trip-album-nextPage
function next(start,size){
	start=start+size;
	albumPaging(start);
}
//trip-album-prePage
function previous(start,size){
	if(start>size)start=start-size;
	albumPaging(start);
}
//page넘기기
function albumPaging(start){
	var tb_no=$('input[name=tb_no]').val();
	var tab=1;

	var page="svc/boardAlbum.go?tb_no="+tb_no+"&start="+start+"&tab="+tab;
	$('#album').load(page);
}
//사진 지우기
function deletePhoto(tb_no,photo_id,start){
	$.ajax({
		type:'POST',
		url:'photoDel.go',
		data:{
			tb_no:tb_no,
			photo_id:photo_id
		},
		success:function(data){
			var page="svc/boardAlbum.go?tb_no="+tb_no+"&start="+start;
			$('#album').load(page);
		},
		error:function(e){
			alert(photodeleteerror);
		}
	});
}

//AJAX 또는 DOM
function passwordCheckFunction() {
	var userPassword1 = $('#userPassword1').val();
	var userPassword2 = $('#userPassword2').val();

	if (userPassword1 != userPassword2) {
		$('#passwordCheckMessage').html("비밀번호가 일치하지 않습니다");
	} else {
		$('#passwordCheckMessage').html(" ");
	}
}

//아이디
var idck = 0;
function IdCheck() {
	var user_id = $("#id_val").val();
	if (user_id) {
		$.ajax({
			async : true,
			type : 'POST',
			data : user_id,
			url : "idCheck.go",
			dataType : "json",
			success : function(data) {
				if (data.countId > 0) {
					$('#IdCheckMessagegg').html(
							"아이디가 존재합니다. 다른 아이디를 입력해주세요.");
				} else {
					$('#IdCheckMessagegg').html("사용가능한 아이디입니다.");
					idck = 1; //아이디 중복체크시 1이됨
				}
			},
			error : function(error) {
				alert("error : " + error);
			}
		});
	}
}

// 닉네임
var genck = 0;
function NameCheck() {
	var name_val = $("#name_val").val();
	if (name_val) {
		$.ajax({
			async : true,
			type : 'POST',
			data : name_val,
			url : "nameCheck.go",
			dataType : "json",
			/* contentType : "application/json", */
			success : function(data) {
				if (data.countName > 0) {
					$('#NameCheckMessage').html("닉네임이 존재합니다.")
				} else {
					$('#NameCheckMessage').html("사용가능한 닉네임입니다.")
					genck = 1; // 닉네임 중복체크시 1이됨
				}
			},
			error : function(error) {
				alert("error : " + error);
			}
		});
	}
}
//이메일
function gridClose(){
	self.close();
}
function EmailClose(){
	self.close();
}

function EmailCheck(email1){
    // 인증을 위해 새창으로 이동
	var url="emailCheck.go?email1="+email1
	open(url,"emailwindow", "statusbar=no, scrollbar=no, menubar=no,width=500, height=200" );
}


function EmailIdCheck(email2){
	var url="EmailIdd.go?email2="+email2
	open(url,"emailwindow", "statusbar=no, scrollbar=no, menubar=no,width=500, height=200" );
}

function EmailIdPasswd(email2){
	var url="EmailPasswdd.go?email2="+email2
	open(url,"emailwindow", "statusbar=no, scrollbar=no, menubar=no,width=500, height=200" );
}



function confirmeMail(authNum){
	var Email = $('#EmailVlaue').val(); //이메일 인증 창에서 내가 입력한 인증번호 값가져옴
    // 입력한 값이 없거나, 인증코드가 일지하지 않을 경우
	if(!Email || Email!= authNum){
		alert(emailconfirmerror);
		self.close();
    // 인증코드가 일치하는 경우
	}else if(Email==authNum){
		alert("인증완료");
		opener.document.inputform.confirm.value = 1;
		self.close();
	}
}

function inputcheck() {
	if (confirm("회원가입을 하시겠습니까?")) {
		if (idck == 0) {
			alert('아이디 중복체크를 해주세요');
			return false;
		} else if (genck == 0) {
			alert('닉네임 중복체크를 해주세요');
			return false;
		} else if (inputform.confirm.value == 0){
			alert('이메일 인증을해주세요');
			return false;
		} else if (inputform.gridCheck1.checked == false ){
			alert('약관을 확인해주세요');
			return false;
		} else {
			alert("회원가입을 축하합니다");
			$("#inputform").button();
		}
	}
}
//modify tripBoard-게시물 수정
function modifyBoard(tb_no){
	location.href="tripMod.go?tb_no="+tb_no;
}
//delete tripBoard-게시물 삭제
function deleteBoard(tb_no){
	location.href="tripDelPro.go?tb_no="+tb_no;
}
//사진 선택click->create checkbox
function selectPhotos(){
	$('input[name=check1]').show();
	$('#select').hide();
	$('#download').show();
}
//사진 다운로드 click->photo download
function downloadPhotos(){
	if($("input[name=check1]:checked").length==0){
		alert(nocheckerror);
	}else{
		//download구현->downloadPhoto.go로 이동 해서 작업
		var form=$('#downloadForm');
		 download(form);
		 endDownload();
		 form.html('');
	}
} 
//download가 끝난 후 처리
function endDownload(){
	var check1=$('input[name=check1]');
	$('#download').hide();	
	check1.prop("checked",false);
	check1.hide();
	$('#select').show();
}
function download(form){
	var check=$('input[name=check1]:checked');
	var photo_url;
	var input;
	check.each(function(i){//i=0 start
		var div = check.parent().eq(i);	
		photo_url=div.find('input[name=photo_url]').val();	
		input+='<input type="hidden" name="photo'+i+'" value="'+photo_url+'">';
	});
	input+='<input type="hidden" name="num" value="'+check.length+'">';	
	form.html(input);
	form.submit();
}
//board게시판 전체 사진 다운로드
function downloadAlbum(){
	var form=$('#downloadAlbumForm');
	form.submit();
	endDownload();
	form.html('');
}

//사진 업로드 click->photo upload
function uploadPhotos(){
	eventOccur(document.getElementById('file'),'click');
	var error=0;
	$('#file').change(function() {  
	    if (this.files) { 
	    	var form=document.getElementById('uploadForm');
	    	var file= document.getElementById("file");
	    	for(var i=0;i<file.files.length;i++){
		    	var fileName=$('#file').get(0).files[i].name;
		    	var size=$('#file').get(0).files[i].size;
		    	if(validation(fileName)){
		    		alert(extensionerror);
		    		error++;
		    		break;
		    	}
		    	if(sizeOver(size)){
		    		alert(sizeerror);
		    		error++;
		    		break;
		    	}
	    	}
	    	if(error==0)form.submit();
	    }
	});	
}
function eventOccur(evEle, evType){
	 if (evEle.fireEvent) {
		 evEle.fireEvent('on' + evType);
	 } else {
		 var mouseEvent = document.createEvent('MouseEvents');
		 mouseEvent.initEvent(evType, true, false);
		 var transCheck = evEle.dispatchEvent(mouseEvent);
		 if (!transCheck) {
			 //만약 이벤트에 실패했다면
			 console.log("click event fail");
		 }
	 }
}
//client-side extension validation
function validation(fileName) {
    fileName = fileName + "";
    var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
    var fileNameExtension = fileName.toLowerCase().substring(
            fileNameExtensionIndex, fileName.length);
    if (!((fileNameExtension === 'jpg')
            || (fileNameExtension === 'gif') || (fileNameExtension === 'png'))) {
        return true;
    } else {
        return false;
    }
}
//size validation
function sizeOver(size){
	if(size>filesize)return true;
	else return false;
}

////comment


function commentInsert(){ //댓글 등록 버튼 클릭시 
	 var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
	 CmtInsert(insertData); //Insert 함수호출(아래)
}

/////댓글 목록 
function commentList(tb_no){
	var SessionID=$("input[name=session]").val();
	$.ajax({
        url : 'commentSelect.go',
        type : 'get',
        data : {tb_no : tb_no},
        success : function(data){
            var commentView ='';
            var UserName = 'Ex-User';
            $.each(data, function(key, comment){ 
            	commentView += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
            	commentView += '</div class="commentInfo'+comment.c_id+'"><b>'+comment.user_name+'</b>';
            	if(SessionID == comment.user_id && comment.user_name != UserName){
            	commentView += '<a onclick="commentUpdate('+comment.c_id+',\''+comment.c_content+'\');"> 수정 </a>';
            	commentView += '<a onclick="commentDelete('+comment.c_id+');"> 삭제 </a>';
            	}
            	commentView += '<div class="commentContent"> <p>'+comment.c_content +'</p>';
            	commentView += '</div></div>'
            });
            $(".commentList").html(commentView);
        },
        error : function(error) {
            alert("댓글을 입력해주세요!");
        }
    });
	}


//댓글 등록
function CmtInsert(insertData){
	var tb_no=$("input[name=tb_no").val();
	if(commentInsertForm.c_content.value){
	$.ajax({
        url : 'commentInsert.go',
        type : 'post',
        data : insertData,
        success : function(data){
        	if(data == 1) {
        		/*오류메세지 작성*/
           }else{
        	   commentList(tb_no);
        	   $('[name=c_content]').val('');
           }
        },
    	error : function(error) {
        alert("error : " + error);
    }
    });
	}else{
		alert("댓글을 입력해주세요");
	}
	}
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(c_id, c_content){
    var commentModify ='';
    
    commentModify += '<div class="input-group">';
    commentModify += '<input type="text" class="form-control" name="c_content_'+c_id+'" value="'+c_content+'"/>';
    commentModify += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+c_id+');">수정</button> </span>';
    commentModify += '</div>';
    
    $('.commentContent'+c_id).html(commentModify);
    
}
 
//댓글 수정
function commentUpdateProc(c_id){
    var updateContent = $('input[name=c_content_'+c_id+']').val();
    var tb_no=$("input[name=tb_no").val();
    $.ajax({
        url : 'commentUpdate.go',
        type : 'post',
        data : {'c_content' : updateContent, 'c_id' : c_id},
        success : function(data){
            commentList(tb_no); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(c_id){
	var tb_no=$("input[name=tb_no]").val();
    $.ajax({
        url : 'commentDelete.go',
        type : 'post',
        data : {
        	c_id : c_id
        },
        success : function(data){
            commentList(tb_no); //댓글 삭제후 목록 출력 
        },
        error : function(error) {
            alert("error : " + error);
        }
    });
}

function loadMoreList(last_row) {
	$.ajax({
		type : 'get',
		data : {last_row : last_row},
		url : "loadMoreList.go",
		success : function(data) {
			if(data.length>0){
				var listForAppend="";
				var last_row_after=last_row;
				$.each(data, function(key, additionalList){
					last_row_after=last_row_after+1;
					
					listForAppend+='<div class="row">';
					listForAppend+=		'<div class="col-md-12">';
					listForAppend+=			'<div class="card flex-md-row mb-3 shadow-sm h-md-250">';
					listForAppend+=				'<div class="card-body d-flex flex-column align-items-start">';
					listForAppend+=					'<strong class="d-inline-block mb-2">';
																	if(additionalList.locs) {
																		$.each(additionalList.locs, function(key, locs) {
																			additionalList.locs;
																		});
																	}
					listForAppend+=					'</strong>';
					listForAppend+=					'<h3 class="mb-0">';
					listForAppend+=						'<a class="text-dark" href="trip.go?tb_no='+additionalList.tb_no+'">'+additionalList.tb_title+'</a>';
					listForAppend+=					'</h3>';
					listForAppend+=					'<div class="mb-1 text-muted text-right">';
					listForAppend+=						'<i><b>With</b></i>&nbsp;'+additionalList.user_id;
					listForAppend+=					'</div>';
					listForAppend+=					'<hr size="1px" color="black" noshade>';
					listForAppend+=					'<p class="card-text mb-auto">'+additionalList.tb_content+'</p>';
					listForAppend+=					'<hr style="width: 100%">';
					listForAppend+=					'<div class="d-flex justify-content-center">';
					listForAppend+=						'<div class="p-2">인원:'+additionalList.tb_m_num+'</div>&nbsp;';
					listForAppend+=						'<div class="p-2">조회수:'+additionalList.tb_v_count+'</div>';
					listForAppend+=						'<div class="p-2"><label class="btn btn-sm taglist">';
																		$.each(additionalList.tags, function(key, tags) {
																			additionalList.tags;
																		});
					listForAppend+=						'</label></div>';
					listForAppend+=					'</div>';
					listForAppend+=				'</div>';
					listForAppend+=			'</div>';
					listForAppend+=		'</div>';
					listForAppend+=	'</div>';
	            });
	            $("#board-list").append(listForAppend);
	            var newButton='<button type="button" class="btn btn-dark col-md-12" onclick="loadMoreList('+last_row_after+')">Load more...</button>';
	            $("#loading-button").html(newButton);
			} else {
				alert('더 이상 불러올 글이 없습니다.');
			}
		},
		error : function(error) {
			alert('글 불러오기에 실패했습니다.'+error);
		}
	});
}
//달력 불러오기 //순서대로 입력 받기
function loadCal(num){ 
	if(num==1){
		$("#start"+num+"").datepicker();
	}else if(num>1){
		var beforeStart=$('#start'+(num-1)+'').val();
		$("#start"+num+"").datepicker({
			minDate:beforeStart
		});
	}
	 $("#start"+num+"").datepicker("option", "onClose", function ( selectedDate ) {
	        $("#end"+num+"").datepicker( "option", "minDate", selectedDate );
	    });
	 
	$("#end"+num+"").datepicker();
	$("#end"+num+"").datepicker("option", "minDate", $("#start"+num+"").val()); 
	$("#end"+num+"").datepicker("option", "onClose", function () {	 
        $("#address").focus();
    });

} 
//add schedule-일정 추가//한글 처리
function addSchedule(num){
	var start=$('input[name=start'+num+']');
	var end=$('input[name=end'+num+']');
	var place=$('input[name=place'+num+']');
	if(!start.val()||!end.val()){//일정날짜가 없는 경우는 일정 추가 x
		alert(noscheduleerror);
		if(!start.val())start.focus();
		else end.focus();
	}else if(!place.val()){
		alert(noplaceerror);
		$('#address').focus();
	}else{
		$('#schedulediv').append(schedule);
		if(num>=maxschedule){
			alert(schedulesizeerror);
		}else{
			$('#address').val('');
			$('#btn_del'+num+'').hide();
			var schedule="";
			$('#btn'+num+'').hide();//btn 숨기기
			num++;
			schedule+= 	'<div id="schedule'+num+'" class="form-group row">';	  
			schedule+= 		'<label for="cal_date" class="col-2 col-form-label">일정 '+num+'</label>';         
			schedule+=      	'<input type="text" name="start'+num+'" id="start'+num+'" maxlength="10" value="yyyy-MM-dd" class="col-2" autocomplete="off"/>';
			schedule+=			'~';
			schedule+=			'<input type="text" name="end'+num+'" id="end'+num+'" maxlength="10" value="yyyy-MM-dd" class="col-2" autocomplete="off"/>&nbsp;&nbsp;';
			schedule+=			'<input name="place'+num+'" id="place'+num+'" type="text" readonly>';
			schedule+=		'<button id="btn'+num+'" class="btn_plus" type="button" onclick="addSchedule('+num+')">';
			schedule+=			'<img  class="btn_img" src="/Travelers/svc/img/addbutton.png">';
			schedule+=			'일정추가';
			schedule+=		'</button>';
			schedule+=		'<button id="btn_del'+num+'" class="btn_del" type="button" onclick="removeSchedule('+num+')">';
			schedule+=			'<img class="del_img" src="/Travelers/svc/img/trash.png"/>';
			schedule+=		'</button>';
			schedule+=		'<div id="coordinfo'+num+'">';
			schedule+=		'</div>';
			schedule+=	'</div>';
			$('#schedulediv').append(schedule);
			loadCal(num);
			
			if(num==maxschedule)$('#btn'+num+'').hide();
			var schedulenum='<input type="hidden" name="schedulenum" value="'+num+'">';
			$('#schedulenum').empty();
			$('#schedulenum').append(schedulenum);
		}		
	}
}
function removeSchedule(num){
	$('#address').val('');
	$('#schedule'+num+'').remove();
	num--;
	$('#btn'+num+'').show();//btn 보여주기
	$('#btn_del'+num+'').show();//btn 보여주기
	$('#schedulenum').empty();
	$("#schedulenum").val(num);//minus schedule num
}
//whenever searching address, update address-장소추가-검색할때 마다 장소갱신
function showPlace(country_code,full_address,lat,lng){
	var num=$('#schedulenum').find('input[name=schedulenum]').val();
	var placeinput=$('input[name=place'+num+']');
	var coordinfo=$('#coordinfo'+num+'');
	placeinput.val('');
	coordinfo.empty();
	
	var placeinfo=full_address;
	placeinput.val(placeinfo);
	var infoinput='<input name="country_code'+num+'" type="hidden" value="'+country_code+'"/>'
		infoinput+='<input name="lat'+num+'" type="hidden" value="'+lat+'"/>'
		infoinput+='<input name="lng'+num+'" type="hidden" value="'+lng+'"/>';
	coordinfo.append(infoinput);	
}
function writeCheck(){
	var num=$('#schedulenum').find('input[name=schedulenum]').val();
	var result=1;
	for(var i=1;i<=num;i++){
		var place=$('#place'+i+'');
		var start=$('#start'+i+'');
		var end=$('#end'+i+'');
		if(!start.val()){
			start.focus();result=0;break;
		}else if(!end.val()){
			end.focus();result=0;break;
		}else if(!place.val()){
			$('#address').focus();result=0;break;
		}
	}
	if(result==0)return false;
}
//글 수정
function tripmodcheck() {
	if (confirm("글수정을 하시겠습니까?")) {
	if( ! tripmodform.trip_title.value ) {
		alert( trip_titleerror );
		modifyform.trip_title.focus();
		return false;
	} else if( ! tripmodform.content.value ) {
		alert( contenterror );
		modifyform.content.focus();
		return false;
	} else {
		alert("작성을 완료하였습니다");
		$("#tripmodform").button();
	}
}
}

function goAdminPage(){
	location.href="adminTrip.go";
}

function attend(td_trip_id) {
	var user_id=trip_detail.user_id.value;
	var order=$('input[name=order_of_'+td_trip_id+']').val();
	var m_num=trip_detail.m_num.value;
	if(user_id) {
		$.ajax({
			type : 'post',
			data : {user_id : user_id,
						td_trip_id : td_trip_id},
			url : "memberAttend.go",
			success : function(data) {
				if(data) {
					var mList="";
					var count=data.length;
					mList+=count+'/'+m_num+', ';
					$.each(data, function(key, memberList) {
						mList+=memberList.user_name+' ';
		            });
					$('#trip_member_list_'+order).html(mList);
					var buttonDiv='<c:if test="${sessionScope.user_id ne null}">';
		            buttonDiv+=			'<button onclick="absent('+td_trip_id+')" class="btn btn-sm">불참</button>';
		            buttonDiv+=	'</c:if>';
		            $('#trip_button_'+td_trip_id).html(buttonDiv);
				} else {
					alert('참가하려는 일정에 이상이 있습니다.');
				}
			},
			error : function(error) {
				alert('멤버 추가에 실패했습니다.');
			}
		});
	}
}

function absent(td_trip_id) {
	var user_id=trip_detail.user_id.value;
	var order=$('input[name=order_of_'+td_trip_id+']').val();
	var m_num=trip_detail.m_num.value;
	if(user_id) {
		$.ajax({
			type : 'post',
			data :  {user_id : user_id,
						td_trip_id : td_trip_id},
			url : "memberAbsent.go",
			success : function(data) {
				if(data) {
					var mList="";
					var count=data.length;
					mList+=count+'/'+m_num+', ';
					$.each(data, function(key, memberList) {
						mList+=memberList.user_name+' ';
		            });
		            $('#trip_member_list_'+order).html(mList);
		            albumPaging(1);
		            var buttonDiv='<c:if test="${sessionScope.user_id ne null}">';
		            buttonDiv+=			'<button onclick="attend('+td_trip_id+')" class="btn btn-sm">참석</button>';
		            buttonDiv+=	'</c:if>';
		            $('#trip_button_'+td_trip_id).html(buttonDiv);
				} else {
					alert('빠지려는 일정에 이상이 있습니다.');
				}
			},
			error : function(error) {
				alert('불참석 처리에 실패했습니다.');
			}
		});
	}
}
