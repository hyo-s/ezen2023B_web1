console.log('board.js')

// ==================== 페이지 관련 객체 = 여러개 변수 묶음 ==================== //
let pageObject = {
    page : 1,               // 현재 페이지
    pageBoardSize : 5,      // 페이지당 표시할 게시물 수
    bcno : 0,                // 카테고리 번호
    key : 'b.btitle',
    keyword : ''
}

// 1. 전체 출력용 : 함수, 매개변수 X , 반환 X , 언제 실행할건지 : 페이지 열릴 때
doViewList(1); // 첫페이지 실행
function doViewList( page ){
    console.log('doViewList()')

    pageObject.page = page;

    $.ajax({
        url: "/board/do",
        method: "GET",
        data: pageObject,
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
                    <td><a href="/board/view?bno=${board.bno}">${board.btitle}</a></td>
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
// ==================== 페이지 네이션 구성 ==================== //
            document.querySelector('.totalPage').innerHTML = r.totalPage;
            document.querySelector('.totalBoardSize').innerHTML = r.totalBoardSize;

        }
    });
    return;
}

// ==================== 2. 페이지당 게시물 수 ==================== //
function onPageBoardSize( object ){
    console.log(object);
    console.log(object.value);

    pageObject.pageBoardSize=object.value;
    doViewList(1);

}

// ==================== 3. 카테고리 선택 ==================== //
function onBcno( bcno ){
    console.log(bcno);
    // bcno : 카테고리 식별번호 [ 0:전체 1:자유 2:노하우]
    pageObject.bcno = bcno;

    // 검색 제거
    pageObject.key = 'b.btitle';
    pageObject.keyword = '';
    document.querySelector('.key').value = 'b.btitle';
    document.querySelector('.keyword').value = '';

    // 카테고리 활성화 CSS
    let categoryBtns = document.querySelectorAll('.categoryBtn > button');
    console.log(categoryBtns);
    // 선택된 카테고리번호(매개변수 bcno)에 class 대입
    // categoryBtns[bcno].classList.add('클래스명')
    // categoryBtns[bcno].classList.remove('클래스명')
    for(let i=0; i<categoryBtns.length; i++){
        categoryBtns[i].classList.remove('categoryActive');
    }
    categoryBtns[bcno].classList.add('categoryActive');

    // 재출력
    doViewList(1);

}

// ==================== 4. 검색 ==================== //
function onSearch(){
    // 1. 입력받은 값 가져오기
    let key = document.querySelector('.key').value;
    console.log(key);
    let keyword = document.querySelector('.keyword').value;
    console.log(keyword);


    // 2. 서버에 전송할 객체에 담아주고
    pageObject.key = key;
    pageObject.keyword = keyword;
    console.log(pageObject.keyword);

    // 3. 출력
    doViewList(1);
}

function clickView(){
    console.log('clickView');
}

