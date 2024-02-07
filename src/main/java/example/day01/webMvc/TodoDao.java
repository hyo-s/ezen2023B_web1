package example.day01.webMvc;   // PACKAGE NAME

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 데이터 접근 객체 : DB에 접근과 SQL(비즈니스 로직) 역할
public class TodoDao  {  // CLASS START

    // 필드
    private Connection conn;        // DB 연동 인터페이스
    private PreparedStatement ps;   // SQL 실행, 매개변수 인터페이스
    private ResultSet rs;           // SQL 실행결과와 연동된 인터페이스

    // 생성자
    public TodoDao(){
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

    // 메소드
    // 할일목록 등록함수
    public boolean doPost( TodoDto todoDto ){
        try{
            // 1. SQL 작성
            String sql = "insert into todo(content, deadline) values(?,?)";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setString(1,todoDto.getContent());
            ps.setString(2,todoDto.getDeadline());
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 실행 결과
            if(count == 1){
                return true;
            }
            // 6. 함수 리턴
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 할일목록 출력함수
    public ArrayList<TodoDto> doGet(){ // METHOD START
        // 레코드 1개 == TodoDto 1개 // 레코드 여러개 == TodoDto 여러개
        // 반환할 todoList 객체
        ArrayList<TodoDto> list = new ArrayList<>();
        try{
            // 1. SQL 작성
            String sql = "select * from todo";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            // 4. SQL 실행
            rs = ps.executeQuery();
            // 5. SQL 실행 결과

            while (rs.next()){

                // next() 레코드 이동
                TodoDto todoDto = new TodoDto(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getString("deadline"),
                rs.getBoolean("state")
                );
                // while 문 끝나면 dto 사라져 while 밖에 있는 객체로 이동
                list.add(todoDto);
            }
            // 6. 함수 리턴
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // METHOD END

}   // CLASS END
