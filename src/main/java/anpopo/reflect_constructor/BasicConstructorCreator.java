package anpopo.reflect_constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class BasicConstructorCreator {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        printConstructor(Book.class);
        printConstructor(Author.class);

        System.out.println("+++++++++++++++++");
        Author author = BasicConstructorCreator.getInstance(Author.class, "칼 세이건", 36);
        Book book = BasicConstructorCreator.getInstance(Book.class, author, "코스모스", 30_000);

        System.out.println(book);

    }

    public static <T> T getInstance(Class<T> clazz, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == args.length) {
                boolean flag = true;
                for (int i = 0; i < args.length; i++) {
                    if (!parameterTypes[i].getSimpleName().equals(args[i].getClass().getSimpleName())) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return (T)constructor.newInstance(args);
                }
            }
        }

        return null;
    }

    public static void printConstructor(Class<?> clazz) throws NoSuchMethodException {
        StringBuilder sb = new StringBuilder();
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            List<String> classNames = Arrays.stream(constructor.getParameterTypes())
                    .map(Class::getSimpleName)
                    .toList();

            sb.append(classNames).append("\n");
        }
        System.out.println(sb);
    }

    public static class Book {
        private Author author;
        private String name;
        private Integer price;

        public Book() {
            this(null, "제목 없음", 0);
        }

        public Book(Author author) {
            this(author, "제목 없음", 0);
        }

        public Book(Author author, String name) {
            this(author, name, 0);
        }

        public Book(Author author, String name, Integer price) {
            this.author = author;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "author=" + author +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static class Author {
        private String name;
        private Integer age;

        public Author(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
