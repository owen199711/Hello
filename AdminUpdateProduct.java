package com.jia.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jia.bean.product;
import com.jia.service.Service;

public class AdminUpdateProduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		 Map<String ,String[]>properties=request.getParameterMap();
		 product product=new product();
		 try {
			BeanUtils.populate(product, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
		
			e.printStackTrace();
		}
		
		//2）、private String pimage;
		product.setPimage("products/1/c_0033.jpg");
		//3）、private String pdate;//上架日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		//4）、private int pflag;//商品是否下载 0代表未下架
		product.setPflag(0);
		Service service =new  Service();
	    service.updataProduct(product);
		 
		response.sendRedirect(request.getContextPath()+"/productlist");




	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}