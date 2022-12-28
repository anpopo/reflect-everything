package anpopo.reflect_class;

import java.util.*;

public class ReflectClass {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;
        Map<String, Integer> mapObject = new HashMap<String, Integer>();
        Class<?> hashMapClass = mapObject.getClass();
        Class<?> bricksClass = Class.forName("anpopo.reflect_class.ReflectClass$Bricks");
        var spongeClass = new Flexable() {
            @Override
            public boolean isFlexable() {
                return true;
            }
        };

        final Class[] classes = {
                Class.forName("java.util.Collections$UnmodifiableList"),
                List.class,
                Collection.class,
                boolean.class,
                int[][].class,
                Size.class,
                stringClass,
                hashMapClass,
                bricksClass,
                spongeClass.getClass()
        };

        printClassInfo(classes);

        for (Class<?> clazz : classes) {
            System.out.println(ClassAnalyzer.createPopupTypeInfoFromClass(clazz));
            System.out.println();
        }

    }

    private static void printClassInfo(Class<?>... classes) {

        for (Class<?> clazz : classes) {
            System.out.printf(
                    "class name : %s, class simple name : %s, package name : %s \n",
                    clazz.getName(),
                    clazz.getSimpleName(),
                    clazz.getPackageName());

            for (Class<?> inter : clazz.getInterfaces()) {
                System.out.printf(
                        "interface name : %s, interface simple name : %s, package name : %s, isInterface : %s\n",
                        inter.getName(),
                        inter.getSimpleName(),
                        inter.getPackageName(),
                        inter.isInterface()
                );
            }

            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null) {
                System.out.printf("super class name : %s, super class simple name : %s, super class package : %s\n",
                        superclass.getName(), superclass.getSimpleName(), superclass.getPackageName());
            }

            System.out.println("isArray : " + clazz.isArray());
            System.out.println("isEnum : " + clazz.isEnum());
            System.out.println("isPrimitive : " + clazz.isPrimitive());
            System.out.println("isInterface : " + clazz.isInterface());
            System.out.println("isAnonymousClass : " + clazz.isAnonymousClass());
            System.out.println();
        }
    }


    private static class Bricks implements Flexable {
        @Override
        public boolean isFlexable() {
            return false;
        }
    }

    private static interface Flexable {
        boolean isFlexable();
    }

    private enum Size {
        SMALL, MEDIUM, BIG,;
    }

}
