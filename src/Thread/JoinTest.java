package Thread;

public class JoinTest extends Thread{
    int start;
    int end;
    int total;

    public JoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void addStartFromEnd(int start, int end ){
        int i ;
        for (i = start; i<=end; i++){
            total += i;
        }
    }

    @Override
    public void run() {
        addStartFromEnd(start,end);
        System.out.println("total = " + total);
    }
}

class run{
    public static void main(String[] args){
            JoinTest joinTest = new JoinTest(1,50);
            JoinTest joinTest2 = new JoinTest(51,100);

            joinTest.start();
            joinTest2.start();

        try {
            //main Thread 에서 joinTest , JoinTest2  쓰레드를 join 하고 있다.
            joinTest.join();
            joinTest2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            int finalTotal = joinTest.total + joinTest2.total;
            System.out.println("joinTest = " + joinTest.total);
            System.out.println("joinTest2 = " + joinTest2.total);
            System.out.println("finalTotal = " + finalTotal);
    }
}
