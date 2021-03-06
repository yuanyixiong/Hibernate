package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.MembershipCard;
import online.shixun.model.User;


public class HibernateTest08 {
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
	public void save(){
		User user=new User("张三","123456789",new Date());
		MembershipCard car=new MembershipCard(500.0, 5000);
		car.setUser(user);
		session.save(car);
	}
	
	/**
	 * 级联查询
	 */
	public void getUserToCar(){
		User user=(User)session.get(User.class,1);
		System.out.println(user.toStringAndCard());
	}
	
	/**
	 * 级联查询
	 */
	public void getCarToUser(){
		MembershipCard card=(MembershipCard)session.get(MembershipCard.class,1);
		System.out.println(card.toStringAndUser());
	}

	public static void main(String[] args) {
		HibernateTest08 hibernateTest08 = new HibernateTest08();
		hibernateTest08.init();
		hibernateTest08.save();
		hibernateTest08.destroy();
	}
}
