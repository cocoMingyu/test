package io.io;

import java.io.*;

public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        read();
    }

    public static void method1() throws IOException {
        InputStream in=null;
        try {
            in=new BufferedInputStream(new FileInputStream("D:/io.txt"));
            byte[] buf = new byte[1024];
            int byteread = in.read(buf);
            while(byteread!=-1){
                for (int i = 0; i < byteread; i++) {
                    System.out.print((char) buf[i]);
                }
                byteread=in.read(buf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in!=null){
                in.close();
            }
        }
    }

    public static void method2() throws IOException {
        FileInputStream in = new FileInputStream("D:/io.txt");
        FileOutputStream out = new FileOutputStream("D:/io_copy.txt");
        int b;
        while ((b = in.read()) != -1){
            out.write(b);
        }

        out.flush();
        in.close();
        out.close();
    }

    public static void read() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:/io.txt"));
        String s;
        StringBuffer stringBuffer = new StringBuffer();
        while ((s=bufferedReader.readLine())!=null){
            stringBuffer.append(s);
        }
        System.out.println(stringBuffer.toString());
    }


}
