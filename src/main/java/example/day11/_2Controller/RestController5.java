package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping( value = "/day11")
public class RestController5 {

    // ========= 경로상의 변수 : 쿼리스트링 [ GET, POST, PUT, DELETE ] ========= //
        // 글 등록, 글 조회, 글 수정, 글 삭제  => GET 다 가능
        // 쿼리스트링 : URL 상에 데이터 / 매개변수가 표시됨
        // 캐시 ( 기록 ) 남기 => 장점 : 빠르다, 단점 : 노출

    // ========= CONTENT TYPE : FORM ========= //
        // URL 상에 데이터 / 매개변수 표시안함 => HTTP BODY(본문) 이용, POST / PUT 가능
        // POST, PUT [ FORM, JSON ]
    @PostMapping("/ajax8")
    public String ajax8(int id, @RequestParam String content){
        System.out.println("RestController5.ajax8");
        System.out.println("id = " + id + ", content = " + content);
        return "응답8";
    }

    @PostMapping("/ajax9")
    public String ajax9(@RequestParam Map<String, String> map){
        System.out.println("RestController5.ajax9");
        System.out.println("map = " + map);
        return "응답9";
    }

    @PostMapping("/ajax10")
    public String ajax10(AjaxDto ajaxDto){
        System.out.println("RestController5.ajax10");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답10";
    }

    // ========= CONTENT TYPE : APPLICATION/JSON ========= //
    @PostMapping("/ajax11")
    public String ajax11(@RequestBody AjaxDto ajaxDto){
        System.out.println("RestController5.ajax11");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답11";
    }

    @PostMapping("/ajax12")
    public String ajax12(@RequestBody Map<String, String> map){
        System.out.println("RestController5.ajax12");
        System.out.println("map = " + map);
        return "응답12";
    }
}
