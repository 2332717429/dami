package com.zjl.controller;

import com.zjl.mapper.pageMapper;
import com.zjl.pojo.page;
import com.zjl.pojo.product;
import com.zjl.utils.BaseServlet;
import com.zjl.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/products")
public class pageController extends BaseServlet {

    public String getPageByTid(HttpServletRequest rep, HttpServletResponse resp){
        int tid = Integer.parseInt(rep.getParameter("tid"));
        int currentPage = Integer.parseInt(rep.getParameter("currentPage"));
        page<product> productPage = new pageMapper().setPage(tid, currentPage, 4);
        rep.setAttribute("pageBean",productPage);
        return Constants.FORWARD +"/goodsList.jsp";
    }
    public String detail(HttpServletRequest rep, HttpServletResponse resp){
        int pid = Integer.parseInt(rep.getParameter("pid"));
        product product= new pageMapper().getProduct(pid);
         rep.setAttribute("product",product);
         return Constants.FORWARD+"/goodsDetail.jsp";
    }
}
