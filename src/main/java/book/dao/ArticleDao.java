package book.dao;

import book.dto.ArticleDto;
import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDao {

    // 필드
    private Connection conn;        // DB 연동 인터페이스 // DB 연동 결과 객체를 연결 // 기재된 SQL Statement 객체 반환
    private PreparedStatement ps;   // SQL 실행, 매개변수 인터페이스 // 기재된 SQL에 매개변수 할당 SQL 실행
    private ResultSet rs;           // SQL 실행결과와 연동된 인터페이스 // Select 결과 여러개 레코드를 호출

    // 생성자
    public ArticleDao(){
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb","root","1234");
            System.out.println("DB 연동성공");
        }catch (Exception e){
            System.out.println("DB 연동실패" + e);
        }
    }

    public boolean createArticle(ArticleDto dto){
        System.out.println("ArticleDao.createArticle");
        System.out.println("form = " + dto);
        try{
            String sql = "insert into article( title,content ) values( ?, ? )";
            System.out.println(sql);
            ps=conn.prepareStatement(sql);
            ps.setString(1,dto.getTitle());
            ps.setString(2,dto.getContent());

            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 2. 개별 글 조회 : 매개변수 : 조회할 게시물 번호 (id) 반환 : 조회할 게시물 정보 1개 (DTO)
    public ArticleForm show(Long id){
        try {
            String sql = "select * from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                ArticleForm form = new ArticleForm(rs.getLong(1),rs.getString(2),rs.getString(3));
                return form;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // 3. 전체 글 조회
    public List<ArticleForm> index(){
        List<ArticleForm> list = new ArrayList<>();
        try {
            String sql = "select * from article";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ArticleForm form = new ArticleForm(rs.getLong(1),rs.getString(2),rs.getString(3));
                list.add(form);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

}
