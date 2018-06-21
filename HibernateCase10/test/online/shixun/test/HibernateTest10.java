package online.shixun.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;


public class HibernateTest10 {
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

	public void getCommoditys() {
		Query query = session.createQuery(" from Commodity where id <? and name like ? ");
		query.setInteger(0, 5000);
		query.setString(1, "%乐事%");
		List<Commodity> list = query.list();
		for (Commodity commodity : list) {
			System.out.println(commodity.toString());
		}
	}
	

	public static void main(String[] args) {
		HibernateTest10 hibernateTest10=new HibernateTest10();
		hibernateTest10.init();
		hibernateTest10.getCommoditys();
		hibernateTest10.destroy();
	}

}
