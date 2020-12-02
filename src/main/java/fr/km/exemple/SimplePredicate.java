package fr.km.exemple;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SimplePredicate {

    public static int getTotalValues(List<Integer>  numbers, Predicate<Integer> selector){
        return numbers.stream()
        .filter(selector)
        .reduce(0, Math::addExact);
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,5,6,9,7,4,3,10);
        System.out.println(getTotalValues(values, v ->v % 3 == 0));
        System.out.println(getTotalValues(values, v -> true));
        System.out.println(getTotalValues(values, v ->v % 2 == 0));
    }

}
