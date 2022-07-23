package InnerClassTest;

/* 지역 inner class */
class B{
    int oa = 10;
    int ob = 20;

    public Runnable getRunnable(int i){
        //getRunnable이 사용되는 시점과
        //run()메서드가 사용되는 시점이 다르다.
        //지역 innerClass의 지역 변수들은 상수화되어 상수영역에 저장된다.
        int b = 30;
        class A implements Runnable{
            int ia = 50;
            int ib = 60;
            @Override
            public void run() {
                //변경 불가
                //i=200;
                //b=40;
                System.out.println("runnable run");;
            }
        }
        return new A();
    }
}
/* 익명 클래스 */
class C{
    int oa = 10;
    int ob = 20;

    //인터페이스나 추상클래스를 익명클래스로 구현하여 해당타입의 변수로 담아 사용한다.
    Runnable runnable = new Runnable(){
            int ia = 50;
            int ib = 60;
            @Override
            public void run() {
                System.out.println("익명 runnable run");;
            }
    };
}
public class AnonymousTest {
    public static void main(String[] args) {
        B b = new B();
        b.getRunnable(2).run();
        C c = new C();
        c.runnable.run();
    }
}
