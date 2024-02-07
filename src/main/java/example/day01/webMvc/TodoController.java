package example.day01.webMvc;   // PACKAGE NAME

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController // 해당 클래스를 스프링MVC 환경에 등록
public class TodoController {   // CLASS START

    private TodoDao todoDao = new TodoDao();

    // 할일목록 등록함수
    @GetMapping("/todo/post.do")
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost(todoDto);
    }
    // http://localhost:8080/todo/post.do?content=안녕하세요 & deadline=2024-02-05

    // 할일목록 출력함수
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }
    // http://localhost:8080/todo/get.do
}   // CLASS END
