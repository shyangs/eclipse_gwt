package chapter10.helloext.client;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class Employee extends BaseModelData 
 {
	private static final long serialVersionUID = 1L;

	public Employee() {
		
	}
	
	public Employee(String name, int age) {
		setName(name);
		setAge(age);
	}
	
	public String getName() {
		return get("name");
	}
	
	public void setName(String name) {
		set("name", name);
	}
	
	public int getAge() {
		return get("age");
	}
	
	public void setAge(int age) {
		set("age", age);
	}
}