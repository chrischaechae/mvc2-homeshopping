package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ChoiceVO;
import board.model.ProductVO;

public class PayAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		int pro_no=Integer.parseInt(request.getParameter("pro_no"));
		int choice_no=Integer.parseInt(request.getParameter("choice_no"));
		int ordering_num=Integer.parseInt(request.getParameter("ordering_num"));
		int ordering_price=Integer.parseInt(request.getParameter("ordering_price"));
		String client_id=request.getParameter("client_id");
		String ordering_request=request.getParameter("ordering_request");
		String ordering_payment=request.getParameter("ordering_payment");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		 
		dbPro.pay(pro_no,choice_no,ordering_num,ordering_price,client_id,ordering_request,ordering_payment);
		dbPro.minusstock(choice_no,ordering_num);
		
		 
		 return "/board/connect.jsp";//해당뷰
	}
	

}
