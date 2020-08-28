package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BasketVO;
import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ChoiceVO;
import board.model.ProductVO;

public class BpayAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String[] basket_no = request.getParameterValues("basket_no");
		String ordering_request=request.getParameter("ordering_request");
		String ordering_payment=request.getParameter("ordering_payment");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		for(int i=1; i<basket_no.length; i++){
		BasketVO bean=dbPro.detailbasket(basket_no[i]);
		ProductVO bean1=dbPro.getpro_detail(bean.getPro_no());
		int pro_no=bean.getPro_no();
		int choice_no=bean.getChoice_no();
		int ordering_num=bean.getBasket_cnt();
		int ordering_price=bean1.getPro_price();
		String client_id=bean.getClient_id();
			
		dbPro.pay(pro_no,choice_no,ordering_num,ordering_price,client_id,ordering_request,ordering_payment);
		dbPro.minusstock(choice_no, ordering_num);
		dbPro.delbasket(basket_no[i]);
		}
		 
		 return "/board/connect.jsp";//해당뷰
	}
	

}
