package org.sample.regex;

public class EmailPattern {
    public static void main(String[] args) {

        // [a-z-A-Z] --> означает что первое слово должно начинаться с буквы
        // \\w все буквы и номеры и знак '*' означает что от нуля до бесконечности
        // {3,} минимум 3 до бесконечности
        // группируем 2 вида конец почти после точек
        String regex = "[a-z-A-Z]\\w*@\\w{3,}\\.(com|org)";
        String email = "abbos887@mail.com";
        System.out.println(email.matches(regex));
    }
}
