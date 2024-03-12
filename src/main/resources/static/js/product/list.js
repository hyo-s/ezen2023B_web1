console.log('list.js');


var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
    center : new kakao.maps.LatLng(37.3218778, 126.8308848), // 지도의 중심좌표 
    level : 6 // 지도의 확대 레벨 
});

// 마커 클러스터러를 생성합니다 
var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    minLevel: 8 // 클러스터 할 최소 지도 레벨 
});

// 인포 윈도우


// 3. 마커 생성 후 클러스터 넣을 마커들의 데이터
// 데이터를 가져오기 위해 jQuery를 사용합니다
// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
$.get("/product/list.do", (r)=>{
    // 데이터에서 좌표 값을 가지고 마커를 표시합니다
    // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
    var markers = r.map((i)=>{
        let marker = new kakao.maps.Marker({
            position : new kakao.maps.LatLng(i.plat, i.plng)
        });
        
        var iwContent = `<div style="padding:5px;">${i.pname}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwPosition = new kakao.maps.LatLng(i.plat, i.plng), //인포윈도우 표시 위치입니다
            iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

        var infowindow = new kakao.maps.InfoWindow({
            map: map, // 인포윈도우가 표시될 지도
            position : iwPosition, 
            content : iwContent,
            removable : iwRemoveable
        });

        // 클러스터에 넣기 전에 마커 커스텀
        // 1. 마커에 클릭이벤트 등록합니다.
        kakao.maps.event.addListener(marker, 'click', function() {
            // 마커 위에 인포윈도우를 표시합니다
            infowindow.open(map, marker);  
        });
        return marker; // 클러스터러에 마커들을 추가합니다
    });
    clusterer.addMarkers(markers);
});

    // var markers = $(data.positions).map(function(i, position) {
    //     return new kakao.maps.Marker({
    //         position : new kakao.maps.LatLng(position.lat, position.lng)
    //     });