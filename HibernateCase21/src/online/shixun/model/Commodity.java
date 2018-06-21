package online.shixun.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * 商品
 */
@Entity
@Table(name = "tb_commodity")
@SelectBeforeUpdate(value=true) // 取值为false DynamicUpdate将无效果
@DynamicUpdate // 动态SQl修改,必须先查询,在修改，修改语句才能动态化
@DynamicInsert // 动态SQl添加
public class Commodity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
	private Long id;

	private String name;

	@Column(name = "`describe`")
	private String describe;

	// TemporalType.DATE 2011-04-12
	// TemporalType.Time 22:50:30
	// TemporalType.TIMESTAMP 2011-04-12 22:50:30
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	private Date modifyDate;

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

	public Commodity(String name, String describe, Date createDate, Date modifyDate) {
		super();
		this.name = name;
		this.describe = describe;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Commodity(Long id, String name, String describe, Date createDate, Date modifyDate) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Commodity() {
		super();
	}

}
