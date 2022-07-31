package IOStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamTest {
    public static void main(String[] args) {
        //첫번째 파라미터는 파일명  두번째 파라미터는 이어쓰기 여부(default는 false)
        //이어쓰기 여부가 false 일 경우 데이터는 항상 덮어쓰기된다
        try (FileOutputStream fos = new FileOutputStream("test.txt",true)) {
            fos.write(65);  //A
            fos.write(66);  //B
            fos.write(67);  //C
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
