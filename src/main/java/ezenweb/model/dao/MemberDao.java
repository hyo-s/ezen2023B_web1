package ezenweb.model.dao;

import example.day05._1SET컬렉션.Member;
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
            ps.setString(6, memberDto.getUuidFile());
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

// ====================== 3. 회원 정보 요청  ====================== //
    public MemberDto doGetLoginInfo(String id){
        MemberDto memberDto = null;
        try {
            String sql = "select * from member where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                memberDto = new MemberDto(
                        rs.getInt(1),
                        rs.getString(2),
                        null,
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        null,
                        rs.getString(7));
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return memberDto;
    }
}
