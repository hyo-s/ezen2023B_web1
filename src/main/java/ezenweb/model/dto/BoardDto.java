package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {
    long bno;
    String btitle;
    String bcontent;
    String bfile;       // [ 첨부파일 이름 출력용 ]
    long bview;
    String bdate;
    long mno;
    long bcno;
    MultipartFile uploadfile; // 실제 첨부파일
}

/*
    글쓰기용
        입력받기 : btitle, bcontent, bfile, bcno
        서버처리 : bno 자동, bview 기본값 0, bdate 기본값 현재날짜, mno 로그인 ( *세션)

*/