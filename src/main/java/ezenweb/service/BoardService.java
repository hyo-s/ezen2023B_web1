package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private FileService fileService;

    // 1. 글쓰기 처리
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardService.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);

        // 1. 첨부 파일 처리
        if(!boardDto.getUploadfile().isEmpty()){    // 첨부 파일이 존재하면
            String fileName = fileService.fileUpload(boardDto.getUploadfile());
            if(fileName != null){   // 업로드 성공했으면
                boardDto.setBfile(fileName);// DB에 저장할 첨부파일명
            }else{
                return -1; // 업로드에 문제가 발생하면 글쓰기 취소
            }
        }
        return boardDao.doPostBoardWrite(boardDto);
    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView( int bno ){
        System.out.println("BoardService.doGetBoardView");
        System.out.println("bno = " + bno);
        return boardDao.doGetBoardView(bno);
    }
}
