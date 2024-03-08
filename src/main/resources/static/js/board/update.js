console.log('view.js');

let bno = new URL(location.href).searchParams.get('bno');

onView();
function onView(){
    console.log('onView()');
    $.ajax({
        url: "/board/view.do",
        method:"get",
        data : {"bno" : bno},
        success: (r)=>{
            console.log(r);

            document.querySelector('.btitle').value = r.btitle;
            document.querySelector('.bcontent').innerHTML = r.bcontent;
            document.querySelector('.bcno').value = r.bcno;
            document.querySelector('.bfile').innerHTML = r.bfile;
            let option = {
                lang : 'ko-KR', // 한글패치
                height : 500, // 에디터 새로 길이
            }
            $('#summernote').summernote(option);
        }
    });
}

function onUpdate(){

    // 1. 폼 가져온다
    let boardUpdateForm = document.querySelector('.boardUpdateForm');
    // 2. 폼 객체화
    let boardUpdateFormData = new FormData(boardUpdateForm);
        // + 폼 객체에 데이터 추가 [ HTML 입력 폼외 데이터 삽입 가능 ]
        // 폼데이터객체명.set( 키, 값 );
        boardUpdateFormData.set('bno',bno);

    $.ajax({
        url: "/board/update.do",
        method : "put",
        data: boardUpdateFormData,
        contentType : false,
        processData : false,
        success: (r)=>{
            if(r){
                alert('수정성공');
                location.href="/board/view?bno="+bno;
            }else{
                alert('수정실패');
            }
            
        }
    });
}