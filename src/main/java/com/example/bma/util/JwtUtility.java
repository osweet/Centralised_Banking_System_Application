package com.example.bma.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public final class JwtUtility {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_SEPERATOR = " ";

    public static String getAccessToken(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            return Arrays.asList(request.getHeader(AUTHORIZATION_HEADER).split(TOKEN_SEPERATOR)).get(1);
        }
        return null;
    }
}
