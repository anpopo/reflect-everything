package anpopo.reflect_fields;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.logging.Logger;

public class ConfigFileParser {

    private static final Logger logger = Logger.getLogger(ConfigFileParser.class.getName());

    public static <T> T createConfigObject(Class<T> clazz, Path path) {
        try (BufferedReader br = new BufferedReader(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            T instance = constructor.newInstance();

            String line;
            while ((line = br.readLine()) != null) {
                String trimmedLine = line.trim();
                int index = trimmedLine.indexOf("=");

                if (index == -1) {
                    continue;
                }

                String key = trimmedLine.substring(0, index);
                String value = trimmedLine.substring(index + 1);

                Field field;
                try {
                    field = clazz.getDeclaredField(key);
                } catch (NoSuchFieldException e) {
                    continue;
                }

                Object fieldValue =
                    field.getType().isArray()
                        ? parseArray(field.getType(), value)
                        : parseValue(field.getType(), value);

                field.setAccessible(true);
                field.set(instance, fieldValue);
            }

            return instance;
        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            logger.severe(e.getMessage());
            throw new UnsupportedOperationException("Failed to create config object: " + clazz.getName());
        }
    }

    private static Object parseArray(Class<?> type, String value) {
        String[] values = value.split(",");
        Object array = Array.newInstance(type.getComponentType(), values.length);
        for (int i = 0; i < values.length; i++) {
            Array.set(array, i, parseValue(type.getComponentType(), values[i]));
        }
        return array;
    }

    private static Object parseValue(Class<?> type, String value) {
        if (type.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (type.equals(String.class)) {
            return value;
        } else if (type.isEnum()) {
            return Enum.valueOf((Class<Enum>) type, value.toUpperCase(Locale.ROOT));
        }
        throw new UnsupportedOperationException("Unsupported type: " + type.getName());
    }

}
