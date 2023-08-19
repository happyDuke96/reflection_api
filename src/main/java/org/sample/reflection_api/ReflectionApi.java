package org.sample.reflection_api;

import java.lang.reflect.*;

public class ReflectionApi {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        User john = new User(1L, "John","Doe");

        Class<? extends User> johnClass = john.getClass();
        Class<User> johnClass2 = User.class;   // all is Singleton

        System.out.println(johnClass == johnClass2);


//        testConstructor();
//
//        testFields(john);

        testMethod(john);

    }
    
    
    private static void testConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<User> userConstructor = User.class.getDeclaredConstructor(Long.class, String.class,String.class);
        User bob = userConstructor.newInstance(2L, "Bob","Marley");
        System.out.println(bob);
    }

    private static void testFields(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // чтобы получить доступ к приватным полям
            Object value = field.get(obj); // если поля private бросается exception IllegalAccessException
            System.out.println(value);
            System.out.println(field.getModifiers());
            System.out.println(Modifier.isPrivate(field.getModifiers()));

            // list of modifiers [0,0,0,0,0,0]
            // if private [0,0,0,0,0,1]
            // if abstract [0,0,0,0,1,1]
            // if final [0,0,0,1,1,1]
        }
    }

    private static void testMethod(User user) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = user.getClass().getDeclaredMethod("getName");
        System.out.println(method.invoke(user));
    }
}
