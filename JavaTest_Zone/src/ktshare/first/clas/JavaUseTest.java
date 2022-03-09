package ktshare.first.clas;

public class JavaUseTest {
    public static void main(String[] args) {
        System.out.println(BaseClasGrammar.class);
        /**
         *  注意
         *  想要把BaseClasGrammar.Companion.addStatic换成想要把BaseClasGrammar.addStatic
         *  需要了解@JvmStatic  同样调用静态属性则要了解@JvmField
         */
        System.out.println(BaseClasGrammar.Companion.addStatic(1,1));

        BaseClasGrammar baseClasGrammar = new BaseClasGrammar();
        System.out.println(baseClasGrammar.add(1,1));
    }
}
