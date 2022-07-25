package Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    private final List<String> stringList= new ArrayList<>();

    public void printAll(){
        stringList.forEach(System.out::println);
    }
    public void addToList(String a ){
        stringList.add(a);
    }
}
