package com.ywg.xml.transform;

import org.apache.commons.lang3.math.NumberUtils;

public class FloatTransform implements Transform<Float> {

	@Override
	public Float transform(String value) {
		return NumberUtils.toFloat(value);
	}

}
