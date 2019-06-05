package io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 在Java NIO中，如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel（译者注：channel中文常译作通道）传输到另外一个channel。
 */
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        transferTo();
    }
    /**
     *FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
     * 方法的输入参数position表示从position处开始向目标文件写入数据，count表示最多传输的字节数。如果源通道的剩余空间小于 count 个字节，则所传输的字节数要小于请求的字节数。
     * 此外要注意，在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中
     */
    public static void transferFrom() throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:/io.txt", "rw");
        FileChannel fromchannel = file.getChannel();

        RandomAccessFile file2 = new RandomAccessFile("D:/io_copy.txt", "rw");
        FileChannel tochannel = file2.getChannel();

        long position=0;
        long size = fromchannel.size();
        long l = tochannel.transferFrom(fromchannel, position, size);
        System.out.println(l);
    }

    public static void transferTo() throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:/io.txt", "rw");
        FileChannel fromchannel = file.getChannel();

        RandomAccessFile file2 = new RandomAccessFile("D:/io_copy.txt", "rw");
        FileChannel tochannel = file2.getChannel();

        long position=0;
        long size = fromchannel.size();
        long l = fromchannel.transferTo(position, size, tochannel);
        System.out.println(l);
    }
}
