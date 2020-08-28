package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ClientVO;
import board.model.ProductVO;

public class LogindoAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {

		
		HttpSession session=request.getSession(true);
		String client_id=request.getParameter("client_id");
		String client_pw=request.getParameter("client_pw");
		ClientVO login=null;
		
		 BoardDAO dbPro = BoardDAO.getInstance();
		 login=dbPro.logindo(client_id,client_pw);
			
			if(login ==null){
				session.setAttribute("login", null);
			}
			else{			
				session.setAttribute("login", login);
			}
				dbPro.updatechk(client_id);
			  return "connect.jsp";//해당뷰
	}
}
