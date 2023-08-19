package org.sample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionReplaceExample {
    public static void main(String[] args) {

        String phoneNumber = "abcdefg +998 (99) 880-32-99 abcdefg abcde +998 (90) 930-43-75 abcdefg abcfg +998 (97) 720-23-94 " +
                "abcdefg dsadas +998 (88) 555-55-70 fasdfdasdf dsa +998 (93) 420-71-64 sdad";

        // Здесь можно группировать regexp,и найти в ниже указанном виде
        String regex1 = "(\\+998)? ?\\((?<code>\\d{2})\\) ?\\d{3}-\\d{2}-\\d{2}";
        String regex2 = "(?:\\+998)? ?\\((?<code>\\d{2})\\) ?(\\d{3})-(\\d{2})-(\\d{2})";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(phoneNumber);
        StringBuilder stringBuilder1 = new StringBuilder();
        while (matcher.find()){
            matcher.appendReplacement(stringBuilder1,"XXX"); // replace all find to XXXX
        }
        // символы не отображается после последнего найденного символа,поэтому надо вызвать метод appendTail()
        matcher.appendTail(stringBuilder1);
        System.out.println(stringBuilder1);

        System.out.println("********************************");

        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        StringBuilder stringBuilder2 = new StringBuilder();

        while (matcher2.find()){
            // в этом случае знак $ ссылается на группы
            matcher2.appendReplacement(stringBuilder2,"$2-$3-$4");
        }
        matcher.appendTail(stringBuilder2);
        System.out.println(stringBuilder2);



        // mask card number
        String panCard = "8600041246356001";
        StringBuilder maskedNumber = new StringBuilder();
        maskedNumber.append(panCard,0,6);

        System.out.println(maskedNumber);
        maskedNumber.append("******");
        System.out.println(maskedNumber);
        maskedNumber.append(panCard,12,panCard.length());
        System.out.println(maskedNumber);

        // substring is substantially faster than appending using a StringBuilder . Arrays.copyOfRange(value, offset, offset+count); This is still much faster, since Arrays
        StringBuilder maskedNumber2 = new StringBuilder()
                .append(panCard.substring(0,6)).append("******").append(panCard.substring(12));
        System.out.println(maskedNumber2);

    }
}
