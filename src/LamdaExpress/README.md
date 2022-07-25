---
### 함수형 인터페이스

- 람다식 선언을 위한 인터페이스
- 익명 함수와 매개 변수만으로 구현되므로 인터페이스는 단하나의 메서드만을 선언해야한다.
- @FunctionalInterface 애노테이션을 사용한다.

---

### 객체지향과 람다식 비교

>- 함수형 인터페이스가 클래스 없이 구현되는 것 같지만 내부적으로는 익명클래스가 생성되어 구현된다.
```java
public class LambdaMain {
    public static void main(String[] args) {

        //클래스 구현
        StringConcat stringConcat = new StringConcat();
        stringConcat.contCat("a","b 클래스");
        //함수형 인터페이스
        Concat concat = (x,y)-> {System.out.println(x+y);};
        concat.concat("a", "b 람다");

    }
}
```