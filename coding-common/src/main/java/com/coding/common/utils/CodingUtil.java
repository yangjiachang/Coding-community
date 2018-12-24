package com.coding.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 杨佳畅
 * @Description: Coding工具类
 * @Date: Created in 2018/12/14 上午12:17
 */
public class CodingUtil {
    private CodingUtil() {

    }

    /**
     * 简单判断是否为手机号
     *
     * @param phoneNo 手机号
     * @return boolean
     */
    public static boolean isPhoneNo(String phoneNo) {
        String regex = "[1]\\d{10}";
        if (StringUtils.isBlank(phoneNo))
            return false;
        else
            return phoneNo.matches(regex);
    }

    /**
     * 判断是否为 AJAX 请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
