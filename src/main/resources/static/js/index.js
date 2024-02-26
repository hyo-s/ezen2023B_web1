// 모든 페이지에서 적용할 공통 JS
console.log(' ◆ index.js ◆ ')

// 1. 로그인 여부 확인 요청 [ JS 열렸을 때 마다 ]
$.ajax({
    url : "/member/login/check",
    method : "get",
    success : (r)=>{
        console.log(' ┗━▶ r : ' + r);
        // 1. 어디에
        let login_menu = document.querySelector('#login_Menu');
        // 2. 무엇을
        let html = '';

        if( r != '' ){ // 로그인 했을때
            html +=`
                <li class="nav-item">
                    <a class="nav-link" onclick='logout()'>로그아웃</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">마이페이지</a>
                </li>
                <li class="nav-item">
                    <img src="#">
                    ${r}님
                </li>
            `;
        }else{ // 로그인 안했을때
            html +=`
                <li class="nav-item">
                    <a class="nav-link" href="/member/login">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/member/signup">회원가입</a>
                </li> `;
        }
        // 3. 대입
        login_menu.innerHTML = html;
    }
})

// ======================= 로그아웃 ======================= //
function logout(){   // FUNCTION START
    console.log(' ◆ logout() ◆ ');

    // SPRING CONTROLLER 통신 [ JQUERY AJAX ]
    $.ajax({    // AJAX START
        url : '/member/logout',
        method : 'get',
        success : function ( result ){
            console.log(" ┗━▶ success : " + result);
            // 4. 결과
            if(result){
                alert(' ◆ 로그아웃 성공 ◆ ')
                    // JS 페이지 전환 로그인 성공 시 메인 페이지
                location.href="/index";
            }else{
                alert(' ※ 로그아웃 실패 ※ ');
            }
        }
    })  // AJAX END
}   // FUNCTION END
