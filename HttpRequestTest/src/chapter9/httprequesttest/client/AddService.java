package chapter9.httprequesttest.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("add")
public interface AddService extends RemoteService  {
	Integer add(int a, int b);
}
