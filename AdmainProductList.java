package com.jia.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jia.bean.product;
import com.jia.service.Service;


public class AdmainProductList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//请求转发
		Service service=new Service();
		List<product> list=service.findAllproduct();
		
		request.setAttribute("productList", list);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}