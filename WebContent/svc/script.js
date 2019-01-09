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
var photodeletesuccess="사진을 삭제했습니다.";
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
	var board_no=$('input[name=board_no]').val();
	if(board_no){
		commentList(board_no); //페이지 로딩시 댓글 목록 출력 
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


function goback(){
	history.back();
}

//////////////////////////////////////////////////////////////// Google Map 관련 ////////////////////////////////////////////////////////////////

// Initialize and add the map
var boardmarkers=[];
var boardmarker;
var boardmap;
var coord_lats=[];
var coord_lngs=[];
var country_codes=[];
var markers=[];
var marker;
var map;

//Map for board
// function initMap() {//trip.jsp에서 좌표로 마커 표시
// 	var coord=$('div[name=coord]');
// 	var coord_lat=[];
// 	var coord_long=[];
// 	var centerLatSum=0;
// 	var centerLngSum=0;
// 	var location=[];
// 		coord.each(function(i){
// 		coord_lat[i]=parseFloat(coord.eq(i).find('input[name=coord_lat]').val());
// 		coord_long[i]=parseFloat(coord.eq(i).find('input[name=coord_long]').val());
		
// 		centerLatSum+=coord_lat[i];
// 		centerLngSum+=coord_long[i];
// 		//location
// 		location[i]= {lat: coord_lat[i], lng: coord_long[i]};
// 	});
// 	var centerLat = centerLatSum / coord.length ; 

//    var centerLng = centerLngSum / coord.length ;   
// 	var center={lat:centerLat,lng:centerLng};
// 	  // The map, centered at allPlace
// 	boardmap = new google.maps.Map(
//      document.getElementById('map'), {zoom: 3, center:center});
//   for(var i=0;i<coord.length;i++){
// 	  addMarker(location[i],i,boardmap);  
//   }
//   if(isSameCountry()==1)boardmap.setZoom(6); 
// }
//Adds a marker to the map.
// function addMarker(location, num,boardmap) {
// 	num++;
// 	var geocoder = new google.maps.Geocoder();
// 	geocoder.geocode({'location': location}, function(results, status) {
// 	   if (status === 'OK') {
// 	     if (results[0]) {
// 	    	 var address=results[0].formatted_address;
// 	       	 boardmarker = new google.maps.Marker({
// 	         position: location,
// 	         map: boardmap,
// 	         title:address,
// 	 	     label:''+num+'',
// 	         animation:google.maps.Animation.DROP,
// 	       });
// 	       	//input에 주소 붙이기   		
// 	       $('#address'+num+'').val(address);
// 	     } else {
// 	       window.alert(noplaceresult);
// 	     }
// 	   } else {
// 	   }
// 	});
// }
// function isSameCountry(){
// 	var result=1;
// 	num=$('div[name=coord]').length;
// 	for(var i=2;i<=num;i++){
// 		if($('#country1').val()!=$('#country'+i+'').val()){
// 			result=0;break;
// 		}
// 	}
// 	return result;
// }
// function focusMarker(order,lng,lat){
// 	boardmap.setZoom(12);
// 	boardmap.setCenter({lat:parseFloat(lat),lng:parseFloat(lng)});
// }
// //Map for writing
// //지도 주소검색

// function searchMap() {
// 	map = new google.maps.Map(document.getElementById('searchmap'), {
// 		zoom: 8,
// 		center: {lat: -34.397, lng: 150.644}
//    });
//    var geocoder = new google.maps.Geocoder();
   
//    document.getElementById('addSubmit').addEventListener('click', function() {
//      geocodeAddress(geocoder, map);
//    });
//  }
// //주소로 좌표 표시
// function geocodeAddress(geocoder, resultsMap) {
//  var address = document.getElementById('address').value;
//  geocoder.geocode({'address': address}, function(results, status) {
//    if (status === 'OK') {
// 		for(var i =0; i<results.length; i++){
// 		alert(results[i]);
// 		}
//      resultsMap.setCenter(results[0].geometry.location);

//      //국가-jason 값 가져오기
//      var country=results[0].address_components.filter(
//      		function(component){
//      			return component.types[0]=="country"
//      		});
//      var country_code=country[0].short_name;
//      var country_name=country[0].long_name;
//      var full_address=results[0].formatted_address;
     
//      var searchmarker = new google.maps.Marker({
//        map: resultsMap,
//        position: results[0].geometry.location,
//        title:full_address,
//      });	
     
//      //좌표 받기
//      var lat=searchmarker.position.lat();//위도 
//      var lng=searchmarker.position.lng();//경도
     
//      var infowindow = new google.maps.InfoWindow;
      
//      geocodeLatLng({lat: lat, lng: lng},geocoder, resultsMap,infowindow); 
//      searchmarker.setMap(null);  
//      showPlace(country_code,full_address,lat,lng);
//    } else {
//      alert(locationerror);
//    }
//  });
// }
// //좌표로 주소 띄우기(coordinate->address)
// function geocodeLatLng(latlng, geocoder, map, infowindow) {
// geocoder.geocode({'location': latlng}, function(results, status) {
//   if (status === 'OK') {
//     if (results[0]) {
//       map.setZoom(8);
//       marker = new google.maps.Marker({
//         position: latlng,
//         map: map,
//         title:results[0].formatted_address,
//         animation:google.maps.Animation.DROP,
//       });
//        updateMarker(marker,num);
//     } else {
//       window.alert(noPlaceresult);
//     }
//   } else {
//   }
// });
// }
// //push marker to the array.
// function updateMarker(marker,num){
// 	markers.push(marker);
// 	marker.setLabel(''+num+'');
// 	deleteMarkers(num);
// }
// // Removes the markers 
// function deleteMarkers(num) {
// 	for (var i = 0; i < markers.length-1; i++) {
// 		markers[i].setMap(null);
// 	}	
// }
//////////////////////////////////////////////////////////////// Google Map 끝 ////////////////////////////////////////////////////////////////
//trip view-button event-map
function showMap(trip_id){
	$('#albumTab_'+trip_id).hide();
	$('#mapTab_'+trip_id).show();
}
//trip view-button event-boardAlbum
function showAlbum(trip_id){
	$('#mapTab_'+trip_id).hide();
	$('#albumTab_'+trip_id).show();
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
	var board_no=$('input[name=board_no]').val();
	var tab=1;

	var page="svc/boardAlbum.go?board_no="+board_no+"&start="+start+"&tab="+tab;
	$('#album').load(page);
}
//사진 지우기
function deletePhoto(board_no,trip_id,photo_id){
	$.ajax({
		type:'POST',
		url:'photoDel.go',
		data:{
			board_no:board_no,
			trip_id:trip_id,
			photo_id:photo_id
		},
		success:function(data){
			var page="svc/boardAlbum.go?board_no="+board_no+"&trip_id="+trip_id;
			$('#albumTab_'+trip_id).load(page);
			alert(photodeletesuccess);
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
			url : "checkId.go",
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
	var user_name = $("#name_val").val();
	var nickck = $("#nickck").val();
	if (user_name) {
		$.ajax({
			async : true,
			type : 'POST',
			data : user_name,
			url : "nameCheck.go",
			dataType : "json",
			/* contentType : "application/json", */
			success : function(data) {
				if (data.countName > 0) {
					$('#NameCheckMessage').html("닉네임이 존재합니다.")
					nickck = 1;
				} else {
					$('#NameCheckMessage').html("사용가능한 닉네임입니다.")
					genck = 1; // 닉네임 중복체크시 1이됨
					nickck=0;
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
	var etype = $("#eType").val();
	var url="email.go?email1="+email1+"&eType="+etype
	open(url,"emailwindow", "statusbar=no, scrollbar=no, menubar=no,width=500, height=200" );
}



function confirmeMail(authNum){
	var Email = $('#EmailValue').val(); //이메일 인증 창에서 내가 입력한 인증번호 값가져옴
    // 입력한 값이 없거나, 인증코드가 일지하지 않을 경우
	if(!Email || Email!= authNum){
		alert(emailconfirmerror);
    // 인증코드가 일치하는 경우
	}else if(Email==authNum){
		alert("인증완료");
		opener.document.inputform.confirm.value = 1;
		self.close();
	}
}
//userModify 폼 
function userModCheck(){
	var nickck = $("#nickck").val();
	var pass1 = $("#userPassword1").val();
	var pass2 = $("#userPassword2").val();
	var chk = $("input:checkbox[name='tags']").is(":checked");
	if(genck == 0){
		alert('닉네임 중복체크를 해주세요');
		return false;
	}else if(pass1 != pass2){
		alert('비밀번호가 일치하지 않습니다');
		return false;
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
//		} else if (inputform.confirm.value == 0){
//			alert('이메일 인증을해주세요');
//			return false;
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
function modifyBoard(board_no){
	location.href="tripMod.go?board_no="+board_no;
}
//delete tripBoard-게시물 삭제
function deleteBoard(board_no){
	location.href="tripDelPro.go?board_no="+board_no;
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
	alert("entered");
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

/////댓글 목록 
function commentList(board_no){
	var SessionID=$("input[name=user_id]").val();
	$.ajax({
        url : 'commentSelect.go',
        type : 'get',
        data : {board_no : board_no},
        success : function(data){
            var commentView ='';
            var UserName = 'Ex-User';
            $.each(data, function(key, comment){ 
            	commentView += '<div class="commentArea">';
            	commentView += '<div class="commentInfo'+comment.comment_id+'"><b>'+comment.user_name+'</b>';
            	if(SessionID == comment.user_id && comment.user_name != UserName) {
            		commentView += '<a style="float:right;padding-left:5px" onclick="commentDelete('+comment.comment_id+')">삭제 </a>';
            		commentView += '<a style="float:right;padding-left:5px" onclick="commentUpdate('+comment.comment_id+',\''+comment.comment_content+'\')">수정 </a>';
            	}
            	commentView += '<div id="commentContent'+comment.comment_id+'"> <p>'+comment.comment_content +'</p>';
            	commentView += '<hr style="width: 100%"></div></div></div>';
            });
            $("#commentList").html(commentView);
        },
        error : function(error) {
            alert("댓글을 입력해주세요!");
        }
    });
}

function commentInsert(){ //댓글 등록 버튼 클릭시 
	var user_id=$('[name=commentUserId]').val();
	var c_content=$('[name=c_content]').val();
	var board_no=$('[name=commentBoardNo]').val();
	if(commentInsertForm.c_content.value){
		$.ajax({
	        url : 'commentInsert.go',
	        type : 'post',
	        data : {
	        	user_id:user_id,
	        	c_content:c_content,
	        	board_no:board_no
	        },
	        success : function(data){
	        	if(data == 1) {
	        		/*오류메세지 작성*/
		       } else {
	        	   commentList(board_no);
	        	   $('[name=c_content]').val('');
		       }
		    },
		    error : function(error) {
		    	alert("error : " + error);
		    }
	    });
	} else {
		alert("댓글을 입력해주세요");
	}
}

//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(comment_id, comment_content){
    var commentModify ='';
    commentModify += '<div class="input-group">';
    commentModify += '<input type="text" class="form-control" name="comment_content_'+comment_id+'" value="'+comment_content+'"/>';
    commentModify += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdatePro('+comment_id+');">수정</button> </span>';
    commentModify += '</div>';
    
    $('#commentContent'+comment_id).html(commentModify);
    
}
 
//댓글 수정
function commentUpdatePro(comment_id){
    var updateContent = $('input[name=comment_content_'+comment_id+']').val();
    var board_no=$("input[name=board_no]").val();
    $.ajax({
        url : 'commentUpdate.go',
        type : 'post',
        data : {
        			comment_content:updateContent,
        			comment_id: comment_id
        		},
        success : function(data){
            commentList(board_no); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(comment_id){
	var board_no=$("input[name=board_no]").val();
    $.ajax({
        url : 'commentDelete.go',
        type : 'post',
        data : {
        	comment_id : comment_id
        },
        success : function(data){
            commentList(board_no); //댓글 삭제후 목록 출력 
        },
        error : function(error) {
            alert("error : " + error);
        }
    });
}
function load(i){
	$("#board-list").load("advanceSearch.go?pageNum="+i);
}
function loadList(i){
	var next_row = $('input[name=next_row]').val();
	$.ajax({
		type : 'get',
		data : {pageNum : i},
		url : "advanceSearch.go?pageNum=${pageNum}",
		success : function(data) {
			if(data){				
				var next_after = i+5;
	     
				var list="";
				list+='<c:forEach var="post" items="${searchReceive}">';
				list+='	<div class="row">';
				list+='	<div class="col-md-11">';
				list+=' <div class="card flex-md-row mb-3 shadow-sm h-md-250">';
				list+='	<div class="card-body">';
				list+='<strong class="d-inline-block mb-2">' ;
				list+='<c:forEach var="trip" items="${post.tripLists}">';
				list+= '${trip.coord_name}';
				list+= '</c:forEach></strong> <h3 class="mb-0">';
				list+=  '<a class="text-dark" href="trip.go?board_no=${post.board_no}">';
				list+='	<c:if test="${post.board_level eq 1}">';
				list+='${trip_notice_1}';
				list+='</c:if>';
				list+='${post.board_title}';
				list+='</a>	</h3>';
				list+='<div class="mb-1 text-muted text-right">';
				list+='<i><b>With</b></i>&nbsp; ${post.user_name}';
				list+='</div><hr style="width: 100%" noshade>';
				list+='<p class="card-text mb-auto">${post.board_content}</p>';
				list+='<hr style="width: 100%" noshade></div>';
				list+='<div class="card-center justify-content-center">';
				list+='<div class="p-2">';
				list+='조회수: ${post.board_view_count}';
				list+='</div><div class="p-2">';
				list+='<c:forEach var="tag" items="${post.board_tags}">';
				list+='<label class="btn btn-sm taglist"> # ${tag.tag_value} </label>';
				list+='</c:forEach>	</div></div></div></div></div></c:forEach>'
					 $("#board-list").append(list);
				
	            var newButton='<button type="button" class="btn btn-dark col-md-12" onclick="loadList('+next_after+')">Load more...</button>';
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
function loadMoreList(next_row) {
	$.ajax({
		type : 'get',
		data : {next_row : next_row},
		url : "loadMoreList.go",
		success : function(data) {
			if(data.length>0){
				var listForAppend="";
				$.each(data, function(key, additionalList){
					next_row=next_row+1;
					
					listForAppend+='<div class="row">';
					listForAppend+='<div class="col-md-11">';
					listForAppend+=	'<div class="card flex-md-row mb-3 shadow-sm h-md-250">';
					listForAppend+=		'<div class="card-body">';
					listForAppend+=			'<strong class="d-inline-block mb-2">';
															if(additionalList.tripLists) {
																$.each(additionalList.tripLists, function(key, tripLists) {
					listForAppend+=												tripLists.coord_name+' ';
																});
															};
					listForAppend+=			'</strong>';
					listForAppend+=			'<h3 class="mb-0">';
					listForAppend+=				'<a class="text-dark" href="trip.go?board_no='+additionalList.board_no+'">';
															if(additionalList.board_level == 1) {
					listForAppend+=								'[공지] ';
															}
					listForAppend+=								additionalList.board_title;
					listForAppend+=			'</h3>';
					listForAppend+=			'<div class="mb-1 text-muted text-right">';
					listForAppend+=				'<i><b>With</b></i>'+additionalList.user_name;
					listForAppend+=			'</div>';
					listForAppend+=			'<hr style="width: 100%" noshade>';
					listForAppend+=			'<p class="card-text mb-auto">'+additionalList.board_content+'</p>';
					listForAppend+=			'<hr style="width: 100%" noshade>';
					listForAppend+=			'<div class="mb-1 text-muted">';
					listForAppend+=				'<label>조회수:'+additionalList.board_view_count+'&nbsp;</label>';
					listForAppend+=				'<c:forEach var="tag" items="${post.board_tags}">';
																	$.each(additionalList.board_tags, function(key, board_tags) {
					listForAppend+=							'<label class="btn btn-sm taglist"> # '+board_tags.tag_value+'</label>';
																	});
					listForAppend+=				'</c:forEach>';
					listForAppend+=			'</div></div>';
					listForAppend+=		'<div class="thumbnail">';
														if(additionalList.thumbnail != null) {
					listForAppend+=				'<img src="'+additionalList.thumbnail+'" class="img-fluid">';
														};
					listForAppend+=		'</div></div></div></div>';
	            });
	            $("#board-list").append(listForAppend);
	            var newButton='<button type="button" class="btn btn-dark col-md-12" onclick="loadMoreList('+next_row_after+')">Load more...</button>';
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
//function loadCal(num){ 
//	if(num==1){
//		$("#start"+num+"").datepicker({
//			minDate:0
//		});
//	}else if(num>1){
//		var beforeStart=$('#start'+(num-1)+'').val();
//		$("#start"+num+"").datepicker({
//			minDate:beforeStart
//		});
//	}
//	 $("#start"+num+"").datepicker("option", "onClose", function ( selectedDate ) {
//	        $("#end"+num+"").datepicker( "option", "minDate", selectedDate );
//	    });
//	 
//	$("#end"+num+"").datepicker();
//	$("#end"+num+"").datepicker("option", "minDate", $("#start"+num+"").val()); 
//	$("#end"+num+"").datepicker("option", "onClose", function () {	 
//        $("#address").focus();
//    });
//
//} 
//add schedule-일정 추가//한글 처리
//function addSchedule(num){
//	var start=$('input[name=start'+num+']');
//	var end=$('input[name=end'+num+']');
//	var place=$('input[name=place'+num+']');
//	if(!start.val()||!end.val()){//일정날짜가 없는 경우는 일정 추가 x
//		alert(noscheduleerror);
//		if(!start.val())start.focus();
//		else end.focus();
//	}else if(!place.val()){
//		alert(noplaceerror);
//		$('#address').focus();
//	}else{
//		$('#schedulediv').append(schedule);
//		if(num>=maxschedule){
//			alert(schedulesizeerror);
//		}else{
//			$('#address').val('');
//			$('#btn_del'+num+'').hide();
//			var schedule="";
//			$('#btn'+num+'').hide();//btn 숨기기
//			num++;
//			schedule+= 	'<div id="schedule'+num+'" class="form-group row">';
//			schedule+=		'<input type="hidden" name="num_counter" value="'+num+'">';	  
//			schedule+= 		'<label for="cal_date" class="col-2 col-form-label">일정 '+num+'</label>';         
//			schedule+=      	'<input type="text" name="start'+num+'" id="start'+num+'" maxlength="10" value="yyyy-MM-dd" class="col-2" autocomplete="off"/>';
//			schedule+=			'~';
//			schedule+=			'<input type="text" name="end'+num+'" id="end'+num+'" maxlength="10" value="yyyy-MM-dd" class="col-2" autocomplete="off"/>&nbsp;&nbsp;';
//			schedule+=			'<input name="place'+num+'" id="place'+num+'" type="text" readonly>';
//			schedule+=		'<button id="btn'+num+'" class="btn_plus" type="button" onclick="addSchedule('+num+')">';
//			schedule+=			'<i class="fas fa-plus-circle"></i>';
//			schedule+=		'</button>';
//			schedule+=		'<button id="btn_del'+num+'" class="btn_del" type="button" onclick="removeSchedule('+num+')">';
//			schedule+=			'<i class="fas fa-minus-circle"></i>';
//			schedule+=		'</button>';
//			schedule+=		'<div id="coordinfo'+num+'">';
//			schedule+=		'</div>';
//			schedule+=	'</div>';
//			$('#schedulediv').append(schedule);
//			loadCal(num);
//			
//			if(num==maxschedule) $('#btn'+num+'').hide();
//			var schedulenum='<input type="hidden" name="schedulenum" value="'+num+'">';
//			$('#schedulenum').empty();
//			$('#schedulenum').append(schedulenum);
//		}		
//	}
//}
//function removeSchedule(num){
//	$('#address').val('');
//	$('#schedule'+num+'').remove();
//	num--;
//	$('#btn'+num+'').show();//btn 보여주기
//	$('#btn_del'+num+'').show();//btn 보여주기
//	$('#schedulenum').empty();
//	$("#schedulenum").val(num);//minus schedule num
//}
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

function attend(trip_id) {
	var user_id=$('input[name=user_id]').val();
	var order=$('input[name=order_'+trip_id+']').val();
	var member_count=$('input[name=member_count_'+trip_id+']').val();
	var board_no=$('input[name=board_no]').val();
	if(user_id) {
		$.ajax({
			type : 'post',
			dataType: 'json',
			data : {user_id : user_id,
						trip_id : trip_id},
			url : 'memberAttend.go',
			success : function(data) {
				if(data) {
					var mList="";
					var count=data.length;
					mList+=count+'/'+member_count+', ';
					$.each(data, function(key, memberList) {
						mList+=memberList.user_name+' ';
		            });
					$('#trip_member_list_'+order).html(mList);
					var buttonDiv='<c:if test="${sessionScope.user_id ne null}">';
		            buttonDiv+=			'<button onclick="absent('+trip_id+')" class="btn btn-sm">빠지기</button>';
		            buttonDiv+=	'</c:if>';
		            $('#trip_button_'+trip_id).html(buttonDiv);
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

function absent(trip_id) {
	var user_id=$('input[name=user_id]').val();
	var order=$('input[name=order_'+trip_id+']').val();
	var member_count=$('input[name=member_count_'+trip_id+']').val();
	var board_no=$('input[name=board_no]').val();
	if(user_id) {
		$.ajax({
			type : 'post',
			dataType: 'json',
			data :  {user_id : user_id,
						trip_id : trip_id},
			url : 'memberAbsent.go',
			success : function(data) {
				if(data) {
					var mList="";
					var count=data.length;
					mList+=count+'/'+member_count+', ';
					$.each(data, function(key, memberList) {
						mList+=memberList.user_name+' ';
		            });
		            $('#trip_member_list_'+order).html(mList);
		            albumPaging(1);
		            var buttonDiv='<c:if test="${sessionScope.user_id ne null}">';
		            buttonDiv+=			'<button onclick="attend('+trip_id+')" class="btn btn-sm">참석</button>';
		            buttonDiv+=	'</c:if>';
		            $('#trip_button_'+trip_id).html(buttonDiv);
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


///////////////////////////////////////////////////박준규  tripList///////////////////////////////////////////////////////////

function initMap() {
	  var map = new google.maps.Map(document.getElementById('map'), {
	    center: {lat: -33.8688, lng: 151.2195},
	    zoom: 13,
	    disableDefaultUI: true
	  });
	  var card = document.getElementById('pac-card');
	  var input = document.getElementById('pac-input');
	  var types = document.getElementById('type-selector');
	  var strictBounds = document.getElementById('strict-bounds-selector');

	  map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

	  var autocomplete = new google.maps.places.Autocomplete(input);

	  // Bind the map's bounds (viewport) property to the autocomplete object,
	  // so that the autocomplete requests use the current map bounds for the
	  // bounds option in the request.
	  autocomplete.bindTo('bounds', map);

	  // Set the data fields to return when the user selects a place.
	  autocomplete.setFields(
	      ['address_components', 'geometry', 'icon', 'name']);

	  var infowindow = new google.maps.InfoWindow();
	  var infowindowContent = document.getElementById('infowindow-content');
	  infowindow.setContent(infowindowContent);
	  var marker = new google.maps.Marker({
	    map: map,
	    anchorPoint: new google.maps.Point(0, -29)
	  });

	  autocomplete.addListener('place_changed', function() {
	    infowindow.close();
	    marker.setVisible(false);
	    var place = autocomplete.getPlace();
	    if (!place.geometry) {
	      return;
	    }

	    // If the place has a geometry, then present it on a map.
	    if (place.geometry.viewport) {
	      map.fitBounds(place.geometry.viewport);
	    } else {
	      map.setCenter(place.geometry.location);
	      map.setZoom(17);  // Why 17? Because it looks good.
	    }
	    marker.setPosition(place.geometry.location);
	    marker.setVisible(true);

	    var address = '';
	    if (place.address_components) {
	      address = [
	        (place.address_components[0] && place.address_components[0].short_name || ''),
	        (place.address_components[1] && place.address_components[1].short_name || ''),
	        (place.address_components[2] && place.address_components[2].short_name || '')
	      ].join(' ');
	    }

	    infowindowContent.children['place-icon'].src = place.icon;
	    infowindowContent.children['place-name'].textContent = place.name;
	    infowindowContent.children['place-address'].textContent = address;
	    infowindow.open(map, marker);
	  });

	  // Sets a listener on a radio button to change the filter type on Places
	  // Autocomplete.
	  function setupClickListener(id, types) {
	    var radioButton = document.getElementById(id);
	    radioButton.addEventListener('click', function() {
	      autocomplete.setTypes(types);
	    });
	  }

	  setupClickListener('changetype-all', []);
	  setupClickListener('changetype-address', ['address']);
	  setupClickListener('changetype-establishment', ['establishment']);
	  setupClickListener('changetype-geocode', ['geocode']);

	  document.getElementById('use-strict-bounds')
	      .addEventListener('click', function() {
	        console.log('Checkbox clicked! New state=' + this.checked);
	        autocomplete.setOptions({strictBounds: this.checked});
	      });
}

///////////////////////////////////////////////////////이민재//////////////////////////////////////////////////////
/*
	//자동완성
		$(function(){
			$("#searchTag").autocomplete({
				source : function( request, response ) {
					$.ajax({
							type: 'post',
							url : 'searchTag.go',
							dataType: 'json',
							data: { tag_value : request.term + "%" },
							success: function(data) {
								response(
									$.map(data, function(item) {
										return {
											label: item.tag_value,
											value: item.tag_value,
											id : item.tag_id
										}
									})
								);
							}
							
					})
				},
				minLength: 1,
				select: function(event, ui){
					var taglength = $('input[name=user_tag]').length
					var tagId = ui.item.id
					var insertBoolean = true;
					for (var i =0; i<taglength; i++){
						if(tagId == $('input[name=user_tag]')[i].value){
							insertBoolean = false;
							break;
						}
					}
					if(insertBoolean){
						var tagStr = "<button type='button' class='btn btn-default'><input type='checkbox' name='user_tag' value='"+tagId+"' checked>"+ui.item.value+"</button>"
						$("#tagArea").append(tagStr)
					} else {
						alert("이미 추가된 태그입니다")
					}
					
				}
			});
		}) 

		function insertUserTag(){
			var tag_value = $("#searchTag").val()
			$.ajax({
				type: 'post',
				url : 'insertUserTag.go',
				dataType : 'json',
				data : { tag_value : tag_value },
				success: function(data) {
					var tagStr = "<button type='button' class='btn btn-default'><input type='checkbox' name='user_tag' value='"+data.tag_id+"' checked>"+data.tag_value+"</button>"
					$("#tagArea").append(tagStr)
				}
			})
		}

		*/
		
		 /* $(function(){
			$("#address").autocomplete({
				source : function( request, response ) {
					$.ajax({
							type: 'post',
							url : 'searchCoord.go',
							dataType: 'json',
							data: { coord_name : "%" +request.term + "%" },
							success: function(data) {
								response(
									$.map(data, function(item) {
										return {
											label: item.coord_name,
											value: item.coord_name,
											id : item.coord_id
										}
									})
								);
							}
							
					})
				},
				minLength: 2
			});
		})  */

		


///////////////////////////////////////////////////////이민재//////////////////////////////////////////////////////
		
		
		
function openSchedule(coord_order) {
	var countOfSchedule=$("input[name=countOfSchedule]").val();
	for (var i =1; i<=countOfSchedule; i++) {
		$('#trip_'+i).hide();
	}
	$('#trip_'+coord_order).show();
}




