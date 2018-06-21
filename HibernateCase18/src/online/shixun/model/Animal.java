package online.shixun.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "tb_animal")
@Inheritance(strategy = InheritanceType.JOINED) //多个表,子表存放特有属性,有一个外键和父表关联
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING) //区分各表的关键字，列
@DiscriminatorValue(value = "animal")//当前表的关键字
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Animal(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}

	public Animal() {
		super();
	}

}
