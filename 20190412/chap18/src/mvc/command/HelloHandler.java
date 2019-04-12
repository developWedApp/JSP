package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("hello", "안녕하세요!");
		return "/WEB-INF/view/hello.jsp";
	}
	
	

}
