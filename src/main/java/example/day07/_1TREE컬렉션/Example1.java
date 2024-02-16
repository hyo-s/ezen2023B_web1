package example.day07._1TREE컬렉션;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Example1 {
    public static void main(String[] args) {

        // 1. 일반 Set 컬렉션 생성
        Set<Integer> set = new HashSet<>();

        // 2. Set 컬렉션 객체에 객체 추가
        set.add(87);
        set.add(98);
        set.add(75);
        set.add(95);
        set.add(80);
        System.out.println("set = " + set);

        // 1. TreeSet 컬렉션 생성
        TreeSet<Integer> scores = new TreeSet<>();

        // 2. TreeSet 컬렉션 객체에 객체 추가
        scores.add(87);
        scores.add(98);
        scores.add(75);
        scores.add(95);
        scores.add(80);
        System.out.println("scores = " + scores);
        /*
            컬렉션 프레임워크 : 널리 알려진 자료구조 기반으로 미리 만들어진 클래스/인터페이스들
                자료구조 : 자료(데이터)를 저장하는 방법론

            이진트리 : 여러 자료구조 중에 하나의 방법

        */

        // 3. 순회
        for( Integer i : scores ) {
            System.out.println("i = " + i);
        }
        System.out.println();
        scores.forEach(( i -> System.out.println("i = " + i)));
        System.out.println();

        // 4. HashSet 보다 추가적인 메소드 제공
        System.out.println("가장 낮은 점수 : " + scores.first());
        System.out.println("가장 높은 점수 : " + scores.last());
        System.out.println("95점 한 단계 아래 점수 : " + scores.lower(95));
        System.out.println("98점 한 단계 위 점수 : " + scores.higher(95));
        System.out.println("95점 이거나 한 단계 아래 점수 : " + scores.floor(95));
        System.out.println("85점 이거나 한 단계 위 점수 : " + scores.ceiling(85));
        
        // 5. 내림차순 Descending
        NavigableSet<Integer> descending = scores.descendingSet();
        System.out.println("descending = " + descending);

        // 6. 범위 검색 ( 80 <= )
        System.out.println("scores.tailSet( 80, true ) = " + scores.tailSet( 80, true ));

        // ( 80 <= <90 ) 80 ~ 90 사이
        System.out.println("scores.subSet(80, true, 90, false) = " + scores.subSet(80, true, 90, false));

    }
}

/*

컬렉션 프레임워크
    1. List : 저장 순서/인덱스 O, 중복 O
        ArrayList, Vector, LinkedList
    2. Set : 저장 순서/인덱스 X, 중복 X
        HashSet, TreeSet
    3. Map : Entry(Key,Value) 저장, Key 중복 X
        HashMap, HashTable, TreeMap

    검색 강화시킨 컬렉션
        TreeSet, TreeMap
        이진트리 : 하나의 노드(뿌리) 시작해서 각 노드에 최대 2개의 노드 연결
        부모 노드의 객체와 비교 (낮은것은) 왼쪽 자식 (높은것은) 오른쪽 자식
        정렬을 하면 검색이 빠르다.

    스택 / 큐 컬렉션
        스택 : 후입선출
            사용되는 용도 : CTRL + Z (뒤로가기), JVM 스택영역
        큐 : 선입선출
            사용되는 용도 : 인쇄 ( 먼저 인쇄한 선수대로 ), 스레드풀( 동기화 = 요청순서 )
*/