package gateway.common;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class I18nUtils {
	private static final String i18n = "i18n/i18nRources.properties";
	private static final String resources = "resources";
	private final Map<String, Map<Locale, ResourceBundle>> bundleMap=
			new HashMap<String, Map<Locale, ResourceBundle>>();
	private final Map<ResourceBundle, Map<String, Map<Locale, MessageFormat>>> formatMap =
			new HashMap<ResourceBundle, Map<String, Map<Locale, MessageFormat>>>();
	private static String[] baseResources;

	private static I18nUtils utils;

	private I18nUtils() {
		initbaseResources();
	}

	public static I18nUtils getInstance() {
		if (utils == null) {
			utils = new I18nUtils();
		}
		return utils;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new I18nUtils().testDefault();
	}

	public void initbaseResources() {
		String filePath = this.getClass().getResource("/").getFile() + i18n;
		System.out.println(filePath);
		Properties properties = new Properties();
		File file = new File(filePath);
		try {
			FileInputStream in = new FileInputStream(file);
			properties.load(in);
			baseResources = properties.getProperty(resources).split(";");
			System.out.println(baseResources[0]);
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String getMessageInternal(String baseName, Object[] args,Locale locale){
		if (baseName==null) {
			return null;
		}
		if (locale==null) {
			locale=Locale.getDefault();
		}
		if (args==null) {
			return parseWithoutParams(baseName, locale);
		}else{
			MessageFormat messageFormat=parseWithParams(baseName, locale);
			if (messageFormat!=null) {
				return messageFormat.format(args);
			}
		}
		return  null;
	}
	
	private String parseWithoutParams(String baseName,Locale locale){
		String result=null;
		for(int i=0;result==null&&i<baseResources.length;i++){
			ResourceBundle resourceBundle=getResourceBundle(baseResources[i], locale);
			if (resourceBundle!=null) {
				result=resourceBundle.getString(baseName);
			}
		}
		return result;
	}
	
	private MessageFormat parseWithParams(String baseName,Locale locale){
		MessageFormat result=null;
		for(int i=0;result==null&&i<baseResources.length;i++){
			ResourceBundle bundle=getResourceBundle(baseResources[i], locale);
			if (bundle!=null) {
				result=getMessageFormat(bundle, baseName,locale);
			}
		}
		return result;
	}
	
	private ResourceBundle doGetResourceBundle(String baseName,Locale locale){
		return ResourceBundle.getBundle(baseName,locale);
	}
	
	private ResourceBundle getResourceBundle(String baseName,Locale locale){
		synchronized (this.bundleMap) {
			Map<Locale, ResourceBundle> localMap=bundleMap.get(baseName);
			if (localMap!=null) {
				ResourceBundle resourceBundle=localMap.get(locale);
				if (resourceBundle!=null) {
					return resourceBundle;
				}
			}
			if (localMap==null) {
				localMap=new HashMap<Locale, ResourceBundle>();
				bundleMap.put(baseName, localMap);
			}
			ResourceBundle bundle=doGetResourceBundle(baseName, locale);
			localMap.put(locale, bundle);
			return bundle;
		}
	}
	
	private MessageFormat doGetMessageFormat(String pattern,Locale locale){
		return new MessageFormat(pattern==null?"":pattern, locale);
	}
	
	private MessageFormat getMessageFormat(ResourceBundle bundle,String baseName,Locale locale){
		synchronized (this.formatMap) {
			Map<String,Map<Locale, MessageFormat>> map=formatMap.get(bundle);
			Map<Locale, MessageFormat> localMap=null;
			if (map!=null) {
				localMap=map.get(baseName);
				if (localMap!=null) {
					MessageFormat format=localMap.get(locale);
					if (format!=null) {
						return format;
					}
				}
			}
			
			String msg=bundle.getString(baseName);
			if (msg!=null) {
				if (map == null) {
					map = new HashMap<String, Map<Locale, MessageFormat>>();
					formatMap.put(bundle, map);
				}
				if (localMap == null) {
					localMap = new HashMap<Locale, MessageFormat>();
					map.put(baseName, localMap);
				}
				MessageFormat format = doGetMessageFormat(msg, locale);
				localMap.put(locale, format);
				return format;
			}else{
				return null;
			}
		}
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
