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
    static class Cclass {
        void innerMeth(){
            System.out.println("static inner");
        }
        static void innerStatic(){
            System.out.println("inner Static Method");
        }
    }
}

public class InnerTest {
    public static void main(String[] args) {
        A a = new A();
        A.Bclass.asd();
        //정적 innerClass 생성
        A.Cclass c = new A.Cclass();
        A.Cclass.innerStatic();
    }
}
