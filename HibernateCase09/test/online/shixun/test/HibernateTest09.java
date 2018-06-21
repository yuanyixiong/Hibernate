package online.shixun.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;

public class HibernateTest09 {
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
		session.save(new Commodity("滑板", "便利的交通工具", new Date(), new Date()));
		session.save(new Commodity("毛巾", "洗脸", new Date(), new Date()));
		session.save(new Commodity("脸盆", "洗脸", new Date(), new Date()));
		session.save(new Commodity("肥皂", "洗澡", new Date(), new Date()));
		session.save(new Commodity("自行车", "便利的交通工具", new Date(), new Date()));
	}

	public void list() {
		List<Commodity> list = session.createQuery(" from Commodity ").list();
		for (Commodity commodity : list) {
			System.out.println(commodity.toString());
		}
	}

	public void iterator() {
		for (Iterator<Commodity> iterator = session.createQuery(" from Commodity ").iterate(); iterator.hasNext();) {
			Commodity commodity = iterator.next();
			System.out.println(commodity.toString());
		}
	}

	public static void main(String[] args) {
		HibernateTest09 hibernateTest09 = new HibernateTest09();
		hibernateTest09.init();
		hibernateTest09.save();
		hibernateTest09.destroy();
	}

}
