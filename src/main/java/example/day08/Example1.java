package example.day08;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public class Example1 {
    /*
        운영체제는 실행중인 프로그램을 프로세스로 관리
        프로그램 1개당 1개 프로세스 존재
        멀티 태스킹 : 두가지 이상을 동시에 처리
            프로그램 1개당 멀티 프로세스가 존재 할 수 있다.
            프로세스 1개당 멀티 스레드가 존재 할 수 있다.

        스레드 : 코드의 실행 흐름
            스레드 마다 스택 할당
            스레드끼리 자원 공유 X
            하나의 스레드에서 예외 발생 시 전체 스레드가 예외 발생

        멀티 스레드 : 하나의 프로세스가 두가지 이상의 작업을 처리
            - 생성 : main 스레드가 추가 작업 스레드 생성
            - 자바는 무조건 하나의 스레드를 갖는다. Main 함수 ( )

        CPU 코어 1개당
            작업요청
                멀티는 안됨
                여러개 스레드들의 작업 순서는 하나씩 처리 ( 컴퓨터는 빠르기 때문에 동시 처리)
                작업순서 : 운영체제가 스케줄링
                자바에서 서로 다른 스레드끼리의 작업순서 정하기 불가능.
                프로그램(소프트웨어)은 자원(하드웨어) 제어권이 없다. (운영체제가 자원 할당)

        JVM
        메소드영역       스택영역                    힙영역
        클래스정보       스레드마다 할당                인스턴스 할당
        static          함수실행{ } 지역변수 할당
    */
    // 1. 메인스레드(메인함수) 선언
    public static void main(String[] args) {

        // ==================== 단일 스레드 ==================== //
        // * java.aws : java.ui
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for( int i = 1; i <=5; i++){
            toolkit.beep(); // 띵 소리 발생
            // for문이 5번 회전하는 것보다 띵 소리가 느리기때문에
            // 메인스레드 잠시 멈추기
                // Thread.sleep(밀릴초) : 밀리초(1/1000초)만큼 일시정지
                    // * 일반 예외처리 : 혹시나 일시정지 도중에 다른쪽 스레드가 예외발생 시킬 수 있으니까
            try {
                Thread.sleep(500);  // MAIN 스레드를 0.5초간 일시정지
            }catch (InterruptedException e){
                System.out.println(e);
            }

        }
        for(int i=1; i<=5; i++){
            System.out.println("띵");
        }
        // =================================================== //

        // ==================== 멀티 스레드1 ==================== //
        // 1. Runnable 인터페이스 구현객체 필요
            // 1. 구현 클래스
            // 2. 익명 구현 : 인터페이스가 new 이용한 직접 추상메소드 재정의
                // new 인터페이스명( ){ }
        // 2. 구현객체를 Thread 생성자에 대입
        // ==================== 작업 스레드가 실행 ==================== //
        Thread thread = new Thread(
            new Runnable() {
                // ============= 작업 스레드 구현 ============= //
                @Override
                public void run() { // RUN START
                    Toolkit toolkit1 = Toolkit.getDefaultToolkit();
                    for(int i=1; i<=5; i++){    // FOR START
                        toolkit.beep();
                        try {
                            Thread.sleep(500);
                        }catch (InterruptedException e){
                            System.out.println(e);
                        }
                    }   // FOR END
                }   // RUN END
            } // NEW RUNNABLE END
        );

        thread.start(); // 작업스레드 실행

        // ==================== 메인 스레드가 실행 ==================== //
        for (int i=1; i<=5; i++){
            System.out.println("띵");
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }

        // ==================== 멀티 스레드2 ==================== //
        // 1. Runnable 객체 필요
        Runnable runnable = new 작업스레드();
        // 2. Thread 객체 필요
        Thread thread1 = new Thread(runnable);

        thread1.start();    // 작업스레드 실행

        for (int i=1; i<=5; i++){
            System.out.println("띵");
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }

        // ==================== 멀티 스레드3 [ Thread 상속 ]  ==================== //
        // 자식 객체
        작업스레드2 작업스레드2 = new 작업스레드2();
        작업스레드2.start();

        // 익명 자식 객체
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                for(int i=1; i<=5; i++){
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    toolkit.beep();
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        System.out.println(e);
                    }
                }
            }
        };
        thread2.start();
    }
}
/*
    [ 인터페이스 ]
    구현객체란 : 추상메소드를 구현한 클래스의 객체
        구현객체
            정의 : 클래스 implements 인터페이스{ }
            인터페이스명 변수명 = new 구현클래스
        익명 구현객체
            인터페이스명 변수명 = new 인터페이스명( ){ 추상메소드 재정의 }

    + Runnable 인터페이스
        1. RUN 메소드 재정의한다. ( 생성된 작업 스레드가 처리할 행위/메소드/함수/코드 )
        2. Thread 클래스 객체 생성해서 start
            Runnable runnable = new Runnable( ){ 재정의 };
            Thread thread = new Thread( runnable );
            thread.start();

    [ 상속 ]
    자식객체란 : 부모클래스로부터 (필드/메소드) 상속/물려받은 클래스의 객체
        자식객체
            정의 : 클래스 extends 부모클래스{ 재정의 }
            부모클래스명 변수명 = new 자식클래스( );
        익명 자식객체
            부모클래스명 변수명 = new 부모클래스( ){ 재정의 }

    + Thread 클래스
        1. RUN 메소드 재정의한다. ( 생성된 작업 스레드가 처리할 행위/메소드/함수/코드 )
        2. Thread 클래스 객체 생성해서 start
            Thread thread = new 자식클래스( );
            thread.start( );
*/