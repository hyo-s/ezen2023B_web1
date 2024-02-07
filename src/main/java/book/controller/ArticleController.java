package book.controller;

import book.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";  // 확장자 빼고 resources/templates
    }

    // 2. 글쓰기 처리
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println("ArticleController.createArticle");

        System.out.println("form = " + form);
        return "";
    }
}
/*
    @ 어노테이션
    1. 표준 어노테이션 : 자바에서 기본적으로 제공
        @Override : 메소드 재정의
    2. 메타 어노테이션 : P.64
        소스코드에 추가해서 사용하는 메타 데이터
        메타 데이터 : 구조화된 데이터
        컴파일 또는 실행 했을때 코드를 어떻게 처리 해야 할지 알려주는 정보
        @SpringBootApplication
            1. 내장 서버(톰캣) 지원
            2. 스프링 MVC 패턴 내장
                view : resources
                CONTROLLER : @Controller, @RestController
                SERVICE : @Service
                MODEL(DAO) : @Repository
        @Controller
        @GetMapping

*/