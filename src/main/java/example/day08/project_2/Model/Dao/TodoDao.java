package example.day08.project_2.Model.Dao;

import book.dto.ArticleForm;
import example.day08.project_2.Model.Dto.TodoDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class TodoDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    public TodoDao(){
        try{
            //1.MYSQL 회사의 JDBC관련된 객체를 JVM에 로딩한다
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Driver 클래스(인터페이스 구현체)를 불러와줌

            //2.연동된 결과의 객체를 connetction 인터페이스에 대입한다
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoDB", "root", "1234");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public List<TodoDto> view(){
        List<TodoDto> arr = new ArrayList<>();
        try{
            String sql = "select * from todoTable order by id;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TodoDto todoDto = new TodoDto(
                        rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4).split(" ")[0]
                );
                arr.add(todoDto);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return arr;
    }

    public boolean todoCreate(String content){ // DB 생성후 다시 함수 만들기
        try{
            String sql = "insert into todoTable(content) values(?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            int count = ps.executeUpdate();
            if(count==1){
               return true;
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean tododelete(int id){
        try{
            String sql = "delete from todoTable where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean success(int id){
        try{
            String sql = "update todoTable set tcondition = true where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
