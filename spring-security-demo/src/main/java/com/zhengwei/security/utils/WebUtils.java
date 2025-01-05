package com.zhengwei.security.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 将字符串渲染到客户端
 */
public class WebUtils {

    public static String renderString(HttpServletResponse response, String str) {
        try {
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
