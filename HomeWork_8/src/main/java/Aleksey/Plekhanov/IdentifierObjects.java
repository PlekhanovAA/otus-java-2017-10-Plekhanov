package Aleksey.Plekhanov;

import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class IdentifierObjects {

    static String defineObject (Object object) {
        Class clazz = object.getClass();
        if (getAllClassesPackage().contains(clazz)) {
            try {
                return Serializer.serializeObject(object);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (clazz.isArray()) {
            return  Serializer.serializeArray(object);
        }
        if (isSimpleType(clazz)) {
            return  Serializer.serializeSimpleType(object);
        }
        return "unsupported type";
    }

    static boolean isSimpleType(Class type) {
        return type.equals(Long.class)
                || type.equals(long.class)
                || type.equals(Integer.class)
                || type.equals(int.class)
                || type.equals(Short.class)
                || type.equals(short.class)
                || type.equals(Byte.class)
                || type.equals(byte.class)
                || type.equals(Float.class)
                || type.equals(float.class)
                || type.equals(Double.class)
                || type.equals(double.class)
                || type.equals(Character.class)
                || type.equals(char.class)
                || type.equals(Boolean.class)
                || type.equals(boolean.class)
                || type.equals(String.class)
                || type.equals(Object.class)
                || Collection.class.isAssignableFrom(type);
    }

    private static List getAllClassesPackage() {
        List<Class> allClasses = new ArrayList<>();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith("Aleksey.Plekhanov")) {
                    allClasses.add(info.load());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allClasses;
    }
} 