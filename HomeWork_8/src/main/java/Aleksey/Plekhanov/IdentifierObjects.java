package Aleksey.Plekhanov;

import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IdentifierObjects {

    static String defineObject (Object object) {
        if (getAllClassesPackage().contains(object.getClass())) {
            try {
                return Serializer.serializeObject(object);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (object.getClass().isArray()) {
            return  Serializer.serializeArray(object);
        }
        return "unsupported type";
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
