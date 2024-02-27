package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ====================== 스프링부트 실행 ====================== //
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}

// favicon.ico static 폴더에 ico 파일 넣기

/*
    세션 : 아파치 톰캣(서버) 안에 저장소
        HTTP 요청에 따른 브라우저 마다의 저장소
        주로 로그인 정보, 비회원제 데이터 ( ex 장바구니, 결제 )
        생명주기 : 서버가 끄면 사라짐 또는 세션 객체 속성들을 초기화
        단점 : 많이 사용하면 웹 서버에 과부하
            메소드
                HTTPServletRequest request 객체
                1. 세션 객체 호출
                    request.getSession( )
                2. 세션 객체의 속성 추가
                    request.getSession( ).setAttribute( "key", value );
                3. 세션 객체의 속성 호출
                    request.getSession( ).getAttribute( "key" );
                4. 세션 객체의 속성들을 초기화
                    모든 세션
                    request.getSession().invalidate( );
                    request.getSession().setAttribute( "속성명",null );
                5. 세션 객체의 속성들의 데이터 생명주기
                    request.getSession().setMaxInactiveInterval( 생명시간 );
    저장
        JAVA 지역변수 VS 전역변수
        DB 처리
        세션처리
        파일처리
        쿠키처리
        JS 세션처리

    쿠키 : 클라이언트( 브라우저 )에 저장소
        단점 : 보안 취약
        비회원제 데이터( 장바구니 ,결제 ) 보안 X
*/