//Initialize and add the map
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
		document.getElementById('map'), 
		{zoom: 3, 
		center:center
		});
	for(var i=0;i<coord.length;i++){
		addMarker(location[i],i,boardmap);  
	}
	if(isSameCountry()==1)boardmap.setZoom(6); 
}
//Adds a marker to the map.
function addMarker(location, num, boardmap) {
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

function searchMap() {
	map = new google.maps.Map(document.getElementById('searchmap'), {
		zoom: 8,
		center: {lat: -34.397, lng: 150.644}
    });
	var geocoder = new google.maps.Geocoder();
    
	document.getElementById('addSubmit').addEventListener('click', function() {
		geocodeAddress(geocoder, map);
	});
}
//주소로 좌표 표시
function geocodeAddress(geocoder, resultsMap) {
	var address = document.getElementById('address').value;
	geocoder.geocode({'address': address}, function(results, status) {
		if (status === 'OK') {
			for(var i =0; i<results.length; i++){
				alert(results[i]);
			}
			resultsMap.setCenter(results[0].geometry.location);
			
			//국가-jason 값 가져오기
			var country=results[0].address_components.filter(function(component){
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
