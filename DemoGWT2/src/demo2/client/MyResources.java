package demo2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface MyResources extends ClientBundle {
	public static final MyResources INSTANCE = GWT.create(MyResources.class);

	public interface Style extends CssResource {
		String redbox();
	}

	@Source("resource/style.css")
	public Style css();

	@Source("resource/config.xml")
	public TextResource config();

	@Source("resource/data.pdf")
	public DataResource data();

	@Source("resource/image.gif")
	public ImageResource image();
}
