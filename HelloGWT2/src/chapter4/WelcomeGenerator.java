package chapter4;

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;

public class WelcomeGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context,
			String typeName) throws UnableToCompleteException {
		try {
			if (typeName.equals("chapter4.client.WelcomeImpl")) {
				PropertyOracle propOracle = context.getPropertyOracle();
				String userAgent = propOracle.getPropertyValue(logger, "user.agent");
				if (userAgent.equals("ie6")) {
					return "chapter4.client.WelcomeImplIE";
				}
				else if (userAgent.equals("gecko") || userAgent.equals("gecko1_8")) {
					return "chapter4.client.WelcomeImplFirefox";
				}
				else {
					return "chapter4.client.WelcomeImpl";
				}
			}
			else {
				throw new UnableToCompleteException();
			}
		} catch (BadPropertyValueException e) {
			e.printStackTrace();
		}
		return null;
	}

}
