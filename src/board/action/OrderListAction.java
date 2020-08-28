package board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.paging.Paging;
import com.board.paging.Paging1;

import board.model.BasketVO;
import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ClientVO;
import board.model.OrderingVO;
import board.model.ProductVO;

public class OrderListAction implements CommandAction{//글목록 처리
	private int pageSize = 6;
	private int blockCount = 10;

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
		
		int currentPage=0;
		String currentPage1 = request.getParameter("pageNum"); //페이지번호
		if(currentPage1 !=null){
			currentPage=Integer.parseInt(currentPage1);
		}
		if(currentPage1 ==null || currentPage1==""){
			currentPage = 1;
		}
		
		String pagingHtml = "";	
		List<OrderingVO> list = null;
		String client_id=request.getParameter("client_id");
		String keyField = request.getParameter("keyField"); 
		String keyWord = request.getParameter("keyWord");
	    int count=dbPro.orderlistcnt(client_id,keyField,keyWord);
	    
	    Paging1 page = new Paging1(keyField, keyWord, currentPage, count,
				this.pageSize, this.blockCount, "orderlist.do", client_id);
	    int start = Integer.valueOf(page.getStartCount());
	    int end = Integer.valueOf(page.getEndCount());
	    list = dbPro.getorderlist(client_id,keyField,keyWord,start,end);//수정3
	    pagingHtml = page.getPagingHtml().toString();
	    request.setAttribute("list",list);
	    request.setAttribute("count",count);
	    request.setAttribute("pagingHtml",pagingHtml);
	    request.setAttribute("currentPage",Integer.valueOf(currentPage));
	    return "/board/orderlist.jsp";//해당뷰
	}

}
