package LamdaExpress;

import java.util.stream.Stream;

public class ConcatUtil {
    public static Concat concatPrint(String s){
        return (x,y) ->{
            System.out.println(x+y+s);

        };
    }
}
