package anpopo.reflect_class;

import java.util.*;
public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
                Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {

        return PopupTypeInfo.builder()
                .primitive(inputClass.isPrimitive())
                .setInterface(inputClass.isInterface())
                .setEnum(inputClass.isEnum())
                .name(inputClass.getName())
                .jdk(isJdkClass(inputClass))
                .inheritedClassNames(getAllInheritedClassNames(inputClass))
                .packageName(inputClass.getPackageName())
                .build();
    }

    private static boolean isJdkClass(Class<?> inputClass) {
        return JDK_PACKAGE_PREFIXES.stream().anyMatch(packagePrefix ->
                inputClass.getPackage() == null
                        || inputClass.getPackage().getName().startsWith(packagePrefix));
    }

    private static String[] getAllInheritedClassNames(Class<?> inputClass) {
        String[] inheritedClasses;
        if (inputClass.isInterface()) {
            inheritedClasses = Arrays.stream(inputClass.getInterfaces())
                    .map(Class::getSimpleName)
                    .toArray(String[]::new);
        } else {
            Class<?> inheritedClass = inputClass.getSuperclass();
            inheritedClasses = inheritedClass != null ?
                    new String[]{inputClass.getSuperclass().getSimpleName()}
                    : null;
        }
        return inheritedClasses;
    }
}