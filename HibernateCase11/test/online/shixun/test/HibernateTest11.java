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


public class HibernateTest11 {
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
		Query query = session.createQuery(" from Commodity where id <?  ");
		query.setInteger(0, 10000);

		query.setMaxResults(3);// 每页显示的数据条数
		query.setFirstResult((2 - 1) * 3);// 跳过的数据条数
		
		List<Commodity> list = query.list();
		for (Commodity commodity : list) {
			System.out.println(commodity.toString());
		}
	}

	public static void main(String[] args) {
		HibernateTest11 hibernateTest11 = new HibernateTest11();
		hibernateTest11.init();
		hibernateTest11.getCommoditys();
		hibernateTest11.destroy();
	}

}
