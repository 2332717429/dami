package com.zjl.mapper;

import com.zjl.pojo.address;
import com.zjl.pojo.user;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/userservlet")
public class userAddressInit extends BaseServlet {
    public String getAddress(HttpServletRequest rep, HttpServletResponse resp){
        user user= (user) rep.getSession().getAttribute("loginUser");
        List<address> allByUid = new addressMapper().getAllByUid(user.getUid());
        rep.setAttribute("list",allByUid);
        return Constants.FORWARD+"/self_info.jsp";
    }
}
