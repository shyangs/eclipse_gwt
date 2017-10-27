package chapter9.hibernate.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HibernateTest implements EntryPoint {
	public void onModuleLoad() {
		Button b1 = new Button("添加用户");
		b1.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				UserServiceAsync userService = GWT.create(UserService.class);
				UserItem user = new UserItem();
				user.setName("a");
				user.setAge(18);
				userService.save(user, new AsyncCallback<Object>(){
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Object result) {
					}
				});
			}
		});
		
		Button b2 = new Button("查询用户");
		b2.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				UserServiceAsync userService = GWT.create(UserService.class);
				userService.query("a", new AsyncCallback<UserItem>(){
					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(UserItem result) {
						Window.alert(Integer.toString(result.getAge()));
					}
				});
			}
		});	
		
		RootPanel.get().add(b1);
		RootPanel.get().add(b2);
	}
}
