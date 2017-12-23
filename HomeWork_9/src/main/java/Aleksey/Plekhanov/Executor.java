package Aleksey.Plekhanov;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class Executor {

    private Statement statement;

    Executor(Statement statement) {
        this.statement = statement;
    }

    <T extends DataSet> void save(T userDataSetTo) {
        try {
        String query = insertQuery(userDataSetTo);
            statement.execute(query);
        }catch (SQLException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    <T extends DataSet> T load(long id, Class<T> clazz){
        T result = null;
        String name = "";
        Integer age = 0;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String query = loadQuery(id);
        try {
            ResultSet resultSet;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                name = resultSet.getString("NAME");
                age = resultSet.getInt("AGE");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            Field fieldId = clazz.getSuperclass().getDeclaredField("id");
            fieldId.setAccessible(true);
            fieldId.set(result, id);

            Field fieldName = clazz.getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(result, name);

            Field fieldAge = clazz.getDeclaredField("age");
            fieldAge.setAccessible(true);
            fieldAge.set(result, age);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String insertQuery (DataSet dataSet) throws IllegalAccessException{
        String name = "";
        Integer age = 0;
        List<Field> fields = new ArrayList<>(Arrays.asList(dataSet.getClass().getDeclaredFields()));
        for (int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            f.setAccessible(true);
            if (f.getName().equals("name")) {
                name = (String)f.get(dataSet);
            }
            if (f.getName().equals("age")) {
                age = (Integer)f.get(dataSet);
            }
        }

        StringBuilder query = new StringBuilder();
        query.append("insert into TEST (NAME, AGE) ");
        query.append("values ('" + name + "' , " + age +");");
        return query.toString();
    }

    private String loadQuery(long id) {
        StringBuilder query = new StringBuilder();
        query.append("select * from TEST where id = " + id);
        return query.toString();
    }
}
