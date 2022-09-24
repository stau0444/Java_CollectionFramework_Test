
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

---
### Set 인터페이스 사용

---


>- Set은 중복을 허용하지 않기 떄문에 Set에 담을 객체에 대하여 
> 동일성을 논리적으로 검증할 수 있도록 equals(), hashcode()
> 두 메서드의 재정의가 필요하다.
>- HashSet  , TreeSet이 많이 사용되며 이중 TreeSet은 내부적으로
> binary search tree로 구현되어 데이터를 꺼낼시 (inorder traversal을 통해) 정렬 되어 나온다. 
>- 때문에 TreeSet을 사용하기 위해선 Comparable 혹은 Comparator 인터페이스를 구현해줘야 한다.
>- Comparator는 파라미터를 두개 받아 비교하고 ,Comparable은 다음에 들어올 객체 하나만 자기자신인 this와 비교하는 차이점이 있다.
>- 보통 Comparable을 더 많이 사용한다고 한다.
>- Comparator로 구현시 아래의 예시처럼 TreeSet 생성시에 담으려는 객체를 생성자 파라미터로 넘겨줘야한다.

#### Comparator로 구현된 객체 생성 예시
```java
TreeSet<Member> = new TreeSet<>(new Member());
```
---
#### Comparable 구현 예시
```java
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
```
---

### Decorator pattern

- 자바의 입출력 스트림은 decorator pattern 으로 구현되어 있다.
- 기반클래스(component)가 여러 decorator들을 활용하여 다양한 기능을 제공받음
- 상속 보다 유연한 구현방식
- 데코레이터는 다른 데코레이터나 또는 컴포넌트를 포함해야한다.
- 지속적인 기능의 추가와 제거가 용이하다.
- decorator와 component는 동일한 것이 아니다 (기반 스트림 클래스가 직접 읽고 쓸수 없다 , 보조클래스는 추가적인 제공만 제공)

<img width="637" alt="스크린샷 2022-07-31 오후 4 38 32" src="https://user-images.githubusercontent.com/51349774/182015416-b10e0016-821b-41a3-94af-d4c1442a1cd4.png">


---


입출력 스트림 (IO Stream)
네트워크에서 자료의 흐름이 물의 흐름과 같다는 비유에서 유래됨
자바는 다양한 입출력 장치에 독립적으로 일관성 있는 입출력에 대한 기능을 추상화한 입출력 스트림을 통해 제공한다.
입출력이 구현되는 곳 : 파일 디스크 , 키보드 ,마우스 ,네트웍 ,메모리 등 모든 자료가 입력되고 출력되는 곳을 말한다.
입출력 스트림의 구분
대상기준: 입력 스트림 / 출력 스트림
한 스트림에 입력과 출력을 모두할 수 없다.
자료의 종류 : 바이트 스트림 / 문자 스트림
기본적으로 바이트 스트림으로 작동하나 자바는 멀티 바이트를 지원하여 2바이트 이상의 문자도 처리가 가능하다.
기능 : 기반 스트림 / 보조 스트림
스트림 클래스중 대상에 대해 직접 입출력을 담당하는 기반 스트림과 , 바이트에서 문자로 변환 ,버퍼링 등 읽고 쓰는 기능은 없지만 기반스트림을 감싸서 보조하는 보조스트림(wrapper stream)이 있다.
표준 입출력 스트림
System 클래스의 표준 입출력 멤버
import java.io.PrintStream;

public class System {
    public static PrintStream out;
    public static InputStream in;
    public static PrintStream err;
}
바이트 단위 입출력 스트림
InputStream
바이트 단위 입력 스트림들의 최상위 추상 클래스
많은 추상 메서드가 선언되어 있고 이를 하위 스트림이 상속받아 구현한다.
스트림 클래스	설명
FileInputStream	파일에서 바이트 단위로 자료를 읽는다.
ByteArrayInputStream	byte 배열 메모리에서 바이트 단위로 자료를 읽는다.
FilterInputStream	기반 스트림에서 자료를 읽을 때 추가 기능을 제공하는 보조 스트림의 상위 클래스이다.
read(byte[] arr)
InputStream에서 바이트를 읽는 메서드이다
파라미터를 넘기지 않으면 한 바이트씩 읽는다
파라미터로 바이트 배열을 넘기면 해당 배열만큼 buffer를 가지고 데이터를 읽어온다.
//read(byte[] arr) 사용 예시
public class SystemInTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("start = " + start);
        for (int t=0 ; t<500; t++){
            int i;
            try (FileInputStream fis = new FileInputStream("input.txt")) {
                //input.txt 에는 abcdefghij klnmopqrst uvwxwz가 적혀있다.
                //버퍼의 크기를 10이라 하면 마지막에 읽어들인 데이터는 uvwxwz 일 것이다.
                //하지만 이전에 읽어들인 qrst가 배열에 남아 있기 때문에
                //확장 포문으로 하나씩 읽을 경우 마지막엔 uvwxwzqrst가 찍히게 된다.
                //아래의 for문에서 j는 arr배열의 인덱스이고
                //i는 파일을 읽어들이는 바이트 수를 말한다.
                //인덱스가 바이트 보다 커진다는 것은 이전에 남아있는 데이터를 의미하기 때문에
                //그 이후로는 출력되지 않도록 하면 남아있는 데이터를 제외하고 출력할 수 있다.
                byte [] arr = new byte[10];
                System.out.println(t);
                while((i=fis.read(arr)) != -1){
                    for (int j = 0; j<i; j++){

                        System.out.print((char)arr[j]);

                    }
                }
                System.out.println();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        long end = System.currentTimeMillis();
        long workTime = end-start;
        System.out.println("workTime = " + workTime);
    }
OutputStream
write() 메서드가 파일을 만들어 낸다.
첫번째 파라미터는 파일명 두번째 파라미터는 이어쓰기 여부(default는 false)
이어쓰기 여부가 false 일 경우 데이터는 항상 덮어쓰기 된다
flush()는 출력 버퍼를 비울때 사용되며 close()될 때 자동으로 불린다.
문자 단위 입출력 스트림
Reader
문자 단위 입력 스트림 최상위 추상 클래스
많은 추상 메서드가 선언되어 있고 이를 하위 스트림이 상속받아 구현한다.
주요 하위 클래스
클래스	설명
FileReader	파일을 문자 단위로 읽는 스트림 클래스
InputStreamReader	바이트 단위로 읽은 자료를 문자로 변환해주는 보조 스트림 클래스
BufferedReader	문자로 읽을 때 배열을 제공하여 한꺼번에 읽을 수 있는 기능을 제공하는 보조 클래스
보조 스트림
실제 읽고 쓰는 스트림이 아닌 보조 기능을 제공하는 스트림
FilterInputStream FilterOutputStream 이 보조 스트림의 상위 클래스다.
생성자의 매개변수로 또 다른 스트림을 제공해야한다.
Decorator Pattern으로 구현된다.
주요 클래스로 바이트 데이터를 문자데이터로 변환해주는 InputStreamReader,OutputStreamWriter 내부적으로 buffer를 가지고 있어 데이터를 더 빨리 읽고 쓸 수 있게하는 BufferedReader ,BufferedWriter 자료가 메모리에 저장된 상태 그대로 읽거나 쓰는 DataInputStream , DataOutputStream 이 있다 .
DataInputStream과 DataOutputStream
메모리에 저장된 자료형 대로 읽고 쓰기가 가능하다 .
DataOutputStream을 사용해 여러 자료형으로 write() 했다면 해당 데이터를 read() 할때도 들어간 자료형의 순서에 맞게 데이터를 읽어 들여야 한다. ex) write 순서 String - int - byte read 순서 String - int - byte
public class DataInputStreamTest {
    public static void main(String[] args) {
        try(
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("distest.txt"));
            DataInputStream dis = new DataInputStream(new FileInputStream("distest.txt"));
           ){
            //String - Int - byte 순으로 입력
            dos.writeUTF("안녕하세요");
            dos.writeInt(125);
            dos.writeByte(65);
            //String - Int - byte 순으로 출력
            String s = dis.readUTF();
            int iNum = dis.readInt();
            byte b = dis.readByte();

            System.out.println("s+iNum+b = " + s+iNum+(char)b);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
Serialization (직렬화)
인스턴스의 상태를 그대로 파일 저장하거나 네트웍으로 전송하고 (serialization)이를 다시 복원(deserialization)하는 방식
자바에서는 보조 스트림을 활용하여 직렬화를 제공한다.
ObjectInputStream 과 ObjectInputStream이 직렬화된 입출력을 제공한다.
Serialization interface
직렬화는 인스턴스의 내부 내용이 외부로 유출되는 것이므로 프로그래머가 해당 객체에 대한 직렬화 의도를 표시해야한다.
구현 코드가 없는 maker interface이다
transiant: 직렬화 하지 않으려는 멤버 변수에 사용한다 ()
Externalizable
직렬화를 직접구현 하도록 구현메서드를 제공한다.
readExternal() , writeExternal() 값을 직접 정의하여 read , write 방식을 구현한다.
그 외의 입출력 클래스
File 클래스
파일 개념을 추상화한 클래스
입출력 기능은 없고 , 파일 이름 , 경로 , 읽기 전용등의 속성을 알 수 있도록한 클래스
RandomAccessFile 클래스
입출력 클래스 중 유일하게 파일에 대한 입력과 출력을 동시에 할 수 있는 클래스이다.
파일 포인터가 있어서 읽고 쓰는 위치의 이동이 가능함


