package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private int no;     // 회원번호 type="text" 자동타입변환 -> int
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private MultipartFile img;  // (MultipartFile) type="file"  첨부파일 형식
    //private String img; // type="text" 일반적인 text 형식
    private String uuidFile; // UUID + FILE
}
