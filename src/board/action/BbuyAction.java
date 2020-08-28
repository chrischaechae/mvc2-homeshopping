package board.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BasketVO;
import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ChoiceVO;
import board.model.ProductVO;

public class BbuyAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String[] paychk=request.getParameterValues("basket_no");
		String[] numchk=request.getParameterValues("numchk");
		String[] basket_no=paychk[0].split(",");
		System.out.println(basket_no);
	
		List<BasketVO> list = null;
		BoardDAO dbPro = BoardDAO.getInstance();//db연동	
		list = dbPro.getbbuy(basket_no);
	
		 request.setAttribute("list",list);
		 
		return "/board/bbuy.jsp";//해당뷰
	}
	

}
