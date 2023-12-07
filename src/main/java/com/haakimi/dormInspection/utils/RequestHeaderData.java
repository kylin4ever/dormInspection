package com.haakimi.dormInspection.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @ClassName ParamData
 * @Description 用于直接提取mvc的前端参数
 * @Author cfx
 * @Date 2019/3/8 14:53
 **/
public class RequestHeaderData extends HashMap {
    private HttpServletRequest httpServletRequest;
    public RequestHeaderData() {
        this.httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> enumeration = this.httpServletRequest.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String headerName = enumeration.nextElement();
            String headerVal = httpServletRequest.getHeader(headerName);
            if(headerVal != null) {
                this.put(headerName, httpServletRequest.getHeader(headerName));
            }
        }
    }
    public Object get(Object key){
        if(!this.containsKey(key)){
            return null;
        }
        return super.get(key);
    }

    public String getString(Object key){
        String ret = "";
        if(this.containsKey(key) & this.get(key) != null){
            return this.get(key).toString();
        }
        return ret;
    }

    public Integer getInteger(Object key){
        Integer ret = null;
        if(this.containsKey(key) && StringUtils.checkNullAndEmpty(this.get(key))){
            ret = Integer.parseInt(this.get(key).toString());
        }
        return ret;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
}
