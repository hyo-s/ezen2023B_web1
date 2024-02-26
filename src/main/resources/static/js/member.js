console.log(' ◆ member.js ◆ ')

// ======================= 1. 회원가입 ======================= //
function signup(){  // FUNCTION START
    console.log(' ◆ signup() ◆ ');

    // 1. HTML 입력값 호출 [ document.querySelector() ]
    let id = document.querySelector('#id').value;           console.log('  id  : ' + id);
    let pw = document.querySelector('#pw').value;           console.log('  pw  : ' + pw);
    let name = document.querySelector('#name').value;       console.log('  name  : ' + name);
    let phone = document.querySelector('#phone').value;     console.log('  phone  : ' + phone);
    let email = document.querySelector('#email').value;     console.log('  email  : ' + email);
    let img = document.querySelector('#img').value;         console.log('  img  : ' + img);

    // === 유효성검사

    // 2. 객체화 [ let info = { } }
    let info = {
        id : id, pw : pw, name : name, phone : phone, email : email, img : img
    };
    console.log(" ┗▶ info : " + info);

    // 3. SPRING CONTROLLER 통신 [ JQUERY AJAX ]
    $.ajax({    // AJAX START
        url : '/member/signup',             // 어디에
        method : 'post',                    // 어떻게
        data : info,                        // 무엇을 보내고 입력 받은 값 보내기
        success : function ( result ){      // 무엇을 받을지 통신 후 응답받은 값
            console.log(" ┗━▶ success : " + result);
            // 4. 결과
            if(result){
                alert(' ◆ 회원가입 성공 ◆ ');
                location.href='/member/login';
            }else{
                alert(' ※ 회원가입 실패 ※ ');
            }
        }
    })  // AJAX END
}   // FUNCTION END

// ======================= 2. 로그인 ======================= //
function login(){   // FUNCTION START
    console.log(' ◆ login() ◆ ');

    // 1. HTML 입력값 호출
    let id = document.querySelector('#id').value;           console.log(' id  : ' + id);
    let pw = document.querySelector('#pw').value;           console.log(' pw  : ' + pw);

    // 2. 객체화
    let info = {
        id : id, pw : pw
    };
    console.log(" ┗━▶ info : " + info);

    // 3. SPRING CONTROLLER 통신 [ JQUERY AJAX ]
    $.ajax({    // AJAX START
        url : '/member/login',
        method : 'post',
        data : info,
        success : function ( result ){
            console.log(" ┗━▶ success : " + result);
            // 4. 결과
            if(result){
                alert(' ◆ 로그인 성공 ◆ ')
                    // JS 페이지 전환 로그인 성공 시 메인 페이지
                location.href="/index";
            }else{
                alert('※ 로그인 실패 ※ ');
            }
        }
    })  // AJAX END
}   // FUNCTION END

