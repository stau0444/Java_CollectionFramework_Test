package IOStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInputReaderTest {
    public static void main(String[] args) {
        try(FileReader fr = new FileReader("src/IOStream/reader.txt");) {
            int i; 
            while((i=fr.read()) != -1){
                System.out.print((char)i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
