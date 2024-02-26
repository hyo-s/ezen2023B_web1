package ezenweb.model.dao;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberDao extends Dao {

// ====================== 1. 회원가입 처리 요청  ====================== //
    public boolean doPostSignUp(MemberDto memberDto){
        System.out.println(" ◆ Member [ Dao ].doPostSignUp ◆ ");
        System.out.println(" ┗▶ memberDto = " + memberDto);
        try {
            String sql = "insert into member(id,pw,name,email,phone,img) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            ps.setString(6, memberDto.getImg());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(" ※ MemberDao [ doPostSingUP ] ERROR ※ = " + e);
        }
        return false;
    }

// ====================== 2. 로그인 처리 요청  ====================== //
    public boolean doPostLogin(LoginDto loginDto){
        System.out.println(" ◆ Member [ Dao ].doPostLogin ◆ ");
        System.out.println(" ┗▶ loginDto = " + loginDto);
        try {
            String sql = "select * from member where id = ? and pw = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,loginDto.getId());
            ps.setString(2,loginDto.getPw());
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        }catch (Exception e){
            System.out.println(" ※ MemberDao [ doPostLogin ] ERROR ※ = " + e);
        }
        return false;
    }
}
