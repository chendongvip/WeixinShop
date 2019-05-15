package com.chendong.common.util;

import java.util.Map;

import com.chendong.constants.BaseApiConstarts;


public class ResultUtils {

	public static Object getResultMap(Map<String, Object> reusltItemDesc) {
		Integer code = (Integer) reusltItemDesc.get(BaseApiConstarts.HTTP_RES_CODE_200_VALUE);
		if (code.equals(BaseApiConstarts.HTTP_RES_CODE_200)) {
			Object object = reusltItemDesc.get("data");
			return object;
		}
		return null;
	}

}
