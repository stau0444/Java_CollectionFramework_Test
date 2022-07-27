package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


class StringCompare implements BinaryOperator<String> {
    @Override
    public String apply(String s1, String s2) {
        return s1.getBytes().length > s2.getBytes().length ? s1 : s2;
    }


}
class StringDescCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2)*(-1);
    }
}

public class SteamMain {
    public static void main(String[] args) {
        String [] stringArr = {"가나다라마",  " 가나다" , "가나다라마바사","abc"};

        //람다식 사용
        String lambdaUsed = Arrays.stream(stringArr).reduce(
                "",
                (s1, s2) -> {
                    return s1.getBytes().length < s2.getBytes().length ? s1 : s2;
                }
        );
        System.out.println("reduce = " + lambdaUsed);
        String classUsed = Arrays.stream(stringArr).reduce("", new StringCompare());
        System.out.println("classUsed = " + classUsed);

        //sorted()에 Comparator 구현 클래스 넘겨서 사용
        String [] engArr = {"a","c","d","b"};
        List<String> collect = Arrays.stream(engArr).sorted(new StringDescCompare()).collect(Collectors.toList());

    }
}
