package 算法

//wait()方法的时候，线程会放弃对象锁
fun main(args: Array<String>) {

    val person = Person()


    for (i in 0..4) {
        Thread(Producer(person, "Producer$i")).start()
    }
    Thread.sleep(100)
    for (i in 0..4) {
        Thread(Consumer(person, "Consumer$i")).start()
    }
}

class Producer(val person: Person, val name: String) : Runnable {
    override fun run() {
        var count = 0
        while (++count < 5) {
            person.produce(name)
        }
    }
}


class Consumer(val person: Person, val name: String) : Runnable {
    override fun run() {
        while (true) {
            person.consume(name)
            Thread.sleep(100)
        }
    }
}


class Person {
    companion object {
        val lock = java.lang.Object()
        val PROJECT_NUM_MAX = 5
    }

    var projectNum = 0
    fun produce(name: String) {
        synchronized(lock) {
            while (projectNum >= PROJECT_NUM_MAX) {
                lock.wait()
            }
            projectNum++
            println("produce:$projectNum \t name:$name")
            lock.notifyAll()
        }
    }

    fun consume(name: String) {
        synchronized(lock) {
            while (projectNum <= 0) {
                lock.wait()
            }
            println("consume--------序列:$projectNum \t name:$name")
            projectNum--
            lock.notifyAll()
        }
    }
}