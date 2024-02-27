package ezenweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    private String uploadPath = "C:\\Users\\504\\Desktop\\ezen_2023B_WEB1_hyoseong\\build\\resources\\main\\static\\img\\";
    // 1. 업로드 메소드
        // 어디에(PATH) 누구를(FILE)
    public String fileUpload(MultipartFile multipartFile){
        // Controller : 중계자 역할 ( HTTP 매핑, HTTP 요청 응답, 데이터 유효성검사 등 )
        // Service : Controller <- Service ( 비즈니스 로직 ) -> Dao, Controller <--> Service(비즈니스로직)

        // 첨부파일 MultipartFile 타입
//        MultipartFile multipartFile = memberDto.getImg();
//        System.out.println(multipartFile);           // 첨부파일 객체 주소
//        System.out.println(multipartFile.getSize()); // 첨부파일 용량 : 4004 바이트
//        System.out.println(multipartFile.getContentType()); // 첨부파일 확장자
//        System.out.println(multipartFile.getOriginalFilename()); // 첨부파일의 이름(확장자포함)
//        System.out.println(multipartFile. getName()); // form input name

        // 서버에 업로드 했을때 설계
        // 1. 여러 클라이언트가 동일한 파일명으로 서버 [1명]에게 업로드 했을때 [ 식별 깨짐 ]
        // 식별 이름 : 1.날짜조합 2.UUID( 난수 생성, 가독성 )
        // 2. 클라이언트 화면 표시
        // 업로드 경로 : 아파치 톰캣에 업로드
        // * 업로드 할 경로 설정 ( 내장 톰캣 경로 )

        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid = " + uuid);

        // * 파일 이름 조합하기 : 새로운 식별 이름과 실제 파일 이름
        // 식별 키와 실제 이름 구분 : 왜? 나중에 쪼개서 구분하려고 [ 다운로드 시 식별키 빼고 제공 ]
        // 혹시나 파일 이름이 구분문자가 있을경우 기준이 깨짐
        // .replaceAll( )
        String fileName = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");
//        memberDto.setUuidFile(fileName);
        // 첨부파일 업로드 [ 업로드란 : 클라이언트의 바이트(대용량/파일)을 복사해서 서버로 ]
        // 1. 첨부파일 저장할 경로
        // FILE 클래스 : 파일 관련된 메소드 제공
        // new File ( 파일경로 );
        File file = new File(uploadPath+fileName);
        System.out.println("file = " + file);
        System.out.println(file.exists());
        // 2. 멀티파트 파일을 .transferTo() 로 옮길거야
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return fileName;
    }


    // 2. 다운로드 메소드
}
