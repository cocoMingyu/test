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
