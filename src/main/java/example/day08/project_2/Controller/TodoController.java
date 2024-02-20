package example.day08.project_2.Controller;

import example.day08.project_2.Model.Dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {

    @Autowired
    TodoDao todoDao;

    @GetMapping("/todo")
    public String todoView(Model model){ // 머스테치 확인 완료
        model.addAttribute("todoArr", todoDao.view());
        return "TeamProjectTodo/todo";
    }

    @PostMapping("/todo/create")
    @ResponseBody
    public boolean todoCreate(String content){
        System.out.println("TodoController.todoCreate");
        System.out.println("content = " + content);
        return todoDao.todoCreate(content);
    }

    @PostMapping("/todo/delete")
    @ResponseBody
    public boolean tododelete(int id){
        System.out.println("TodoController.tododelete");
        System.out.println("id = " + id);
        return todoDao.tododelete(id);
    }

    @PostMapping("/todo/success")
    @ResponseBody
    public boolean success(int id){
        System.out.println("TodoController.success");
        System.out.println("id = " + id);
        return todoDao.success(id);
    }



}
