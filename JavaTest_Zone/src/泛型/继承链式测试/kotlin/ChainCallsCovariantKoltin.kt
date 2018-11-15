//package 泛型.继承链式测试.kotlin
//
///**
// * https://softwareengineering.stackexchange.com/questions/356782/multiple-layers-of-abstraction-and-chain-calls-of-methods-java-functional-like/356802
// */
//object ChainCallsCovariantKoltin {
//
//    internal open class Layer1<T : Layer1<T>> {
//        fun layer1Method(): T {
//            return this as T
//        }
//    }
//
//    internal open class Layer2<T : Layer2<T>> : Layer1<T>() {
//        fun layer2Method(): T {
//            return this as T
//        }
//
//    }
//
//    internal class Layer3<T : Layer3<T>> : Layer2<T>() {
//        fun layer3Method(): T {
//            return this as T
//        }
//
//        companion object {
//
//            fun < U : @JvmSuppressWildcards Layer3<U>> newLayer3Instance(): @JvmSuppressWildcards U {
//                return Layer3() as U
//            }
//        }
//
//    }
//
//    @JvmStatic
//    fun main(args: Array<String>) {
//        Layer3.newLayer3Instance<U>().layer1Method().layer2Method().layer3Method().layer2Method()
//    }
//}