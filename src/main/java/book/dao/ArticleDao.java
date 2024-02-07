package book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


}
