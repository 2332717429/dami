package com.zjl.controller;


import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dsna.util.images.ValidateCode;
import com.zjl.mapper.typeMapper;
import com.zjl.mapper.userMapper;
import com.zjl.pojo.user;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/user")
public class userController extends BaseServlet {

    public String checkedUser(HttpServletRequest rep, HttpServletResponse resp) {
        String username = rep.getParameter("username");
        if (new userMapper().getAllByName(username)) {
            return Constants.NOT_HAS_USER;
        }
        return Constants.HAS_USER;
    }

    public String register(HttpServletRequest rep, HttpServletResponse resp) {
        user user = new user();
        Map<String, String[]> parameterMap = rep.getParameterMap();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        user.setUcode("123");
        user.setUstatus(1);

        if (new userMapper().insert(user) == 1) {
            return Constants.FORWARD + "/login.jsp";
        }
        return Constants.FORWARD + "/register.jsp";
    }

    public String login(HttpServletRequest rep, HttpServletResponse resp) {
        String username = rep.getParameter("username");
        String pwd = rep.getParameter("password");
        String code = rep.getParameter("code");
        String auto = rep.getParameter("auto");
        user user = new userMapper().login(username, pwd);
        String code1 = (String) rep.getSession().getAttribute("code");
        if (code1.equalsIgnoreCase(code)) {
            if (user != null) {
                rep.getSession().setAttribute("loginUser", user);
                if (auto != null) {
                    String cookieUser = username + ":" + pwd;
                    Cookie cookie = new Cookie(Constants.AUTO_NAME, cookieUser);
                    cookie.setMaxAge(7 * 60 * 60 * 24);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                    return Constants.FORWARD + "/index.jsp";
                }
                return Constants.FORWARD + "/index.jsp";
            } else {
                rep.setAttribute("msg", "账号或密码错误");
                return Constants.FORWARD + "/login.jsp";
            }
        } else {
            rep.setAttribute("msg", "小伙子,看清楚了在填");
            return Constants.FORWARD + "/login.jsp";
        }
    }

    public String logOut(HttpServletRequest rep, HttpServletResponse resp) {
        rep.getSession().removeAttribute("loginUser");
        return Constants.FORWARD + "/index.jsp";

    }
}
