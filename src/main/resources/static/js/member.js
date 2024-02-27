console.log(' ◆ member.js ◆ ')

// ======================= 1. 회원가입 ======================= //
function signup(){  // FUNCTION START
    console.log(' ◆ signup() ◆ ');

    // 1. HTML 입력값 호출 [ document.querySelector() ]
    // 1. 데이터 하나씩 요청
        // let id = document.querySelector('#id').value;           console.log('  id  : ' + id);
        // let pw = document.querySelector('#pw').value;           console.log('  pw  : ' + pw);
        // let name = document.querySelector('#name').value;       console.log('  name  : ' + name);
        // let phone = document.querySelector('#phone').value;     console.log('  phone  : ' + phone);
        // let email = document.querySelector('#email').value;     console.log('  email  : ' + email);
        // let img = document.querySelector('#img').value;         console.log('  img  : ' + img);
    // 2. 폼 가져오기
    let signUpFrom = document.querySelectorAll('.signUpForm');
        console.log(signUpFrom);
    let signUpFromData = new FormData(signUpFrom[0]);
        console.log(signUpFromData);    // new FromData : 문자데이터가 아닌 바이트 데이터로 변환 ( 첨부파일 필수 )
    // === 유효성검사

    // 2. 객체화 [ let info = { } }
    // let info = {
    //     id : id, pw : pw, name : name, phone : phone, email : email, img : img
    // };
    //     console.log(" ┗━▶ info : " + info);

    // 3. SPRING CONTROLLER 통신 [ JQUERY AJAX ]
    $.ajax({    // AJAX START
        url : '/member/signup',             // 어디에
        method : 'post',                    // 어떻게
        // data : info
        data : signUpFromData,                        // 무엇을 보내고 입력 받은 값 보내기
        contentType : false,
        processData : false,
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

// ======================= 2. 로그인 ======================= //
function onChangeImg(event){
    console.log('preImg()');
    console.log(event); // 현재 함수를 실행한 input
    console.log(event.files); // 현재 input의 첨부파일들
    console.log(event.files[0]); // 첨부파일들 중에서 0번째

    // 현재 input에 업로드 된 파일을 바이트로 가져오기
        // new FileReader(); 파일 읽기 관련 메소드 제공

    // 1. 파일 읽기 객체 생성
    let fileReader = new FileReader();
    // 2. 파일 읽기 메소드
    fileReader.readAsDataURL(event.files[0]);
    console.log(fileReader);
    console.log(fileReader.result);
    // 3. 파일 onload 정의
    fileReader.onload = e=>{
        console.log(e) // ProgressEvent
        console.log(e.target); // FileReader
        console.log(e.target.result); // FileReader의 result
        document.querySelector('#preimg').src=e.target.result;
    }
}
/*
    함수 정의 방법
        1. function 함수명(매개변수){ }
        2. function(매개변수){ }
            let 변수명 = function(매개변수){ }
            let 변수명 = {
                e : function(매개변수){ }
            }
        3. (매개변수)=>{ } 람다식
*/