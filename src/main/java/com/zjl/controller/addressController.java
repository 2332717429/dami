package com.zjl.controller;

import com.zjl.mapper.addressMapper;
import com.zjl.pojo.address;
import com.zjl.pojo.user;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/address")
public class addressController extends BaseServlet {

    public String delete(HttpServletRequest rep, HttpServletResponse resp) {
        int aid = Integer.parseInt(rep.getParameter("aid"));
        int i = new addressMapper().delAllByAid(aid);
        user user = (user) rep.getSession().getAttribute("loginUser");
        List<address> allByUid = new addressMapper().getAllByUid(user.getUid());
        rep.setAttribute("list", allByUid);
        return Constants.FORWARD + "/self_info.jsp";
    }

    public String update(HttpServletRequest rep, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        user loginUser = (user) rep.getSession().getAttribute("loginUser");
        address address = new address();
        Map<String, String[]> map = rep.getParameterMap();
        BeanUtils.populate(address,map);
        address.setUid(loginUser.getUid());
        int i = new addressMapper().updAllByAid(address);
        List<address> allByUid = new addressMapper().getAllByUid(loginUser.getUid());
        rep.setAttribute("list", allByUid);
        return Constants.FORWARD + "/self_info.jsp";
    }
    public String add(HttpServletRequest rep, HttpServletResponse resp){
        user loginUser = (user) rep.getSession().getAttribute("loginUser");
        String aname = rep.getParameter("aname");
        String aphone = rep.getParameter("aphone");
        String adetail = rep.getParameter("adetail");
        int i = new addressMapper().addAll(aname, aphone, adetail, loginUser.getUid());
        List<address> allByUid = new addressMapper().getAllByUid(loginUser.getUid());
        rep.setAttribute("list", allByUid);
        return Constants.FORWARD + "/self_info.jsp";
    }
    public String setDefault(HttpServletRequest rep, HttpServletResponse resp){
        user loginUser = (user) rep.getSession().getAttribute("loginUser");
        int aid = Integer.parseInt(rep.getParameter("aid"));
        new addressMapper().updDefaultStatusByAid(loginUser.getUid(), aid);
        List<address> allByUid = new addressMapper().getAllByUid(loginUser.getUid());
        rep.setAttribute("list", allByUid);
        return Constants.FORWARD + "/self_info.jsp";
    }
}
