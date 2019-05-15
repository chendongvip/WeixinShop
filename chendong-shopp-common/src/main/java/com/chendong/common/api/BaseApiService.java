package com.chendong.common.api;

import java.util.HashMap;
import java.util.Map;

import com.chendong.constants.BaseApiConstarts;



/**
 * 通用baseapi 父类
 * @author Administrator
 *
 */
public class BaseApiService{

	// 封装成功
		public Map<String, Object> setResultSuccess() {
			return setResult(BaseApiConstarts.HTTP_RES_CODE_200, BaseApiConstarts.HTTP_RES_CODE_200_VALUE, null);
		}

		public Map<String, Object> setResultData(Object data) {
			return setResult(BaseApiConstarts.HTTP_RES_CODE_200, BaseApiConstarts.HTTP_RES_CODE_200_VALUE, data);
		}

		// 失败
		public Map<String, Object> setResultError(String msg) {
			return setResult(BaseApiConstarts.HTTP_RES_CODE_500, msg, null);
		}
		
		//参数异常
		public Map<String, Object> setResultParamterError(String msg) {
			return setResult(BaseApiConstarts.HTTP_RES_CODE_400, msg, null);
		}

		/**
		 * 
		 * @methodDesc: 功能描述:(返回成功)
		 * @param: @param
		 *             msg
		 * @param: @return
		 */
		public Map<String, Object> setResutSuccess(String msg) {
			return setResult(BaseApiConstarts.HTTP_RES_CODE_200, msg, null);
		}
		
		// 自定义
		public Map<String, Object> setResult(Integer code, String msg, Object data) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(BaseApiConstarts.HTTP_RES_CODE_NAME, code);
			resultMap.put(BaseApiConstarts.HTTP_RES_CODE_MSG, msg);
			if (data != null)
				resultMap.put(BaseApiConstarts.HTTP_RES_CODE_DATA, data);
			return resultMap;
		}

}
