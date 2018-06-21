package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Order;
import online.shixun.model.User;


public class HibernateTest04 {
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
	 * 级联查询
	 */
	public void getOrder(){
		Order order=(Order) session.get(Order.class,1);
		System.out.println(order);
	}
	/**
	 * 级联添加
	 */
	public void save(){
		Order order=new Order("100001",new Date());
		User user=new User("Arvin","123456",new Date());
		order.setUser(user);
		session.saveOrUpdate(order);
	}

	public static void main(String[] args) {
		HibernateTest04 hibernateTest04=new HibernateTest04();
		hibernateTest04.init();
		hibernateTest04.save();
		hibernateTest04.destroy();
	}
}
