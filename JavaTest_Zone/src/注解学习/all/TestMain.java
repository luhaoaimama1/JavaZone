package 注解学习.all;

import 注解学习.all.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by fuzhipeng on 2016/10/28.
 */
public class TestMain {

    public static void main(String[] args) {
        try {
            annotationTypeAnnotation();
            classAnnotation();
            constructorAnnotation();
            fieldAnnotation();
            methodAnnotation();
            parameterAnnotation();

            //这个特殊  需要创建 package-info.java
            // 然后在内里添加个包 然后在包上添加注解
            packageAnnotation();
            //暂时没什么用
//            但是目前的javac不会在bytecode中的local variable中保存annotation信息，
//            所以就无法在runtime时获取该annotaion。
//            也就是说ElementType.LOCAL_VARIABLE只能用在RetentionPolicy.SOURCE情况下。
            localVariableAnnnotaiton();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void packageAnnotation() {
        Package p = Package.getPackage("注解学习.all.annotation");
        //判断构造方法中是否有指定注解类型的注解
//　　      boolean hasAnnotation = constructor.isAnnotationPresent(TestA.class);
        if(p!=null && p.isAnnotationPresent(PackageBinds.class)){
            PackageBinds nav = p.getAnnotation(PackageBinds.class);
            if(nav !=null){
                System.out.println("name:"+"======================="+nav.value());
            }
        }
    }

    private static void localVariableAnnnotaiton() {

    }

    private static void annotationTypeAnnotation() {
        // @Target这个就是 此类型
        ClassBinds classBinds = Test.class.getAnnotation(ClassBinds.class);
        if (classBinds != null) {
            Annotation[] annotations = classBinds.annotationType().getAnnotations();
            Target target = classBinds.annotationType().getAnnotation(Target.class);
            if (target != null)
                System.out.print("ANNOTATION_TYPE---->targets:" );
            for (ElementType elementType : target.value()) {
                System.out.print("\t elementType:"+elementType);
            }
            System.out.println();
        }


    }

    private static void parameterAnnotation() throws NoSuchMethodException {
        Method method = Test.class.getMethod("use", int.class, String.class);
        //双维 因为 一个参数可以多个 注解；
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return;
        }
        for (int i = 0; i < parameterAnnotations.length; i++) {
            System.out.printf("第%s个参数注解-->", i);
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof ParameterBinds)
                    System.out.println("value:" + ((ParameterBinds) annotation).value());
            }
        }
    }

    private static void methodAnnotation() {
        Method[] methods = Test.class.getMethods();
        for (Method method : methods) {
            MethodBinds methodBinds = method.getAnnotation(MethodBinds.class);
            if (methodBinds != null)
                System.out.println("name:" + methodBinds.value());
        }
    }

    private static void fieldAnnotation() throws NoSuchFieldException {
        Field field = Test.class.getField("tag");
        FieldBinds fieldBinds = field.getAnnotation(FieldBinds.class);
        if (fieldBinds != null)
            System.out.println("name:" + fieldBinds.name());
    }

    //构造器注解 需要得到构造器 ,构造器getAnnotation
    private static void constructorAnnotation() throws NoSuchMethodException {
        Test test = new Test();
        Constructor<? extends Test> cons = test.getClass().getConstructor();
        ConstructorBinds constructorBinds = cons.getAnnotation(ConstructorBinds.class);
        if (constructorBinds != null)
            System.out.println("name:" + constructorBinds.name());
    }

    //class注解：  class.getAnnotation
    private static void classAnnotation() {
        ClassBinds classBinds = Test.class.getAnnotation(ClassBinds.class);
        if (classBinds != null)
            System.out.println("name:" + classBinds.value() + "\t name:" + classBinds.name());
    }

    @ClassBinds(value = {"类注解_Zone", "非主流_Zone"}, name = "kb_Zone")
    public static class Test {
        @FieldBinds(name = "field注解_Zone")
        public String tag = "test";

        @ConstructorBinds(name = "构造注解_Zone")
        public Test() {
        }

        @MethodBinds("方法注解_Zone")
        public void use(@ParameterBinds("par1_Zone") int par1, @ParameterBinds("par2_Zone") String par2) {
            @LocalVariableBinds
            String infoInner = "sa";
        }
    }
}
