package com.zjl.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zjl.mapper.typeMapper;
import com.zjl.pojo.type;
import com.zjl.utils.BaseServlet;

@WebServlet("/types")
public class typeController extends BaseServlet{

	public String getAll(HttpServletRequest rep,HttpServletResponse resp) {
		
		List<type> list= new typeMapper().getAll();
		String jsonString=new Gson().toJson(list);
		HttpSession session = rep.getSession();
		return jsonString;
	}
}
