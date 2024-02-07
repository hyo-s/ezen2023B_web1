console.log('todo.js실행')

// 1. 할일 등록
function doPost(){

}
doGet(); // JS 실행 시 최초 1번 실행
// 2. 할일 출력
function doGet(){
    // - 스프링(자바)와 통신 (주고 받고)
    // JQUERY AJAX
        // $.ajax (JSON형식의 통신정보)
        /*
        HTTTP METHOD : post, get, put, delete 등등
        $.ajax({
        url : SPRING CONTROLLER URL / 통신 대상 식별
            method : 'HTTP METHOD / 통신 방법'
            data : 'HTTP request value / 통신 요청으로 보낼 데이터'
            success : function result(returnValue){} / 통신 응답 함수
        })
        */
//    $.ajax({
//        url : 'spring controller mapping 주소'
//        method : 'mapping 방법'
//        data : '보낼 데이터'
//        success : function result('받을 데이터'){}
//    })

    $.ajax({
        url : '/todo/get.do',
        method : 'get',
        success : function result(resultValue){
        console.log(resultValue);
        // 통신 응답 결과를 HTML 형식으로 출력해주기.
        // 1. 어디에
        let tbody = document.querySelector('table tbody')
        // 2. 무엇을
        let html = '';
            for(let i=0; i<resultValue.length; i++){
                html+=`<tr>
                    <th>${resultValue[i].id}</th>
                    <th>${resultValue[i].content}</th>
                    <th>${resultValue[i].deadline}</th>
                    <th>${resultValue[i].state}</th></tr>`
            }   // FOR END
            tbody.innerHTML = html;
        }   // SUCCESS END
    })  // AJAX END
}   // METHOD END
// 3. 할일 상태변경
function doPut(){

}
// 4. 할일 삭제
function doDelete(){

}