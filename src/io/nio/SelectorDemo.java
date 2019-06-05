package io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 */
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        test1();
    }

    /**
     * register()方法的第二个参数。这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：
     * Connect  连接就绪    SelectionKey.OP_CONNECT
     * Accept   接受就绪    SelectionKey.OP_ACCEPT
     * Read     读就绪      SelectionKey.OP_READ
     * Write    写就绪      SelectionKey.OP_WRITE
     *
     * 向Selector注册Channel时，register()方法会返回一个SelectionKey对象。这个对象包含了一些你感兴趣的属性：
     * interest集合
     * ready集合
     * Channel
     * Selector
     */
    public static void test1() throws IOException {
        //通过调用Selector.open()方法创建一个Selector
        Selector selector=Selector.open();
        ServerSocketChannel channel=ServerSocketChannel.open();
        //为了将Channel和Selector配合使用，必须将channel注册到selector上。通过SelectableChannel.register()方法来实现
        //与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
        channel.configureBlocking(false);
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
        //interest集合是你所选择的感兴趣的事件集合
        int i = selectionKey.interestOps();
        //ready 集合是通道已经准备就绪的操作的集合
        int i1 = selectionKey.readyOps();

        //通过selectionkey访问channel，selector
        SelectableChannel channel1 = selectionKey.channel();
        Selector selector1 = selectionKey.selector();

        //可以将一个对象或者更多信息附着到SelectionKey上，这样就能方便的识别某个给定的通道。例如，可以附加 与通道一起使用的Buffer，或是包含聚集数据的某个对象。使用方法如下：
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        selectionKey.attach(byteBuffer);
        Object attachedObj = selectionKey.attachment();

        //还可以在用register()方法向Selector注册Channel的时候附加对象。如：
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ, byteBuffer);

        //select()阻塞到至少有一个通道在你注册的事件上就绪了。返回有多少通道已经就绪
        //select(long timeout)和select()一样，除了最长会阻塞timeout毫秒(参数)。
        //selectNow()不会阻塞，不管什么通道就绪都立刻返回（译者注：此方法执行非阻塞的选择操作。如果自从前一次选择操作后，没有通道变成可选择的，则此方法直接返回零。）。
        int x = selector.select();

        //一旦调用了select()方法，并且返回值表明有一个或更多个通道就绪了，然后可以通过调用selector的selectedKeys()方法，访问“已选择键集（selected key set）”中的就绪通道
        Set<SelectionKey> keys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = keys.iterator();
        //遍历已选择键集中的每个键，并检测各个键所对应的通道的就绪事件
        while (iterator.hasNext()){
            SelectionKey next = iterator.next();
            if (next.isConnectable()){

            }else if (next.isAcceptable()){

            }
            //Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。下次该通道变成就绪时，Selector会再次将其放入已选择键集中
            iterator.remove();
        }

        //某个线程调用select()方法后阻塞了，即使没有通道已经就绪，也有办法让其从select()方法返回。只要让其它线程在第一个线程调用select()方法的那个对象上调用Selector.wakeup()方法即可。阻塞在select()方法上的线程会立马返回。
        //如果有其它线程调用了wakeup()方法，但当前没有线程阻塞在select()方法上，下个调用select()方法的线程会立即“醒来（wake up）”
        selector.wakeup();

        //用完Selector后调用其close()方法会关闭该Selector，且使注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭
        selector.close();
    }
}
