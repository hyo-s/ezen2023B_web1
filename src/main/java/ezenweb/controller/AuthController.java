package ezenweb.controller;

import ezenweb.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController // @Controller + @ResponseBody
@RequestMapping("/auth") // 해당 클래스의 매핑 ( 주로 해당 클래스내 함수들의 매핑주소 중에 공통 )
public class AuthController {

    // 세션 객체 호출
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;

    @GetMapping("/email/req")
    public boolean getEmailReq(@RequestParam String email){
        System.out.println("AuthController.getEmailReq");

        // 1. 난수 이용한 6자리 인증 코드 발급
        Random random = new Random();
        String ecode = "";
        for( int i = 1; i<=6; i++){
            ecode += random.nextInt(10); // 0~9 // .nextInt(미만) + 시작번호
        }
        System.out.println("ecode = " + ecode);
        // 2. HTTP 세션에 특정 시간만큼 인증코드 보관
            // 1. 세션에 속성 추가 해서 발급된 인증코드 대입하기
        request.getSession().setAttribute("ecode",ecode);
            // 2. 세션에 생명주기
        request.getSession().setMaxInactiveInterval(10); // 초단위 // 10초 동안 세션유 지하고 10초 후 삭제
        // 3. 발급된 인증코드를 인증할 이메일로 전송
        emailService.send(email,"인증번호입니다.","인증코드 : "+ecode);
        return true;
    }

    @GetMapping("/email/check")
    public boolean getEmailCheck(@RequestParam String ecodeinput){
        System.out.println("AuthController.getEmailCheck");
        System.out.println("ecodeinput = " + ecodeinput);

        // 1. HTTP 세션테 보관했던 인증코드를 꺼내서
            // 세션 속성 호출
        Object object = request.getSession().getAttribute("ecode");
            // 만약 세션 속성이 존재하면
        if(object != null){
            String ecode = (String)object;
            // 2. 입력받은 인증코드와 생성된 인증코드와 일치 여부
            // 1. 발급된 인증코드와 입력받은 인증코드와 같으면
            if(ecodeinput.equals(ecode)){
                return true;
            }
        }
        return false;
    }
}
