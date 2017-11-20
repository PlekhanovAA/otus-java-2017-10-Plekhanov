package Aleksey.Plekhanov;
import Aleksey.Plekhanov.annotations.After;
import Aleksey.Plekhanov.annotations.Before;
import Aleksey.Plekhanov.annotations.Test;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Testing {

    static void start (Class clazz){
        System.out.println("____________________ LAUNCH ____________________");
        Map<Method, String> beforeMethods = new HashMap<>();
        Map<Method, String> testMethods = new HashMap<>();
        Map<Method, String> afterMethods = new HashMap<>();
        Set<Method> methods = ReflectionHelper.getMethods(clazz);
        for (Method method : methods) {
            Annotation [] annotations = ReflectionHelper.getAnnotations(method);
            for (Annotation annotation : annotations) {
                if(annotation instanceof Before) {
                    beforeMethods.put(method, ((Before) annotation).resultTest());
                }
                if(annotation instanceof Test) {
                    testMethods.put(method, ((Test) annotation).resultTest());
                }
                if(annotation instanceof After) {
                    afterMethods.put(method, ((After) annotation).resultTest());
                }
            }
        }
        currentTestRun (beforeMethods);
        currentTestRun (testMethods);
        currentTestRun (afterMethods);
        System.out.println("____________________ FINISH ____________________");
    }

    private static void currentTestRun (Map<Method, String> methods) {
        Object object = ReflectionHelper.instantiate(TestClass.class);
        for (Method nameMethod : methods.keySet()) {
            String resultMethod = (String) ReflectionHelper.callMethod(object, nameMethod.getName());
            String resultTest = methods.get(nameMethod);
            if (resultMethod != null && resultMethod.equals(resultTest)) {
                System.out.println("-- " + nameMethod.getDeclaringClass() + "." + nameMethod.getName() + " --" + " method OK");
            } else {
                System.out.println("-- " + nameMethod.getName() + " --" + " method FAILED");
            }
        }
    }

    static void start(String packageName) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith(packageName))
                    start(info.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}