package Aleksey.Plekhanov;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class Serializer {

    static String serialize (Object object) {
        Class clazz = object.getClass();
        if (isSimpleType(clazz)) {
            return  Serializer.serializeSimpleType(object);
        }
        if (clazz.isArray()) {
            return  Serializer.serializeArray(object);
        }
        try {
            return Serializer.serializeObject(object);
        }catch (IllegalAccessException | InstantiationException e1) {
            e1.printStackTrace();
        }
        return "unsupported type";
    }

    private static boolean isSimpleType(Class type) {
        return     type.equals(Long.class)
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

    private static String serializeObject(Object object) throws IllegalAccessException, InstantiationException {
        StringBuilder result  = new StringBuilder();
        result.append("{");
        Class clazz = object.getClass();
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            Class type = field.getType();
            if (isSimpleType(type)) {
                result.append(serializeFieldSimpleType(field, object));
            }
            if (type.isArray()) {
                result.append(serializeFieldArrayType(field, object));
            }
            if(type.isMemberClass()) {
                result.append(serializeFieldMemberClass (field, object));
            }
            if (i != fields.size() - 1) {
                result.append(",");
            }
        }
        result.append("}");
        return result.toString();
    }

    private static String serializeFieldSimpleType(Field field, Object object) throws IllegalAccessException {
        String type = field.getType().getSimpleName();
        if (type.equals("String")) {
            return "\"" +
                    field.getName() +
                    "\"" +
                    ":" +
                    "\"" +
                    field.get(object).toString().replace("\"", "\\" + "\"") +
                    "\"";
        }else {
            return "\"" +
                    field.getName() +
                    "\"" +
                    ":" +
                    field.get(object);
        }
    }

    private static String serializeFieldArrayType(Field field, Object object) throws IllegalAccessException, InstantiationException {
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
                array.append(Serializer.serializeObject(obj));
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

    private static String serializeFieldMemberClass(Field field, Object object) throws IllegalAccessException, InstantiationException {
        StringBuilder result  = new StringBuilder();
        result.append("\"").append(field.getName()).append("\"").append(":");
        result.append("{");
        Class clazz = field.get(object).getClass();
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));

        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            f.setAccessible(true);
            Class type = f.getType();
            if (isSimpleType(type)) {
                result.append(serializeFieldSimpleType(f, field.get(object)));
            }
            if (type.isArray()) {
                result.append(serializeFieldArrayType(f, object));
            }
            if(type.isMemberClass()) {
                result.append(serializeFieldMemberClass (f, object));
            }
            if (i != fields.size() - 1) {
                result.append(",");
            }
        }
        result.append("}");
        return result.toString();
    }

    private static String serializeArray (Object object) {
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

    private static String serializeSimpleType (Object object) {
        if (object.getClass().getSimpleName().equals("String")) {
            return "\"" +
                    object.toString().replace("\"", "\\" + "\"") +
                    "\"";
        }
        return object.toString();
    }
} 