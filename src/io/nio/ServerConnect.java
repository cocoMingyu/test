package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerConnect {
    private static final int BUF_SIZE=1024;
    private static final int PORT=8001;
    private static final int TIMEOUT=3000;

    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel sschannel = (ServerSocketChannel) key.channel();
        //监听新进来的连接
        SocketChannel sc = sschannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    public static void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        int read = sc.read(buf);
        while (read>0){
            buf.flip();
            while (buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
            read=sc.read(buf);
        }
        if (read==-1){
            sc.close();
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel channel = (SocketChannel) key.channel();
        while (buf.hasRemaining()){
            channel.write(buf);
        }
        buf.compact();
    }

    /**
     * 为了将Channel和Selector配合使用，必须将Channel注册到Selector上，通过SelectableChannel.register()方法来实现
     * 与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以。
     *
     * 注意register()方法的第二个参数。这是一个“interest集合”，意思是在通过Selector监听Channel时对什么事件感兴趣。可以监听四种不同类型的事件：
     * 1. Connect
     * 2. Accept
     * 3. Read
     * 4. Write
     * 通道触发了一个事件意思是该事件已经就绪。所以，某个channel成功连接到另一个服务器称为“连接就绪”。一个server socket channel准备好接收新进入的连接称为“接收就绪”。
     * 一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”。
     *
     * 这四种事件用SelectionKey的四个常量来表示：
     * 1. SelectionKey.OP_CONNECT
     * 2. SelectionKey.OP_ACCEPT
     * 3. SelectionKey.OP_READ
     * 4. SelectionKey.OP_WRITE
     */
    public static void selector(){
        Selector selector=null;
        ServerSocketChannel ssc=null;
        try {
            //创建selector
            selector = Selector.open();
            ssc=ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            //ServerSocketChannel设置为非阻塞模式，accept（）方法立即返回，如果没有新的连接进来返回null
            ssc.configureBlocking(false);
            ssc.register(selector,SelectionKey.OP_ACCEPT);
            while (true){
                if (selector.select(TIMEOUT)==0){
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()){
                        handleAccept(key);
                    }
                    if (key.isReadable()){
                        handleRead(key);
                    }
                    if (key.isWritable()&&key.isValid()){
                        handleWrite(key);
                    }
                    if (key.isConnectable()){
                        System.out.println("isConnectionable=true");
                    }
                    iter.remove();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (selector!=null)
                    selector.close();
                if (ssc!=null)
                    ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
