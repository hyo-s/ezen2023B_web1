package book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // * 이 클래스가 컨트롤러임을 선언
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){   // 인수에 model 매개변수 선언

        model.addAttribute("username","홍팍");

        // return " MUSTACHE 파일명";
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        // MUSTACHE에 전달할 변수 등록
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }

}
