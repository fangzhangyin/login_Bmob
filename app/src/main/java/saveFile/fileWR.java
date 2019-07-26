package saveFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fileWR {
    public static String file1(String name,String path) throws IOException {
        Boolean flag=false;
        String newpath=null;
        Reader in = null;
        PrintWriter out=null;
        try {
            in = new InputStreamReader(new FileInputStream(path),"GBK");
             out= new PrintWriter(new FileWriter("/storage/emulated/0/PictureSelector."+name+".jpg"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //写入相应的文件

        //读取数据
        //循环取出数据
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = in.read()) != -1) {
            System.out.println(len);
            //写入相关文件
            out.write(len);
        }
        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
        newpath="/storage/emulated/0/PictureSelector."+name+".jpg";
        return newpath;
    }

    public static String  file2(String name,String path) throws IOException {
        //读取文件(字节流)
        InputStream in = null;
        OutputStream out =null;
        String newpath=null;
        try {
            in = new FileInputStream(path);
            out = new FileOutputStream("/storage/emulated/0/PictureSelector."+name+".jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //写入相应的文件

        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
//            String str = new String(bytes,0,n,"GBK");
//            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //关闭流
        in.close();
        newpath="/storage/emulated/0/PictureSelector."+name+".jpg";
        return newpath;
    }

    public static String  file3(String name,String path) throws IOException {
        //读取文件(字节流)
        InputStream in = null;
        OutputStream out =null;
        String newpath=null;
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        try {
            in = new FileInputStream(path);
            out = new FileOutputStream("/storage/emulated/0/PictureSelector."+name+dateFormat.toString()+".jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //写入相应的文件

        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
//            String str = new String(bytes,0,n,"GBK");
//            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //关闭流
        in.close();
        newpath="/storage/emulated/0/PictureSelector."+name+dateFormat+".jpg";
        return newpath;
    }

}
