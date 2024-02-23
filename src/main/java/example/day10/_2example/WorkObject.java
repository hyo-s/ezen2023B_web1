package example.day10._2example;

public class WorkObject {
    public synchronized void methodA(){
        // 현재 스레드 객체 호출 .currentThread()
        Thread thread = Thread.currentThread();
        // 스레드 이름 호출 .getName()
        System.out.println("thread.getName() = " + thread.getName());

        // 다른 스레드를 실행 대기 상태로 변경
        notify();
        try {
            // 현재 스레드를 일시정지 상태로 변경
            wait();
        }catch (InterruptedException e){
            System.out.println("e = " + e);
        }
    }

    public synchronized  void methodB(){
        Thread thread = Thread.currentThread();
        System.out.println("thread.getName() = " + thread.getName());
        notify();
        try {
            wait();
        }catch (InterruptedException e){
            System.out.println("e = " + e);
        }
    }
}
