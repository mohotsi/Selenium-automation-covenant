package ae.proctected.covenant.Seleniumautomation.annotation;

import java.util.Arrays;

public class non {
    public static void main(String[] args) {
         int [] numbers = {8,8,2,5,3,1,2,5,4,12,2,11,852,12,3};
        Arrays.stream(numbers).sequential().filter(number->number%2!=0).forEach(System.out::println);
    }
}
