package com.jia.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jia.bean.product;
import com.jia.service.Service;

public class AdmainAddProduct extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//读取表单信息并封装成对象
		Map<String , String[] >propertiesMap= request.getParameterMap();
		
		product pro=new product();
		
		try {
			BeanUtils.populate(pro, propertiesMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//对其他数据进行封装
		//1）、private String pid;
		pro.setPid(UUID.randomUUID().toString());
				//2）、private String pimage;
	    pro.setPimage("products/1/c_0033.jpg");
				//3）、private String pdate;//上架日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	String pdate = format.format(new Date());
	    pro.setPdate(pdate);
				//4）、private int pflag;//商品是否下载 0代表未下架
		pro.setPflag(0);
		
		
		//将数据发送到service层
		 Service service =new  Service();
		 service.AddProduct(pro);
		 
		 response.sendRedirect(request.getContextPath()+"/productlist");


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}