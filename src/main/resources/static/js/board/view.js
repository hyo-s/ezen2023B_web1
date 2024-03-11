console.log('view.js');

// * 경로(URL)상의 쿼리스트링(매개변수) 호출하기
    // 1. new URL(location.href) : 현재 페이지의 경로 호출
    console.log(new URL(location.href));
    // 2. [ .searchParams ]경로상의 (쿼리스트링) 매개변수들
console.log(new URL(location.href).searchParams);
    // 3. [.get('queryStringKey') ] (쿼리스트링) 매개변수들에서 특정 매개변수 호출
console.log(new URL(location.href).searchParams.get('bno'));

let bno = new URL(location.href).searchParams.get('bno');
console.log(bno);

onView();
function onView(){
    console.log('onView()');
    $.ajax({
        url: "/board/view.do",
        method:"get",
        data : {"bno" : bno},
        success: (r)=>{
            console.log(r);
            document.querySelector('.btitle').innerHTML = r.btitle;
            document.querySelector('.bcontent').innerHTML = r.bcontent;
            document.querySelector('.bcno').innerHTML = r.bcno;
            document.querySelector('.mid').innerHTML = r.mid;
            document.querySelector('.bdate').innerHTML = r.bdate;
            document.querySelector('.bview').innerHTML = r.bview;
            // 다운로드 링크
            if(r.bfile != null){
                document.querySelector('.bfile').innerHTML = `<a href="/board/file/download?bfile=${r.bfile}">${r.bfile}</a>`;
            }
            

            $.ajax({
                url: "/member/login/check",
                method :'get',
                success: (loginId)=>{
                    console.log(loginId);
                    if(loginId==r.mid){
                        document.querySelector('.btnBox').innerHTML += `<button class="boardBtn" type="button" onclick="onDelete(${r.bno})">삭제</button>
                        <button class="boardBtn" type="button" onclick="location.href='/board/update?bno=${r.bno}'">수정</button>`;
                    }
                }
            });
            onReplyList();
        }
    });
}

function onDelete(bno){
    $.ajax({
        url: "/board/delete.do",
        method : 'delete',
        data: {'bno' : bno},
        success: (r)=>{
            if(r){
                location.href="/board";
            }else{
            
            }
        }
    });
}

function onReplyWrite(brindex){
    console.log('onReplyWrite()');
    console.log(bno);
    $.ajax({
        url: "/board/reply/write.do",
        method:'post',
        data: {
            'bno' : bno,
            'brcontent' : document.querySelector(`.brcontent${brindex}`).value,
            'brindex' : brindex
        },
        success: (r) => {
            console.log(r);
            if(r){
                alert('댓글 작성 성공');
                onReplyList();
            }else{
                alert('댓글 작성 실패')
            }
        }
    });
}

function onReplyList(){
    $.ajax({
        url: "/board/reply/do",
        method : 'get',
        data: {'bno':bno},
        success: (r) => {
            console.log(r);
            let replyListBox = document.querySelector('.replyListBox');
            let html = '';
            r.forEach((reply) => {
                html +=`<div><sapn>${reply.brcontent}</span>
                <sapn>${reply.mno}</span>
                <sapn>${reply.brdate}</span>
                <button type="button" onclick="subReplyView(${reply.brno})">답변작성</button>
                <div class="subReplyBox${reply.brno}"></div>
                ${onSubReplyList(reply.subReply)}
                </div>`
            });
            replyListBox.innerHTML = html;
        }
    });
}

function subReplyView(brno){
    let html = `<textarea class="brcontent${brno}"></textarea>
        <button onclick="onReplyWrite(${brno})" type="button">작성</button>`
    document.querySelector('.subReplyBox'+brno).innerHTML = html;

}

/*
    1. JS에서 배열을 반복문으로 순회하는 방법
        let array = [ ]

        1. 일반 for 문
            for(let i=0; i<array.length; i++){
                array[i];
            }

        2-1. 향상된 for 문 : 인덱스 순회
            for(let 반복변수 in array){ }

        2-1. 향상된 for 문 : 데이터 순회
            for(let 반복변수 of array){ }

        3. forEach() : return 없는 함수
            array.forEach( (반복변수) => { } )

        4. map() : return 있는 함수
            array.map( (반복변수) => { } )


    2. JAVA에서 배열을 반복문으로 순회하는 방법
        List<Object> list = new ArrayList<>();

        1. 일반 for 문
            for( int i=0; i<array.size(); i++){
                array[i]
            }

        2. 향상된 for 문 : 콜론 : 데이터 순회
            for( 타입 반복변수 : array ){ }
    
        3. forEach() : return 없는 함수
            array.forEach( (반복변수) -> { } )

        3. map 함수 제공 : return 있는 함수

*/

function onSubReplyList(subReply){
    let subHTML='';
    subReply.forEach((subReply)=>{
        subHTML+=`<div style="margin-left : 50px">
            <sapn>${subReply.brcontent}</span>
            <sapn>${subReply.mno}</span>
            <sapn>${subReply.brdate}</span>
        </div>`
    })
    return subHTML;
}