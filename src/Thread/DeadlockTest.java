
package Thread;
//https://www.javatpoint.com/deadlock-in-java 참조함

public class DeadlockTest{
    public static void main(String[] args) {
        final String resource1 = "강아지";
        final String resource2 = "고양이";
        // t1 은 resource1에 접근하고 resource2에 접근한다.
        Thread t1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");

                    try { Thread.sleep(100);} catch (Exception e) {}

                    synchronized (resource2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };

        // t1 은 resource2에 접근하고 resource1에 접근한다.
        Thread t2 = new Thread() {
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");

                    try { Thread.sleep(100);} catch (Exception e) {}

                    synchronized (resource1) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };


        t1.start();
        t2.start();
    }
}