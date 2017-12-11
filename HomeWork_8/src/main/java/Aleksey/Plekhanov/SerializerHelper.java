package Aleksey.Plekhanov;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class SerializerHelper {

    static String serializeSimpleType(Field field, Object object) throws IllegalAccessException {
        String type = field.getType().getSimpleName();
        if (type.equals("String")) {
            return "\"" +
                    field.getName() +
                    "\"" +
                    ":" +
                    "\"" +
                    field.get(object) +
                    "\"";
        }else {
            return "\"" +
                    field.getName() +
                    "\"" +
                    ":" +
                    field.get(object);
        }
    }

    static String serializeArray (Field field, Object object) throws IllegalAccessException, InstantiationException {
        StringBuilder array = new StringBuilder();
        int arraySize = Array.getLength(field.get(object));
        Class componentTypeArray = field.get(object).getClass().getComponentType();
        String typeArray = field.get(object).getClass().getSimpleName();
        if (typeArray.equals("String[]")) {
            for (int i = 0; i < arraySize; i++) {
                array.append("\"");
                array.append(Array.get(field.get(object), i));
                array.append("\"");
                if (i != arraySize - 1) {
                    array.append(",");
                }
            }
        } else if (isSimpleType(componentTypeArray)) {
            for (int i = 0; i < arraySize; i++) {
                array.append(Array.get(field.get(object), i));
                if (i != arraySize - 1) {
                    array.append(",");
                }
            }
        } else {
            for (int i = 0; i < arraySize; i++) {
                Object obj = componentTypeArray.newInstance();
                //Object obj = Array.get(field.get(object), i).getClass().newInstance();
                array.append(Serializer.serialize(obj));
                if (i != arraySize - 1) {
                    array.append(",");
                }
            }
        }
        return "\"" +
                field.getName() +
                "\"" +
                ":" +
                "[" +
                array.toString() +
                "]";
    }

    static boolean isSimpleType(Class type) {
        return type.equals(Long.class)
                || type.equals(Integer.class)
                || type.equals(int.class)
                || type.equals(Short.class)
                || type.equals(Byte.class)
                || type.equals(Float.class)
                || type.equals(Double.class)
                || type.equals(Character.class)
                || type.equals(Boolean.class)
                || type.equals(String.class)
                || type.equals(Object.class)
                || Collection.class.isAssignableFrom(type);
    }

    static boolean isArray(Class type) {
        return type.isArray();
    }
}
