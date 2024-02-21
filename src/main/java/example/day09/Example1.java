package example.day09;  // PACKAGE NAME

import java.awt.*;

public class Example1 { // CLASS START

    // MAIN 함수 안에는 MAIN 스레드가 포함
    public static void main(String[] args) {    // MAIN START

        // 1. 현재 코드의 스레드객체 호출
        Thread thread = Thread.currentThread();
        // 2. 현재 코드를 실행하는 스레드 객체의 이름 호출
        System.out.println(thread.getName());

        // 3. 작업스레드 생성 4가지 방법
            // 자식 익명 객체 : 부모타입 변수명 = new 부모타입( ){ 재정의; }
        for(int i=0; i<3; i++){
            Thread threadA = new Thread(){
                @Override
                public void run() { // * 1. 작업스레드가 최초로 실행할 되는 함수 재정의
                    Thread thread = Thread.currentThread();
                    // * 3. 스레드 이름 변경
                    thread.setName("내가만든 작업스레드");   // i가 안되는 이유 : MAIN 스레드(다른 스레드)의 지역변수 호출 불가능
                }
            };
            threadA.start();
        }
        // * 2. 작업스레드 실행
        System.out.println("3. thread.getName() = " + thread.getName());

        // 주어진 시간동안 스레드 일시정지 .sleep( ) : 정적메소드 (static) 호출 : 클래스명.정적메소드( );
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i=0; i<10; i++){
            toolkit.beep();
            // 3초 동안 현재 스레드 일시정지
            try {
                Thread.sleep(3000); // 3초
            }catch (InterruptedException e){
                System.out.println("e = " + e);
            }
        }

        // MAIN 함수 코드가 모두 끝나도 작업스레드의 코드가 끝날 때까지 기다림.

    }   // MAIN END
}   // CLASS END
