package 编写高质量的代码;

import java.util.HashMap;
import java.util.Map;




public class Test1l {
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String,String>();
		final Runtime rt =Runtime.getRuntime();
		/**
		 * 查看对堆内存的方法
		 */
		rt.addShutdownHook(new Thread(){
			@Override
			public void run() {
				StringBuffer sb=new StringBuffer();
				long heapMaxSize=rt.maxMemory()>>20;
				sb.append("最大可用内存："+heapMaxSize+"M\n");
				long total=rt.totalMemory()>>20;
				sb.append("堆内存大小："+total+"M\n");
				long free=rt.freeMemory()>>20;
				sb.append("空闲内存大小："+free+"M");
				System.out.println(sb);
			}
		});
		for (int i = 0; i < 4000000; i++) {
			map.put("key"+i, "value"+i);
		}
		
	}
}
