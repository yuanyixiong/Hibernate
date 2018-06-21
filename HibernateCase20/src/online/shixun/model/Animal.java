package online.shixun.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass  //该实体不会被构建成表
public class Animal {

	@Id
	private Long id;

	private String name;

	private String sex;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Animal(Long id, String name, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}

	public Animal() {
		super();
	}

}
