package chap18;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2단계 요청 분석
		//request 객체로부터 사용자의 요청을 분석하는 코드
		String type = request.getParameter("type");
		//3단계 모델을 사용하여 요청한 기능을 수행한다.
		if(type.equals("a")){
		
		} else if(type.equals("b")) {
			
		}
			
		//4단계, request나 session에 처리 결과를 저장
		//request.setAttribute("result",resultObject); 이런 형태의 코드
		
		//5단계, requestDispatcher를 사용하여 알맞은 뷰로 포워딩
		RequestDispatcher dispatcther = 
				request.getRequestDispatcher("/view.jsp");
		dispatcther.forward(request, response);
		
		
		
		
	}
	
	
	
	
	
}
