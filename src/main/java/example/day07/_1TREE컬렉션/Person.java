package example.day07._1TREE컬렉션;

public class Person implements Comparable<Person> {

    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        // * 같으면 0, 적으면 -1, 크면 1
            // 만약 필드의 age 보다 매개변수의 age가 작으면 -1
        if(this.age < o.age) return -1;
            // 만약 필드의 age와 매개변수의 age가 같으면 0
        else if (this.age == o.age) return 0;
        else return 1;
        // 2. name 이름 정렬 ( String 클래스이므로 또 정렬기준 필요 )
        // return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
