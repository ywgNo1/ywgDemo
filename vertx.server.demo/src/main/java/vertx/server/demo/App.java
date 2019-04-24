package vertx.server.demo;

import java.io.UnsupportedEncodingException;

import io.vertx.core.buffer.Buffer;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     *
     * @time 2018年6月20日  下午3:12:46
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
    	String aString="sdfs测试";
        Buffer buffer=Buffer.buffer(aString.getBytes());
        Buffer buffer1=Buffer.buffer("{\"get\":\"test\"}");
        buffer.appendString("sdf");
        //System.out.println(buffer.toJsonArray());
        //System.out.println(buffer.toJsonObject());
        System.out.println(buffer.toString());
        System.out.println(new String(buffer.getBytes(), "UTF-8"));
        
        System.out.println(buffer1.toString());
        System.out.println(buffer1.toJsonObject());
    }
}
