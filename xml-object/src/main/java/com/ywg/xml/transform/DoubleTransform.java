package com.ywg.xml.transform;

import org.apache.commons.lang3.math.NumberUtils;

public class DoubleTransform implements Transform<Double> {

	@Override
	public Double transform(String value) {
		return NumberUtils.toDouble(value);
	}

}
