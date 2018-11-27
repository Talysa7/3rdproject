<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<script type="text/javascript">
	//<!--
	var cityInfos = new Array();
	var cnt = 11583;
	var csvContent = '';
	var cityNames = new Array();
	var cityCon = new Array();
	var lineLength = 0;
	
	
	String.prototype.replaceAll = function(org, dest) {
	    return this.split(org).join(dest);
	}
	

	$(document).ready(function() {
		 $.ajax({
			url : '/3rdProject/csv/worldcities.csv',
			dataType : 'text',
			success : function(data){
				var lines = data.split("\n");
				for (var i = cnt; i<12893; i++){
					var column = lines[i].split(",");
					cityNames[i] = column[0].replaceAll("\"" ,'')
					cityCon[i] = column[5].replace(/\"/g ,'')
				}
				
			}
		}) 
	})
	



	function crawl(){
		$.ajax({
			type : "GET",
			url : "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+cityNames[cnt]+"&type=locality&key=AIzaSyCWs0AeisrO_lEunkZnPecAki2ZoyQTW7k",
			dataType : "json",
			success : function(data){
				if(data.status == "OK"){
					var cityInfo = new Array()
					cityInfo[0] = "\"" +  data.results[0].name + "\""
					cityInfo[1] = "\"" + cityCon[cnt] + "\""
					cityInfo[2] = "\"" + data.results[0].geometry.location.lat + "\""	//위도
					cityInfo[3] = "\"" + data.results[0].geometry.location.lng + "\""	//경도
					cityInfo[4] = "\"" + data.results[0].place_id + "\""			// 플레이스 id
					cityInfo[5] = "\"" + data.results[0].formatted_address + "\""	// 전체 주소
					cityInfo[6] = "\"" + data.results[0].geometry.viewport.northeast.lat + "\""	// 뷰포트 북동 위도
					cityInfo[7] = "\"" + data.results[0].geometry.viewport.northeast.lng + "\""	// 뷰포트 북동 경도
					cityInfo[8] = "\"" + data.results[0].geometry.viewport.southwest.lat + "\""	// 뷰포트 남서 위도
					cityInfo[9] = "\"" + data.results[0].geometry.viewport.southwest.lng + "\""	// 뷰포트 남서 경도
					cityInfo[10] = "\"" + data.results[0].id + "\""					//	id ?? 이건 뭔지 모르겠다.
					cityInfos[cnt] = cityInfo
				}
				cnt++;
				if(cnt < 12893 ){
					setTimeout(crawl,100)
				} else {
					exportCsv()
				}
			}, error: function () {
				exportCsv()
			}
		})
    }

	function exportCsv(){
		cityInfos.forEach(function(cityInfo){
			var cont = cityInfo.join(",");
			csvContent += cont + "\n"
		})
		var hiddenElement = document.createElement('a');
		hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csvContent);
		hiddenElement.target = '_blank';
		hiddenElement.download = 'cityInfo.csv';
		hiddenElement.click();
	}
	
	//-->
</script>

<input id="startCrawl" type="button" value="실행" onclick="crawl()">
<input id="downCsv" type="button" value="다운로드" onclick="exportCsv()">