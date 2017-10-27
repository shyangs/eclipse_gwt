package chapter9.httprequesttest;


import chapter9.httprequesttest.client.AddService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AddServiceImpl extends RemoteServiceServlet implements AddService {

	private static final long serialVersionUID = 1L;

	public Integer add(int a, int b) {
		return a + b;
	}
}