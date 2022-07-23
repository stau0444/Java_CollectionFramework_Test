---
### Set 인터페이스 사용

---


>- Set은 중복을 허용하지 않기 떄문에 Set에 담을 객체에 대하여 
> 동일성을 논리적으로 검증할 수 있도록 equals(), hashcode()
> 두 메서드의 재정의가 필요하다.
>- HashSet  , TreeSet이 많이 사용되며 이중 TreeSet은 내부적으로
> binary search tree로 구현되어 데이터를 꺼낼시 (inorder traversal을 통해) 정렬 되어 나온다. 
>- 때문에 TreeSet을 사용하기 위해선 Comparable 혹은 Comparator 인터페이스를 구현해줘야 한다.
>- Comparator는 파라미터를 두개 받아 비교하고 ,Comparable은 다음에 들어올 객체 하나만 자기자신인 this와 비교하는 차이점이 있다.
>- 보통 Comparable을 더 많이 사용한다고 한다.
>- Comparator로 구현시 아래의 예시처럼 TreeSet 생성시에 담으려는 객체를 생성자 파라미터로 넘겨줘야한다.

#### Comparator로 구현된 객체 생성 예시
```java
TreeSet<Member> = new TreeSet<>(new Member());
```
---
#### Comparable 구현 예시
```java
public class Member implements Comparable{
    int id;
    String name;

    public Member() {
    }

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    //현재 자신과 파라미터로 다음 들어올 객체와의 비교 
    //리턴되는 음수 or 0 or 양수 3개의 값을 통해 작거나 큼을 구분해 이진검색트리를 구성하게 된다.
    //0일 경우 동일함으로 트리에 들어갈 수 없다.
    
    @Override
    public int compareTo(Object o) {
        Member m = (Member) o;
        return (this.id - m.id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
---


