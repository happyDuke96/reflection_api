package org.sample.reflection_api.hibernate_ref;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class EmployeeDemo {

    public static void main(String[] args) {
        Employee employee = new Employee(1, "Abbos");
        generateInsertSqlOnHibernateDemo(employee);
    }

    private static void generateInsertSqlOnHibernateDemo(Employee employee){
        String SQL = "INSERT INTO %s.%s (%s) VALUES (%s)";
        Field[] fields = employee.getClass().getDeclaredFields();
        Table table = employee.getClass().getAnnotation(Table.class);

        String filedNames = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .sorted(Comparator.comparing(Field::getName))
                .map(field -> field.getAnnotation(Column.class))
                .map(Column::name)
                .collect(Collectors.joining(","));

        String fieldValues = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .sorted(Comparator.comparing(Field::getName))
                .peek(field -> field.setAccessible(true))
                .map(field -> {
                    try {
                        return field.get(employee);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .map(field -> "'" + field + "'")
                .collect(Collectors.joining(","));

        System.out.println(String.format(SQL,table.schema(),table.value(),filedNames,fieldValues));
    }

    private static void generateSelectSqlOnHibernateDemo(Employee employee){
        String SQL = "SELECT * FROM %s.%s WHERE %s = %s";
        Field[] fields = employee.getClass().getDeclaredFields();
        Table table = employee.getClass().getAnnotation(Table.class);


    }
}
