package IOStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;

public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("start = " + start);
        for (int t = 0; t< 400; t++){
            System.out.println();
            System.out.println(t);
            int i;
            try (FileInputStream fis = new FileInputStream("input.txt")) {
                while((i=fis.read()) != -1){
                    System.out.print((char)i);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        long end = System.currentTimeMillis();
        long workTime = end-start;
        System.out.println("workTime = " + workTime);

    }
}