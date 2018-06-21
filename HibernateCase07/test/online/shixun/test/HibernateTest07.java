package online.shixun.test;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;
import online.shixun.model.Order;


public class HibernateTest07 {
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
	public void saveOrderToCommoditys(){
		Order order=new Order("1000001",new Date());
		order.getCommodities().add(new Commodity("牙膏", "刷牙", new Date(), new Date()));
		order.getCommodities().add(new Commodity("毛巾", "洗脸", new Date(), new Date()));
		order.getCommodities().add(new Commodity("卫生纸", "擦嘴", new Date(), new Date()));
		order.getCommodities().add(new Commodity("脸盆", "洗脸", new Date(), new Date()));
		order.getCommodities().add(new Commodity("沐浴露", "洗澡", new Date(), new Date()));
		
		session.saveOrUpdate(order);
	}
	
	/**
	 * 级联添加
	 */
	public void saveCommodityToOrders(){
		Commodity commodity=new Commodity("牙膏", "刷牙", new Date(), new Date());
		commodity.getOrders().add(new Order("1000002", new Date()));
		commodity.getOrders().add(new Order("1000003", new Date()));
		commodity.getOrders().add(new Order("1000004", new Date()));
		commodity.getOrders().add(new Order("1000005", new Date()));
		commodity.getOrders().add(new Order("1000006", new Date()));
		
		
		session.saveOrUpdate(commodity);
	}
	
	/**
	 * 级联查询
	 */
	public void getCommodityToOrders(){
		Commodity commodity=(Commodity) session.get(Commodity.class, 6);
		System.out.println(commodity.toStringAndOrders());
	}
	
	
	/**
	 * 级联查询
	 */
	public void getOrderToCommoditys(){
		Order order=(Order) session.get(Order.class, 1);
		System.out.println(order.toStringAndCommodities());
	}


	public static void main(String[] args) {
		HibernateTest07 hibernateTest07 = new HibernateTest07();
		hibernateTest07.init();
		hibernateTest07.saveCommodityToOrders();
		hibernateTest07.destroy();
	}
}
