console.log(' ◆ member.js ◆ ')

// ======================= 1. 회원가입 ======================= //
function signup(){  // FUNCTION START
    console.log(' ◆ signup() ◆ ');

    // * 유효성 검사 체크 현황 중에 하나라도 FLASE 이면 회원가입 금지
    for(let i=0; i<checkArray.length; i++){
        if(!checkArray[i]){
            alert('입력사항들을 모두 정확히 입력해주세요.')
            return;
        }
    }

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
        console.log(signUpFromData);    // * new FromData : 문자데이터가 아닌 바이트 데이터로 변환 ( 첨부파일 필수 -> 대용량 ) *
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

// ======================= 3. 미리보기 ======================= //
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

// ======================= 4. 아이디 유효성 검사 (아이디 입력 할 때 마다) ======================= //
// * 현재 유효성 검사의 체크 현황
let checkArray = [false, false, false, false, false];   // 아이디, 비밀번호, 이름, 전화번호, 이메일


function idCheck(){
        console.log('idCheck()');
    // 1. 입력된 데이터 가져오기
    let id = document.querySelector('#id').value;
        console.log(id);
    // 2. 정규 표현식 : 영소문자+숫자 조합의 5~30 글자 사이 규칙
    let idj = /^[a-z0-9]{5,30}$/

    // 3. 정규표현식에 따른 검사
    console.log(idj.test(id));
    if(idj.test(id)){
        // * 아이디 중복검사 ( DB ) -> AJAX
        $.ajax({
            url : "/member/find/idcheck",
            mehtod : "get", // HTTP BODY -> 없다 -> 쿼리스트링
            data : {'id':id}, // `/member/find/idcheck?id=${id}`
            success : (r)=>{
                if(r){
                    document.querySelector('.idCheckBox').innerHTML = '사용중인 아이디입니다.';
                    checkArray[0] = false;
                }else{
                    document.querySelector('.idCheckBox').innerHTML = '통과';
                    checkArray[0] = true;
                }   // IF END
            }   // SUCCESS END
        })  // AJAX END
    }else{
        document.querySelector('.idCheckBox').innerHTML = '영소문자+숫자 조합의 5~30글자 사이로 입력해주세요.';
        checkArray[0] = false;
    }
}

// ======================= 5. 비밀번호 유효성 검사 (비밀번호 입력 할 때 마다) ======================= //
function pwCheck(){
    console.log('pwCheck()');

    // 1. 입력값 가져온다.
    let pw = document.querySelector('#pw').value;
    let pwconfirm = document.querySelector('#pwconfirm').value;

    // 비밀번호에 대한 정규표현식 : 영대소문자 1개 필수와 숫자 1개 필수의 조합 5~30 글자
    
    let pwj = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/
    let msg = "통과";
    checkArray[1]=false;
    // 2. 유효성 검사
        if(pwj.test(pw)){
            if(pwj.test(pwconfirm)){
                if(pw==pwconfirm){
                    msg = "통과";
                    checkArray[1]=true;
                }else{
                    msg = "패스워드 불일치";
                }
            }else{
                msg = "영대소문자 1개 필수와 숫자 1개 필수의 조합 5~30 글자를 입력해주세요."
            }
        }else{
            msg = "영대소문자 1개 필수와 숫자 1개 필수의 조합 5~30 글자를 입력해주세요.";
        }
    document.querySelector(".pwcheckbox").innerHTML = msg;
}
// ======================= 6. 이름 유효성 검사 (이름 입력 할 때 마다) ======================= //
function namecheck(){
    console.log('namecheck()');

    // 입력 값을 가져온다.
    let name = document.querySelector('#name').value;
    
    let msg = '';
    checkArray[2]=false;
    // 정규표현식
    let namej = /^[가-힣]{5,20}$/
    // 정규표현식 검사한다
    if(namej.test(name)){
        msg = '통과';
        checkArray[2]=true;
    }else{
        msg = '한글 5~20글자를 입력해주세요.'
    }

    document.querySelector(".namecheckbox").innerHTML = msg;
}

// ======================= 7. 전화번호 유효성 검사 (전화번호 입력 할 때 마다) ======================= //
    // phone 중복검사
function phonecheck(){
    console.log('phonecheck()');

    let phone = document.querySelector('#phone').value;
    let phonej = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]+([0-9]{4})$/
    
    let msg = '000-000-000 또는 00-000-0000을 입력해주세요.';
    checkArray[3]=false;

    if(phonej.test(phone)){
        msg = '통과';
        checkArray[3]=true;
    }

    document.querySelector(".phonecheckbox").innerHTML = msg;
}

// ======================= 8. 이메일 유효성 검사 (이메일 입력 할 때 마다) ======================= //
function emailcheck(){
    console.log('emailcheck()');

    let email = document.querySelector('#email').value;

    let emailj = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-z]+$/

    let msg = '아이디@도메인을 입력해주세요.';
    checkArray[4]=false;
    authreqbtn.disabled = true; // 버튼 사용 금지
    
    if(emailj.test(email)){
        authreqbtn.disabled=false;     // 버튼 사용 가능  
        msg = '인증요청 가능' 
    }

    document.querySelector(".emailcheckbox").innerHTML = msg;
}

/*
    onclick : 클릭 할 때 마다
    onchange : 값이 바뀔 때 마다
    onkeyup : 키보드 키를 떼었을 때 마다

    정규 표현식
        특정한 규칙을 가진 문자열의 집합을 표현 할 때 사용하는 형식 언어
        주로 문자열 데이터를 검사할 때 사용 -> 유효성 검사
        메소드
            정규표현식.test(검사대상)
        형식 규칙
            /^ : 정규표현식 시작 알림
            $/ : 정규표현식 끝 알림
            { 최소길이, 최대길이 } : 허용 문자 길이 규칙
            [ 허용할 문자/숫자 ] : 허용 문자 규칙
                [a-z] : 소문자 a ~ z 허용
                [a-zA-z] : 영 대소문자 a ~ z 허용
                [a-zA-Z0-9] : 영 대소문자, 숫자 허용
                [a-zA-z0-9가-힣] : 영 대소문자, 숫자, 한글 허용
            + : 앞에 있는 패턴 1개 이상 반복
            ? : 앞에 있는 패턴 0개 혹은 1개 이상 반복
            * : 앞에 있는 패턴 0개 반복
            (?=.*[1개이상문자패턴])
            . : 1개 문자
            ( ) : 패턴의 그룹
            ?= : 앞에 있는 패턴 문자 존재 여부
            \ : 뒤에 있는걸 문자로 인식
*/
// ======================= 9. 이메일 인증 요청 (버튼을 클릭 할 때 마다) ======================= //

let timer = 0;
let authbox = document.querySelector('.authbox');   // 1. 인증 구역
let authreqbtn = document.querySelector('.authreqbtn'); // 2. 인증요청 버튼
let timerInter = null;
function authreq(){

    // 1. 인증 구역
    
    // 2. 인증 구역 구성
    let html = `<span class="timebox">00 : 00</span>
                <input type="text" class="ecodeinput"/>
                <button onclick="auth()" type="button">인증</button>`;

    // 3. 인증 구역 출력
    authbox.innerHTML = html;

    // ======= 자바에게 인증 요청 ======= //
    $.ajax({
        url: "/auth/email/req",
        mehtod: "get",
        data : {"email" : document.querySelector('#email').value},
        success: (r)=>{
            if(r){
                // 4. 타이머 함수
                timer = 10;
                ontimer();
                authreqbtn.disabled=true;
            }else{
                alert('시스템 오류');
            }
        }
    });
    // =============================== //



}

// ======================= 10. 타이머 함수 ======================= //
function ontimer(){
    // 테스트
    // 정의 : setInterval( 함수, 밀리초 ) 특정 밀리초마다 함수 실행
    // 종료 : clearInterval( 종료할 Interval 변수 ) : 종료할 Interval의 변수 대입

        timerInter = setInterval( ()=>{

        // 1. 초 변수를 분/초로 변환
        let m = parseInt( timer / 60 ); // 분
        let s = parseInt( timer % 60 ); // 분 제외한 초

        // 2. 시간을 두 자릿수로 표현
        m = m < 10 ? "0" + m : m;
        s = s < 10 ? "0" + s : s;

        // 3. 시간 출력
        document.querySelector('.timebox').innerHTML = `${m} : ${s}`;

        // 4. 초 감소
        timer--;

        // 5. 만약에 초가 0보다 작아지면
        if( timer < 0 ){
            clearInterval(timerInter);
            authbox.innerHTML='다시 인증 요청 해주세요.';
            authreqbtn.disabled=false;
        }

    },1000)
}

// ======================= 11. 인증 함수 ======================= //
function auth(){

    // 1. 내가 입력한 인증 번호
    let ecodeinput = document.querySelector('.ecodeinput').value;

    // 2. 내가 입력한 인증번호를 자바에게 보내기
    $.ajax({
        url : "/auth/email/check",
        method: "get",
        data : { 'ecodeinput' : ecodeinput },
        success : (r)=>{
            // 3. 성공 시 / 실패 시
            if(r){
                checkArray[4]=true;
                document.querySelector(".emailcheckbox").innerHTML = '통과';
                clearInterval(timerInter);
                authbox.innerHTML='';
                authreqbtn.disabled=false;
            }else{
                alert('인증번호가 다릅니다.')
            }
        } // SUCCESS END
    }); // AJAX END
}   // FUNCTION END