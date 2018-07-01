package com.ywg.xml.transform;

import org.apache.commons.lang3.math.NumberUtils;

public class LongTransform implements Transform<Long> {

	@Override
	public Long transform(String value) {
		return NumberUtils.toLong(value);
	}

}
