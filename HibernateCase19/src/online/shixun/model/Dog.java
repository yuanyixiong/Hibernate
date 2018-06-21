package online.shixun.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_dog")
public class Dog extends Animal {

	private Long leg;

	public Long getLeg() {
		return leg;
	}

	public void setLeg(Long leg) {
		this.leg = leg;
	}

	public Dog(Long id, String name, String sex, Long leg) {
		super(id, name, sex);
		this.leg = leg;
	}

	public Dog() {
		super();
	}

}
