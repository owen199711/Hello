package com.jia.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jia.bean.category;
import com.jia.service.Service;


public class AdminAddProductUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//发送请求
		Service service =new Service();
		List<category> categories=service.getCategoryList();
		
		
		request.setAttribute("categoryList", categories);
		request.getRequestDispatcher("/admin/product/add.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}