package juc.queue;

/**
 * @ Author:kn
 * @ Description:【 阻塞队列 】
 * ➤ BlockingQueue通常用于一个线程生产对象，而另外一个线程消费这些对象的场景。
 * ➤ 一个线程将会持续生产新对象并将其插入到队列之中，直到队列达到它所能容纳的临界
 *    点。也就是说，它是有限的。如果该阻塞队列达到了其临界点，负责生产的线程将会在
 *    往里边插入新对象时发生阻塞。它会一直处于阻塞之中，直到负责消费的线程从队列中
 *    拿走一个对象。
 * BlockingQueue也是一个处理接口，如果要想操作BlockingQueue也需要使用它的一系列子类。
 */
public class BlockingQueueDemo {
}
