package com.ywg.xml.transform;

public class BooleanTransform implements Transform<Boolean> {

	@Override
	public Boolean transform(String value) {
		return Boolean.valueOf(value);
	}

}
