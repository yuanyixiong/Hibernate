package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;


public class HibernateTest03 {
	SessionFactory sessionFactory;
	Session session;
	Transaction transaction;

	/**
	 * 完成hibernate初始化
	 */
	public void init() {
		// 获取配置文件
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		// 注册服务对象
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = ssrb.applySettings(configuration.getProperties()).build();
		// 获取sessionfactory
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 获取Session
		session = sessionFactory.openSession();
		// 开启事物
		transaction = session.beginTransaction();
	}

	/**
	 * 销毁hibernate资源
	 */
	public void destroy() {
		// 提交事物
		transaction.commit();
		// 一级缓存
		session.close();
		// 二级缓存
		sessionFactory.close();
	}



	/**
	 * saveOrUpdate 实现数据保存
	 */
	public void saveOrUpdate_dosave() {
		init();
		Commodity commodity=new Commodity("单车", "单车",new Date(), new Date());
		session.saveOrUpdate(commodity);
		System.out.println(commodity.toString());
		destroy();
	}
	/**
	 * saveOrUpdate 实现数据更新
	 */
	public void saveOrUpdate_doupdate() {
		init();
		Commodity commodity=new Commodity(2l,"单车", "单车",new Date(), new Date());
		session.saveOrUpdate(commodity);
		System.out.println(commodity.toString());
		destroy();
	}
	
	/**
	 * saveOrUpdate 实现数据更新
	 */
	public void saveOrUpdate_doGetToUpdate() {
		init();
		Commodity commodity=(Commodity) session.get(Commodity.class,2);
		commodity.setModifyDate(new Date());
		commodity.setDescribe("便利的交通工具");
		session.saveOrUpdate(commodity);
		System.out.println(commodity.toString());
		destroy();
	}
	
	/**
	 * merge 实现数据保存
	 */
	public void merge_dosave() {
		init();
		Commodity commodity=new Commodity("单车", "单车",new Date(), new Date());
		session.merge(commodity);
		System.out.println(commodity.toString());
		destroy();
	}
	/**
	 * merge 实现数据更新
	 */
	public void merge_doupdate() {
		init();
		Commodity commodity=new Commodity(2l,"单车", "单车",new Date(), new Date());
		session.merge(commodity);
		System.out.println(commodity.toString());
		destroy();
	}
	
	/**
	 * saveOrUpdate 实现数据更新
	 */
	public void merge_doGetToUpdate() {
		init();
		Commodity commodity=(Commodity) session.get(Commodity.class,2);
		commodity.setModifyDate(new Date());
		commodity.setDescribe("便利的交通工具");
		session.merge(commodity);
		System.out.println(commodity.toString());
		destroy();
	}
	

	public static void main(String[] args) {
		new HibernateTest03().merge_dosave();
	}
}
