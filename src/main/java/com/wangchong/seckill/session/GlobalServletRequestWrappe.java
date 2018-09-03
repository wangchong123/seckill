package com.wangchong.seckill.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @Author: wangchong
 * @Description
 * @Date : Created in 11:49 2018/9/3
 */
public class GlobalServletRequestWrappe extends HttpServletRequestWrapper {

    private HttpSession session;
    public GlobalServletRequestWrappe(HttpServletRequest request,HttpSession session) {
        super(request);
        if(session == null){
            this.session = session;
        }
    }

    @Override
    public HttpSession getSession() {
        return session;
    }

    @Override
    public HttpSession getSession(boolean create) {
        if(create && session != null){
            return new NativeHttpSession(getServletContext());
        }
        return session;
    }
}
