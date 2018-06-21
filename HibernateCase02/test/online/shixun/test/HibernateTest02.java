package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;

public class HibernateTest02 {
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
	 * 添加
	 */
	public void save() {
		init();
		session.save(new Commodity("滑板", "便利的交通工具", new Date(), new Date()));
		destroy();
	}

	/**
	 * get获取数据
	 */
	public void get() {
		init();
		Commodity commodity = (Commodity) session.get(Commodity.class, 1);
		destroy();
		System.out.println(commodity.toString());
	}

	/**
	 * load获取数据
	 */
	public void load() {
		init();
		Commodity commodity = (Commodity) session.load(Commodity.class, 1);
		System.out.println(commodity.toString());
		destroy();
	}

	/**
	 * get获取数据,saveOrUpdate修改数据
	 */
	public void saveOrUpdate() {
		init();
		Commodity commodity = (Commodity) session.get(Commodity.class, 1);
		System.out.println(commodity.toString());

		commodity.setCreateDate(new Date());
		commodity.setDescribe("我是描述");

		session.saveOrUpdate(commodity);
		System.out.println(commodity.toString());

		destroy();

	}

	/**
	 * remove删除数据
	 */
	public void remove() {
		init();
		session.delete(new Commodity(1l));
		destroy();
	}

	public static void main(String[] args) {
		new HibernateTest02().save();
	}
}
