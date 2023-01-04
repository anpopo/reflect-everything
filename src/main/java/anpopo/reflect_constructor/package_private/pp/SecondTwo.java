package anpopo.reflect_constructor.package_private.pp;

class SecondTwo {
    private final ThirdTwo thirdTwo;

    public SecondTwo(ThirdTwo thirdTwo) {
        this.thirdTwo = thirdTwo;
    }

    @Override
    public String toString() {
        return "\nSecondTwo{" +
                "thirdTwo=" + thirdTwo +
                "}\n";

    }
}
