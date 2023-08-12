package org.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionIntro {

    public static void main(String[] args) {
        String phoneNumber = "abcdefg +998 (99) 880-32-99 abcdefg abcde +998 (90) 930-43-75 abcdefg abcfg +998 (97) 720-23-94" +
                "abcdefg dsadas +998 (88) 555-55-70 fasdfdasdf dsa +998 (93) 420-71-64";

        // знак плюс считается зарезеревaнный по этому над добавить \\ -slash
        // () --> это группировка
        String regex = "(\\+998)? ? \\(\\d{2}\\) ?\\d{3}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        while (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.group(1)); // +998
        }

        System.out.println("**************************************");
        // здесь найдем последний два цифры похож на второй группы --> 99
        String regex2 = "(\\+998)? ?\\((\\d{2})\\) ?\\d{3}-\\d{2}-\\2"; // last symobol match to first group -> +998
        Pattern patter2 = Pattern.compile(regex2);
        Matcher matcher2 = patter2.matcher(phoneNumber);
        while (matcher2.find()){
            System.out.println(matcher2.group());
        }

        System.out.println("**************************************");
        // здесь дадим название группы и по его может найти --> <code>
        // также можно исключить группу с помощью знака ?:  --> (?:\\+998)
        String regex3 = "(\\+998)? ?\\((?<code>\\d{2})\\) ?\\d{3}-\\d{2}-\\2"; // last symobol match to first group -> +998
        Pattern patter3 = Pattern.compile(regex3);
        Matcher matcher3 = patter3.matcher(phoneNumber);
        while (matcher3.find()){
            System.out.println(matcher3.group("code")); // --> 99
        }
    }
}
