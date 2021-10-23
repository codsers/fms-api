package cn.codser.fmsapi.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    /**
     * 获取web基地址
     * @param request
     * @return
     */
    public static String getWebBasePath(HttpServletRequest request){
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path;
    }

    /**
     * 判断session是否存在
     * @param request
     * @return
     */
    public static Boolean sessionIsExist(HttpServletRequest request){
        return request.getSession(false)!=null;
    }
}
