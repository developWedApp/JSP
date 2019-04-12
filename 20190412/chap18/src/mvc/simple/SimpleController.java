package mvc.simple;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SimpleController extends HttpServlet {

	/*servlet의 생명주기  servlet container에서 servlet 객체를 생성 -> init() 을 호출 -> service() 호출 -> HTTP 요청에 따라
	   doGet, doPost메서드를 실행.*/
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//클라이언트로부터 요청을 받는다.  
		processRequest(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request,response);
	}
	
	

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//2.요청을 분석한다.
		String type = request.getParameter("type");
		
		Object resultObject = null;
		//3.요청에 대한 기능 수행(보통은 모델을 통해서 수행) 현재 파일은 모델을 분리하지 않음.
		if(type == null || type.equals("greeting")) {
			resultObject = "안녕하세요";
		} else if(type != null && type.equals("date")) {
			
			//날짜를 생성해줌. 자주 사용한다? 회원가입, 게시판 등 많은 비즈니스 들이 Register Date(등록일)을 요구하므로 이 때 자주 쓰일 듯.
			SimpleDateFormat format = new SimpleDateFormat("YY/MM/DD");
			String date = format.format(new java.util.Date());
			resultObject = date;
		} else {
			resultObject = "Invalid Type";
		}
		//4. 요청에 대한 처리 결과를 저장(request. session) 보통 결과를 저장할 때 request. session 객체를 사용
		request.setAttribute("result", resultObject);
		//5. reqeust, seession 에 담긴 처리결과(응답)을 뷰에 전달한다. <jsp:forward> 포워딩하면 해당 페이지에서 다음페이지로 객체를 가지고 넘어간다.
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("simpleView.jsp");
		dispatcher.forward(request, response);
	}

}
