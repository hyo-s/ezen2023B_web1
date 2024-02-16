package example.day07._2스택큐컬렉션;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Example1 {
    public static void main(String[] args) {
        
        // 1. 스택 컬렉션 생성 [ Vector 상속받음 ]
        Stack<Integer> coinBox = new Stack<>();
        
        // 2. 동전 넣기 = 스택 삽입 = push
        coinBox.push(100);
        coinBox.push(50);
        coinBox.push(500);
        coinBox.push(10);
        System.out.println("coinBox = " + coinBox);
        // 3. 동전 빼기 = 스택 빼기 = pop
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        
        // 1. 큐 컬렉션 생성
        Queue<String> messageQue = new LinkedList<>();
        
        // 2. 메세지 넣기 = 큐 삽입 = offer
        messageQue.offer("안녕 홍길동");
        messageQue.offer("안녕 신용권");
        messageQue.offer("안녕 감자바");
        System.out.println("messageQue = " + messageQue);
        
        // 3. 메세지 빼기 = 큐 빼기 = poll
        messageQue.poll();
        System.out.println("messageQue = " + messageQue);
        System.out.println("messageQue.poll() = " + messageQue.poll());
        System.out.println("messageQue = " + messageQue);
        
    }
}
/*
    P.641 List
    P.647 LinkedList
    P.650 Set
    P.656 Map
    P.664 Tree 이진트리 : P.665 TreeSet P.668 TreeMap
    P.676 Stack/Queue
*/