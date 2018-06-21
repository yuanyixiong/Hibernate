package online.shixun.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Order;
import online.shixun.model.User;

public class HibernateTest13 {
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
	 * 内链接
	 */
	public void inner_join() {
		// HQL 内连接 以数组的方式进行存储
		List<Object[]> list = session.createQuery("from User u inner join u.orders").list();
		for (Object[] objects : list) {
			User user = (User) objects[0];
			Order order = (Order) objects[1];

			System.out.println(user.toStringAndOrders());
			System.out.println(order.toStringAndUser());
			System.out.println("===============================================");
		}
	}

	/**
	 * 迫切内链接
	 */
	public void inner_join_fetch() {
		// 迫切内连接 以对象的方式进行存储
		 List<User> list = session.createQuery("from User u inner join fetch u.orders").list();
		 for (User user : list) {
		 System.out.println(user);
		 }

	}

	/**
	 * 左外连接
	 */
	public void left_outer_join() {
		// 左外连接 以数组的方式进行存储
		List<Object[]> list = session.createQuery("from User u left outer join u.orders").list();
		for (Object[] objects : list) {
			User user = (User) objects[0];

			System.out.println(user.toStringAndOrders());
			System.out.println("===============================================");
		}

	}

	/**
	 * 迫切左外连接
	 */
	public void left_outer_join_fetch() {
		// 迫切左外连接 以对象的方式进行存储
		List<User> list = session.createQuery("from User u left outer join fetch u.orders").list();
		for (User user : list) {
			System.out.println(user);
		}

	}

	/**
	 * 右外连接
	 */
	public void right_outer_join() {
		// 右外连接 以数组的方式进行存储
		List<Object[]> list = session.createQuery("from User u right outer join u.orders").list();
		for (Object[] objects : list) {
			User user = (User) objects[0];
			Order order = (Order) objects[1];

			System.out.println(user==null?null:user.toStringAndOrders());
			System.out.println(order==null?null:order.toStringAndUser());
			System.out.println("===============================================");
		}
	}

	/**
	 * 迫切右外连接
	 */
	public void right_outer_join_fetch() {
		// 迫切右外连接 以对象的方式进行存储
		List<User> list = session.createQuery("from User u right outer join fetch u.orders").list();
		for (User user : list) {
			System.out.println(user);
		}
	}

	public static void main(String[] args) {
		HibernateTest13 hibernateTest13 = new HibernateTest13();
		hibernateTest13.init();
		
		//添加数据
		hibernateTest13.saveUserToOrders();
		
		//内连接
		hibernateTest13.inner_join();
		hibernateTest13.inner_join_fetch();
		
		//左外
		hibernateTest13.left_outer_join();
		hibernateTest13.left_outer_join_fetch();
		
		//右外
		hibernateTest13.right_outer_join();
		hibernateTest13.right_outer_join_fetch();
		
		hibernateTest13.destroy();
	}
}
