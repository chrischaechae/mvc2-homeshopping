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

public class ChkstockAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		int choice_no = Integer.parseInt(request.getParameter("choice_no"));
		BoardDAO dbPro = BoardDAO.getInstance();
		int chk=dbPro.chkstock(choice_no);
	
		
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
