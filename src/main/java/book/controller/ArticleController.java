package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleDto;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    // 조회
        // [ 개별 조회 ]
        // 1. 클라이언트가 서버 요청 시 id / 식별키
        // 2. HTTP URL 이용한 요청 /articles/1, /articles/2
        // 3. 서버 Controller 요청 URL 매핑 / 연결 받기
        // 4. @GetMapping("/articles/{id}")
        // 5. 함수 매개변수에서 URL
        // 6. 함수 매개변수 앞에 @PathVariable
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        System.out.println("ArticleController.show");
        System.out.println("id = " + id);
        // 1. 요청된 id를 가지고 DAO에게 데이터 요청
        ArticleForm form = articleDao.show(id);
        // 2. DAO에게 전달받은 값을 뷰 템플릿에게 전달하기
        model.addAttribute("article",form);
        model.addAttribute("name","유재석");
        // 3. 해당 함수가 종료될 때 리턴값 1. 화면 / 뷰 ( 머스테치, HTML ) 2. 값( JSON / 자바객체 )
        return "articles/show";
    }
        // [ 전체 조회 ]

    @GetMapping("/articles")
    public String index(Model model){
        // 1. DAO에게 요청해서 데이터 가져온다.
        List<ArticleForm> result = articleDao.index();
        // 2. 뷰 템플릿(머스테치)에게 전달할 값을 model 담아준다.
        model.addAttribute("articleList",result);
        // 3. 뷰 페이지 설정
        return "articles/index";
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