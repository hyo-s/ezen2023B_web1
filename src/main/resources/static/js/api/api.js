console.log('api.js')

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

// 데이터를 가져오기 위해 jQuery를 사용합니다
// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
$.get("https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=7rwoonqHnVVRf7fVwPBx8lcdTQNepaj6Ii1Lu%2B3MpaZaJChTycTcKqNJ0XKP1uAD1ti6iRZdl7CHz5HGAuiWVA%3D%3D", (r)=>{
    // 데이터에서 좌표 값을 가지고 마커를 표시합니다
    // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다

    var markers = r.data.map( (object) => {
        
        let mark = new kakao.maps.Marker({
            position : new kakao.maps.LatLng(object.식당위도,object.식당경도)
        });
        return mark;

    });

    // 클러스터러에 마커들을 추가합니다
    clusterer.addMarkers(markers);
});

$.ajax({
    url: "https://api.odcloud.kr/api/15111852/v1/uddi:71ee8321-fea5-4818-ade4-9425e0439096?page=1&perPage=100&serviceKey=7rwoonqHnVVRf7fVwPBx8lcdTQNepaj6Ii1Lu%2B3MpaZaJChTycTcKqNJ0XKP1uAD1ti6iRZdl7CHz5HGAuiWVA%3D%3D",
    method : 'get',
    success: (r)=>{
        console.log(r);
        let apiTable1= document.querySelector('.apiTable1');
        let html = ''
        r.data.forEach((data)=>{
            html += `<tr>
            <td>${data.관리기관명}</td>
            <td>${data.날짜}</td>
            <td>${data.시도명}${data.시군구명}${data.읍면동}</td>
            <td>${data['우량(mm)']}</td>
            </tr>`
        });
        apiTable1.innerHTML = html;
    }
});

$.ajax({
    url: "https://api.odcloud.kr/api/15109590/v1/uddi:3e550608-d205-411b-a92d-e7fd2278b7bc?page=1&perPage=100&serviceKey=7rwoonqHnVVRf7fVwPBx8lcdTQNepaj6Ii1Lu%2B3MpaZaJChTycTcKqNJ0XKP1uAD1ti6iRZdl7CHz5HGAuiWVA%3D%3D",
    method : 'get',
    success: (r) => {
        console.log(r);
        let apiTable2 = document.querySelector('.apiTable2');
        let html = '';
        r.data.forEach((data)=>{
            html +=`<tr>
            <td>${data.사업장명}</td>
            <td>${data.도로명전체주소}</td>
            <td>${data.대표메뉴1}</td>
            <td>${data.메뉴가격1.toLocaleString()}</td>
            <td>${data.대표전화}</td>
            <td>${data['주차 가능']}</td>
            </tr>`
        })
        apiTable2.innerHTML = html;
    }
});