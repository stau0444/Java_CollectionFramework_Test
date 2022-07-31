package IOStream;

import java.io.*;

public class BufferedReaderTest {
    public static void main(String[] args) {
        String s = null;

        try(BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")));){

            while((s=br.readLine()) != null){
                System.out.println(s);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
