package ezenweb.controller;

import example.day05._1SET컬렉션.Member;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;

// =============================== 1. 글쓰기 처리 =============================== //
    // 1. 글쓰기 처리            /board/write     @POST      DTO
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardController.doPostBoardWrite");

        // 1. 현재 로그인 된 세션 ( 톰캣 서버 ( 자바프로그램 ) 메모리 (JVM) 저장소 )
        Object object = request.getSession().getAttribute("loginDto");
        if( object==null ) return -2; // -2 세션오류 ( 로그인이 안됌 )

        // 2. 형변환
        String mid = (String) object;

        // 3. MID -> MNO
        long mno = memberService.doGetLoginInfo(mid).getNo();

        // 4. 작성자 번호 대입
        boardDto.setMno(mno);

        return boardService.doPostBoardWrite(boardDto);
    }

// =============================== 2. 전체 글 출력 호출 =============================== //
    // 2. 전체 글 출력 호출       /board.do        @GET       페이징처리, 검색
    @GetMapping("/do") // 매개변수 : 현재페이지
    @ResponseBody
    public BoardPageDto doGetBoardViewList(int page, int pageBoardSize, int bcno, @RequestParam("key") String field, @RequestParam("keyword") String value){
        System.out.println("BoardController.doGetBoardViewList");
        System.out.println("value   = " + value  );
        return boardService.doGetBoardViewList(page, pageBoardSize, bcno, field, value);
    }
// =============================== 3. 개별 글 출력 호출 =============================== //
    // 3. 개별 글 출력 호출       /board/view.do   @GET       게시물번호
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");
        System.out.println("bno = " + bno);
        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리           /board/update.do @PUT       DTO
    @PutMapping("/update.do")
    @ResponseBody
    public boolean doPutBoard(BoardDto boardDto){
        System.out.println("BoardController.doPutBoard");
        System.out.println("boardDto = " + boardDto);

        Object object = request.getSession().getAttribute("loginDto");
        if( object != null ){
            String mid = (String)object;
            boolean result = boardService.boardWriteAuth(boardDto.getBno(),mid);
            if(result){
                return boardService.doPutBoard(boardDto);
            }
        }
        return false;
    }

    // 5. 글 삭제 처리           /board/delete.do @DELETE    게시물번호
    @DeleteMapping("/delete.do")
    @ResponseBody
    public boolean doDeleteBoard(int bno){
        System.out.println("BoardController.doDeleteBoard");
        System.out.println("bno = " + bno);
        Object object = request.getSession().getAttribute("loginDto");
        if( object != null ){
            String mid = (String)object;
            boolean result = boardService.boardWriteAuth(bno,mid);
            if(result){
                return boardService.doDeleteBoard(bno);
            }
        }
        return false;
    }

    // 6. 다운로드 ( 1.매개변수 : 파일이름 2.반환 3.사용처
    @GetMapping("/file/download")
    @ResponseBody
    public void getBoardFileDownload(@RequestParam String bfile){
        System.out.println("BoardController.getBoardFileDownload");
        System.out.println("bfile = " + bfile);
        fileService.fileDownload(bfile);
    }

    // 7. 댓글 작성 ( brcontent, brindex, mno, bno )
    @PostMapping("/reply/write.do")
    @ResponseBody
    public boolean doPostReply( @RequestParam Map<String, String> map ){
        System.out.println("BoardController.doPostReply");
        System.out.println("map = " + map);

        // 1. 현재 로그인 된 세션 ( 톰캣 서버 ( 자바프로그램 ) 메모리 (JVM) 저장소 )
        Object object = request.getSession().getAttribute("loginDto");
        if( object==null ) return false; // -2 세션오류 ( 로그인이 안됌 )

        // 2. 형변환
        String mid = (String) object;

        // 3. MID -> MNO
        long mno = memberService.doGetLoginInfo(mid).getNo();
        // 4. Map에 mno 넣기
        map.put("mno",mno+"");
        System.out.println("map = " + map);
        return boardService.doPostReply(map);
    }

    // 8. 댓글 출력 ( brno, brcontent, brinex, brdate ,mno )
    @GetMapping("/reply/do")
    @ResponseBody
    public List<Map<String, Object>> getReplyDo(int bno){
        System.out.println("BoardController.getReplyDo");
        return boardService.getReplyDo(bno);
    }
// ====================== 머스테치는 컨트롤에서 뷰 반환 ====================== //

// =============================== 1. 글 쓰기 페이지 호출 =============================== //
    // 1. 글쓰기 페이지 이동        /board/write    @GET
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezenweb/board/write";
    }

// =============================== 2. 게시판 페이지 호출 =============================== //
    // 2. 게시판 페이지 이동        /board          @GET
    @GetMapping("")
    public String getBoard(){
        return "ezenweb/board/board";
    }

// =============================== 3. 게시판 상세 페이지 호출 =============================== //
    // 3. 게시판 상세페이지 이동     /board/view     @GET
    @GetMapping("/view")
    public String getBoardView( int bno ){
        return "ezenweb/board/view";
    }

    // 4. 글수정 페이지 이동        /board/update   @GET
    @GetMapping("/update")
    public String getBoardUpdate(){
        return "ezenweb/board/update";
    }

}
