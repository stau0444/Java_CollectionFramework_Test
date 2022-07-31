package IOStream;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.net.http.WebSocket;

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
