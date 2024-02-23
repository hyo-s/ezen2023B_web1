package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/day11")
public class RestController3 {

    // http://localhost:8080/day11/black?sendMsg=안녕
    // HTTP 이용한 매개변수 보내는 방법
        // 1. 경로상의 변수 http://localhost:8080/day11/black/안녕
        // 2. 쿼리스트링 변수 http://localhost:8080/day11/black?sendMsg=안녕


    // Get
    @GetMapping(value = "red")
    public String getWhite(HttpServletRequest req)throws IOException {
        System.out.println("RestController2.getWhite");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "안녕[클라이언트]";
    }

    // Post
    @PostMapping("red")
    public String[] postWhite(HttpServletRequest req)throws IOException{
        System.out.println("RestController2.postWhite");

        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        String[]  strArray = new String[2];
        strArray[0] = "안녕";
        strArray[1] = "클라이언트";

        List<String> strArrayList = new ArrayList<>();
        strArrayList.add("안녕");
        strArrayList.add("클라이언트");

        Map<String, String> strMap = new HashMap<>();
        strMap.put("안녕", "클라이언트");

        return strArray;

    }

    // Put
    @PutMapping("red")
    public int putWhite(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        System.out.println("RestController2.putWhite");

        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        return 10;
    }

    // Delete
    @DeleteMapping("red")
    public boolean deleteWhite(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        System.out.println("RestController2.deleteWhite");

        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        return true;
    }
}
