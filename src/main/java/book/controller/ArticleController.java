package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleDto;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    ArticleDao articleDao;


    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new")    // GET 방식
    public String newArticleForm(){
        System.out.println("ArticleController.newArticleForm");
        return "/articles/new";  // 확장자 빼고 resources/templates
    }

    // 2. 글쓰기 처리
    @PostMapping("/articles/create")
    public String createArticle(ArticleDto dto){
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

        ArticleDto result = articleDao.createArticle(dto);

        return "/redirect:/articles/" + result.getId();
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
    public String show(@PathVariable long id, Model model){
        System.out.println("ArticleController.show");
        System.out.println("id = " + id);
        // 1. 요청된 id를 가지고 DAO에게 데이터 요청
        ArticleForm form = articleDao.show(id);
        // 2. DAO에게 전달받은 값을 뷰 템플릿에게 전달하기
        model.addAttribute("article",form);
        model.addAttribute("name","유재석");
        // 3. 해당 함수가 종료될 때 리턴값 1. 화면 / 뷰 ( 머스테치, HTML ) 2. 값( JSON / 자바객체 )
        return "/articles/show";
    }
        // [ 전체 조회 ]

    @GetMapping("/articles")
    public String index(Model model){
        // 1. DAO에게 요청해서 데이터 가져온다.
        List<ArticleForm> result = articleDao.index();
        // 2. 뷰 템플릿(머스테치)에게 전달할 값을 model 담아준다.
        model.addAttribute("articleList",result);
        // 3. 뷰 페이지 설정
        return "/articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model){
        System.out.println("id = " + id);
        // 1. 기존 데이터 불러오기 DAO 요청
        ArticleForm form = articleDao.findById(id);
        // 2. 응답결과를 뷰 템플릿에게 보낼 준비 model;
        model.addAttribute("article",form);
        // 3. 뷰 페이지 설정
        return "/articles/edit";
    }

    @PostMapping("/articles/update") // @PatchMapping @PutMapping
    // 수정 데이터 받아오기
    public String update(ArticleForm form){
        // * form 입력 데이터를 Dto 매개변수로 받을 때
            // 1. form 입력상자의 name과 Dto 필드명 동일
            // 2. Dto의 필드 값을 저장할 생성자 필요
        System.out.println("form = " + form);
        // 2. DAO 요청하고 응답받기
        ArticleForm updated = articleDao.update(form);
        // 수정 처리 된 상세페이지로 이동
        return "redirect:/articles/" + updated.getId();
    }
    // @PathVariable : 요청한 HTTP URL 경로상의 매개변수 대입
        // URL : /articles/{매개변수명}/edit
        // JAVA 함수( @PathVariable("URL매개변수명") 타입 매개변수명 )
        // URL 매개변수명 생략 시 함수의 매개변수명과 일치할 경우 자동 대입

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable("id") long id){
        System.out.println("id = " + id);
        // 1. 삭제할 대상
        // 2. DAO 삭제 요청하고 응답받기
        boolean result = articleDao.delete(id);
        // 3. 결과 페이지로 리다이렉트 하기.
        return "redirect:/articles";
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