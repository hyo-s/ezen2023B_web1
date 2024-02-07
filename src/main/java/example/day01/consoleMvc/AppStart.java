package example.day01.consoleMvc;   // PACKAGE NAME

public class AppStart { // CLASS START
    public static void main(String[] args) {    // MAIN START

        // 1.
        MainView mainView = new MainView();
        mainView.home();

        // 2.
        // new MainView().home();

        // 3. 싱글톤 일때
        // MainView.getInstance().home();

        // 4. 메소드가 정적메소드 일때
        // MainView.home();

        // 5. IOC 제어역전, DI 의존성주입
            // SPRING이 객체를 만들어준다. 메모리 관리도 대신


    }   // MAIN END
}   // CLASS END
/*
    1.

*/