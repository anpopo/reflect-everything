package anpopo.reflect_method;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFramework {

    private static final String SET = "set";
    private static final String GET = "get";

    public static void getterTest(Class<?> clazz) {
        List<Field> fields = getFields(clazz);
        Map<String, Method> methodMap = mapMethod(clazz);

        for (Field field : fields) {
            String fieldName = field.getName();
            String getterName = createGetterName(fieldName);

            if (!methodMap.containsKey(getterName)) {
                throw new IllegalStateException("getter method not found: " + getterName);
            }

            Method getterMethod = methodMap.get(getterName);

            if (!getterMethod.getReturnType().equals(field.getType())) {
                throw new IllegalStateException("getter method return type is not same with field type: " + getterName);
            }

            if (getterMethod.getParameterCount() != 0) {
                throw new IllegalStateException("getter method should not have any parameter: " + getterName);
            }
        }
    }

    public static void setterTest(Class<?> clazz) {
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            Method method;
            try {
                method = clazz.getMethod(createSetterName(field.getName()), field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("setter method not found: " + field.getName());
            }

            if (method.getParameterCount() != 1) {
                throw new IllegalStateException("setter method should have only one parameter: " + field.getName());
            }

            if (!method.getReturnType().equals(void.class)) {
                throw new IllegalStateException("setter method should not have return type: " + field.getName());
            }
        }
    }

    private static List<Field> getFields(Class<?> clazz) {
        if (clazz == null || clazz.equals(Object.class)) {
            return Collections.emptyList();
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList<>(Arrays.asList(declaredFields));

        for (Field field : declaredFields) {
            if (field.isSynthetic()) {
                continue;
            }
            fields.addAll(getFields(clazz.getSuperclass()));
        }


        return fields;
    }

    private static String createGetterName(String fieldName) {
        return GET + firstLetterUppercase(fieldName);
    }

    private static String firstLetterUppercase(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
    }

    private static String createSetterName(String fieldName) {
        return SET + firstLetterUppercase(fieldName);
    }

    private static Map<String, Method> mapMethod(Class<?> clazz) {
        Map<String, Method> methodMap = new HashMap<>();
        Method[] method = clazz.getMethods();

        for (Method m : method) {
            methodMap.put(m.getName(), m);
        }

        return methodMap;
    }
}
