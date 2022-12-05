package com.zjl.controller;

import com.zjl.mapper.userMapper;
import com.zjl.pojo.user;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 获取浏览器的本地cookie并且判断用户的cookie是否存在
 * 如果存在：根据cookie中的value来查询数据库中的用户，并且把这个设置值到session中
 * 反之：不做任何操作
 */
@WebServlet("/init")
public class pageLoadController extends BaseServlet {
    public void pageLoadInit(HttpServletRequest rep, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = rep.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(Constants.AUTO_NAME)) {
                    String string=cookies[i].getValue();
                    int flag = string.indexOf(":");
                    String uname = string.substring(0, flag);
                    String pwd = string.substring(flag+1, string.length());
                    rep.getSession().setAttribute("loginUser", new userMapper().login(uname, pwd));
                    resp.getWriter().write("ok");
                    break;
                }
        }
    }
}
