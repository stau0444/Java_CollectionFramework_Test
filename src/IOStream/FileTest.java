package IOStream;

import java.io.*;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("src/IOStream/newFile.txt");

        try(FileOutputStream fos = new FileOutputStream(file);) {
            boolean newFile = file.createNewFile();
            fos.write(65);
            fos.write(66);
            fos.write(67);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
