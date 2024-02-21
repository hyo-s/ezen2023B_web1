
console.log( 'day08Board');

// 1. 저장 메소드 : 실행조건 : 등록 버튼 클릭시    매개변수x , 리턴x
function doCreate(){
    console.log( "doCreate()" );

    let bcontent = document.querySelector('#bcontent').value;
    console.log(bcontent);
    let bwriter = document.querySelector('#bwriter').value;
    console.log(bwriter);
    let bpassword = document.querySelector('#bpassword').value;
    console.log(bpassword);

    let info = { bcontent : bcontent, bwriter : bwriter, bpassword : bpassword }
    console.log(info);
    $.ajax({
       url : '/board/create',
       method : 'post',
       data :  info,
       success : function ( result ){
            if(result){
                alert('글쓰기 성공');
                doRead();
            } else{
                alert('글쓰기 실패');
            }
        }
    })
}
// 2. 전체 호출 메소드 : 실행조건 : 페이지 열릴때 , 변화(저장,수정,삭제)가 있을때(새로고침)   매개변수 x , 리턴 x
doRead(); // JS 열릴때 최초 실행;
function doRead( ){
    console.log( "doRead()" );

    $.ajax({
       url : '/board/read',
       method : 'get',
       success : function ( result ){
        console.log(result);
            let tbody = document.querySelector('table tbody');

            let html = "";
            for(let i=0; i<result.length; i++){
            console.log(result[i])
            // ` 백틱 : TAB 키 위 ${ JS코드 }
            html +=
                    `<tr>
                        <th>${result[i].bno}</th>
                        <th>${result[i].bcontent}</th>
                        <th>${result[i].bwriter} </th>
                    <th>
                        <button onclick="doDelete(${result[i].bno})" >삭제</button>
                        <button onclick="doUpdate(${result[i].bno})" >수정</button>
                    </th>
                    </tr>`
            }
            tbody.innerHTML = html;
       }
    })
}
// 3. 수정 메소드 : 실행조건 : 수정 버튼 클릭시    매개변수 : 수정할식별키bno , 리턴 x
function doUpdate( bno ){
    console.log( "doUpdate()" + bno );

     let bcontent = prompt('수정할 내용 : ');
     let bpassword = prompt('게시물 비밀번호 : ');

     let info = {
        bno : bno,
        bcontent : bcontent,
        bpassword : bpassword
     }

    $.ajax({
       url : '/board/update',
       method : 'post',
       data : info,
       success : function ( result ){
            console.log(result);
            if(result){
                alert('글수정 성공');
                doRead();
            }else{
                alert('글수정 실패')
            }

       }
    })

}
// 4. 삭제 메소드 : 실행조건 : 삭제 버튼 클릭시    매개변수 : 삭제할식별키bno , 리턴 x
function doDelete( bno ){
    console.log( "doDelete()" + bno  );

    let bpassword = prompt('게시물 비밀번호 : ')

    $.ajax({
       url : `/board/delete/${bno}/${bpassword}`,
       method : 'get',
       success : function ( result ){
            console.log(result);
            if(result){
                alert('글삭제 성공');
                doRead();
            }else{
                alert('글삭제 실패');
            }
       }
    })
 }