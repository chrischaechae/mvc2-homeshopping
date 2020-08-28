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

public class ChkbasketAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		int choice_no = Integer.parseInt(request.getParameter("choice_no"));
		int basket_cnt = Integer.parseInt(request.getParameter("basket_cnt"));
		String client_id=request.getParameter("client_id");
		
		BoardDAO dbPro = BoardDAO.getInstance();
		int chk=dbPro.chkbasket(choice_no,client_id);
		System.out.println(chk);
		
		if(chk!=0){
			dbPro.plusbasket(choice_no,client_id,basket_cnt);
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("chkk", chk);
		response.setContentType("text/xml;charset=utf-8");
		
		PrintWriter writer = response.getWriter();
        writer.print(resultJson);
        writer.flush();
        writer.close();
		return "";//해당뷰
	}

}
