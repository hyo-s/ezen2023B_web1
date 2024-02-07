package example.day01.consoleMvc;   // PACKAGE NAME

import java.util.ArrayList;

public class TodoController {   // CLASS START

    private TodoDao todoDao = new TodoDao();

    // 할일목록 등록함수
    public boolean doPost( TodoDto todoDto ){
        return todoDao.doPost(todoDto);
    }

    // 할일목록 출력함수
    public ArrayList<TodoDto> doGet(){
        return todoDao.doGet();
    }

}   // CLASS END
