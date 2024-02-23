console.log('day11Ajax.js 실행')

    // 1. 함수 function 함수명( ){ }
    // 2. 익명 function( ){ }
    // 3. 화살표 ( ) => { }

    let id = 9;
    let content = "AJAX테스트중"

// 간단한 통신
function ajax1(){
    console.log('ajax1 함수 실행');

    $.ajax({
        url : "/day11/ajax1",
        method : "get",
        success : (result) => {
            console.log(result);
        },
        error : (error) => {
            console.log(error);
        }
    });
}

// 경로 상에 매개변수 포함하기
function ajax2(){
    console.log('ajax2 함수 실행');

    $.ajax({
        url : `/day11/ajax2/${id}/${content}`,
        method : "get",
        success : (result) => {
            console.log(result);
        }
    })
}

// 경로 상에 쿼리스트링 포함하기
function ajax3(){
    console.log('ajax3 함수 실행');

    $.ajax({
        url : `/day11/ajax3?id=${id}&content=${content}`,
        method : "get",
        success : (result) => {
            console.log(result);
        }
    })
}

// MAP
function ajax4(){
    console.log('ajax4 함수 실행');

    $.ajax({
        url : `/day11/ajax4?id=${id}&content=${content}`,
        method : "get",
        success : (result) => {
            console.log(result);
        }
    })
}

// DTO
function ajax5(){
    console.log('ajax5 함수 실행');

    $.ajax({
        url : `/day11/ajax5?id=${id}&content=${content}`,
        method : "get",
        success : (result) => {
            console.log(result);
        }
    })
}

// 4. HTTP 본문(BODY)에 객체 보내기
function ajax6(){
    console.log('ajax6 함수 실행');

    $.ajax({
        url : "/day11/ajax6",
        method : "get",
        data : { 'id' : id, 'content' : content },
        success : (result) => {
            console.log(result);
        }
    })
}

function ajax7(){
    console.log('ajax7 함수 실행');

    $.ajax({
        url : "/day11/ajax7",
        method : "get",
        data : { 'id' : id, 'content' : content },
        success : (result) => {
            console.log(result);
        }
    })
}

function ajax8(){
    console.log('ajax8 함수 실행');

    $.ajax({
        url : "/day11/ajax8",
        method : "post",
        data : { 'id' : id, 'content' : content },
        success : (result) => {
            console.log(result);
        }
    })
}

function ajax9(){
    console.log('ajax9 함수 실행');

    $.ajax({
        url : "/day11/ajax9",
        method : "post",
        data : { 'id' : id, 'content' : content },
        success : (result) => {
            console.log(result);
        }
    })
}

// 10. 본문에 데이터 보내는 방식 content type : form
function ajax10(){
    console.log('ajax10 함수 실행');

    $.ajax({
        url : "/day11/ajax10",
        method : "post",
        data : { 'id' : id, 'content' : content },
        success : (result) => {
            console.log(result);
        }
    })
}

// 11.
function ajax11(){
    console.log('ajax11 함수 실행');

    $.ajax({
        url : "/day11/ajax11",
        method : "post",
        data : JSON.stringify({ 'id' : id, 'content' : content }),
        contentType : "application/json",
        success : (result) => {
            console.log(result);
        }
    })
}
function ajax12(){
    console.log('ajax12 함수 실행');

    $.ajax({
        url : "/day11/ajax12",
        method : "post",
        data : JSON.stringify({ 'id' : id, 'content' : content }),
        contentType : "application/json",
        success : (result) => {
            console.log(result);
        }
    })
}