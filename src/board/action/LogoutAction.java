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

public class LogoutAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		HttpSession session=request.getSession(true);
		session.invalidate();
		
		 return "connect.jsp";//해당뷰
	}
}
