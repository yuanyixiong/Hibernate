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


public class HibernateTest12 {
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
		Query query = session.getNamedQuery("findListByCommoditys");
		query.setString("name", "%乐事%");
		query.setInteger("id", 10000);

		List<Commodity> list = query.list();
		for (Commodity commodity : list) {
			System.out.println(commodity.toString());
		}
	}

	public static void main(String[] args) {
		HibernateTest12 hibernateTest12 = new HibernateTest12();
		hibernateTest12.init();
		hibernateTest12.getCommoditys();
		hibernateTest12.destroy();
	}

}
