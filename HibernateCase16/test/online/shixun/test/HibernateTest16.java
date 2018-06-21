package online.shixun.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import online.shixun.model.Commodity;


public class HibernateTest16 {
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
		init();
		session.save(new Commodity("滑板", "便利的交通工具", new Date(), new Date()));
		session.save(new Commodity("毛巾", "洗脸", new Date(), new Date()));
		session.save(new Commodity("脸盆", "洗脸", new Date(), new Date()));
		session.save(new Commodity("肥皂", "洗澡", new Date(), new Date()));
		session.save(new Commodity("自行车", "便利的交通工具", new Date(), new Date()));
		destroy();
	}

	public void getCommoditys() {
		Criteria criteria=session.createCriteria(Commodity.class);
		//条件筛选
		criteria.add(Restrictions.le("id", 5000));
		//分页
		criteria.setMaxResults(3);
		criteria.setFirstResult((2-1)*3);
		//排序
		criteria.addOrder(Order.asc("id"));
		List<Commodity> list = criteria.list();
		for (Commodity commodity : list) {
			System.out.println(commodity.toString());
		}
	}

	public static void main(String[] args) {
		HibernateTest16 hibernateTest09 = new HibernateTest16();
		hibernateTest09.init();
		hibernateTest09.getCommoditys();
		hibernateTest09.destroy();
	}

}
