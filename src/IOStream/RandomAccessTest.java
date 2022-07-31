package IOStream;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessTest {
    public static void main(String[] args) {
        try(RandomAccessFile rf = new RandomAccessFile("random.txt", "rw");){
            rf.writeInt(100);
            //인트형(4byte)이므로 파일포인터의 위치는 4가된다.
            long filePointer = rf.getFilePointer();
            System.out.println("filePointer = " + filePointer);
            //rf.writeDouble(3.14);
            //포인터를 맨앞으로 위치시킨다.
            rf.seek(4);
            System.out.println("rf.getFilePointer() = " + rf.getFilePointer());
            //들어간 자료형 순서대로 읽어야 한다.
            int i = rf.read();
            System.out.println("i = " + i);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
