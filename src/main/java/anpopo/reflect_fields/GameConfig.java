package anpopo.reflect_fields;

import anpopo.reflect_fields.FieldsMain.Category;

public class GameConfig {
    private String name;
    private int ageLimit;
    private Category category;

    public String getName() {
        return name;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public Category getCategory() {
        return category;
    }
}
