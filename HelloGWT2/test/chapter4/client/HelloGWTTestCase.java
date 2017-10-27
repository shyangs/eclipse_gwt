package chapter4.client;

import chapter4.client.HelloGWT;

import com.google.gwt.junit.client.GWTTestCase;

public class HelloGWTTestCase extends GWTTestCase {

	private HelloGWT helloGWT;
	
	@Override
	public String getModuleName() {
		return "chapter4.HelloGWT";
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
