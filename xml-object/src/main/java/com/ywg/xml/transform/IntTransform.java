package com.ywg.xml.transform;

import org.apache.commons.lang3.math.NumberUtils;

public class IntTransform implements Transform<Integer> {


	@Override
	public Integer transform(String value) {
		return NumberUtils.toInt(value);
	}

}
