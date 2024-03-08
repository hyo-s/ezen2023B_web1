package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private FileService fileService;

// =============================== 1. 글쓰기 처리 =============================== //
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
// =============================== 2. 전체 글 출력 호출 =============================== //
    public BoardPageDto doGetBoardViewList(int page, int pageBoardSize, int bcno, String field, String value){
        System.out.println("BoardService.doGetBoardViewList");

        // 페이징 처리 시 사용할 SQL 구문 : limit 시작 레코드 번호 ( 0부터 ), 출력할 개수

        // 1. 페이지당 게시물을 출력할 개수
            // int pageBoardSize = 5;


        // 2. 페이지당 게시물을 출력할 시작 레코드번호
        int startRow = (page-1)*pageBoardSize;
        // 3. 총 페이지 수
            // 1. 전체 게시물 수
        int totalBoardSize = boardDao.getBoardSize( bcno, field, value ) ;
            // 2. 총 페이지 수 계산
        int totalPage = totalBoardSize % pageBoardSize == 0 ? totalBoardSize / pageBoardSize : totalBoardSize / pageBoardSize +1;
            System.out.println("totalPage = " + totalPage);
        // 4. 게시물 정보 요청
        List<BoardDto> list =  boardDao.doGetBoardViewList(startRow,pageBoardSize,bcno, field, value);

        // 5. 페이징버튼 최대 개수
            // 1. 페이지버튼 최대 개수
        int btnSize = 5; // 5개씩
            // 2. 페이지버튼 시작번호
        int startBtn = ((page-1)/btnSize*btnSize)+1;
            // 3. 페이지버튼 끝 번호
        int endBtn = startBtn+btnSize-1;
            // 만약에 총 페이지 수 보다는 커질 수 없다.
        if(endBtn >= totalPage) endBtn = totalPage;


//        BoardPageDto boardPageDto = new BoardPageDto(
//                page,
//                totalPage,
//                startBtn,
//                endBtn,
//                list
//        );
        // pageDto 구성 * 빌더패턴 : 생성자의 단점 ( 매개변수에 따른 유연성 부족 )을 보완
            // new 연산자 없이 builder() 함수 이용한 객체 생성 라이브러리 제공
            // 사용 방법 : 클래스명.builder().필드명(대입값).필드명(대입값).build();
            // + 생성자 보단 유연성 : 매개변수의 순서와 개수 자유롭다.
            // 빌더패턴 vs 생성자 vs Setter
        BoardPageDto boardPageDto = BoardPageDto.builder()
                .page(page)
                .totalBoardSize(totalBoardSize)
                .totalPage(totalPage)
                .list(list)
                .startBtn(startBtn)
                .endBtn(endBtn)
                .build();
        return boardPageDto;
    }

// =============================== 3. 개별 글 출력 호출 =============================== //
    public BoardDto doGetBoardView( int bno ){
        System.out.println("BoardService.doGetBoardView");
        System.out.println("bno = " + bno);
        // 조회수 처리
        boardDao.boardViewIncrease(bno);
        return boardDao.doGetBoardView(bno);
    }

// =============================== 4. 개별 글 삭제 =============================== //
    public boolean doDeleteBoard(int bno){
        System.out.println("BoardService.doDeleteBoard");
        System.out.println("bno = " + bno);

        String bfile = boardDao.doGetBoardView(bno).getBfile();

        // 1. DAO 처리
        boolean result = boardDao.doDeleteBoard(bno);

        if(result){
            // 기존에 첨부파일이 있었으면
            System.out.println("bfile = " + bfile);
            if(bfile != null){
                fileService.fileDelete(bfile);
            }
        }
        return result;
    }

// =============================== 5. 개별 글 수정 =============================== //
    public boolean doPutBoard(BoardDto boardDto){
        System.out.println("BoardService.doPutBoard");
        System.out.println("boardDto = " + boardDto);

        // 기존 첨부파일명
        String bfile = boardDao.doGetBoardView((int)boardDto.getBno()).getBfile();

        if(!boardDto.getUploadfile().isEmpty()){
            // 새로운 첨부파일을 업로드 하고 기존 첨부파일 삭제
            String fileName = fileService.fileUpload(boardDto.getUploadfile());
            if(fileName != null){
                boardDto.setBfile(fileName);
                // 기존 첨부파일 삭제
                fileService.fileDelete(bfile);
            }else {
                return false;
            }
        }else {
            boardDto.setBfile(bfile);
        }
        boolean result = boardDao.doPutBoard(boardDto);
        return result;
    }

// =============================== 6. 게시물 작성자 인증 =============================== //
    public boolean boardWriteAuth(long bno, String mid){
        return boardDao.boardWriteAuth(bno,mid);
    }

// =============================== 7. 댓글 등록 =============================== //
    public boolean doPostReply( Map<String, String> map ){
        System.out.println("BoardService.doPostReply");
        System.out.println("map = " + map);
        return boardDao.doPostReply(map);
    }

// =============================== 8. 댓글 출력 =============================== //
    public List<Map<String, String>> getReplyDo(int bno){
        System.out.println("BoardService.getReplyDo");
        return boardDao.getReplyDo(bno);
    }
}


