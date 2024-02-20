package example.day08._2인과제.controller;

import example.day08._2인과제.dao.BoardDao;
import example.day08._2인과제.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardDao boardDao;

    @PostMapping("/board/create")
    @ResponseBody
    // 1. 저장
    public boolean boardCreate(BoardDto boardDto){
        System.out.println("BoardController.boardCreate");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardDao.boardCreate(boardDto);
        System.out.println("result = " + result);

        return result;
    }

    @GetMapping("/board/read")
    @ResponseBody
    // 2. 전체 호출
    public List<BoardDto> boardRead(){
        System.out.println("BoardController.boardRead");
        System.out.println("boardDao = " + boardDao);

        List<BoardDto> result = boardDao.boardRead();
        System.out.println("result = " + result);

        return result;
    }

    @GetMapping("/board/update")
    @ResponseBody
    // 3. 수정
    public boolean boardUpdate(BoardDto boardDto){
        System.out.println("BoardController.boardUpdate");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardDao.boardUpdate(boardDto);
        System.out.println("result = " + result);

        return result;
    }

    @GetMapping("/board/delete/{bno}")
    @ResponseBody
    // 4. 삭제
    public boolean boardDelete(@PathVariable("bno") int bno){
        System.out.println("BoardController.boardDelete");
        System.out.println("bno = " + bno);

        boolean result = boardDao.boardDelete(bno);
        System.out.println("result = " + result);

        return result;
    }

}
