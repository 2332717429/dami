package com.zjl.controller;

import com.zjl.mapper.cartMapper;
import com.zjl.mapper.orderMapper;
import com.zjl.pojo.*;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/order")
public class orderController extends BaseServlet {
    /**
     * 查看订单预览
     *
     * @param rep
     * @param resp
     * @return
     */
    public String preView(HttpServletRequest rep, HttpServletResponse resp) {
        int uid = Integer.parseInt(rep.getParameter("uid"));
        List<address> addressList = new orderMapper().getAddressByUid(uid);
        rep.setAttribute("addressList", addressList);
        user user = (user) rep.getSession().getAttribute("loginUser");
        List<cart> cart = new cartMapper().getCart(user.getUid());
        rep.setAttribute("cartList", cart);
        return Constants.FORWARD + "/order.jsp";
    }

    public String create(HttpServletRequest rep, HttpServletResponse resp){
        int uid = Integer.parseInt(rep.getParameter("uid"));
        int sum = Integer.parseInt(rep.getParameter("sum"));
        int aid = Integer.parseInt(rep.getParameter("aid"));

        orders orders = new orders();
        orders.setOid(UUID.randomUUID().toString());
        orders.setAid(aid);
        orders.setUid(uid);
        orders.setOcount((double) sum);
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        orders.setItems(orderMapper.getItemss(uid, orders.getOid()));
        orders.setOtime(format);
        orders.setOstate(1);
        new orderMapper().createOrder(orders);
                new cartMapper().deleteAll(uid);
        return Constants.FORWARD+"/orderSuccess.jsp";
    }
    public String show(HttpServletRequest rep, HttpServletResponse resp){
        user user = (user) rep.getSession().getAttribute("loginUser");
        List<orders> ordersList = new orderMapper().getAllByUid(user.getUid());
        rep.setAttribute("ordersList",ordersList);
        return Constants.FORWARD+"/orderList.jsp";
    }
    public String detail(HttpServletRequest rep, HttpServletResponse resp){
        String oid = rep.getParameter("oid");
        orders order = new orderMapper().getOrderByOid(oid);
        order.setItems(new orderMapper().getItemList(oid));
        rep.setAttribute("order",order);
        return Constants.FORWARD+"/orderDetail.jsp";
    }
    public String changeStatus(HttpServletRequest rep, HttpServletResponse resp){
        String oid = rep.getParameter("oid");
        new orderMapper().setStu(oid);
        orders order = new orderMapper().getOrderByOid(oid);
        order.setItems(new orderMapper().getItemList(oid));
        rep.setAttribute("order",order);
        return Constants.FORWARD+"/orderDetail.jsp";
    }
}
