package Aleksey.Plekhanov;

import java.lang.reflect.Field;
import java.util.*;

class Serializer {

    static String serialize (Object object) throws IllegalAccessException, InstantiationException {
        StringBuilder result  = new StringBuilder();
        result.append("{");
        Class clazz = object.getClass();
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            Class type = field.getType();
            if (SerializerHelper.isSimpleType(type)) {
                result.append(SerializerHelper.serializeSimpleType(field, object));
            }
            if (SerializerHelper.isArray(type)) {
                result.append(SerializerHelper.serializeArray(field, object));
            }
            if (i != fields.size() - 1) {
                result.append(",");
            }
        }
        result.append("}");
        return result.toString();
    }
}
