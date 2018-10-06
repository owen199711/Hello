package com.jia.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jia.service.Service;

public class AdminDelProduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//向数据库发送请求
		String pid=request.getParameter("pid");
		Service service =new Service();
		service.DelProduct(pid);
		
		
	   response.sendRedirect(request.getContextPath()+"/productlist");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}