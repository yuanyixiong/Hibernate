package online.shixun.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fish")
public class Fish extends Animal {

	private Long size;

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Fish(Long id, String name, String sex, Long size) {
		super(id, name, sex);
		this.size = size;
	}

	public Fish() {
		super();
	}

}
