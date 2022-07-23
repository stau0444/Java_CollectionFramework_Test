package InnerClassTest;

import java.sql.SQLOutput;

class A{
    int a=1;
    static int b=2;
    Bclass bClass;
    public A() {
        bClass = new Bclass();
    }

    public class Bclass{
        C cClass;
        public Bclass() {
            cClass= new C();
        }

        int c = 3;
        static int d = 4;
        {
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            System.out.println(d);
        }

        static public void asd(){
            String as = "as";

            System.out.println(b);
            System.out.println(as);
            System.out.println(d);
        }
    }
    static class C {
        {
            Bclass.asd();
        }
    }
}

public class InnerTest {
    public static void main(String[] args) {
        A a = new A();
        A.Bclass.asd();
    }
}
