package com.zjl.controller;

import cn.dsna.util.images.ValidateCode;
import com.zjl.utils.BaseServlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/code")
public class codeController extends BaseServlet {

    public void createCode(HttpServletRequest rep, HttpServletResponse resp) throws IOException {
        ValidateCode validateCode=new ValidateCode(150,50,5,20);
        String code = validateCode.getCode();
        HttpSession session= rep.getSession();
        session.setAttribute("code",code);
        ServletOutputStream outputStream = resp.getOutputStream();
        validateCode.write(outputStream);
    }
}
