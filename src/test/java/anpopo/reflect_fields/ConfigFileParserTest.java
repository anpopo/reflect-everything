package anpopo.reflect_fields;

import static org.junit.jupiter.api.Assertions.*;

import anpopo.reflect_fields.FieldsMain.Category;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class ConfigFileParserTest {

    @Test
    void configFileParseTest() {
        GameConfig configFile = ConfigFileParser.createConfigObject(
            GameConfig.class,
            Path.of("src/test/resources/game.properties")
        );
        assertNotNull(configFile);

        assertEquals("대장장이키우기", configFile.getName());
        assertEquals(12, configFile.getAgeLimit());
        assertEquals(Category.ADVENTURE, configFile.getCategory());
    }

}