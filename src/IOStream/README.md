### 입출력 스트림 (IO Stream)

----
- 네트워크에서 자료의 흐름이 물의 흐름과 같다는 비유에서 유래됨
- 자바는 다양한 입출력 장치에 독립적으로 일관성 있는 입출력에 대한 기능을 추상화한 입출력 스트림을 통해 제공한다.
- 입출력이 구현되는 곳 : 파일 디스크 , 키보드 ,마우스 ,네트웍 ,메모리 등 모든 자료가 입력되고 출력되는 곳을 말한다.

---
#### 입출력 스트림의 구분
- 대상기준: 입력 스트림 / 출력 스트림   
  * 한 스트림에 입력과 출력을 모두할 수 없다.
- 자료의 종류 : 바이트 스트림 / 문자 스트림
  * 기본적으로 바이트 스트림으로 작동하나 자바는 멀티 바이트를 지원하여 2바이트 이상의 문자도 처리가 가능하다.
- 기능 : 기반 스트림 / 보조 스트림
  * 스트림 클래스중 대상에 대해 직접 입출력을 담당하는 기반 스트림과 ,
    바이트에서 문자로 변환 ,버퍼링 등 읽고 쓰는 기능은 없지만 기반스트림을 감싸서 보조하는  보조스트림(wrapper stream)이 있다.
    
---

### 표준 입출력 스트림

--- 

#### System 클래스의 표준 입출력 멤버

````java
import java.io.PrintStream;

public class System {
    public static PrintStream out;
    public static InputStream in;
    public static PrintStream err;
}
````

---

### 바이트 단위 입출력 스트림

- InputStream 
  * 바이트 단위 입력 스트림들의 최상위 추상 클래스
  * 많은 추상 메서드가 선언되어 있고 이를 하위 스트림이 상속받아 구현한다.
  

|스트림 클래스|설명|
|-----|------|  
|FileInputStream| 파일에서 바이트 단위로 자료를 읽는다. |
|ByteArrayInputStream | byte 배열 메모리에서 바이트 단위로 자료를 읽는다.|
|FilterInputStream | 기반 스트림에서 자료를 읽을 때 추가 기능을 제공하는 보조 스트림의 상위 클래스이다.|

---

##### read(byte[] arr)
- InputStream에서 바이트를 읽는 메서드이다 
- 파라미터를 넘기지 않으면 한 바이트씩 읽는다
- 파라미터로 바이트 배열을 넘기면 해당 배열만큼 buffer를 가지고 데이터를 읽어온다.

````java
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

````

---
##### OutputStream

- write() 메서드가 파일을 만들어 낸다. 
- 첫번째 파라미터는 파일명  두번째 파라미터는 이어쓰기 여부(default는 false)
- 이어쓰기 여부가 false 일 경우 데이터는 항상 덮어쓰기 된다
- flush()는 출력 버퍼를 비울때 사용되며 close()될 때 자동으로 불린다.


---
### 문자 단위 입출력 스트림


#### Reader

- 문자 단위 입력 스트림 최상위 추상 클래스
- 많은 추상 메서드가 선언되어 있고 이를 하위 스트림이 상속받아 구현한다.
- 주요 하위 클래스 

|클래스|설명|
|----|----|
|FileReader|파일을 문자 단위로 읽는 스트림 클래스|
|InputStreamReader| 바이트 단위로 읽은 자료를 문자로 변환해주는 보조 스트림 클래스|
|BufferedReader|문자로 읽을 때 배열을 제공하여 한꺼번에 읽을 수 있는 기능을 제공하는 보조 클래스|

---

### 보조 스트림
- 실제 읽고 쓰는 스트림이 아닌 보조 기능을 제공하는 스트림
- FilterInputStream FilterOutputStream 이 보조 스트림의 상위 클래스다.
- 생성자의 매개변수로 또 다른 스트림을 제공해야한다.
- Decorator Pattern으로 구현된다.
- 주요 클래스로 바이트 데이터를 문자데이터로 변환해주는 InputStreamReader,OutputStreamWriter
  내부적으로 buffer를 가지고 있어 데이터를 더 빨리 읽고 쓸 수 있게하는 BufferedReader ,BufferedWriter
  자료가 메모리에 저장된 상태 그대로 읽거나 쓰는 DataInputStream , DataOutputStream 이  있다 .
  


#### DataInputStream과 DataOutputStream

- 메모리에 저장된 자료형 대로 읽고 쓰기가 가능하다 .
- DataOutputStream을 사용해 여러 자료형으로 write() 했다면
  해당 데이터를 read() 할때도 들어간 자료형의 순서에 맞게 데이터를 읽어 들여야 한다.
  ex) write 순서  String - int - byte
      read 순서  String - int - byte

````java
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

````

---

### Serialization (직렬화)

- 인스턴스의 상태를 그대로 파일 저장하거나 네트웍으로 전송하고 (serialization)이를 다시 복원(deserialization)하는 방식
- 자바에서는 보조 스트림을 활용하여 직렬화를 제공한다.
- ObjectInputStream 과 ObjectInputStream이 직렬화된 입출력을 제공한다.


#### Serialization  interface

- 직렬화는 인스턴스의 내부 내용이 외부로 유출되는 것이므로 프로그래머가 해당 객체에 대한 직렬화 의도를 표시해야한다.
- 구현 코드가 없는 maker interface이다
- transiant: 직렬화 하지 않으려는 멤버 변수에 사용한다 ()

#### Externalizable

- 직렬화를 직접구현 하도록 구현메서드를 제공한다.
- readExternal() , writeExternal() 값을 직접 정의하여 
  read , write 방식을 구현한다.
  
---
### 그 외의 입출력 클래스

#### File 클래스 
- 파일  개념을 추상화한 클래스 
- 입출력 기능은 없고 , 파일 이름 , 경로 , 읽기 전용등의 속성을 알 수 있도록한 클래스


#### RandomAccessFile 클래스

- 입출력 클래스 중 유일하게 파일에 대한 입력과 출력을 동시에 할 수 있는 클래스이다.
- 파일 포인터가 있어서 읽고 쓰는 위치의 이동이 가능함




