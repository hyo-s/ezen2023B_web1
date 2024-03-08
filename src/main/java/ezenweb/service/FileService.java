package ezenweb.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private HttpServletResponse response;
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

// =============================== 6. 다운로드 =============================== //
    public void fileDownload(String bfile){
        System.out.println("FileService.fileDownload");
        System.out.println("bfile = " + bfile);
        
        // 1. 다운로드 할 파일의 경로와 파일명 연결
        String downloadPath = uploadPath + bfile;
        System.out.println("downloadPath = " + downloadPath);

        // 2. 해당 파일을 객체(라이브러리 기능을 쓰기 위해서)로 가져오기
        File file = new File(downloadPath);
        System.out.println("file = " + file);

        // 3. .exists() 해당 경로에 파일이 있다 없다.
        if(file.exists()){
            System.out.println("첨부파일 있다");
            try {
                // HTTP로 응답 시 응답방법(Download 형식)에 대한 정보를 추가
                    // URL은 한글을 지원 안한다. URLEncoder.encode("안녕","utf-8")
                response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(bfile.split("_")[1],"utf-8"));

                // HTTP 파일 전송하는 방법 : 파일을 바이트로 전송

                // 1. 해당 파일을 바이트로 불러온다. [ BufferedInputStream ]
                    // 스트림 : 바이트가 다니는 통로
                    // BufferedInputStream fin = new BufferedInputStream( 파일스트림 );
                // 1-1 파일 입력스트림 객체 생성
                BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file) );
                    // 1-2 바이트 배열(고정길이) vs 리스트(가변길이)
                        // 파일의 사이즈 용량 ( 파일의 크기만큼 바이트배열 선언하기 위해 )
                        long fileSize = file.length();
                        // 해당 파일의 사이즈만큼 바이트 배열 선언
                        byte[] bytes = new byte[(int)fileSize]; // 배열의 길이는 int형
                    // 1-2 입력( 불러오기 ) 메소드
                        // 바이트 하나씩 읽어오면서 바이트배열 저장
                    fin.read(bytes);    // read() 하나씩 바이트를 읽어와서 해당 바이트 배열에 저장 해주는 함수
                    //  읽어온 파일의 바이트가 들어가있다.
                    System.out.println("bytes = " + Arrays.toString( bytes));

                // 2. 불러온 바이트를 HTTP Response 이용한 바이트로 응답한다. [ BufferedOutputStream ]
                    // BufferedOutputStream fout = new BufferedOutputStream( HTTP 응답스트림 );
                    // HttpServletRequest HTTP로 요청을 보낸 정보가 담긴 객체 ( 매개변수와 브라우저 정보 -> 세션 )
                    // HttpServletResponse HTTP로 응답을 보낼 정보와 기능/메소드 가지고 있는 객체
                // 2-2 HTTP 응답 스트립 객체 생성
                response.getOutputStream().write(bytes);



                BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
                fout.write(bytes);

                // 버퍼 초기화
                fin.close();
                response.getOutputStream().close();

            }catch (Exception e){
                System.out.println("e = " + e);
            }

        }else {
            System.out.println("첨부파일 없다");
        }
    }
    // 3. 파일삭제 [ 게시물 삭제 시 만약에 첨부파일 있으면 첨부파일도 같이 삭제, 게시물 수정 시 첨부파일 변경하면 기존 첨부파일 삭제 ]
    public boolean fileDelete(String bfile){
        // 1. 경로와 파일을 합쳐서 파일 위치 찾기
        String filePath = uploadPath + bfile;
        System.out.println("filePath = " + filePath);

        File file = new File(filePath);
        System.out.println("file = " + file);
        if(file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }
}
