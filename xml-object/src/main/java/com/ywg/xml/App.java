package com.ywg.xml;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new App().test();
    }
    
    @SuppressWarnings("unused")
	public void test(){
    	Book book=(Book) XmlObjectUtil.toObject(Book.class, this.getClass().getResource("Book.xml").getFile());
    	System.out.println(1);
    }
}
