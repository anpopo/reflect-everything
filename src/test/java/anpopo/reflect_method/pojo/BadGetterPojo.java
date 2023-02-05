package anpopo.reflect_method.pojo;

public class BadGetterPojo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public long getAge(String dd) {
//        return age;
//    }

    public void setAge(int age) {
        this.age = age;
    }
}
