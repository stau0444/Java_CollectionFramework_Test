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

### 스레드와 프로세스

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


### Thread의 상태

<img width="550" alt="스크린샷 2022-08-01 오후 2 09 02" src="https://user-images.githubusercontent.com/51349774/182076967-c5be3bb2-00e3-4b9e-ac96-a112c8b60732.png">




  
  
