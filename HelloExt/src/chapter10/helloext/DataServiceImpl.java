package chapter10.helloext;

import java.util.ArrayList;
import java.util.List;

import chapter10.helloext.client.DataService;
import chapter10.helloext.client.Employee;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Employee> readData() {
		List<Employee> list = new ArrayList<Employee>(); 
		list.add(new Employee("张三", 24));
		list.add(new Employee("李四", 28));
		return list;
	}
}