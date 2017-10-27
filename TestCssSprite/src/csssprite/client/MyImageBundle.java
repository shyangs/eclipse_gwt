package csssprite.client;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface MyImageBundle extends ImageBundle {
	
	@Resource("down.png")
	public AbstractImagePrototype downIcon();

	@Resource("up.png")
	public AbstractImagePrototype upIcon();

	@Resource("left.png")
	public AbstractImagePrototype leftIcon();

	@Resource("right.png")
	public AbstractImagePrototype rightIcon();
}
