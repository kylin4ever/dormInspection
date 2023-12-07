package com.haakimi.dormInspection.aop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.haakimi.dormInspection.enums.ResultStatus;
import com.haakimi.dormInspection.exception.SystemErrorException;
import com.haakimi.dormInspection.utils.RequestHeaderData;
import com.haakimi.dormInspection.utils.ResultMapHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(0)
@Component
public class ControllerLogInterceptor {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Pointcut("execution(public *  com.haakimi.dormInspection.controller..*.*(..))") // 两个..代表所有子目录，最后括号里的两个..代表所有参数
	public void logPointCut() {
	}



	@SuppressWarnings("unchecked")
	@Around("logPointCut()")
	public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
		RequestHeaderData requestHeaderData = new RequestHeaderData();
		long beginTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); // 获取被拦截的方法
		String methodName = method.getName(); // 获取被拦截的方法名
		Object[] args = pjp.getArgs();
		Map<String, Object> paramsMap = new HashMap<>();
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		for (Object arg : args) {
			if (arg instanceof Map<?, ?>) {
				paramsMap = (Map<String, Object>) arg;
			} else if (arg instanceof HttpServletRequest) {
				HttpServletRequest rueq = (HttpServletRequest) arg;
				Map<String, String[]> pMap = rueq.getParameterMap();
				if (paramsMap == null) {
					paramsMap = new HashMap<>();
				}
				for (Map.Entry<String, String[]> entry : pMap.entrySet()) {
					paramsMap.put(entry.getKey(), entry.getValue());
				}
			}else if(arg instanceof HttpServletResponse){
				;
			}
		}
		// 记录下请求内容
		log.info("开始进入请求 : " + request.getRequestURL().toString());
		log.info("请求参数（已转JSON）："+ JSONUtil.toJsonStr(paramsMap));
		log.info("请求头参数（已转JSON）："+ JSONUtil.toJsonStr(requestHeaderData));
		Object result = null;
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			log.error("请求：" +  request.getRequestURL().toString());
			log.error("请求参数（已转JSON）："+ JSONUtil.toJsonStr(paramsMap));
			log.error("请求error:", e);
			throw new SystemErrorException(e.toString());
		}
		long costMs = System.currentTimeMillis() - beginTime;
		log.info("{}请求结束，请求返回数据为：{}，耗时：{}ms",method,JSONUtil.toJsonStr(result),costMs);
		return result;
	}
}
