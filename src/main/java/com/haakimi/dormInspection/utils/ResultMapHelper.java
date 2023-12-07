package com.haakimi.dormInspection.utils;

import com.haakimi.dormInspection.enums.ResultStatus;
import java.util.HashMap;
import java.util.Map;

public class ResultMapHelper {

	public static Map<String, Object> result(String status,String msg,Object data){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	public static Map<String, Object> result(String status,String msg){
		return result(status, msg, null);
	}

	/**
	 * @Description: 新方式，采用枚举的方式
	 * @Param returnEnums
	 * @Author: cfx
	 * @Date: 2019/12/15
	 */
	public static Map<String, Object> result(ResultStatus resultStatus){
		return result(resultStatus.getCode(),resultStatus.getMsg(), null);
	}
	
	
	public static Map<String, Object> failure(String msg){
		return result(ResultStatus.Fail.getCode(), msg, null);
	}
	
	public static Map<String, Object> success(Object obj,String msg){
		return result(ResultStatus.Success.getCode(), msg, obj);
	}

	public static Map<String,Object> success(Object obj,Map<String,Object> userData,String msg){
		Map<String,Object> resultMap = result(ResultStatus.Success.getCode(), msg, obj);
		resultMap.put("userData",userData);
		return resultMap;
	}

	public static Map<String,Object> success(Object obj,Map<String,Object> userData){
		Map<String,Object> resultMap = result(ResultStatus.Success.getCode(), ResultStatus.Success.getMsg(), obj);
		resultMap.put("userData",userData);
		return resultMap;
	}
	
	public static Map<String, Object> success(Object obj){
		return result(ResultStatus.Success.getCode(), null, obj);
	}
	
	public static Map<String, Object> success(){
		return result(ResultStatus.Success);
	}


	
	public static boolean isSuccess(Map<String, Object> result){
		String status = result.get("status").toString();
		if(StringUtils.equals(status, ResultStatus.Success.getCode())){
			return true;
		}
		return false;
	}
	
	public static Map<String, Object> nullParamsResult(){
		return result(ResultStatus.NullParams);
	}
	
	public static Map<String, Object> bizError(String msg){
		return result(ResultStatus.IncorrectOperation);
	}
	
}