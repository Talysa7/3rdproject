function initMap() {
	var card = document.getElementById('pac-card');
	var input = document.getElementById('pac-input');
	var types = document.getElementById('type-selector');
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
	
	map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

	var autocomplete = new google.maps.places.Autocomplete(input);
	autocomplete.bindTo('bounds', map);
	autocomplete.setFields([ 'address_components', 'formatted_phone_number', 'geometry', 'icon', 'name', 'photos', 'vicinity' ]);

	var infowindow = new google.maps.InfoWindow();
	infowindow.setContent(infowindowContent);

	var marker = new google.maps.Marker({
		map : map,
		anchorPoint : new google.maps.Point(0, -29)
	});
	
//	마커 이벤트
	google.maps.event.addListener(marker,'click',function() {
		after_zoom++;
		map.setZoom(after_zoom);
		map.setCenter(marker.getPosition());
	});
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

		infowindowContent.children['place-icon'].src = place.icon;
		infowindowContent.children['place-name'].textContent = place.name;
		infowindowContent.children['place-address'].textContent = address;
		infowindowContent.children['place-location'].textContent = 
			place.geometry.location.lat() + place.geometry.location.lng();
		var num = $("input[name=num_counter]");
		$("#place"+num).val( place.name );
		
		infowindow.open(map, marker);
	});
}