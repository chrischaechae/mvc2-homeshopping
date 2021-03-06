package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ChoiceVO;
import board.model.ClientVO;
import board.model.ProductVO;

public class EditinfoAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		HttpSession session=request.getSession(true);
		ClientVO clientVO = (ClientVO)session.getAttribute("login");
		BoardDAO dbPro = BoardDAO.getInstance();//db연동
		int client_chk9=dbPro.ddcheck(clientVO.getClient_no());
		System.out.println("1="+client_chk9);
		System.out.println("2="+clientVO.getClient_chk());
		if(clientVO.getClient_chk()!=client_chk9-1){
			session.invalidate();
			return "/board/login.jsp";
		}//새로 로그인했을 때 세션번호 체크 후 로그인 페이지로 이동
		
		String client_id = request.getParameter("client_id");
		ClientVO bean = null;
	    bean = dbPro.editinfo(client_id);//수정3
	    request.setAttribute("bean",bean);
	   
	    return "/board/editinfo.jsp";//해당뷰
	}

}
