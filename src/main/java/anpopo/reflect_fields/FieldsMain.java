package anpopo.reflect_fields;

import java.lang.reflect.Field;

public class FieldsMain {

    public static void main(String[] args) throws NoSuchFieldException {
//        Movie avatar = new Movie("아바타 물의길", 2023, true, Category.ADVENTURE, 20.99);
//        FieldsMain.printFields(avatar.getClass());

        printFields(Movie.class);
        System.out.println("------------- Line for check synthetic field");
        printFields(Category.class);
    }

    public static <T> void printFields(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        if (clazz != null) {
            printFields(clazz.getSuperclass());

            for (Field field : clazz.getDeclaredFields()) {
                sb.append(String.format("field name : %s, type : %s\n", field.getName(), field.getType().getName()))
                    .append(String.format("is synthetic : %s", field.isSynthetic()))
                    .append("\n");
            }

            System.out.println(sb);
        }


    }

    public static class Movie extends Product {

        public static final double MINIMUM_PRICE = 10.99;
        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String productName, int year, boolean isReleased, Category category, double actualPrice) {
            super(productName, year);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = actualPrice;
        }
    }

    public static class Product {

        public String productName;
        protected int year;
        double actualPrice;

        public Product(String productName, int year) {
            this.productName = productName;
            this.year = year;
        }

    }

    public enum Category {
        ADVENTURE,
        ACTION,
        COMEDY
    }


}
