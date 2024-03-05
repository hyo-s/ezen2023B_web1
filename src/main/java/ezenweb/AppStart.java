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

    첨부파일 전송 시
        1. 폼 가져온다
            let signUpFrom = document.querySelectorAll('.signUpForm');
        2. 폼 객체화
            let signUpFromData = new FormData(signUpFrom[0]);
                * new FromData : 문자데이터가 아닌 바이트 데이터로 변환 *
        3. AJAX 대용량 폼 전송 속성
            data : signUpFromData,
            contentType : false,
            processData : false,

        ( 스프링 ) 컨트롤러에서 첨부파일 매핑할 때 = MultipartFile
            전제조건 : DTO MultipartFile 존재해야 함
        4. MultipartFile 객체를 서비스로 보낸다.
        5. 서비스가 MultipartFile 업로드 처리
            [ 어디에 ]
            File file = new File(uploadPath+fileName);
            [ 무엇을 ]
            multipartFile.transferTo(file);

       * 업로드 된 파일들을 DB 처리 ( 파일 자체를 DB 처리 하지는 않음 )


    <회원제> 카테고리 게시판 계층형 댓글
    카테고리 테이블
        카테고리 번호
        카테고리 이름

    게시판 테이블
        게시판 번호
        게시판 제목
        게시판 내용
        게시판 작성자 ( 회원번호 FK )
        게시판 첨부파일(1개)
        조회수
        작성일

    (계층형) 댓글 테이블
        댓글 번호
        댓글 내용
        댓글 작성자 ( 회원번호 FK )
        게시판 번호 ( 게시판 번호 FK )
        댓글 작성일
        댓글 인덱스 * ( 대댓글 사용되는 인덱스 ) / 댓글 위치
            0 : 최상위(루트) 댓글      (댓글)
            그외 : 상위 댓글 댓글번호   (대댓글)

            rno : 1 rindex : 0  댓글
            rno : 2 rindex : 1  1번 댓글에 1번째 대댓글
            rno : 3 rindex : 0  댓글
            rno : 4 rindex : 1  1번 댓글에 2번째 대댓글
            rno : 5 rindex : 2  1번 댓글에 1번째 대댓글에 댓글

    < 권장 >
        1. 테이블 1개당 PK 1개 이상
        2. 테이블 레코드 생성일 + 변경일 필드 ( JPA 자동 )
        3. 테이블 들의 레코드 로그 / 이벤트 log ( DB, FILE )

    < 제약조건 >
    1. PK FK
        FK 필드 - not null
    2. 제약조건
    3. 정수타입에서 양수 판단 : unsigned 키워드 효율

    (데이터 많을때) 페이징처리 vs 무한 스크롤
    1. 페이징처리

*/