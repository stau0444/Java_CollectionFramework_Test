package CollectionFramework;

import java.util.TreeSet;

public class TestMain {
    public static void main(String[] args) {

        Member m1 = new Member(1,"ugo1");
        Member m2 = new Member(2,"ugo2");
        Member m3 = new Member(3,"ugo3");
        Member m4 = new Member(4,"ugo4");

        TreeSet<Member> members = new TreeSet<Member>();
        members.add(m4);
        members.add(m1);
        members.add(m3);
        members.add(m2);
        System.out.println(members);
    }
}
