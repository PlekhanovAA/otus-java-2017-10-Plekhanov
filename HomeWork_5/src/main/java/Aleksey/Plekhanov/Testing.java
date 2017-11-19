package Aleksey.Plekhanov;
import Aleksey.Plekhanov.annotations.Before;

import java.lang.reflect.Method;
import java.util.Set;

class Testing {

    static void run () {
        System.out.println("____________________ LAUNCH ____________________");
        Object object = ReflectionHelper.instantiate(TestClass.class);
        Set<Method> beforeMethods = ReflectionHelper.getMethods(object, Before.class);
        for (Method beforeMethod : beforeMethods) {
            try {
            ReflectionHelper.callMethod(object, beforeMethod.getName());
            System.out.println("-- " + beforeMethod.getName() + " --" + " method OK");
            }
            catch (Exception e) {
                System.out.println("-- " + beforeMethod.getName() + " --" + " method FAILED");
                e.printStackTrace();
            }
        }
        System.out.println("____________________ FINISH ____________________");
    }
}
