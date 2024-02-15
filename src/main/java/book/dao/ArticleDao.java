package book.dao;

import book.dto.ArticleDto;
import book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.*;
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

    public ArticleDto createArticle(ArticleDto dto){
        System.out.println("ArticleDao.createArticle");
        System.out.println("form = " + dto);
        // 1. 세이브 성공 시 반환할 DTO
        ArticleDto saved = new ArticleDto();
        try{
            String sql = "insert into article( title,content ) values( ?, ? )";
            System.out.println(sql);
            // ps=conn.prepareStatement(sql);
            // * insert 된 auto_increment 자동번호 식별키 호출하는 방법
            // 1. SQL 기재할 때 자동으로 생성된 키를 호출
            // 2. rs = ps.getGeneratedKeys( );
            // 3. rs.next( ) ---> rs.get타입(1) : 방금 생성된 키 반환
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,dto.getTitle());
            ps.setString(2,dto.getContent());

            int count = ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if(rs.next()){
                System.out.println("방금 자동으로 생성된 PK(ID) : " + rs.getLong(1));
                Long id = rs.getLong(1);
                saved.setId(id);
                saved.setTitle(dto.getTitle());
                saved.setContent(dto.getContent());
                return saved;
            }
            // 5.
//            if(count == 1){
//                return true;
//            }
        }catch (Exception e){
            System.out.println(e);
        }
        return saved;
    }

    // 2. 개별 글 조회 : 매개변수 : 조회할 게시물 번호 (id) 반환 : 조회할 게시물 정보 1개 (DTO)
    public ArticleForm show(Long id){
        try {
            String sql = "select * from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                ArticleForm form = new ArticleForm(rs.getLong("id"),rs.getString("title"),rs.getString("content"));
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

    public ArticleForm findById(long id){
        try {
            String sql = "select * from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                return new ArticleForm(rs.getLong("id"),rs.getString(2),rs.getString(3));
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }

    public ArticleForm update(ArticleForm form){
        try {
            String sql = "update article set title = ?, content = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());
            ps.setLong(3,form.getId());

            int count = ps.executeUpdate();
            if(count==1){
                return form;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }

    public boolean delete(long id){
        try {
            String sql = "delete from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
}
