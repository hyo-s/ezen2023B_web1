package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    ArticleDao articleDao;


    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new")
    public String newArticleForm(){
        System.out.println("ArticleController.newArticleForm");
        return "articles/new";  // 확장자 빼고 resources/templates
    }

    // 2. 글쓰기 처리
    @PostMapping("/articles/create")
    public boolean createArticle(ArticleDto dto){
        System.out.println("ArticleController.createArticle");

        // 디버그 로그
        log.debug(dto.toString());
        // 경고 로그
        log.warn(dto.toString());
        // 에러 로그
        log.error(dto.toString());
        // 정보 로그
        log.info(dto.toString());

        System.out.println("dto = " + dto);

        boolean result = articleDao.createArticle(dto);

        return result;
    }

    // DAO에게 요청하고 응답받기

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