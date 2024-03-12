package ezenweb.model.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDto {

    // ========== 제품 관련
    private int pno;                                // 제품번호
    private String pname;                           // 제품이름
    private int pprice;                             // 제품가격
    private String pcontent;                        // 제품내용
    private byte pstate;                            // 제품상태
    private String pdate;                           // 제품등록일
    private String plat;                            // 위도
    private String plng;                            // 경도
    private int mno;                                // 회원번호

    // 출력 시 작성자 번호가 아닌 작성자 아이디
    private String mid;

    // ========== 제품 이미지 관련
    // == 등록
    private List<MultipartFile> uploadFiles;        // 제품이미지 경로
    // == 출력
    private List<String> pimg;                      // 제품이미지 파일명


    // 1. 제품 등록 [ 이름, 가격, 내용, 위도, 경도, uploadFiles ]

    // 2. 제품 출력 [ 번호, 이름, 가격, 상태, 위도, 경도 ]

    // 3. 제품 지도에서 마커 클릭시 상세 출력 [ 번호, 이름, 가격, 내용, 상태, 등록일, 위도, 경도, 회원번호 ]
}
