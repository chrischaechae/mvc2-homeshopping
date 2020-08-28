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

public class AddbasketAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{	
		
		String client_id=request.getParameter("client_id");
		int pro_no=Integer.parseInt(request.getParameter("pro_no"));
		int choice_no = Integer.parseInt(request.getParameter("choice_no"));
		int basket_cnt = Integer.parseInt(request.getParameter("basket_cnt"));
		
		BoardDAO dbPro = BoardDAO.getInstance();
		dbPro.addbasket(client_id,pro_no,choice_no,basket_cnt);
		
		return "";//해당뷰
	}

}
