package anpopo.reflect_constructor.package_private.pp;

class SecondOne {

    private final ThirdOne thirdOne;
    private final ThirdTwo thirdTwo;

    public SecondOne(ThirdOne thirdOne, ThirdTwo thirdTwo) {
        this.thirdOne = thirdOne;
        this.thirdTwo = thirdTwo;
    }

    @Override
    public String toString() {
        return "\nSecondOne{" +
                "thirdOne=" + thirdOne +
                ", thirdTwo=" + thirdTwo +
                "}\n";
    }
}
