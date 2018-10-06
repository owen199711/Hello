package com.jia.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jia.bean.category;
import com.jia.bean.product;
import com.jia.service.Service;

public class AdminUpdateProductUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//发送请求
		String pid = request.getParameter("pid");
		Service service=new Service();
		product product =service.getAllparameter(pid);
		
		List<category> categoryList = null;
        categoryList=service.getCategoryList();
        
        request.setAttribute("categoryList", categoryList);
		request.setAttribute("product", product);

		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}