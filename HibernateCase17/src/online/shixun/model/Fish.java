package online.shixun.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "fish")//当前表的关键字
public class Fish extends Animal {
	private Long size;

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Fish(String name, String sex, Long size) {
		super(name, sex);
		this.size = size;
	}

	public Fish() {
		super();
	}

}
