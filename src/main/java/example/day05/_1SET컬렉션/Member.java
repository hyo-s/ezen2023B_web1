package example.day05._1SET컬렉션;

public class Member {
    public String name;
    public int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }


    // haseCode, equals => 메소드의 주인 Object
        // 힙 가상주소를 int 값이 아닌 새로 값 정의
    @Override   // 재정의
    public int hashCode() {
        return name.hashCode()+age;
    }

    // toString => 메소드의 주인 Object
        // 객체의 주소를 반환
        // 오버라이딩 : 주소 반환 대신 필드(정보)로 반환
        // Member target = (Member)obj;
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member target){  // 매개변수의 객체가 Member 타입이면
            return target.name.equals(this.name) && (target.age==this.age);
        }else {
            return false;
        }
    }
    // instanceof : 타입 확인
}
