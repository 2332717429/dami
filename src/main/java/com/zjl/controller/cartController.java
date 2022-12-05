package com.zjl.controller;

import com.zjl.mapper.cartMapper;
import com.zjl.mapper.pageMapper;
import com.zjl.pojo.cart;
import com.zjl.pojo.product;
import com.zjl.pojo.user;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cart")
public class cartController extends BaseServlet {
    /**
     * 加入购物车 判断session 中的用户是否登录
     *
     * @param rep
     * @param resp
     * @return
     */
    public String create(HttpServletRequest rep, HttpServletResponse resp) {
        //接受参数和取出参数
        int pid = Integer.valueOf(rep.getParameter("pid"));
        user loginUser = (user) rep.getSession().getAttribute("loginUser");

        //判断是否登录
        if (loginUser == null) {
            rep.setAttribute("msg", "请先登录");
            return Constants.FORWARD + "/login.jsp";
        }
        //查询商品信息
        product product = new pageMapper().getProduct(pid);

        //判断购物是否有信息 无则新增 有则修改
        cart selCart = new cartMapper().cart(pid, loginUser.getUid());
        if(selCart.getCnum()==null){
            //创建一个购物车实例
            cart cart = new cart();
            cart.setUid(loginUser.getUid());
            cart.setPid(pid);
            cart.setProduct(product);
            cart.setCnum(cart.getCnum() == null ? 1 : cart.getCnum() + 1);
            cart.setCcount(product.getPprice() * cart.getCnum());
            //加入购物车
            int save = new cartMapper().save(cart);
            return Constants.FORWARD + "/cartSuccess.jsp";
        }
        selCart.setCnum(selCart.getCnum()+1);
        selCart.setCcount(product.getPprice()*selCart.getCnum());
        int i = new cartMapper().setCart(selCart);

        return Constants.FORWARD + "/cartSuccess.jsp";
    }

    /**
     * 展示购物车中的所有商品
     * @param rep
     * @param resp
     * @return
     */
    public String show(HttpServletRequest rep, HttpServletResponse resp){
        int uid = Integer.parseInt(rep.getParameter("uid"));
        List<cart> cart = new cartMapper().getCart(uid);
        rep.setAttribute("list",cart);
        return Constants.FORWARD+"/cart.jsp";
    }

    /**
     * 购物车删除功能 删除购物车中为数量为1的商品
     * @param rep
     * @param resp
     * @return
     */
    public String delete(HttpServletRequest rep, HttpServletResponse resp){
        int cid = Integer.parseInt(rep.getParameter("cid"));
      new cartMapper().deleteCart(cid);
        user user= (user) rep.getSession().getAttribute("loginUser");
        List<cart> cart = new cartMapper().getCart(user.getUid());
        rep.setAttribute("list",cart);
      return Constants.FORWARD+"/cart.jsp";
    }

    /**
     * 购物车修改数量和小计
     * @param rep
     * @param resp
     * @return
     */
    public String update(HttpServletRequest rep, HttpServletResponse resp){
        int cid = Integer.parseInt(rep.getParameter("cid"));
        Double price = Double.valueOf(rep.getParameter("price"));
        int cnum = Integer.parseInt(rep.getParameter("cnum"));
        cart getpid = new cartMapper().getpid(cid);
        int i = new cartMapper().setCnumAndCounts(getpid.getPid(), cnum,price);
        user user= (user) rep.getSession().getAttribute("loginUser");
        List<cart> cart = new cartMapper().getCart(user.getUid());
        rep.setAttribute("list",cart);
        return Constants.FORWARD+"/cart.jsp";
    }
    public String clear(HttpServletRequest rep, HttpServletResponse resp){
        int clear = Integer.parseInt(rep.getParameter("uid"));
        new cartMapper().deleteAll(clear);
        user user= (user) rep.getSession().getAttribute("loginUser");
        List<cart> cart = new cartMapper().getCart(user.getUid());
        rep.setAttribute("list",cart);
        return Constants.FORWARD+"/cart.jsp";
    }
}
