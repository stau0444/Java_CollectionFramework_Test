---
### 스트림(stream)

- 자료의 대상과 상관없이 동일한 연산을 수행 배열, 컬렉션을 대상으로 연산을 수행 한다.
- 일관성 있는 연산으로 자료처리를 간단하게 할 수 있도록 자료 처리에 대한 추상화가 구현되어 있다.
- 한번 생성하고 사용한 스트림은 재사용 할 수 없다 자료에 대한 스트림을 생성하여 연산을 수행하면 스트림은 소모된다.(재사용 불가)
- 스트림 연산은 기존 자료를 변경하지 않는다 자료에 대한 스트림을 생성하면 스트림이 사용하는 메모리 공간을
  별도로 생성되므로 연산이 수행되도 기존 자료 변경을 발생되지 않는다.
- 스트림의 연산은 중간연산과 최종연산으로 구분되며 중간연산은 여러번 일어날 수있지만 최종연산은 한번만 일어난다.
최종연산이 호출되어야 중간 연산에 대한 수행이 이루어지고 그 결과가 만들어지기 때문에 중간 연산에 대한 결과를 연산 중에 알 수 없으며 이를 지연연산이라 한다.
  
#### reduce()

- 정의된 연산이 아닌 프로그래머가 직접 구현한 연산을 적용시킬 때 사용하는 연산이다.
- 첫번쨰 파라미터로는 초기값 (identity), 두번째 파라미터로는 연산기준을 구현한 람다식 혹은 BinaryOperator라는 인터페이스를 구현한 클래스를 넣을 수 있다.
- BinaryOperator 인터페이스는 apply라는 메서드를 구현하도록 되어있다.
  apply()는 처음과 다음 요소를 파라미터로 받고 파라미터로 비교 연산하여 원하는 값을 return하는 로직을 구현하면된다.

#### sorted()
- 데이터를 정렬 시켜야 할 때 사용한다 .
- 파라미터로 람다식 혹은 Comparator 구현클래스를 넘겨서 정렬 기준을 구현할 수 있다.

#### reduce(),sorted() 사용 예시

  
````java
//BinaryOperator 구현클래스
class StringCompare implements BinaryOperator<String> {
    @Override
    public String apply(String s1, String s2) {
        return s1.getBytes().length > s2.getBytes().length ? s1 : s2;
    }
}
class StringDescCompare implements Comparator<String> {
  @Override
  public int compare(String o1, String o2) {
    return o1.compareTo(o2)*(-1);
  }
}
public class StreamMain {
    public static void main(String[] args) {
        String [] stringArr = {"가나다라마",  " 가나다" , "가나다라마바사","abc"};

        //람다식 사용
        String lambdaUsed = Arrays.stream(stringArr).reduce(
                "",
                (s1, s2) -> {
                    return s1.getBytes().length > s2.getBytes().length ? s1 : s2;
                }
        );
        System.out.println("reduce = " + lambdaUsed);
        
        //BinaryOperator 구현 클래스 사용
        String classUsed = Arrays.stream(stringArr).reduce("", new StringCompare());
        System.out.println("classUsed = " + classUsed);
        
        //sorted()에 Comparator 구현 클래스 넘겨서 사용
        String [] engArr = {"a","c","d","b"};
        List<String> collect = Arrays.stream(engArr).sorted(new StringDescCompare()).collect(Collectors.toList());

    }
}
````