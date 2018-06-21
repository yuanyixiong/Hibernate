package online.shixun.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 订单
 */
@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 订单号
	private String code;

	// 订单创建时间
	@Temporal(TemporalType.DATE)
	private Date createDate;

	// 特殊属性:一个订单可以有很多商品
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="tb_order_itme",//中间表名称
			joinColumns={
					@JoinColumn(name="order_id",referencedColumnName="id") //和当前订单绑定关系的列
			},
			inverseJoinColumns={
					@JoinColumn(name="commodity_id") //其他列
			}
	)
	private List<Commodity> commodities = new ArrayList<Commodity>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	public Order(String code, Date createDate) {
		super();
		this.code = code;
		this.createDate = createDate;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", code=" + code + ", createDate=" + createDate + "]";
	}

	public String toStringAndCommodities() {
		return "Order [id=" + id + ", code=" + code + ", createDate=" + createDate + ",commodities=" + commodities
				+ "]";
	}
}
