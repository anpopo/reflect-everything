package anpopo.reflect_fields;

import anpopo.reflect_fields.FieldsMain.Category;

public class GameConfig {
    private String name;
    private int ageLimit;
    private Category[] categories;

    public String getName() {
        return name;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public Category[] getCategories() {
        return categories;
    }
}
