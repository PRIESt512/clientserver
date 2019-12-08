package ru.hacker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartAnnotation {

    Map
    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException {

        StartAnnotation.Test test = (Test) Class.forName("ru.hacker.StartAnnotation$Test").newInstance();

        for (Field field : test.getClass().getDeclaredFields()) {
            System.out.println(String.format("Поле - %s", field.toString()));

            System.out.println(String.format("Аннотации - %s", field.getAnnotation(TestAnnotation.class)));

            TestAnnotation testAnnotation = field.getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {

                String instanceClass = field.getAnnotation(TestAnnotation.class).instanceClass();
                System.out.println(String.format("Значение instanceClass - %s", instanceClass));

                field.set(test, Class.forName(instanceClass).newInstance());//test.list = new ArrayList()

                test.list.add("123");
                test.list.add("321");

                for (Object item : test.list) {
                    System.out.println(item);
                }
            }
        }
    }

    static class Test {
        @TestAnnotation(instanceClass = "java.util.LinkedList")
        public List list;
    }
}
