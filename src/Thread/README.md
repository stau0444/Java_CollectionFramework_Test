### Thread

- 실행 중인 프로그램이 실행되면 OS로 부터 메모리를 할당받아 프로세스 상태가 된다.
- 하나의 프로세스는 하나 이상의 thread를 가지게 되고 ,실제 작업을 수행하는 단위는 thread 이다.

---
#### muti-threading

- 여러 thread가 동시에 수행되는 프로그래밍 , 여러 작업이 동시에 실행된다.
- thread는 각각 자신만의 작업 공간은 갖는다 (context)
- thread 사이에서 공유하는 자원이 있을 수 있다 (자바에서는 static instance)  
- 각 thread가 자원을 공유하며 작업이 수행되는 경우 서로 자원을 차지하려는 race condition이 발생할 수 있다 .
- 이렇게 여러 thread가 공유하는 자원중 경쟁이 발생하는 부분을 critical section(임계영역)이라고 한다.
- critical section(일종의 순차적 수행)에 대한 동기화를 구현히자 않으면 오류가 발생할 수 있다.

---

#### 스레드와 프로세스

- 특정 프로그램이 실행될때 하드디스크로부터 해당 프로그램을 불러와 OS로 부터 메모리를 할당 받아 프로세스화 되고 
  프로세스안의 작업단위인 스레드가 cpu를 점유하며 작업을 수행하게된다. 멀티스레드의 경우 여러 스레드가 동시에 수행되는 것 처럼 보이지만
  cpu에서 스레드들이 switching되며 동시에 작업이 수행되는 것 같이 동작한다 . 
  이떄  스레드에 대한 수행순서는 scheduler가 관리한다.
   각 쓰레드는 자기자신만의 변수나 환경변수 들을 갖게되며 이를 컨텍스트라 부른다.
  반면 쓰레드가 동시에 공유하는 자원도 존재하는데 공유 자원에 대한 스레드 간의 race condition이 발생할 수 있으며
  이와 같이 경쟁이 발생하는 부분을 critical section(임계 영역)이라 한다. critical section(임계 영역)에 대해서는
  해당 자원에 대해서 동기화(synchronized :순차적수행) 구현이 필요하다.
   자바에서는 동기화에 방식으로 synchronized 메서드 , synchronized block를 제공한다.
  
  
#### 자바에서 Thread 생성

```` java
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
````

---

#### Thread의 상태

<img width="550" alt="스크린샷 2022-08-01 오후 2 09 02" src="https://user-images.githubusercontent.com/51349774/182076967-c5be3bb2-00e3-4b9e-ac96-a112c8b60732.png">

- 스레드가 시작되면 스레드 풀에 runnable한 상태(cpu를 점유할 수 있는 상태)로 존재한다.
- Not Runnable 상태가 되면 cpu를 점유할 수 없는 상태가 되며 Sleep , wait , join 메서드로 해당상태가 될 수 있다.
- Not Runnable 상태는 Not Runnable이  되게한 각 메서드들에 따른 방식으로 다시 Runnable 상태로 돌아간다.


---

#### Thread 클래스의 메서드들

- sleep() 은 파라미터로 넘긴 시간만큼 스레드를 Not Runnable 상태로 만든다.
- join()은 특정 스레드에 대해 join을 걸면 해당 쓰레드 호출한 스레드는 join이 걸어진 스레드가 완료될 때까지 잠든다.
- join()은 스레드가 다른 스레드의 결과를 참조해야할때 사용된다. (* JoinTest.java 참조)
- wait()은 리소스가 한정 되어있는 상태에서 리소스 고갈시 리소스 사용 가능시까지 스레드를 Not Runnable 상태로 만든다.
- notify()는 wait()에 의해 잠든 스레드중 하나를 무작위로 꺠운다.
- notifyAll()은 wait()에 의해 잠든 스레드 모두를 꺠운다.
- notify()는 무작위로 스레드가 꺠어나기 떄문에 불리지 못하는 스레드가 생길 수 있다 .
  때문에 자바에선 notify()보다는 notifyAll()의 사용을 권장한다


---

### 멀티 Thread 프로그래밍에서의 동기화

----

####critical section 과 semaphore

- critical section은 두 개 이상의 thread가 접근 하는 경우 문제가 생길 수 있기 때문에 동시에 접근할 수 없는 영역
- semaphore는 특별한 형태의 시스템 객체이며 get/ release 두 개의 기능이 있다. 
- 한 순간 오직 하나의 thread 만이 semaphore를 얻을 수 있고, 나머지 thread는 대기상태(block)가 된다
- semaphore를 얻은 thread만이 critical section에 들어갈 수 있다.

---

#### synchronized 예시

- SyncTest.java 참조
- Parent , Child 클래스 모두 공유자원인 Bank에 접근하고 있는 상황 
- 임계영역에 접근한 경우 공우자원을 lock하여 다른 thread의 접근을 제어함
- 전반적으로 클래스내의 메서드가 동기화를 지원해야한다면 synchronized 메서드로 선언하는 것이 좋다.
- 객체를 사용하는 시점에서 동기화가 필요하다면 synchronized 블럭을 사용하는게 좋다.   
- 동기화를 잘못 구현하면 deadlock에 빠질 수 있다.
- 자바에서는 deadlock을 방지하는 기술이 제공되지 않으므로 되도록이면 synchronized 메서드에서
  다른 synchronized 메서드가 호출되는 일이 없어야 한다.
  
````java
    // synchronized 메서드
    public synchronized void saveMoney(int plus){
        int m = getMoney();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setMoney( m + plus );
    }
    
    // synchronized block 방식
    // this는 공유자원인 bank 자신을 가리킨다.
    public void minusMoney(int minus){
        synchronized (this) {
            int m = getMoney();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setMoney(m - minus);
        }
    }
    // Thread 자체에서 동기화를 할 경우
    class Parent extends Thread{
        @Override
        public void run() {
            // 동기화가 필요한 메서드를 Synchronized 블럭으로 감싸주고
            // 파라미터로 공유되는 자원을 넣어준다
            synchronized (SyncTest.bank){
                System.out.println("parents save money");
                SyncTest.bank.saveMoney(3000);
                System.out.println("saveMoney(3000):" + SyncTest.bank.getMoney());
            }
        }
    }
````

#### deadlock 


````java

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
````
---

#### 데드락을 피하기 위한 방법 
- 중첩되는 데이터 락을 피해야한다.
- 불필요한 락을 피해야한다.
- Thread join을 사용하자.

---

### wait()/notify()를 활용한 동기화

- 리소스가 어떤 조건에서 더 이상 유효하지 않은 경우 리소스를 기다리기 위해 Thread가 wait() 상태가 된다.
- wait() 상태가 된 Thread는 notify() 가 호출 될 때까지 기다린다.
- 유효한 자원이 생기면 notify()가 호출되고 wait()하고 있는  Thread중 무작위로 재시작 하도록 한다.
- notifyAll()이 호출될 경우 wait()하고 있는 모든 Thread가 재시작 된다.
- 이경우 유효한 리소스만큼의 Thread만이 수행될 수 있고 자원을 갖지 못한 Thread의 경우 다시 wait() 상태로 만든다.
- 자바에서는 notifyAll()의 사용을 권장한다.

---











  
  
