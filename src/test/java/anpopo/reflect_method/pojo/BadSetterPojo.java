package anpopo.reflect_method.pojo;

public class BadSetterPojo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

//    public int setAge(int age, String type) {
//        this.age = age;
//        return age;
//    }
}
