package ezenweb.service;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

// @Component
@Service // 해당 클래스를 스프링 컨테이너에 등록
public class MemberService {

    @Autowired
    private FileService fileService;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private EmailService emailService;

    // 1. 회원가입 서비스
    public boolean doPostSingUp(MemberDto memberDto){

        /*
            만약에
                1. 첨부파일이 있다 없다
                    있다 ( 업로드 성공 했다 vs 실패 했다 )
                        성공 DB 처리
                        실패 일단 false
                    없다
                        DB 처리
        */
        // 1. 파일처리
            // 업로드 된 파일 이름
        String fileName = "default.jpg";

        if(!memberDto.getImg().isEmpty()){
            fileName = fileService.fileUpload(memberDto.getImg());
            if( fileName == null){
                // 2. DB 처리
                // DTO에 업로드 성공한 파일명을 대입한다.
                return false;
            }
        }
        memberDto.setUuidFile(fileName);
        // =========== 테스트 =========== //
        boolean result = memberDao.doPostSignUp(memberDto);

        return result;
    }

    // 2. 로그인 된 회원정보 요청 서비스
    public MemberDto doGetLoginInfo( String id ){
        // 1. DAO 호출
        return memberDao.doGetLoginInfo( id );
    }

    // 3. 아이디 중복 체크 요청
    public boolean doGetFindIdCheck( String id ){
        System.out.println("MemberController.doGetFindIdCheck");
        System.out.println("id = " + id);
        return memberDao.doGetFindIdCheck(id);
    }

}   // CLASS END
