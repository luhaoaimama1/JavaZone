package gson学习与反射.反射;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class ZoneBase{
    public ZoneBase() {
    }
}

class Zone2 extends ZoneBase{
    public Zone2() {
    }
}

public class RelfectClass {
    public static <T extends ZoneBase> T getInstance(Class<T> cls){
        try {
            Constructor<T> constructor = cls.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Zone2 instance = getInstance(Zone2.class);
        ZoneBase instance2 = getInstance(ZoneBase.class);
        System.out.println();
    }
}

