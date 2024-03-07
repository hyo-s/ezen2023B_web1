package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao extends Dao {

// =============================== 1. 글쓰기 처리 [ 글쓰기 성공 했을 때 자동 생성된 글번호 반환, 실패 시 0 ] =============================== //
    public long doPostBoardWrite( BoardDto boardDto ){
        System.out.println("BoardDao.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);
        try {
            String sql = "insert into board(btitle, bcontent, bfile, mno, bcno) values( ?, ?, ?, ? ,? )";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3,boardDto.getBfile());
            ps.setLong(4,boardDto.getMno());
            ps.setLong(5,boardDto.getBcno());
            int count = ps.executeUpdate();
            if(count == 1){
                rs = ps.getGeneratedKeys();
                System.out.println("rs = " + rs);
                if(rs.next()){
                    return rs.getLong(1);
                }
            }
        }catch (Exception e){
            System.out.println("boardDto = " + boardDto);
        }
        return 0;
    }

// =============================== 2. 전체 글 출력 호출 =============================== //
    public List<BoardDto> doGetBoardViewList(int startRow, int pageBoardSize, int bcno, String field, String value){
        System.out.println("BoardDao.doGetBoardViewList");
        BoardDto boardDto = null;
        List<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board b inner join member m on b.mno = m.no ";
                if(bcno > 0){
                    sql += " where bcno = " + bcno;
                }
                if(!value.isEmpty()) {
                    if (bcno > 0) {
                        sql += " and ";
                    } else {
                        sql += " where ";
                    }
                    sql += field + " like '%" + value + "%' ";
                }
            sql += " order by b.bdate desc limit ?, ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageBoardSize);
            rs = ps.executeQuery();
            while(rs.next()){
                boardDto = new BoardDto(
                        rs.getLong("bno"),
                        rs.getString("btitle"),
                        rs.getString("bcontent"),
                        rs.getString("bfile"),
                        rs.getLong("bview"),
                        rs.getString("bdate"),
                        rs.getLong("mno"),
                        rs.getLong("bcno"),
                        null,
                        rs.getString("id"),
                        rs.getString("img")
                );
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }
// =============================== 2-2. 전체 게시물 수 호출 =============================== //
    public int getBoardSize(int bcno, String field, String value){
        System.out.println("BoardService.getBoardSize");
        System.out.println("value = " + value);
        try {
            String sql = "select count(*) from board b inner join member m on b.mno = m.no";
            // === 만약에 카테고리 조건이 있으면 where 추가
            if(bcno > 0){
                sql += " where b.bcno = " + bcno;
            }
            // === 만약에 검색이 있을 때
            if(!value.isEmpty()){
                if( bcno > 0){
                    sql += " and ";
                }else {
                    sql += " where ";
                }
                sql += field + " like '%" + value + "%' ";
                System.out.println(sql);
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println("e = " + e);

        }
        return 0; // 없으면 0
    }
// =============================== 3-2. 개별 글 출력 시 조회수 증가 =============================== //
    public void boardViewIncrease( int bno ){
        try {
            String sql = "update board set bview = bview+1 where bno = "+bno;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    }
// =============================== 3. 개별 글 출력 호출 =============================== //
    public BoardDto doGetBoardView( int bno ){
        System.out.println("BoardDao.doGetBoardView");
        System.out.println("bno = " + bno);
        BoardDto boardDto = null;
        try {
            String sql = "select * from board b inner join member m on b.mno = m.no where b.bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno);
            rs = ps.executeQuery();
            if(rs.next()){
                boardDto = new BoardDto(
                        rs.getLong("bno"),
                        rs.getString("btitle"),
                        rs.getString("bcontent"),
                        rs.getString("bfile"),
                        rs.getLong("bview"),
                        rs.getString("bdate"),
                        rs.getLong("mno"),
                        rs.getLong("bcno"),
                        null,
                        rs.getString("id"),
                        rs.getString("img")
                );
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return boardDto;
    }

// =============================== 4. 개별 글 삭제 =============================== //
    public boolean doDeleteBoard(int bno){
        System.out.println("BoardDao.doDeleteBoard");
        System.out.println("bno = " + bno);
        try {
            String sql = "delete from board where bno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            // ps.executeUpdate()가 1이라는 것은 sql 구문을 실행을 했는데 1개 했다
            int count = ps.executeUpdate();
            if (count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

// =============================== 5. 개별 글 수정 =============================== //
    public boolean doPutBoard(BoardDto boardDto){
        System.out.println("BoardDao.doPutBoard");
        System.out.println("boardDto = " + boardDto);
        try {
            String sql = "update board set btitle = ?, bcontent = ?, bcno = ?, bfile = ? where bno = ?";
            ps = conn. prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.setLong(3,boardDto.getBcno());
            ps.setString(4,boardDto.getBfile());
            ps.setLong(5,boardDto.getBno());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
}
