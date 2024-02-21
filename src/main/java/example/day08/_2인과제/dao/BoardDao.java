package example.day08._2인과제.dao;

import example.day08._2인과제.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public BoardDao(){
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day08","root","1234");
            System.out.println("DB 연동성공");
        }catch (Exception e){
            System.out.println("DB 연동실패" + e);
        }
    }

    public boolean boardCreate(BoardDto boardDto){
        System.out.println("BoardDao.boardCreate");
        System.out.println("boardDto = " + boardDto);

        try {
            String sql = "insert into board(bcontent, bwriter, bpassword) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBcontent());
            ps.setString(2,boardDto.getBwriter());
            ps.setString(3,boardDto.getBpassword());

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }

    // 2. 전체 호출
    public List<BoardDto> boardRead(){
        System.out.println("BoardDao.boardRead");
        List<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new BoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

    // 3. 수정
    public boolean boardUpdate(BoardDto boardDto){
        System.out.println("BoardDao.boardUpdate");
        System.out.println("boardDto = " + boardDto);
        try {
            String sql = "update board set bcontent = ? where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBcontent());
            ps.setInt(2,boardDto.getBno());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 4. 삭제
    public boolean boardDelete(int bno){
        System.out.println("BoardDao.boardDelete");
        System.out.println("bno = " + bno);
        try {
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 5. 게시물 번호에 따른 패스워드 검증
    public boolean confirmPassword(int bno, String bpassword){
        try {
            String sql = "select * from board where bno = ? and bpassword = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.setString(2,bpassword);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }



        return false;
    }
}
