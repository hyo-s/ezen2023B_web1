package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.UpdateDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberDao memberDao;

    // 1. VIEW <---> CONTROLLER HTTP 통신방식 설계
    // 2. CONTROLLER MAPPING 체크 ( API TESTER )
    // 3. CONTROLLER REQUEST 매개변수 매핑
    // 4. 응답 : 뷰 반환 TEXT/HTML ( "/MUSTACHE" ) vs 데이터/값 APPLICATION/JSON ( @ResponseBody )

    // ====================== 1. 회원가입 처리 요청 [ POST ]  ====================== //
    @PostMapping("/member/signup") // http://localhost:8080/member/signup
    @ResponseBody // 응답방식 APPLICATION/JSON
    public boolean doPostSignup(MemberDto memberDto){
        System.out.println(" ◆ Member [ Controller ].doPostSignup ◆");
        System.out.println(" ┗▶ memberDto = " + memberDto);
        boolean result = memberDao.doPostSignUp(memberDto); // DAO처리
        return result;  // DAO 요청 후 응답 결과를 보내기
    }

    // ====================== 2. 로그인 처리 요청 [ POST ] / 세션 저장 ====================== //
    @PostMapping("/member/login") // http://localhost:8080/member/login
    @ResponseBody // 응답방식 APPLICATION/JSON
    public boolean doPostLogin(LoginDto loginDto){
        System.out.println(" ◆ Member [ Controller ].doPostLogin ◆");
        System.out.println(" ┗▶ loginDto = " + loginDto);
        boolean result = memberDao.doPostLogin(loginDto);  // DAO처리
        // * 로그인 성공 시 ( 세션 )
            // 세션 저장소 : 톰캣 서버에 * 브라우저마다 메모리 할당
            // 세션 객체 타입 : Object ( 여러가지의 타입들을 저장하려고 )
            // 1. HTTP 요청 객체 호출 HttpServletRequest
            // 2. HTTP 세션 객체 호출 .getSession( )
            // * 3. HTTP 세션 데이터 [ 저장 ] .setAttribute( "세션명","데이터" ) -- 자동 형변환 ( 자 --> 부 )
        if(result){ // 만약에 로그인 성공이면 세션 부여
            request.getSession().setAttribute("loginDto",loginDto.getId());
        }
        return result;  // DAO 요청 후 응답 결과를 보내기
    }
    // ====================== 2-2. 로그인 여부 확인 요청 [ GET ] / 세션 호출 ====================== //
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // * 로그인 여부 확인 = 세션이 있다 없다 [ 확인 ]
            // 1. HTTP 요청 객체 호출
            // 2. HTTP 세션 객체 호출 .getSession( )
            // * 3. HTTP 세션 데이터 [ 호출 ] .getAttribute( "세션명" ) -- 강제 형변환 ( 부 --> 자 )
            // NULL 형변환 불가 유효성 검사 필요
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj != null){
            loginDto = (String)sessionObj;
        }
        // 만약에 로그인 했으면 ( 세션에 데이터가 있으면 ) 강제 형변환을 통해 데이터 호출
        return loginDto;
    }
    // ====================== 2-2. 로그아웃 [ GET ] / 세션 초기화 ====================== //
    @GetMapping("/member/logout")
    @ResponseBody
    public boolean doGetLogOut(){
        // 1. 로그인 세션 초기화
            // 방법 1. 현재 요청 보낸 브라우저의 모든 세션 초기화 .invalidate( )
        request.getSession().invalidate();
            // 방법 2. 특정 세션속성 초기화 세션 호출 후 NULL 대입
        // request.getSession().setAttribute("loginDto",null);

        return true;
    }




    // ====================== 3. 회원가입 페이지 요청 [ GET ] ====================== //
    @GetMapping("/member/signup") // http://localhost:8080/member/signup    // 응답방식 뷰 반환 TEXT/HTML
    public String viewSignup(){
        System.out.println(" ◆ Member [ Controller ].viewSignup ◆");
        return "ezenweb/signup";
    }

    // ====================== 4. 로그인 페이지 요청 [ GET ] ====================== //
    @GetMapping("/member/login") // http://localhost:8080/member/login  // 응답방식 뷰 반환 TEXT/HTML
    public String viewLogin(){
        System.out.println(" ◆ Member [ Controller ].viewLogin ◆");
        return "ezenweb/login";
    }

    // ====================== 5. 회원번호 요청 [ GET ] ====================== //
    @GetMapping("/member/{no}/update")
    public String findByNo(@PathVariable("no") int no){
        System.out.println(" ◆ Member [ Controller ].findByNo ◆o");
        System.out.println(" ┗▶ no = " + no);
        return "ezenweb/update";
    }

    // ====================== 6. 회원 수정 요청 [ POST ] ====================== //
    @PostMapping("/member/update")
    @ResponseBody
    public boolean doPostUpdate(UpdateDto updateDto){
        System.out.println(" ◆ Member [ Controller ].doPostUpdate ◆");
        System.out.println(" ┗▶ updateDto = " + updateDto);
        boolean result = true;
        return result;
    }

    // ====================== 7. 회원 탈퇴 요청 [ GET ] ====================== //
    @GetMapping("/member/{no}/delete")
    public String delete(@PathVariable("no") int no){
        System.out.println(" ◆ Member [ Controller ].delete ◆");
        System.out.println(" ┗▶ no = " + no);
        return "ezenweb/delete";
    }
}
