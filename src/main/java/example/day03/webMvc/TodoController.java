package example.day03.webMvc;   // PACKAGE NAME

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

// * 스프링에게 해당 클래스가 컨트롤이라는걸 알려야한다.
@RestController // 스프링 컨테이너[스프링이 관리하는 메모리 공간]에 빈(객체) 등록 IOC
    // IOC 제어역전 기법 : 개발자가 객체 관리X, 스프링이 대신
public class TodoController {   // CLASS START

    @Autowired
    private TodoDao todoDao;

    // 할일 등록
    @PostMapping("/todo/post.do")
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost(todoDto);
    }

    // 할일 출력
    @GetMapping("/todo/get.do")
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

    // 할일 상태변경
    @PostMapping("/todo/put.do")
    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }

    // 할일 삭제
    @DeleteMapping("/todo/delete.do")
    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }

}   // CLASS END
