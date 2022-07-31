package IOStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
    public static void main(String[] args) {
        //FileInputStream를 InputStreamReader로 감싸 데이터를 문자로 변환한다.
        try (InputStreamReader ifr = new InputStreamReader(new FileInputStream("src/IOStream/reader.txt"))){
            int i ;
            while((i=ifr.read()) != -1){
                System.out.print((char)i);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
