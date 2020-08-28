package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ProductVO;

public class JoindoAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String client_id=request.getParameter("client_id");
		String client_pw=request.getParameter("client_pw");
		String client_name=request.getParameter("client_name");
		String client_phone=request.getParameter("client_phone");
		String client_address=request.getParameter("client_address");
		
		 BoardDAO dbPro = BoardDAO.getInstance();
		 dbPro.joindo(client_id,client_pw,client_name,client_phone,client_address);
		
		
		 return "/board/login.jsp";
	}
	

}
