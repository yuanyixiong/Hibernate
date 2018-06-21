package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;

public class HibernateTest21 {
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

	private void save() {
		session.save(new Commodity("牙膏",null, new Date(), new Date()));
	}
	
	private void edit() {
		Commodity commodity=new Commodity(1l,"牙膏1111233333322",null, new Date(), new Date());
		session.saveOrUpdate(commodity);
	}

	public static void main(String[] args) {
		HibernateTest21 hibernateTest21 = new HibernateTest21();
		hibernateTest21.init();
		hibernateTest21.save();
		hibernateTest21.destroy();
	}

}
