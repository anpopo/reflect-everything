package anpopo.reflect_constructor.package_private.main;

import anpopo.reflect_constructor.package_private.pp.DependencyInjectionGateway;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        DependencyInjectionGateway dependencyInjectionGateway = getInstanceRecursively(DependencyInjectionGateway.class);
        System.out.println("dependencyInjectionGateway = " + dependencyInjectionGateway);
    }

    private static <T> T getInstanceRecursively(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = getFirstConstructor(clazz);

        List<Object> constructorArguments = new ArrayList<>();

        for (Class<?> argumentType : constructor.getParameterTypes()) {
            Object instanceRecursively = getInstanceRecursively(argumentType);
            constructorArguments.add(instanceRecursively);
        }
        constructor.setAccessible(true);
        return (T) constructor.newInstance(constructorArguments.toArray());
    }

    private static <T> Constructor<?> getFirstConstructor(Class<T> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        return constructors[0];
    }
}
