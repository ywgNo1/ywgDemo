package com.ywg.xml.transform;

import org.apache.commons.lang3.math.NumberUtils;

public class ShortTransform implements Transform<Short> {

	@Override
	public Short transform(String value) {
		return NumberUtils.toShort(value);
	}

}
