package fr.km.exemple;

import java.util.function.Function;

public class ComposeService {
    public static void main(String[] args) {

        String s = "a <   B";
        // composing a functions
        Function<String, String> normalize = String::toUpperCase;
        normalize = normalize.andThen(ComposeService::escapeHtml);
        normalize = normalize.andThen(ComposeService::collapseSpaces);
        s = normalize.apply(s);

       //  initial
//        s =collapseSpaces(escapeHtml(s.toUpperCase()));

        System.out.println(s);

    }



    private static String escapeHtml(String input){
        return input.replace("<", "&lt;");
    }

    private static String collapseSpaces(String input){
        return input.replaceAll("\\s+", " ");
    }

}
