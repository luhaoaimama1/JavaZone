package Thread与Executor的学习;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorType {
	public static void main(String[] args) {
		//FixedThreadPool  (1) 一样
		ExecutorService single=Executors.newSingleThreadExecutor();
		//固定的  然后 按顺序循环
		ExecutorService fixed=Executors.newFixedThreadPool(5);
		//通常会创建与所需数量相同的线程  当回收旧线程时停止创建新的线程   即可重复利用 
		ExecutorService cached=Executors.newCachedThreadPool();
		//此线程对时间排班比较有利
		ScheduledExecutorService scheduled=Executors.newScheduledThreadPool(10);
		//上面那几个都是应用的原理都是下面的　　此粒子为cached源码
		ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		//allowCoreThreadTimeOut属性为true 那么闲置的核心线程也会有超市时时间　超过时间也会被终止　
		//非核心则不管设不设置都会有超市时间
		threadPoolExecutor.allowCoreThreadTimeOut(true);
	}
}
