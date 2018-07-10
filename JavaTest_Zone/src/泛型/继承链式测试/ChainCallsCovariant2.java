package 泛型.继承链式测试;

/**
 * https://softwareengineering.stackexchange.com/questions/356782/multiple-layers-of-abstraction-and-chain-calls-of-methods-java-functional-like/356802
 */
public class ChainCallsCovariant2 {


    static class Layer2<T extends Layer2<T>> {
        public T layer2Method() {
            return (T) this;
        }

    }

    static class Layer3<T extends Layer3<T>> extends Layer2<T> {
        public T layer3Method() {
            return (T) this;
        }

        public static final <U extends Layer3<U>> U newLayer3Instance() {
            return (U) new Layer3();
        }

    }

    public static void main(String[] args) {
        Layer3.newLayer3Instance().layer2Method().layer3Method().layer2Method();
    }
}