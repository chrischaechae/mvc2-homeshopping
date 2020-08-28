package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ChoiceVO;
import board.model.ProductVO;

public class BuyAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String client_id=request.getParameter("client_id");
		int pro_no=Integer.parseInt(request.getParameter("pro_no"));
		int choice_no=Integer.parseInt(request.getParameter("choice_no"));
		int ordering_num=Integer.parseInt(request.getParameter("ordering_num"));
		
		
		 BoardDAO dbPro = BoardDAO.getInstance();
		 
		 ProductVO bean1=dbPro.getpro_detail(pro_no);
		 ChoiceVO bean2=dbPro.getcho_detail(choice_no);
		 request.setAttribute("bean",ordering_num);
		 request.setAttribute("bean1",bean1);
		 request.setAttribute("bean2",bean2);
		 
		 return "/board/buy.jsp";//해당뷰
	}
	

}
