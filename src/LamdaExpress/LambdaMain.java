package LamdaExpress;

import java.awt.*;

public class LambdaMain {
    public static void main(String[] args) {

        //클래스 구현
        StringConcat stringConcat = new StringConcat();
        stringConcat.contCat("a","b 클래스");
        //함수형 인터페이스
        Concat concat = (x,y)-> {System.out.println(x+y);};
        concat.concat("a", "b 람다");

        //매개 변수로 활용됨
        StringUtil util = (x,y)->{
          x.concat(y,y);
        };
        //리턴타입으로 활용됨
        Concat concat1 = ConcatUtil.concatPrint("s");
        concat1.concat("a","b");
    }
}
