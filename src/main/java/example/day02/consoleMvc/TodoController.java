package example.day02.consoleMvc;   // PACKAGE NAME

import java.util.ArrayList;

public class TodoController {   // CLASS START

    private TodoDao todoDao = new TodoDao();

    // 할일 등록
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost(todoDto);
    }

    // 할일 출력
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

    // 할일 상태변경
    public boolean doPut(TodoDto todoDto){
        return todoDao.doPut(todoDto);
    }

    // 할일 삭제
    public boolean doDelete(int id){
        return todoDao.doDelete(id);
    }

}   // CLASS END
