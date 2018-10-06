package com.jia.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jia.bean.PageBean;
import com.jia.bean.product;
import com.jia.service.Service;

public class AdmainPageBeanList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//向service发送请求得到pageBean
		Service service=new Service();
		String currentPageString=request.getParameter("currentPage");
		if(currentPageString==null)currentPageString="1";
		int currentPage=Integer.parseInt(currentPageString);
		PageBean pagebeanList=service.getPageBeanList(currentPage,12);
		
		request.setAttribute("pageBean", pagebeanList);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}