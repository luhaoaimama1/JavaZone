package rxjava

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import 多线程学习.valatitle.同步常用类.ArrayBlockedQueueTest.TAG


object RxjavaExample {

    @JvmStatic
    fun main(args: Array<String>) {
        val emitter = Observable.create(ObservableOnSubscribe<String> {
            it.onNext("1")
            it.onNext("2")
            it.onNext("3")
//            it.onComplete()
            it.onError(IllegalStateException("state error"))
        }).doFinally {
            println("doFinally")
        }.doOnTerminate {
            println("doOnTerminate")
        }.subscribe({
            println("subscribe ${it}")
        }, {
            println("subscribe onError")
        }, {
            println("subscribe onComplete")
        })


//        Observable.fromArray(1, 2, 3).subscribe {
//            println(it)
//        }
//
//        val emitter = Observable.create(ObservableOnSubscribe<String> {
//            it.onNext("1")
//            it.onNext("2")
//            it.onNext("3")
////            it.onComplete()
//            it.onError(IllegalStateException("state error"))
//        })
//
//        val subscribe = emitter.subscribe({
//            println(it)
//        }, {
//            println(it.message)
//        })
//
//        val upstream = Flowable.create<Int>({ emitter ->
//            emitter.onNext(1)
//            emitter.onNext(2)
//            emitter.onNext(3)
//            emitter.onComplete()
//        }, BackpressureStrategy.ERROR)
//


        // 创建被观察者Flowable
//        backpressureSample()
//        flowable()
//        while (true) {
//        }
    }

    fun backpressureSample() {
        Observable.create<Int> { e ->
            var i = 0
            while (true) {
                Thread.sleep(5)
                i++
                e.onNext(i)
                println("每50ms发送一次数据：$i")
            }
        }.subscribeOn(Schedulers.newThread()) //使被观察者存在独立的线程执行
                .observeOn(Schedulers.newThread()) //使观察者存在独立的线程执行
                .subscribe {
                    Thread.sleep(5000)
                    println("每5000m接收一次数据：$it")
                }
    }

    fun flowable() {
        Flowable.create<Int>({ e ->
            for (j in 0..200) {
                e.onNext(j)
                println(" 发送数据：$j")
                try {
                    Thread.sleep(5)
                } catch (ex: Exception) {
                }
            }
            println(" over!")
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(object : Subscriber<Int> {
                    override fun onSubscribe(s: Subscription) {
                        s.request(Long.MAX_VALUE) //观察者设置接收事件的数量,如果不设置接收不到事件
                        //可以在click里动态设置 表示可以发送多少个
//                        s.request(3) //表示这个发布者会发给订阅者的个数
                    }

                    override fun onNext(integer: Int) {
                        try {
                            Thread.sleep(50)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        println("onNext : $integer")
                    }

                    override fun onError(t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onComplete() {
                        println("onComplete")
                    }
                })
    }
}