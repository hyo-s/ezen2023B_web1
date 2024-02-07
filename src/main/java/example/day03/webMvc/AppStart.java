package example.day03.webMvc;   // PACKAGE NAME

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// ----------------------- 스프링부트 실행에 관련 기능 주입 ----------------------- //
// 1. 내장 서버(톰캣) 실행
// 2. ************** 동등한 패키지 혹은 하위 패키지내
// @Controller @RestController 들을 찾아서 스캔
@SpringBootApplication
public class AppStart { // CLASS START
    public static void main(String[] args) {    // MAIN START
        SpringApplication.run(AppStart.class);
    }   // MAIN END
}   // CLASS END
/*
    1.

*/