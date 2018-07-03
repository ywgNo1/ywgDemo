package gateway.common;

import java.util.Locale;

public class MessageUtils {
	private static I18nUtils utils=I18nUtils.getInstance();
	public static String getMessage(String key) {
		Locale locale=Locale.getDefault();
		return utils.getMessageInternal(key, null, locale);
	}

	public static String getMessage(String key, Object[] objs) {
		Locale locale=Locale.getDefault();
		return utils.getMessageInternal(key, objs, locale);
	}
}
