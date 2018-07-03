package gateway.common;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {

	public static void main(String[] args) {
		long t=System.currentTimeMillis();
		System.out.println(MessageUtils.getMessage("greeting"));
		System.out.println(System.currentTimeMillis()-t);
		
		long t1=System.currentTimeMillis();
		System.out.println(MessageUtils.getMessage("greeting"));
		System.out.println(System.currentTimeMillis()-t1);
		
		
		long t2=System.currentTimeMillis();
		System.out.println(MessageUtils.getMessage("userinfo"));
		System.out.println(System.currentTimeMillis()-t2);
		
		
		long t3=System.currentTimeMillis();
		System.out.println(MessageUtils.getMessage("userinfo"));
		System.out.println(System.currentTimeMillis()-t3);
		
		String name = "liky";
		int age = 18;
		Date birth = new Date();

		// 格式化参数,注意是一个Object的数组,这意味着可以将任何类型的对象来格式化模板.
		Object[] params = { name, age, birth };
		
		long t4=System.currentTimeMillis();
		System.out.println(MessageUtils.getMessage("userinfo",params));
		System.out.println(System.currentTimeMillis()-t4);
		
		long t5=System.currentTimeMillis();
		System.out.println(MessageUtils.getMessage("userinfo",params));
		System.out.println(System.currentTimeMillis()-t5);
	}
	
	public void test(){
		
	}
	
	public void testDefault() {
		// 获得缺省的系统区域
		Locale locale = Locale.getDefault();
		// 获得资源文件
		ResourceBundle rb = ResourceBundle.getBundle("i18n/operationLog/operation", locale);

		// 获得相应的key值
		String greeting = rb.getString("greeting");
		String userInfo = rb.getString("userinfo");

		String name = "liky";
		int age = 18;
		Date birth = new Date();

		// 格式化参数,注意是一个Object的数组,这意味着可以将任何类型的对象来格式化模板.
		Object[] params = { name, age, birth };

		// 格式化参数,返回格式后的字符串
		String result = MessageFormat.format(userInfo, params);

		System.err.println(greeting + result);
	}

	public void testCustom() {
		// 设置定制的语言国家代码
		Locale locale = new Locale("en_US");
		// 获得资源文件
		ResourceBundle rb = ResourceBundle.getBundle("i18n/operationLog/message", locale);
		// 获得相应的key值

		String greeting = rb.getString("greeting");
		String userInfo = rb.getString("userinfo");

		String name = "liky";
		int age = 18;
		Date birth = new Date();

		// 格式化参数,注意是一个Object的数组,这意味着可以将任何类型的对象来格式化模板.
		Object[] params = { name, age, birth };

		// 格式化参数,返回格式后的字符串
		String result = MessageFormat.format(userInfo, params);

		System.err.println(greeting + result);
	}
}
