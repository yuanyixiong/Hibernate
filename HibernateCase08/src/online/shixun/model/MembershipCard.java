package online.shixun.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 会员卡信息
 */
@Entity
@Table(name = "tb_card")
public class MembershipCard {

	// 卡号
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;

	// 余额
	private Double money;

	// 积分
	private Integer integral;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MembershipCard(Double money, Integer integral) {
		super();
		this.money = money;
		this.integral = integral;
	}

	public MembershipCard() {
		super();
	}

	@Override
	public String toString() {
		return "MembershipCard [number=" + number + ", money=" + money + ", integral=" + integral + "]";
	}

	public String toStringAndUser() {
		return "MembershipCard [number=" + number + ", money=" + money + ", integral=" + integral + ",user=" + user
				+ "]";
	}
}
