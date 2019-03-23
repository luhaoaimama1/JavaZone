package 泛型.继承链式测试.test2

/**
 * Created by fuzhipeng on 2019/3/5.
 *
 *  可以扩展但 只能有一个实现类可以有多个抽象类
 */
fun main(args: Array<String>) {
    Holder3().go3().go().go2().go3()
}
