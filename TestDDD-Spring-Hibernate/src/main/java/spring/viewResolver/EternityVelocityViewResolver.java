package spring.viewResolver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

public class EternityVelocityViewResolver extends VelocityViewResolver {
	private static final Pattern VIEW_NAME_PATTERN = Pattern.compile(".*/(.*?)$");
	private List<String> dojoIoViewNames;
	
	public void setDojoIoViewNames(List<String> dojoIoViewNames) {
		this.dojoIoViewNames = dojoIoViewNames;
	}
	
	private String findViewName(String viewName) {
		Matcher m = VIEW_NAME_PATTERN.matcher(viewName);
		m.matches();
		return m.group(1);
	}
	
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityView view = (VelocityView) super.buildView(viewName);
		
		if(viewName.matches(".*/data/.*?$")) {
			if(dojoIoViewNames.contains(findViewName(viewName))) {
				view.setContentType("text/html;charset=UTF-8");
			} else {
				view.setContentType("application/json;charset=UTF-8");
			}
		} else if(viewName.matches(".*/resource/.*?$")) {
			view.setContentType("text/javascript;charset=UTF-8");
		} else {
			view.setContentType("text/html;charset=UTF-8");
		}
		
		return view;
	}
}
