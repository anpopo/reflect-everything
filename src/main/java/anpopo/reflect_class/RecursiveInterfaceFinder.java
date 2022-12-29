package anpopo.reflect_class;

import java.util.HashSet;
import java.util.Set;

public class RecursiveInterfaceFinder {

    public static Set<Class<?>> findAllImplementedInterface (Class<?> inputClass) {

        Set<Class<?>> implementedInterface = new HashSet<>();
        Class<?>[] interfaces = inputClass.getInterfaces();

        for (Class<?> inter : interfaces) {
            implementedInterface.add(inter);

            implementedInterface.addAll(findAllImplementedInterface(inter));
        }

        return implementedInterface;
    }
}
