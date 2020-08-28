package board.action;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;




import board.model.BasketVO;
import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ProductVO;

public class CchkstockAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		String[] chono = request.getParameterValues("chono");
		String[] basno = request.getParameterValues("basno");
		int stock=0;
		int bascnt=0;
		int bbchk=0;
		BoardDAO dbPro = BoardDAO.getInstance();
		for(int i=0; i<chono.length; i++){
			stock=dbPro.chkstock(chono[i]);
			bascnt=dbPro.checkcnt(basno[i]);
			if(stock>=bascnt){
				bbchk=0;
			}else{
				bbchk=1;
				break;
			}
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("bbchk", bbchk);
		response.setContentType("text/xml;charset=utf-8");
		
		PrintWriter writer = response.getWriter();
        writer.print(resultJson);
        writer.flush();
		writer.close();
		
		return "";//해당뷰
	}

}
