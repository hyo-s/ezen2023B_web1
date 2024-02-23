package example.day10._3Servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 자바 회사에서 웹개발 위한 HTTP 통신 클래스 : HttpServlet
    // 주로 MVC 패턴에서는 주로 CONTROLLER 역할
    // Servlet 자바 회사에서 HTTP와 통신 할 수 있는 클래스를 만들었다.
@WebServlet("/hello-servlet")   // WEB.xml 해당 서블릿 등록
public class HelloServlet extends HttpServlet {

    // 서블릿 선언하는 방법
    // 1. 해당 클래스에 HTTP Servlet 상속받는다.
    // 2. 해당 클래스에 @WebServlet("HTTP 식별주소") 어노테이션을 주입 Web.xml 등록한다.
    // 3. HttpServlet이 제공한느 메소드를 오버라이딩 : init( ), service( ), doGet( ), doPost( ), destroy( )

    /*
         서블릿 실행 구동 순서
         1. (HTTP) 클라이언트의 요청이 (AWS) 톰캣서버에 들어온다
         2. 서블릿 컨테이너에 요청받은 서블릿이 있는지 없는지 판단
         3. 없으면 init( )로 서블릿을 생성한다.
         4. 있으면 (작업)스레드로 할당
         5. Service( ) 실행하고 HTTP 요청에 따른 메소드를 호출한다.
         6. doXXX 메소드 실행 될 때 요청(HttpServletRequest)
            - HTTP 관련된 정보를 요청 할 수 있는 기능 가지고 있다.
         7. doXXX 메소드 종료 될 때 응답(HttpServletResponse)
            - HTTP 관련된 정보를 응답 할 수 있는 기능 가지고 있다.

         ----- 다음 요청이 올때까지
         1 -> 2 -> 4 -> 5 -> 6
         ----- 서버가 종료되면 destroy( ) 실행되면서 안전하게 서블릿 제거
    */
    // HttpServlet 클래스로부터 상속받으면 다양한 HTTP 관련 메소드 사용가능
    @Override   // 1. [최초 1번 실행]해당 서블릿 객체가 생성 되었을 때 실행되는 메소드
    public void init(ServletConfig config) throws ServletException {
        System.out.println("HelloServlet.init");
        super.init();
    }

    @Override   // 2. [요청마다 실행] 해당 서블릿으로부터 HTTP 서비스 실행 되었을 때 실행되는 메소드
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        super.service(req, resp);
    }

    @Override   // 3. [HTTP 메소드에 따라 실행] HTTP 서비스 요청 중에 HTTP METHOD 방식이 GET이면 실행되는 메소드
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doGet");
        super.service(req, resp);
    }

    @Override   // 4. [서버가 종료될 때] 해당 서블릿 객체가 삭제 되었을 때 실행되는 메소드
    public void destroy() {
        System.out.println("HelloServlet.destroy");
        super.destroy();
    }
}
