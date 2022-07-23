package CollectionFramework;

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
