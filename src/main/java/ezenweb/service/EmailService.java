package ezenweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
/*
    SMTP : 간이 우편 전송 프로토콜 ( 메일 전송 )
        자바에서 메일보내기
        1. SMTP 라이브러리 필요
        2. Dependencies : JAVA MAIL SENDER
        3. 이메일 전송
            이메일 내용을 구성 => 구성 포멧 : MIME ( SMTP 사용 시 사용되는 포맷 )
            SMTP 프로토콜
        4. 보내는 사람 이메일 인증
            application.properties
*/
    // 1. JAVA(SPRING) 지원하는 SMTP 객체 필요 = JAVA MAIL SENDER
    @Autowired
    private JavaMailSender javaMailSender;  // 다른 함수에서도 사용하려고

    public void send( String toEmail, String subject, String content ){
        try {
            // * 메일 내용물들을 포맷하기 위한 MIME 형식 객체
            MimeMessage message = javaMailSender.createMimeMessage();

            // 1. 메시지 구성  // MimeMessageHelper ( MIME 객체, 첨부파일여부, 인코딩타입 ) : 내용물 구성
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true,"utf-8");
                // 2. 메시지를 보내는 사람
                mimeMessageHelper.setFrom("sere4580@naver.com");    // 관리자 이메일
                // 3. 메시지를 받는 사람
                mimeMessageHelper.setTo(toEmail);  // 클라이언트(회원) 이메일 ( 매개변수 )
                // 4. 메시지 제목
                mimeMessageHelper.setSubject(subject);  // ( 매개변수 )
                // 5. 메일 내용
                mimeMessageHelper.setText(content);   // ( 매개변수 )
            // 6. 메일 전송
            javaMailSender.send(message);

        }catch (Exception e){
            System.out.println("이메일 전송 실패 = " + e);
        }
    }
}
