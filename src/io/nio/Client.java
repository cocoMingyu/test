package io.nio;

import org.omg.CORBA.TIMEOUT;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void client() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel=null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8001));
            if (socketChannel.finishConnect()){
                int i=0;
                while (true){
                    TimeUnit.SECONDS.sleep(1);
                    String info="我是第"+i+++"个客户端";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    //buffer.hasRemaining()判断是否还有可用数据
                    while (buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (socketChannel!=null){
                socketChannel.close();
            }
        }
    }
}
