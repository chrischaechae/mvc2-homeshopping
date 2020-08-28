package board.action;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import board.model.BoardDAO;
import board.model.BoardVO;
import board.model.ProductVO;

public class DelbasketAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("utf-8");
		String[] delchk=request.getParameterValues("basket_no");
		int no[]=new int[delchk.length];;
		for (int i = 0; i < delchk.length; i++) {
		        no[i] = Integer.parseInt(delchk[i]);    
		}	
		int cnt=Integer.parseInt(request.getParameter("cnt"));
		BoardDAO dbPro = BoardDAO.getInstance();
		
		for(int i=0; i<cnt; i++){	
			BoardDAO.delbasket(delchk[i]);			
		}
		 return "";
	}

}
