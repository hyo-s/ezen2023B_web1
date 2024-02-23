package example.day10._1example;

public class Example1 {
    public static void main(String[] args) {
        // 1. 계산기 객체 생성
        Calculator calculator = new Calculator();
            // 두개 이상의 스레드가 하나의 메모리를 공유해서 사용

        // 2. User1 스레드 생성
        User1Thread user1Thread = new User1Thread();
        // 2-1. User1 스레드에 계산기 객체 대입
        user1Thread.setCalculator(calculator);
        // 2-2. User1 스레드 시작
        user1Thread.start();

        // 3. User2 스레드 생성
        User2Thread user2Thread = new User2Thread();
        // 3-1. User2 스레드에 계산기 객체 대입
        user2Thread.setCalculator(calculator);
        // 3-2. User2 스레드 시작
        user2Thread.start();

    }
}
