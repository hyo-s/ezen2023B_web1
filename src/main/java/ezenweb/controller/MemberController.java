package ezenweb.controller;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.UpdateDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    // 1. VIEW <---> CONTROLLER HTTP 통신방식 설계
    // 2. CONTROLLER MAPPING 체크 ( API TESTER )
    // 3. CONTROLLER REQUEST 매개변수 매핑
    // 4. 응답 : 뷰 반환 TEXT/HTML ( "/MUSTACHE" ) vs 데이터/값 APPLICATION/JSON ( @ResponseBody )

    // ====================== 1. 회원가입 처리 요청 [ POST ]  ====================== //
    @PostMapping("/member/signup") // http://localhost:8080/member/signup
    @ResponseBody // 응답방식 APPLICATION/JSON
    public boolean doPostSignup(MemberDto memberDto){
        System.out.println("MemberController.doPostSignup");
        System.out.println("memberDto = " + memberDto);
        boolean result = true; // DAO처리
        return result;  // DAO 요청 후 응답 결과를 보내기
    }

    // ====================== 2. 로그인 처리 요청 [ POST ] ====================== //
    @PostMapping("/member/login") // http://localhost:8080/member/login
    @ResponseBody // 응답방식 APPLICATION/JSON
    public boolean doPostLogin(LoginDto loginDto){
        System.out.println("MemberController.doPostLogin");
        System.out.println("loginDto = " + loginDto);
        boolean result = true;  // DAO처리
        return result;  // DAO 요청 후 응답 결과를 보내기
    }

    // ====================== 3. 회원가입 페이지 요청 [ GET ] ====================== //
    @GetMapping("/member/signup") // http://localhost:8080/member/signup    // 응답방식 뷰 반환 TEXT/HTML
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezenweb/signup";
    }

    // ====================== 4. 로그인 페이지 요청 [ GET ] ====================== //
    @GetMapping("/member/login") // http://localhost:8080/member/login  // 응답방식 뷰 반환 TEXT/HTML
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezenweb/login";
    }

    // ====================== 5. 회원번호 요청 [ GET ] ====================== //
    @GetMapping("/member/{no}/update")
    public String findByNo(@PathVariable("no") int no){
        System.out.println("MemberController.findByNo");
        System.out.println("no = " + no);
        return "ezenweb/update";
    }

    // ====================== 6. 회원 수정 요청 [ POST ] ====================== //
    @PostMapping("/member/update")
    @ResponseBody
    public boolean doPostUpdate(UpdateDto updateDto){
        System.out.println("MemberController.doPostUpdate");
        System.out.println("updateDto = " + updateDto);
        boolean result = true;
        return result;
    }

    // ====================== 7. 회원 탈퇴 요청 [ GET ] ====================== //
    @GetMapping("/member/{no}/delete")
    public String delete(@PathVariable("no") int no){
        System.out.println("MemberController.delete");
        System.out.println("no = " + no);
        return "ezenweb/delete";
    }
}
