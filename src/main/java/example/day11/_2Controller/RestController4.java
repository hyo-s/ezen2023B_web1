package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping(value = "day11")    // 해당 클래스 내 공통 URL
public class RestController4 {

    // 1.
    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답1";
    }

    // 2. 경로 상 변수 활용한 매개변수 요청 받기
    @GetMapping("/ajax2/{id}/{content}")
    public String ajax2(@PathVariable("id") int id, @PathVariable("content") String content){
        System.out.println("RestController4.ajax2");
        System.out.println("id = " + id);
        System.out.println("content = " + content);
        return "응답2";
    }

    // 3. 경로 상 쿼리스트링 포함하기
    @GetMapping("/ajax3")
    public String ajax3(int id, @RequestParam("content") String content){
        System.out.println("RestController4.ajax3");
        System.out.println("id = " + id);
        System.out.println("content = " + content);
        return "응답3";
    }

    // 4. MAP @RequestParam
    @GetMapping("/ajax4")
    public String ajax4(@RequestParam Map<String, String> map){
        System.out.println("RestController4.ajax4");
        System.out.println("map = " + map);
        return "응답4";
    }

    // 5. DTO
    @GetMapping("/ajax5")
    public String ajax5(AjaxDto ajaxDto){
        System.out.println("RestController4.ajax5");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답5";
    }

    // 6. 본문
    @GetMapping("/ajax6")
    public String ajax6(int id, @RequestParam("content") String content){
        System.out.println("RestController4.ajax6");
        System.out.println("id = " + id + ", content = " + content);
        return "응답6";
    }

    // 7. MAP
    @GetMapping("/ajax7")
    public String ajax7(@RequestParam Map<String, String> map){
        System.out.println("RestController4.ajax7");
        System.out.println("map = " + map);
        return "응답7";
    }

}
