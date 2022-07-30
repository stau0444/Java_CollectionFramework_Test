package IOStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SystemInTest {
    public static void main(String[] args) throws IOException {
        try (FileInputStream fis = new FileInputStream("input.txt")) {
            int i;
            while((i=fis.read()) != -1){
                System.out.print((char)fis.read());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
