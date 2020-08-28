package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ChoiceVO;
import board.model.ProductVO;

public class DetailAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		int pro_no = Integer.parseInt(request.getParameter("pro_no"));
		ProductVO bean = null;
	    BoardDAO dbPro = BoardDAO.getInstance();//db연동
	    bean = dbPro.getpro_detail(pro_no);//수정3
	    List<ChoiceVO> choicelist = dbPro.choicelist(pro_no);
	    request.setAttribute("bean",bean);
	    request.setAttribute("list1",choicelist);
	    return "/board/detail.jsp";//해당뷰
	}

}
