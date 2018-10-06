package com.jia.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jia.bean.category;
import com.jia.bean.condition;
import com.jia.bean.product;
import com.jia.service.Service;

public class AdmainSearchProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收数据并封装成实体
		condition cond=new condition();
		Map<String ,String[]>properties=request.getParameterMap();
		try {
			BeanUtils.populate(cond, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Service service =new Service();
		List<product>productlist=service.getProductBycondition(cond);
		
		request.setAttribute("productList", productlist);
		request.setAttribute("condition", cond);
		
		List<category>categories=null;
		
		categories=service.getCategoryList();
		
		request.setAttribute("categoryList", categories);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}