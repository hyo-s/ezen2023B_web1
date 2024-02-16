package example.day07._1TREE컬렉션;

import java.util.TreeSet;

public class Example3 {
    public static void main(String[] args) {

        // 1. TreeSet 컬렉션
            // 필수 : 정렬기준 필요 ( Integer, String, Double 등 타입 )
        TreeSet<Person> treeSet = new TreeSet<>();

        // 2. 객체
        treeSet.add(new Person("홍길동", 29));
        treeSet.add(new Person("김자바", 25));
        treeSet.add(new Person("박지원", 31));
        System.out.println("treeSet = " + treeSet);

        String str = "유재석";
        System.out.println("str.compareTo() = " + str.compareTo("유재석"));
        System.out.println("str.compareTo() = " + str.compareTo("강호동"));
        System.out.println("str.compareTo() = " + str.compareTo("홍길동"));
    }
}
