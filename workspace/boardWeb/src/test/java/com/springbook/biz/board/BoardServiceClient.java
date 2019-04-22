package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class BoardServiceClient {

	public static void main(String[] args) {
		
		//스프링 컨테이너를 구동한다.
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		//스프링 컨테이너가 아이디가 boardService인 객체를LookUp한다.
		BoardService boardService = (BoardService)container.getBean("boardService");
			
		
		BoardVO vo = new BoardVO();
		vo.setTitle("어드바이스 동작시점");
		vo.setWriter("김건우");
		vo.setContent("동작시점 체크 중");
		boardService.insertBoard(vo);
		
		//글 목록 조회
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for(BoardVO board : boardList) {
			System.out.println("---->" + board);
		}
		
		container.close();
		
		
	}
}
