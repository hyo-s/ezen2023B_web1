console.log('board.js')

// 1. 전체 출력용 : 함수, 매개변수 X , 반환 X , 언제 실행할건지 : 페이지 열릴 때
doViewList(1); // 첫페이지 실행
function doViewList( page ){
    console.log('doViewList()')
    $.ajax({
        url: "/board/do",
        method: "GET",
        data: {'page': page},
        success: (r)=>{
            console.log(r);
            console.log(r.list);

// ==================== 테이블 레코드 구성 ==================== //
            // 1. 어디에
            let boardTableBody = document.querySelector('#boardTableBody');
            // 2. 무엇을
            let html = '';
                // 서버가 보내준 데이터를 출력
                // 1. 
                r.list.forEach(board => {
                    console.log(board);
                    html +=`<tr>
                    <th>${board.bno}</th>
                    <td>${board.btitle}</td>
                    <td><img src="/img/${board.mimg}" style="width:20px; border-radius:50%;"/>${board.mid}</td>
                    <td>${board.bdate}</td>
                    <td>${board.bview}</td>
                    </tr>`
                });
                                
            // 3. 출력
            boardTableBody.innerHTML=html;
// ==================== 페이지 네이션 구성 ==================== //
            // 1. 어디에
            let pagination = document.querySelector('.pagination');
            // 2. 무엇을
            let pagehtml = '';
                // 이전
                pagehtml +=`<li class="page-item"><a class="page-link" onclick="doViewList(${page-1 < 1 ? 1 : page-1})">이전</a></li>`
                // 페이지 번호 버튼
                for(let i=r.startBtn; i<=r.endBtn; i++){
                    // 만약에 i와 현재 페이지가 같으면 active 클래스 삽입 아니면 생략 ( 조건부 렌더링 )
                    pagehtml +=`<li class="page-item ${ i == page ? 'active':''}"><a class="page-link" onclick="doViewList(${i})">${i}</a></li>`
                }
                // 다음
                pagehtml +=`<li class="page-item"><a class="page-link" onclick="doViewList(${page+1 > r.totalPage ? r.totalPage : page +1})">다음</a></li>`
            // 3. 출력
            pagination.innerHTML=pagehtml;
        }
    });
    return;
}