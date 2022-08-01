package Thread;


//Thread 클래스 상속
class ExtendThread extends Thread{
    @Override
    public void run() {
        System.out.println("extendThread");
        for (int i = 0; i<100; i++){
            System.out.print(i+",");
        }
    }
}

//Runnable 인터페이스 구현
class ImplementRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("implementRunnable");
        for (int i = 0; i<100; i++){
            System.out.print(i+",");
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        ExtendThread et = new ExtendThread();
        //Runnable을 구현한 클래스는 새로운 Thread를 생성해서 파라미터로 넘겨줘야
        //start()가 가능하다.
        Thread it = new Thread(new ImplementRunnable());
        et.start();
        System.out.println();
        it.start();
    }
}
