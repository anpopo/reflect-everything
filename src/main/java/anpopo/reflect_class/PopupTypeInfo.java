package anpopo.reflect_class;

import java.util.*;
import java.util.stream.Collectors;

public class PopupTypeInfo {
    private final boolean isPrimitive;
    private final boolean isInterface;
    private final boolean isEnum;
    
    private final String name;
    private final boolean isJdk;
    
    private final List<String> inheritedClassNames;
    private final String packageName;

    public PopupTypeInfo(boolean isPrimitive, boolean isInterface, boolean isEnum, String name,
                         boolean isJdk, List<String> inheritedClassNames, String packageName) {
        this.isPrimitive = isPrimitive;
        this.isInterface = isInterface;
        this.isEnum = isEnum;
        this.name = name;
        this.isJdk = isJdk;
        this.inheritedClassNames = Collections.unmodifiableList(inheritedClassNames);
        this.packageName = packageName;
    }

    public static PopupTypeInfoBuilder builder() {
        return new PopupTypeInfoBuilder();
    }

    public static class PopupTypeInfoBuilder {
        private boolean isPrimitive;
        private boolean isInterface;
        private boolean isEnum;
        private String name;
        private boolean isJdk;
        private final List<String> inheritedClassNames = new ArrayList<>();

        private String packageName;

        public PopupTypeInfoBuilder primitive(boolean isPrimitive) {
            this.isPrimitive = isPrimitive;
            return this;
        }

        public PopupTypeInfoBuilder setInterface(boolean isInterface) {
            this.isInterface = isInterface;
            return this;
        }

        public PopupTypeInfoBuilder setEnum(boolean isEnum) {
            this.isEnum = isEnum;
            return this;
        }

        public PopupTypeInfoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PopupTypeInfoBuilder jdk(boolean isJdkType) {
            this.isJdk = isJdkType;
            return this;
        }

        public PopupTypeInfoBuilder inheritedClassNames(String[] inheritedClassNames) {
            if (inheritedClassNames != null) {
                this.inheritedClassNames.addAll(Arrays.stream(inheritedClassNames).toList());
            }
            return this;
        }

        public PopupTypeInfoBuilder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public PopupTypeInfo build() {
            return new PopupTypeInfo(
                    this.isPrimitive,
                    this.isInterface,
                    this.isEnum,
                    this.name,
                    this.isJdk,
                    this.inheritedClassNames,
                    this.packageName
            );
        }

    }

    @Override
    public String toString() {
        return "PopupTypeInfo{" +
                "isPrimitive=" + isPrimitive +
                ", isInterface=" + isInterface +
                ", isEnum=" + isEnum +
                ", name='" + name + '\'' +
                ", isJdk=" + isJdk +
                ", inheritedClassNames=" + inheritedClassNames +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}