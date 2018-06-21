package online.shixun.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 商品
 */
@Entity
@Table(name = "tb_commodity")
public class Commodity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "`describe`")
	private String describe;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	private Date modifyDate;

	// 特殊属性:商品可以属于很多个订单
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="tb_order_itme",//中间表名称
			joinColumns={
					@JoinColumn(name="commodity_id",referencedColumnName="id")//和当前商品绑定关系的列
			},
			inverseJoinColumns={
					@JoinColumn(name="order_id")//其他列
			}
	)
	private Set<Order> orders = new HashSet<Order>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Commodity(String name, String describe, Date createDate, Date modifyDate) {
		super();
		this.name = name;
		this.describe = describe;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Commodity() {
		super();
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", name=" + name + ", describe=" + describe + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}

	public String toStringAndOrders() {
		return "Commodity [id=" + id + ", name=" + name + ", describe=" + describe + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ",orders=" + orders + "]";
	}

}
