package io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;

/**
 * NIO主要有三大核心部分：Channel(通道)，Buffer(缓冲区), Selector。传统IO基于字节流和字符流进行操作，而NIO基于Channel和Buffer(缓冲区)进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。
 * Selector(选择区)用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个线程可以监听多个数据通道。
 *
 * NIO和传统IO（一下简称IO）之间第一个最大的区别是，IO是面向流的，NIO是面向缓冲区的。 Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。此外，
 * 它不能前后移动流中的数据。如果需要前后移动从流中读取的数据，需要先将它缓存到一个缓冲区。NIO的缓冲导向方法略有不同。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动。
 * 这就增加了处理过程中的灵活性。但是，还需要检查是否该缓冲区中包含所有您需要处理的数据。而且，需确保当更多的数据读入缓冲区时，不要覆盖缓冲区里尚未处理的数据。
 *
 * IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了。 NIO的非阻塞模式，
 * 使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。而不是保持线程阻塞，所以直至数据变得可以读取之前，该线程可以继续做其他的事情。
 * 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）。
 * Channel
 * 首先说一下Channel，国内大多翻译成“通道”。Channel和IO中的Stream(流)是差不多一个等级的。只不过Stream是单向的，譬如：InputStream, OutputStream.而Channel是双向的，既可以用来进行读操作，又可以用来进行写操作。
 * NIO中的Channel的主要实现有：
 * FileChannel
 * DatagramChannel
 * SocketChannel
 * ServerSocketChannel
 * 这里看名字就可以猜出个所以然来：分别可以对应文件IO、UDP和TCP（Server和Client）。下面演示的案例基本上就是围绕这4个类型的Channel进行陈述的。
 *
 * Buffer
 * NIO中的关键Buffer实现有：ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer, ShortBuffer，分别对应基本数据类型: byte, char, double, float, int, long, short。
 * 当然NIO中还有MappedByteBuffer, HeapByteBuffer, DirectByteBuffer等这里先不进行陈述。
 *
 * 控制buffer状态的三个变量
 * position：跟踪已经写了多少数据或读了多少数据，它指向的是下一个字节来自哪个位置
 * limit：代表还有多少数据可以取出或还有多少空间可以写入，它的值小于等于capacity。
 * capacity：代表缓冲区的最大容量，一般新建一个缓冲区的时候，limit的值和capacity的值默认是相等的。
 * flip、clear这两个方法便是用来设置这些值的。
 *
 * Selector
 * Selector运行单线程处理多个Channel，如果你的应用打开了多个通道，但每个连接的流量都很低，使用Selector就会很方便。例如在一个聊天服务器中。要使用Selector, 得向Selector注册Channel，
 * 然后调用它的select()方法。这个方法会一直阻塞到某个注册的通道有事件就绪。一旦这个方法返回，线程就可以处理这些事件，事件的例子有如新的连接进来、数据接收等。
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        method2();
    }
    public static void method1() throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:/io.txt","rw");
        //获取通道
        FileChannel channel = file.getChannel();
        //初始化buffer
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //通道数据读到buffer缓冲区
        int read = channel.read(buf);
        while (read!=-1){
            //flip()使position=0，limit=之前position所在的位置，这样读取的数据为写入buffer的数据长度
            buf.flip();
            while (buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            //buf.clear()清空缓存区，compact() 将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面
            buf.compact();
            read=channel.read(buf);
        }
        file.close();
    }

    public static void method2() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("D:/io.txt"));
        //append决定是否在原文件基础上追加，true追加，false-否
        FileOutputStream outputStream = new FileOutputStream(new File("D:/io_copy.txt"),true);

        FileChannel inchannel = inputStream.getChannel();
        FileChannel outchannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int read = inchannel.read(buffer);
        while (read!=-1) {
            //重设一下buffer的position=0，limit=position
            buffer.flip();
            //写入
            outchannel.write(buffer);
            buffer.clear();
            read=inchannel.read(buffer);
        }

        inchannel.close();
        outchannel.close();
        inputStream.close();
        outputStream.close();

    }
}
