package com.jia.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jia.bean.PageBean;
import com.jia.bean.category;
import com.jia.bean.condition;
import com.jia.bean.product;
import com.jia.dao.Pdao;

public class Service {

	public List<product> findAllproduct() {
		//无业务，转发给dao层
		List<product>productlist=null;
		Pdao dao=new Pdao();
		try {
			productlist=dao.findAllproduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productlist;
	}

	public List<category> getCategoryList() {
		List<category> list=null;
		Pdao dao=new Pdao();
		try {
			list=dao.getCategoryList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public void AddProduct(product pro) {
		//将数据传到pdao层
		Pdao dao=new Pdao();
		try {
			dao.AddProduct(pro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void DelProduct(String pid) {
		Pdao dao=new Pdao();
		try {
			dao.DelProduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public product getAllparameter(String pid) {
		Pdao dao=new Pdao();
		product product=null;
		try {
			product = dao.getAllparameter(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	public void updataProduct(product pro) {
		Pdao dao=new Pdao();
		try {
			dao.updataProduct(pro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<product> getProductBycondition(condition condition) {
		List<product>productlist=null;
		Pdao dao=new Pdao();
		try {
			productlist=dao.getProductBycondition(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productlist;
	}

	public PageBean<?> getPageBeanList(int CurrentPage,int CurrentCount) {
		// 封装pageBean
		Pdao pdao=new Pdao();
		
		PageBean<product> pageBeanList=new PageBean<product>();
		
		int index=(CurrentPage-1)*CurrentCount;
		
		List<product> productList=null;
		try {
			productList = pdao.getProdutList(index,CurrentCount);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//当前页面条数
		pageBeanList.setCurrentCount(CurrentCount);
		//当前页面索引
		pageBeanList.setCurrentPage(CurrentPage);
		//总数据条数
		int tatalCount = 0;
		try {
			tatalCount = pdao.getTatalCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBeanList.setTatalCount(tatalCount);
		//总页面数
		int TatalPage=(int)Math.ceil(1.0*tatalCount/CurrentCount);//向上取整
		
		pageBeanList.setTotalPage(TatalPage);
		//每个页面数据
		pageBeanList.setProductList(productList);
		
		return pageBeanList;
	}

}
