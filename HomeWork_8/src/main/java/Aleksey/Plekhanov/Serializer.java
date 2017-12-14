package Aleksey.Plekhanov;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

class Serializer {

    static String serializeObject(Object object) throws IllegalAccessException, InstantiationException {
        StringBuilder result  = new StringBuilder();
        result.append("{");
        Class clazz = object.getClass();
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            Class type = field.getType();
            if (SerializerObjects.isSimpleType(type)) {
                result.append(SerializerObjects.serializeFielsSimpleType(field, object));
            }
            if (SerializerObjects.isArray(type)) {
                result.append(SerializerObjects.serializeFieldArrayType(field, object));
            }
            if (i != fields.size() - 1) {
                result.append(",");
            }
        }
        result.append("}");
        return result.toString();
    }

    static String serializeArray (Object object) {
        StringBuilder result  = new StringBuilder();
        String typeArray = object.getClass().getSimpleName();
        int arraySize = Array.getLength(object);
        result.append("[");
        if (typeArray.equals("String[]")) {
            for (int i = 0; i < arraySize; i++) {
                result.append("\"");
                result.append(Array.get(object, i));
                result.append("\"");
                if (i != arraySize - 1) {
                    result.append(",");
                }
            }
        } else {
            for (int i = 0; i < arraySize; i++) {
                result.append(Array.get(object, i));
                if (i != arraySize - 1) {
                    result.append(",");
                }
            }
        }
        result.append("]");
        return  result.toString();
    }
}
