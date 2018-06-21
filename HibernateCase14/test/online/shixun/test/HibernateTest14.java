package online.shixun.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Order;
import online.shixun.model.User;


public class HibernateTest14 {
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
	 * 级联添加
	 */
	public void saveUserToOrders() {
		User user = new User("Arvin", "123456", new Date());
		user.getOrders().add(new Order("100001", new Date()));
		user.getOrders().add(new Order("100002", new Date()));
		user.getOrders().add(new Order("100003", new Date()));
		user.getOrders().add(new Order("100004", new Date()));
		user.getOrders().add(new Order("100005", new Date()));
		user.getOrders().add(new Order("100006", new Date()));
		session.saveOrUpdate(user);
	}

	/**
	 * 级联查询
	 */
	public void getOrders() {
		Query query = session.createQuery("from Order o where o.user in (from User u where u.loginName=? and u.password=? )");
		query.setString(0, "Arvin");
		query.setString(1, "123456");
		List<Order> orders = query.list();
		for (Order order : orders) {
			System.out.println(order);
		}
	}

	public static void main(String[] args) {
		HibernateTest14 hibernateTest14 = new HibernateTest14();
		hibernateTest14.init();
		hibernateTest14.getOrders();
		hibernateTest14.destroy();
	}
}
