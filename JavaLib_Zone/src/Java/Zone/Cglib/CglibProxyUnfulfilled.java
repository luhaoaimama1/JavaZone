package Java.Zone.Cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 *  Aop cglib 动态代理   开始结束方法未实现
 * @author zone
 *
 */
public abstract class CglibProxyUnfulfilled implements MethodInterceptor {

	/**
	 * 要创建的对象的父类对象；
	 */
	private Object target;

	/**
	 * 创建代理对象
	 * 
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target) {
		this.target = target;
		// 设置需要创建子类的类
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		// 回调方法
		enhancer.setCallback(this);
		// 创建代理对象 通过字节码技术动态创建子类实例
		return enhancer.create();
	}

	// 拦截父类所有方法的调用 可以对返回的结果进行修改！！！
	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		/**
		 * 代码在这里改
		 */
		Object result = null;
		methodBefore(proxy,args);
//		System.out.println("事物开始");
		// 通过代理类调用父类中的方法
		result = methodProxy.invokeSuper(proxy, args);
		methodAfter(result);
//		System.out.println("事物结束");
//		// 对结果进行了更改 这个屌！！！！
//		result = "2";
		return result;
	}
	/**
	 * 当然也可以打印咯
	 * @param proxy	父类的实体  可以修改一些东西
	 * @param args	方法里的参数  执行之前 可以修改
	 */
	public abstract  void methodBefore(Object proxy,Object[] args);
	/**
	 * 当然也可以打印咯
	 * @param result 可以对返回的结果进行处理 
	 */
	public abstract  void methodAfter(Object result);

}