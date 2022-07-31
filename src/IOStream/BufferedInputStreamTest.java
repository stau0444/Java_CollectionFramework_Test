package IOStream;

import java.io.*;

public class BufferedInputStreamTest {
    public static void main(String[] args) throws IOException {
        //BufferedInputStream 사용
        int i = 0;
        long msec = 0 ;
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/IOStream/test.JPG"));
            FileOutputStream fis = new FileOutputStream("copy.JPG");
        ){
            msec = System.currentTimeMillis();
            while ((i=bis.read()) != -1){
                fis.write(i);
            }
            msec = System.currentTimeMillis() - msec;
            System.out.println("msec = " + msec);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        //FileInputStream 사용
        try(FileInputStream fis = new FileInputStream("src/IOStream/test.JPG");
            FileOutputStream fos = new FileOutputStream("copy2.JPG");){
            msec = System.currentTimeMillis();
            while ((i=fis.read()) != -1){
                fos.write(i);
            }
            msec = System.currentTimeMillis() - msec;
            System.out.println("msec = " + msec);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
