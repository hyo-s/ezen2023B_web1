console.log('list.js');
navigator.geolocation.getCurrentPosition( ( myLocation ) =>{
    console.log( myLocation );
    console.log( myLocation.coords.latitude );  // 현재 위치
    console.log( myLocation.coords.longitude );  // 현재 위치

    // 카카오 지도 실행
    kakaoMapView( myLocation.coords.latitude  , myLocation.coords.longitude  );
})

function kakaoMapView( latitude , longitude ){
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(latitude,longitude), // 지도의 중심좌표 
        level : 6 // 지도의 확대 레벨 
    });

    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 8 // 클러스터 할 최소 지도 레벨 
    });

    var imageSrc = '/img/marker.png', // 마커이미지의 주소입니다    
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        
    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

    // 3. 마커 생성 후 클러스터 넣을 마커들의 데이터
    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/product/list.do", (r)=>{
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = r.map((i)=>{
            console.log(i);
            let marker = new kakao.maps.Marker({
                position : new kakao.maps.LatLng(i.plat, i.plng),
                image: markerImage
            });

            // 클러스터에 넣기 전에 마커 커스텀
            // 1. 마커에 클릭이벤트 등록합니다.
            kakao.maps.event.addListener(marker, 'click', function() {
                // 인포 윈도우
                var iwContent = `<div style="padding:5px;">${i.pname}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                iwPosition = new kakao.maps.LatLng(i.plat, i.plng), //인포윈도우 표시 위치입니다
                iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

                var infowindow = new kakao.maps.InfoWindow({
                    map: map, // 인포윈도우가 표시될 지도
                    position : iwPosition,
                    content : iwContent,
                    removable : iwRemoveable
                });
                // 마커 위에 인포윈도우를 표시합니다
                infowindow.open(map, marker);
                document.querySelector('.sideBarBtn').click();
                document.querySelector('.offcanvas-title').innerHTML = `제품명:${i.pname}`
                let carouselHTML = '';
                let index = 0;
                    i.pimg.forEach((img) => {
                        console.log(img);
                        carouselHTML += `<div class="carousel-item ${ index == 0 ? 'active' : '' }">
                        <img style="height:400px; object-fit: cover;" src="/img/${img}" class="d-block w-100" alt="...">
                        </div>`
                        index ++;
                    });
                document.querySelector('.offcanvas-body .carousel-inner').innerHTML = carouselHTML
                plikeView(i.pno)
            });
            return marker; // 클러스터러에 마커들을 추가합니다
        });
        clusterer.addMarkers(markers);
    });
}

function plikeDo( pno, method ){
    let result = false;
    $.ajax({
        url: "/product/plike.do",
        method: method,
        async:false,
        data: {'pno':pno},
        success: (r)=>{
            console.log(r);
            result = r;
        }
    });
    if(method !='get') plikeView(pno);
    return result;
}  

function plikeView(pno){
    let result = plikeDo(pno,'get');
    if(result){
        document.querySelector('.offcanvas-body .sideBarBtnBox').innerHTML = `
        <button onclick="plikeDo(${pno},'delete')" type="button">찜하기 취소</button>
        <button type="button">채팅</button>`
    }else{
        document.querySelector('.offcanvas-body .sideBarBtnBox').innerHTML = `
        <button onclick="plikeDo(${pno},'post')" type="button">찜하기 등록</button>
        <button type="button">채팅</button>`
    }

}