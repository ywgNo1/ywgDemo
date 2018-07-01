package com.ywg.xml;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ywg.xml.transform.BooleanTransform;
import com.ywg.xml.transform.DoubleTransform;
import com.ywg.xml.transform.FloatTransform;
import com.ywg.xml.transform.IntTransform;
import com.ywg.xml.transform.LongTransform;
import com.ywg.xml.transform.ShortTransform;
import com.ywg.xml.transform.StringTransform;
import com.ywg.xml.transform.Transform;

public class XmlObjectUtil {
	private static Logger logger = LoggerFactory.getLogger(XmlObjectUtil.class);
	@SuppressWarnings("rawtypes")
	private static Map<String, Transform> transFormMap = new HashMap<>();

	static {
		transFormMap.put(String.class.getName(), new StringTransform());

		IntTransform intTransform = new IntTransform();
		transFormMap.put(Integer.class.getName(), intTransform);
		transFormMap.put("int", intTransform);

		BooleanTransform booleanTransform = new BooleanTransform();
		transFormMap.put(Boolean.class.getName(), booleanTransform);
		transFormMap.put("boolean", booleanTransform);

		DoubleTransform doubleTransform = new DoubleTransform();
		transFormMap.put(Double.class.getName(), doubleTransform);
		transFormMap.put("double", doubleTransform);

		LongTransform longTransform = new LongTransform();
		transFormMap.put(Long.class.getName(), longTransform);
		transFormMap.put("long", longTransform);
		
		ShortTransform shortTransform = new ShortTransform();
		transFormMap.put(Short.class.getName(), shortTransform);
		transFormMap.put("short", shortTransform);
		
		FloatTransform floatTransform = new FloatTransform();
		transFormMap.put(Float.class.getName(), floatTransform);
		transFormMap.put("float", floatTransform);

	}

	public static Object toObject(Class<?> instance, String xmlPath) {
		Document document = initDocument(xmlPath);
		if (document == null) {
			return null;
		}
		Element root = document.getRootElement();
		Object entity = null;
		try {
			entity = instance.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			logger.error("error when create the instance", e1);
			return null;
		}

		Field[] fields = instance.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			Element element = root.element(fieldName);
			if (element == null) {
				continue;
			}
			Class<?> type = field.getType();
			Transform<?> transform = transFormMap.get(type.getName());
			if (transform == null) {
				//
				continue;
			}
			field.setAccessible(true);

			try {
				field.set(entity, transform.transform(element.getStringValue()));
			} catch (IllegalArgumentException e) {

			} catch (IllegalAccessException e) {

			}
		}

		return entity;
	}

	private static Document initDocument(String xmlPath) {
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(new File(xmlPath));
		} catch (DocumentException e) {
			logger.error("not find the xml file:" + xmlPath);
		}
		return document;
	}

}
