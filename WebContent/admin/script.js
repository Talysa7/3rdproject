var taginserterror="겹치는 tag명이 존재합니다.";
var valueerror="값을 입력하세요";
var checkerror="추가할 값을 선택하세요";
var notice_can="공지취소";
var str_notice=" 공  지 ";
var m="수정";
var d="삭제";
var n="공지";
var p="사진";
var nocheckerror="할 값을 체크해주세요";
var deleteerror=" 삭제에러입니다.";
var deletesuccess="삭제를 성공했습니다."
$(document).ready(
	function(){
		var check1=$('input[name=check1]');
		var n=check1.length;
		//전체 선택 체크시 모든 체크 박스 check
		$('#checkAll').change(function(){
			if($('#checkAll').prop("checked")){
				check1.prop("checked",true);
			}else{
				check1.prop("checked",false);
			}
		});
		
		check1.click(function(){
			if($(this).prop('checked')){
			}else{
				$('#checkAll').prop("checked",false);
			}
		});
		
		$('span[name=notice]').mouseover(
				function(event){
					$(this).html(notice_can);
					
					$(this).click(function(){
						var tb_no=$(this).parent().parent().find('td[name=key]').text();
						
						$.ajax({
							type:'POST',
							url:'adminTrip.go',
							data:{
								yn:'no',
								tb_no:tb_no
							},
							success:function(data){
								location.reload();
							},
						
						});
						
					});
				}
		).mouseout(
				function(event){
					$(this).html(str_notice);	
				}
		);
		
/*		setTimeout(
				function(){
					if(!$('input:checkbox').is(':checked')){
						location.reload();
					}
				},2000
			);
*/	
	}		
);

function erroralert(msg){
	alert(msg);
	history.back();
}
//first letter->capital첫글자 대문자로 변경
function UpperFirstLetter(string){
	return string.charAt(0).toUpperCase()+string.slice(1);
}
//delete
function deleteList(page){
	if($("input[name=check1]:checked").length==0){
		alert(d+nocheckerror);
	}else{
		var form=document.getElementById(page+'Form');
		
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method","Post");
		form.setAttribute("action", "admin"+UpperFirstLetter(page)+"Del.go");
		form.setAttribute("target","del");
		
		var input;
		var checkbox=$("input[name=check1]:checked");
		checkbox.each(function(i){//i=0 start
			var tr = checkbox.parent().parent().eq(i);	
			if(page=="comment"){
				tag_id=tr.find('input[name=key]').val();
			}else{
				tag_id=tr.find('td[name=key]').text();//수정(태그 id로)
			}
			input=document.createElement('input');
			input.type="hidden";
			input.name="key"+i;
			input.value=tag_id;
			form.appendChild(input);
		});
		input=document.createElement('input');
		input.type="hidden";
		input.name="num";
		input.value=checkbox.length;
		form.appendChild(input);
	
		form.submit();
		
	}
}

//tag 추가
function addTag(state){  
	var checkbox=$("input[name=check1]:checked");
	var values=new Array();
	if($("input[name=check1]:checked").length==0){
		setCancel();
	}else{
		checkbox.each(function(i){
			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);	
		
			var tag_value=tr.find('input[type="text"]').val();
			var tag_type=tr.find('select[name=tag_type]').val();
			// 체크된 row의 모든 값을 배열에 담는다.
			if(!tag_value){
				alert(valueerror);
				
			}else{
				values.push(''+tag_type+''+tag_value);
				
				location.href="adminTagMng.go?state="+state+"&values="+values;
				
			}
		});
	}
}
function modTag(state){
	location.href="adminTagMng.go?state="+state;
}
//adminTagMod.go로 이동//post로 넘김
function goModTag(){
	var tag_id;
	if($("input[name=check1]:checked").length==0){
		alert(m+nocheckerror);
	}else{
		var form=document.getElementById('tagForm');
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method","Post");
		form.setAttribute("action", "adminTagMod.go");
		form.setAttribute("target","tag_mod");
		
		var input;
		var checkbox=$("input[name=check1]:checked");
		checkbox.each(function(i){
			var tr = checkbox.parent().parent().eq(i);	
			tag_id=tr.find('td[name=key]').text();//수정(태그 id로)
				//ids.push(tag_id);
			input=document.createElement('input');
			input.type="hidden";
			input.name="id"+i;
			input.value=tag_id;
			form.appendChild(input);
		});
		input=document.createElement('input');
		input.type="hidden";
		input.name="num";
		input.value=checkbox.length;
		form.appendChild(input);
		
		window.open("adminTagMod.go","tag_mod","confirm window","scrollbar=yes,status=no,menubar=no,width=500,height=200");
		form.submit();

		//document.createElement("input").setAttribute("id",tag_id);
		//form.submit();
			
	}
}
	
//adminTagAdd.go로 이동
function goAddTag(){
		open("adminTagAdd.go","confirm window","scrollbar=yes,status=no,menubar=no,width=600,height=300");
}

function setCancel(){
	self.close();
}
function addRow(){
	var tag=document.getElementById('t');
	var row=tag.insertRow(tag.rows.length);
	
	var cell=[];
	var n=3;
	for(var i=0;i<n;i++){
		cell[i]=row.insertCell(i);
		if(i==0){
			cell[i].innerHTML="<input type='checkbox' name='check1'>";
		}else if(i==n-1){
			cell[i].innerHTML="<select name='tag_type'><option value='1' selected='selected'>사용자성향</option><option value='2'>도시이름</option></select>";
		}else if(i==n-2){
			cell[i].innerHTML="<input class='input' type='text' autofocus=''>";
		}
	}	
}
//공지글로 올리기
function notice(){
	if($("input[name=check1]:checked").length==0){
		alert(n+nocheckerror);
	}else{
		var checkbox=$("input[name=check1]:checked");
		checkbox.each(function(i){//i=0 start
			var tr = checkbox.parent().parent().eq(i);	
			var tb_no=tr.find('td[name=key]').text();
			
			$.ajax({
				type:'POST',
				url:'adminTrip.go',
				data:{
					yn:'yes',
					tb_no:tb_no
				},
				success:function(data){
					location.reload();
				},
				error:function(e){
					alert('fail');
				}
			});
		});
	}
}
//delete photo
function deletePhoto(){
	if($("input[name=check1]:checked").length==0){
		alert(p+nocheckerror);
	}else{
		var checkbox=$("input[name=check1]:checked");
		var num=checkbox.length;
		checkbox.each(function(i){//i=0 start
			var div = checkbox.parent().eq(i);	
			photo_id=div.find('input[name=key]').val();

			$.ajax({
				type:'POST',
				url:'adminAlbumDel.go',
				data:{
					photo_id:photo_id,
					num:num
				},
				success:function(data){
					location.reload();
				}
			});
		});
	}
}



