package com.zjl.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
    	resp.setCharacterEncoding("utf-8");
        //1.鑾峰彇璇锋眰鍙傛暟锛堟爣璇嗙锛�
        String methodStr = req.getParameter(Constants.TAG);

        //2.濡傛灉method娌℃湁鑾峰彇鍒板�硷紒鎴戜滑灏辫烦杞埌棣栭〉锛侊紙鏍囪瘑绗﹀紓甯稿鐞嗭級
        if (methodStr == null && methodStr.equals("")) {
            methodStr = Constants.INDEX;
        }

        //3.鍙嶅皠璋冪敤瀵瑰簲鐨勪笟鍔￠�昏緫鏂规硶
        Class clazz = this.getClass();

        try {
            Method method = clazz.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            Object result = method.invoke(this,req,resp);
            //4.闆嗕腑澶勭悊杩斿洖鍊煎搷搴�
            if (result != null) {
                //杞彂 閲嶅畾鍚�  杩斿洖瀛楃
                String str = (String) result;
                if (str.startsWith(Constants.FORWARD)) {
                    //杞彂
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    req.getRequestDispatcher(path).forward(req,resp);
                }else if (str.startsWith(Constants.REDIRECT)){
                    //閲嶅畾鍚�
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    resp.sendRedirect(path);
                }else{
                    resp.getWriter().println(str);
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
            //controller 鍜� service dao 鏈夊紓甯搁兘浼氬埌姝ゅ锛�
            req.getSession().setAttribute("msg", "绋嬪簭寮傚父!璇风◢鍚庡啀璇曪紒");
            resp.sendRedirect("/message.jsp");
        }
    }
    public String index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return Constants.FORWARD + "/index.jsp";
    }

}
