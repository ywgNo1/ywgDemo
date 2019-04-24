package gateway.common;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestXml().initbaseResources();
	}
	@SuppressWarnings("unused")
	public void initbaseResources() {
		SAXReader reader=new SAXReader();
		//通过获得当前线程，然后获取当前的类加载器，来获取对应的资源
		InputStream in=null;
		try {
			in =Thread.currentThread().getContextClassLoader().getResourceAsStream("i18n/i18n.xml");
			Document doc=reader.read(in);
			
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			list.forEach(element->{
				System.out.println(element.getName());
				System.out.println(element.attribute("id").getText());
				System.out.println(element.getStringValue());
			});
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			IOUtils.closeQuietly(in);
		}
	}

}
