package online.shixun.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_dog")
@DiscriminatorValue(value = "dog")//当前表的关键字
public class Dog extends Animal {
	private Long leg;

	public Long getLeg() {
		return leg;
	}

	public void setLeg(Long leg) {
		this.leg = leg;
	}

	public Dog(String name, String sex, Long leg) {
		super(name, sex);
		this.leg = leg;
	}

	public Dog() {
		super();
	}

}
