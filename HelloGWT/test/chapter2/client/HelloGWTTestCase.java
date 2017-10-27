package chapter2.client;

import chapter2.client.HelloGWT;

import com.google.gwt.junit.client.GWTTestCase;

public class HelloGWTTestCase extends GWTTestCase {

	private HelloGWT helloGWT;
	
	@Override
	public String getModuleName() {
		return "chapter2.HelloGWT";
	}
	
	@Override
	protected void gwtSetUp() throws Exception {
		helloGWT = new HelloGWT();
	}
	
	public void testAdd() {
		assertEquals(5, helloGWT.add(2, 3));
	}
	
	public void testAddFail() {
		assertEquals(6, helloGWT.add(2, 3));
	}	

}
