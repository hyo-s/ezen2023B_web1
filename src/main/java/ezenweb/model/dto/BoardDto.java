package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
// @Builder // 생성자 단점을 보완한 라이브러리 함수 제공
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
    // + 전체 출력 시
    String mid;
    String mimg;
}

/*
    용도에 다양한 DTO 존재할 수 있다.
    하나의 DTO에 서로 다른 용도로 사용.

    1. 글쓰기용
        입력받기 : btitle, bcontent, bfile, bcno
        서버처리 : bno 자동, bview 기본값 0, bdate 기본값 현재날짜, mno 로그인 ( *세션)
    2. 개별출력용
        출력용 : bno, btitle, bcontent, bfile, bview, bdate, mno, bcno
    3. 전체출력용
        전체출력용 : bno, btitle, bcontent, bfile, bview, bdate, mno, bcno, mid, mimg

    생성자 단점 / 규칙
        매개변수의 순서, 개수 => 유연성 떨어짐
        빌더 패턴 : @Builder
*/