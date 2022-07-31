package IOStream;

import javax.lang.model.SourceVersion;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
}
