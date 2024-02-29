package example.day11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
/*
    어노테이션
    @ServletComponentScan
        스프링 MVC 환경에서 서블릿을 단독적으로 사용할 때

    @SpringBootApplication
        스프링에서 웹에 관련된 기능을 주입
        1. 내장 아파치 톰캣 설정 / 실행
            Uses Apache Tomcat as the default embedded container
            IP : 톰캣이 설치된 컴퓨터의 IP 주소
            PORT : 톰캣(소프트웨어) 식별번호
                프로젝트내 src -> resources -> application.properties
                    server port = 80
                * application.properties : 스프링 설정파일 ( MAP 컬렉션 프레임워크 타입 )

            http://192.168.17.96:80
                HTTP 통신 규약을 이용한 톰캣에 요청

        2. including RESTFUL ( GET POST PUT DELETE )

        3. applications using Spring MVC ( CONTROLLER, VIEW, MODEL )
            컴포넌트 스캔해서 빈(객체)를 스프링 컨테이너에 등록
                [ AppStart 기준 ] 같은 패키지나 하위 패키지에 있는 클래스만 스캔 가능
                @Controller
                @RestController
                @Component
                등등

            스프링 실행 방법
            @SpringBootApplication
            public class AppStart {
                public static void main(String[] args) {
                    SpringApplication.run(AppStart.class);
                }
            }

    Spring MVC
        @Controller
            컨트롤러 역할 : DAO VIEW 사이의 통신 / 중계 역할
                컨트롤러가 VIEW에게 데이터(MODEL) 또는 화면(VIEW) 응답 할 수 있다.
                서버 사이드 렌더링 : 서버가 화면을 구성해준다.
            스프링 MVC 아키텍쳐로 구성된 스프링컨테이너(저장소)에 컨트롤러객체(빈)를 등록
                why ? 요청이 들어왔을 때 해당 요청에 클래스들을 찾으려고

        * HTTP 요청 매핑
        @RequestMapping( value = "URL", method = RequestMethod.XXX )
            GET : @RequestMapping ( value = "URL", method = RequestMethod.GET)
            POST : @RequestMapping ( value = "URL", method = RequestMethod.POST)
            PUT : @RequestMapping ( value = "URL", method = RequestMethod.PUT)
            DELETE : @RequestMapping ( value = "URL", method = RequestMethod.DELETE)
            주로 : 컨트롤 안에 있는 매핑 함수들의 URL 공통 매핑 할 때
            @RequestMapping("/URL")

        * HTTP 응답
        @ResponseBody : 응답(Response) 객체(Body) : 응답 데이터를 객체로 하겠다.
            String --> HTTP ContentType : text/plain
            컬렉션프레임워크 / 배열 / 기본타입 --> HTTP ContentType : application/json

        @RestController
            @Controller + @ResponseBody 포함
            컨트롤러가 VIEW에 데이터(MODEL)만 응답
            컨트롤러 사이드 렌더링 : 클라이언트 화면을 구성한다. [ 최초 화면 제외 ]

        @XXXMapping
            Restful
            POST : @PostMapping("URL")  : C 저장
            GET : @GetMapping("URL")    : R 조회
            PUT : @PutMapping("URL")    : U 수정/저장
            DELETE : @DeleteMapping("URL") : D 삭제

    HTTP 요청 시 매개변수 / 데이터를 보내는 방법
        < URL 경로 > GET POST PUT DELETE
        1. 쿼리스트링 : url?key = value & key = value
            식별이 가능

            키와 매핑함수의 매개변수명 동일
            @RequestParam("key") 매개변수 [ 권장 ]
            @RequestParam Map<String, String> map
            DTO ( 단 : DTO 필드명과 동일 )

        2. PathVariable : url/value
            식별이 불가능

            @PathVariable
                * 매핑함수Mapping("URL/{매개변수}")
                @PathVariable("URL매개변수명") 매개변수

        < HTTP BODY > : HTTP 안에 내용을 첨부 할 수 있는 보관소
            POST / PUT : BODY 사용 함
                회원가입, 로그인, 글쓰기
            GET / DELETE : BODY 사용 안함
                글 전체 목록, 글 개별 조회, 글 삭제
        ContentType
            1. FORM [ AJAX 기본형태 ]
                쿼리스트링 동일
            2. APPLICATION / JSON
                @RequestBody

        MVC 패턴 1
        MVC 패턴 2
        MVC 패턴 2 3티어 ( 스프링 MVC 아키택쳐 패턴 )

                        ==톰캣===========================================================================================
        [ 클라이언트 ]       [ VIEW ]                 [ CONTROLLER ]         [ SERVICE ]         [ REPOSITORY ]    [ DB ]
                        resources                       @RestController      @Service           @Repository
                        static 정적                                                              @Component
                        (HTML,CSS,JS,IMG,MP4 등등)
                        templates 동적
                        (머스테치)
                        ================================================================================================


===============================================================================================
    AJAX
        비동기 통신 메소드
        jQuery ( JS 라이브러리 )

        사용방법
            1. HTML에서 JQuery 라이브러리 호출한다.
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            2. JS에서 AJAX 작성

        기본문법
            $.ajax( );
            $.ajax( { } );
            $.ajax( { url : " ", method : " " } );

        AJAX 정보 객체 생성
            1. url : "URL"
            2. method : "HTTP METHOD"
            3. success : ( ) => { } HTTP RESPONSE
                function 함수명 ( ){ }
                function( ){ }
                ( ) => { }
            4. error : "HTTP ERROR MSG"
            5. data : "HTTP SEND DATA"
                METHOD : GET,DELETE -> 쿼리스트링
                METHOD : POST,PUT -> BODY(본문)
            7. content type :
                일반 FORM        : [ DEFAULT ] application/x-www-form-urlencoded
                첨부파일 FORM     : [ FALSE ] multipart/form-data
                JS JSON         : application/json
            8. 첨부파일( 대용량 파일) 시 추가 속성
                processData : false 문자형식이 아닌 바이트 형식으로 보내는 방법
            9. async : AJAX 통신 상태를 동기화( 응답이 오기 전까지 대기 상태 ) 비동기화 ( 응답상태를 대기 안함 )
                async : true 기본값 ( 비동기 )
                async : false ( 동기화 )





*/