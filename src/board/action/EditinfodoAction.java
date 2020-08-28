package board.action;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ProductVO;

public class EditinfodoAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{		
		request.setCharacterEncoding("utf-8");
		
		int client_no=Integer.parseInt(request.getParameter("client_no"));
		String client_pw=request.getParameter("client_pw");
		String client_phone = request.getParameter("client_phone");
		String client_address = request.getParameter("client_address");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		dbPro.editinfodo(client_no,client_pw,client_phone,client_address);
		
		 return "connect.jsp";
	}

}
