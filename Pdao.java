package com.jia.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jia.bean.PageBean;
import com.jia.bean.category;
import com.jia.bean.condition;
import com.jia.bean.product;
import com.jia.util.JdbcUtil;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class Pdao {

	public List<product> findAllproduct() throws SQLException {
		//连接数据库
		String sqlString="select * from product";
		QueryRunner qRunner=new QueryRunner(JdbcUtil.getDataSource());
		List<product> list =qRunner.query(sqlString, new BeanListHandler<product>(product.class));
		return list;
	}

	public List<category> getCategoryList() throws SQLException {
		String sqlString="select *from category";
		QueryRunner qRunner=new QueryRunner(JdbcUtil.getDataSource());
		List<category> list =qRunner.query(sqlString, new BeanListHandler<category>(category.class));
		return list;
		
	}

	public void AddProduct(product product) throws SQLException {
		String sqlString="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
		queryRunner.update(sqlString, product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
				product.getPdesc(),product.getPflag(),product.getCid());

		
	}

	public void DelProduct(String pid) throws SQLException {
		String sqlString="delete from product where pid=?";
		QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
		queryRunner.update(sqlString,pid);
	}

	public product getAllparameter(String pid) throws SQLException {
		String sqlString="select * from product where pid=?";
		QueryRunner qRunner=new QueryRunner(JdbcUtil.getDataSource());
		product product =qRunner.query(sqlString,new BeanHandler<product>(product.class),pid);
		return product;
		
	}

	public void updataProduct(product pro) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		runner.update(sql,pro.getPname(),pro.getMarket_price(),
				pro.getShop_price(),pro.getPimage(),pro.getPdate(),pro.getIs_hot(),
				pro.getPdesc(),pro.getPflag(),pro.getCid(),pro.getPid());
		
	}

	public List<product> getProductBycondition(condition condition) throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sqlBuffer="select *from product where 1=1";
		List<Object>paramList=new ArrayList<Object>();
		if(condition.getCid()!=null&&!condition.getCid().trim().equals("")){
			sqlBuffer=sqlBuffer+" and cid=? ";
			paramList.add(condition.getCid().trim());
		}
		if(condition.getIsHot()!=null&&!condition.getIsHot().trim().equals("")){
			sqlBuffer=sqlBuffer+" and is_hot=? ";
			paramList.add(condition.getIsHot().trim());
		}
		if(condition.getPname()!=null&&!condition.getPname().trim().equals("")){
			sqlBuffer=sqlBuffer+" and pname like ? ";
			paramList.add("%"+condition.getPname().trim()+"%");
		}
		
		List<product>productlist=runner.query(sqlBuffer, new BeanListHandler<product>(product.class),paramList.toArray());
		return productlist;
		
		
	}
	public List<product> getProdutList(int index, int currentCount) throws SQLException {
		//获取分页商品数据
		QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
		String sql="select * from product limit ?,?";
		List<product>productList=queryRunner.query(sql, new BeanListHandler<product>(product.class),index,currentCount);
		return productList;
	}

	public int getTatalCount() throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
		String sqlString="select count(*) from product";
		Long queryLong=(Long)queryRunner.query(sqlString, new ScalarHandler());
		return queryLong.intValue();
	}

}
