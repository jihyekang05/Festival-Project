var address      = document.getElementById("address");
var mapContainer = document.getElementById("map");
var coordXY   = document.getElementById("coordXY");
var mapOption;
var map;
var x,y          = "";



 mapOption = {
  center: new daum.maps.LatLng(33.450701, 126.570667), // 임의의 지도 중심좌표
        level: 4            // 지도의 확대 레벨

 };
 


// 지도 생성
map = new daum.maps.Map(mapContainer, mapOption);


//편의점 찾기
function addressChk(categoryCode) {

 marker = []

 var gap = address.value; // 주소검색어
 if (gap=="") {
  alert("주소입력값 없음");
  address.focus();
  return;
 }
 
 // 주소-좌표 변환 객체를 생성
 var geocoder = new daum.maps.services.Geocoder();


 // 주소로 좌표를 검색
 geocoder.addressSearch(gap, function(result, status) {
  
  // 정상적으로 검색이 완료됐으면,
  if (status == daum.maps.services.Status.OK) {
   
   var coords = new daum.maps.LatLng(result[0].y, result[0].x);

   y = result[0].x;
   x = result[0].y;



   // 결과값으로 받은 위치를 마커로 표시합니다.
   var marker1 = new daum.maps.Marker({
    map: map,
    position: coords
   });



   // 인포윈도우로 장소에 대한 설명표시
   var infowindow1 = new daum.maps.InfoWindow({
    content: '<div style="width:150px;text-align:center;padding:5px 0;">행사위치</div>'
   });

   infowindow1.open(map,marker1);


   // 지도 중심을 이동
   map.setCenter(coords);

  }
  console.log(x);
  // 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places(map);

// 카테고리로 편의시설을 검색합니다
ps.categorySearch(categoryCode, placesSearchCB, {useMapBounds:true});

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        for (var i=0; i<data.length; i++) {
            displayMarker(data[i]);
        }
    }
}
// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });

    var infowindow = new daum.maps.InfoWindow({
        // content: '<div style="width:150px;text-align:center;padding:5px 0;">행사위치</div>'
       });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });

}
 });
 
}



