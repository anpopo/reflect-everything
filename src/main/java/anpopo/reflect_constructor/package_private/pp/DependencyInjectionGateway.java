package anpopo.reflect_constructor.package_private.pp;

public class DependencyInjectionGateway {
    private final First first;

    public DependencyInjectionGateway(First first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "DependencyInjectionGateway{" +
                "first=" + first +
                '}';
    }
}
