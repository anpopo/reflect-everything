package anpopo.reflect_method;

import anpopo.reflect_method.pojo.BadGetterPojo;
import anpopo.reflect_method.pojo.BadSetterPojo;
import anpopo.reflect_method.pojo.ChildPojo;
import anpopo.reflect_method.pojo.TestPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestFrameworkTest {

    @Test
    void getterTest() {
        Assertions.assertDoesNotThrow(() -> TestFramework.getterTest(TestPojo.class));
    }

    @Test
    void setterTest() {
        Assertions.assertDoesNotThrow(() -> TestFramework.setterTest(TestPojo.class));
    }


    @Test
    void badGetterTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> TestFramework.getterTest(BadGetterPojo.class));
    }

    @Test
    void badSetterTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> TestFramework.setterTest(BadSetterPojo.class));
    }

    @Test
    void inheritanceTest() {
        Assertions.assertDoesNotThrow(() -> TestFramework.getterTest(ChildPojo.class));
        Assertions.assertDoesNotThrow(() -> TestFramework.setterTest(ChildPojo.class));
    }

}