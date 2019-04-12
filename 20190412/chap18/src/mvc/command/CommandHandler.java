package mvc.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//커맨드 핸들러 인터페이스를 구현해서 다형성을 사용한다.
public interface CommandHandler {
	
	public abstract String process(HttpServletRequest request, HttpServletResponse response) 
	throws Exception;
}
