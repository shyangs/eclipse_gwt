package chapter9.hibernate.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="UserItem")
public class UserItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	
	public UserItem() {
		
	}
	
	@Id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
