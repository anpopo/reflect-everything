package anpopo.reflect_constructor.package_private.pp;

class First {

    private final SecondOne secondOne;
    private final SecondTwo secondTwo;

    public First(SecondOne secondOne, SecondTwo secondTwo) {
        this.secondOne = secondOne;
        this.secondTwo = secondTwo;
    }

    @Override
    public String toString() {
        return "\nFirst{" +
                "secondOne=" + secondOne +
                ", secondTwo=" + secondTwo +
                "}\n";
    }
}
