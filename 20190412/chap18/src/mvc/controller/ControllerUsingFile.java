package mvc.controller;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;



public class ControllerUsingFile extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
			
	
	public void init() throws ServletException {
		//서블릿 클래스에서 코드의 변경이 일어나면 다시 컴파일 해주어야하고 변경이 필요한 부분을 찾는 것이 어렵다.
		//그렇기 때문에 getInitParameter 메서드를 통해 web.xml에서 init-param태그를 통해 변경을 좀 더 간편하게 할 수 있다.
		//Properties 는 맵의 형태를 가지는데 <String, String>
		
		String configFile = getInitParameter("configFile");			
		
		Properties prop = new Properties();
		
		String configFilePath = getServletContext().getRealPath(configFile);
		//트라이 리소스 try() 괄호안에 입출력에 관련된, DB연동에 관련된 구문을 사용하면 닫을필요가 없다 자동으로 닫아준다. 예외가 발생하면 캣치문으로 간다
		try (FileReader fis = new FileReader(configFilePath)){
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		
		Iterator keyIter = prop.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String handlerClassName = prop.getProperty(command);
			
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException();
			}
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		String command = request.getParameter("cmd");
		//command = hello
		CommandHandler handler = commandHandlerMap.get(command);
		//commandHandlerMap에서 hello에 대한 커맨드 핸들러를 저장. 다형성 CommandHandler handler = HelloHandler
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = null;
		try {
			viewPage = handler.process(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);		
			dispatcher.forward(request, response);
		}
	}
}

