package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {

	private static BoardDAO instance = null;
	private BoardDAO(){}
	public static BoardDAO getInstance(){
		if(instance == null){
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
				
			}
		}
		return instance;
	}

	//이곳에 게시판 작업의 기능 하나하나 메소드를 추가 할 것이다.
	
	/*public int getBoardCount(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int x =0;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()){
				x=rs.getInt(1);				
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
	return x;	
	}*/
	

	/*public List<BoardVO> getBoards(int start,int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<BoardVO> boardList = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from (SELECT rownum rnum,NUM,WRITER,EMAIL,SUBJECT,PASS,REGDATE,READCOUNT,REF,STEP,DEPTH,CONTENT,IP FROM (select * from board order by ref desc,step asc)) where rnum>=? and rnum<=?");//수정3
			pstmt.setInt(1,start);
			pstmt.setInt(2,end);
			rs = pstmt.executeQuery();
			if(rs.next()){
				boardList = new ArrayList<BoardVO>();//수정4		
			do{
				BoardVO board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setPass(rs.getString("pass"));
				board.setRegdate(rs.getTimestamp("regdate"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setStep(rs.getInt("STEP"));
				board.setDepth(rs.getInt("depth"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				boardList.add(board);				
			}while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return boardList;
	}*/
	public List<ProductVO> getmalllist(String keyField, String keyWord, int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<ProductVO> malllist = null;
		try{
			conn = ConnUtil.getConnection();
			if(keyField!=null || keyField=="" || keyWord!=null || keyWord==""){
				pstmt = conn.prepareStatement("select * from(select ROW_NUMBER() OVER(order by pro_no desc) rnum ,pro_no,pro_name,pro_price,pro_detail,pro_img from product where " + keyField + " like '%"+keyWord+"%' order by pro_no desc) where rnum >= ? and rnum <= ?");//수정3
				pstmt.setInt(1,start);
				pstmt.setInt(2,end);
			}else{		
				//pstmt = conn.prepareStatement("select * from(select rownum rnum,pro_no,pro_name,pro_price,pro_detail,pro_img from product where pro_name like '%캐년%')order by pro_no desc");//수정3
				pstmt = conn.prepareStatement("select * from (select ROW_NUMBER() OVER(order by pro_no desc) rnum,pro_no,pro_name,pro_price,pro_detail,pro_img from product order by pro_no desc) where rnum >= ? and rnum <= ?");//수정3
				pstmt.setInt(1,start);
				pstmt.setInt(2,end);
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				malllist = new ArrayList<ProductVO>();//수정4		
			do{
				ProductVO mall = new ProductVO();
				mall.setPro_no(rs.getInt("pro_no"));
				mall.setPro_name(rs.getString("pro_name"));
				mall.setPro_price(rs.getInt("pro_price"));
				mall.setPro_detail(rs.getString("pro_detail"));
				mall.setPro_img(rs.getString("pro_img"));
				malllist.add(mall);	
			}while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return malllist;
	}
	
	public ProductVO getpro_detail(int pro_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ProductVO bean = new ProductVO();
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from product where pro_no=?");//수정3
			pstmt.setInt(1,pro_no);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				bean.setPro_no(rs.getInt("pro_no"));
				bean.setPro_name(rs.getString("pro_name"));
				bean.setPro_price(rs.getInt("pro_price"));
				bean.setPro_detail(rs.getString("pro_detail"));
				bean.setPro_img(rs.getString("pro_img"));
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return bean;
	}
	public void joindo(String client_id, String client_pw, String client_name,String client_phone, String client_address) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("insert into client (client_no,client_id,client_pw,client_name,client_phone,client_address,client_chk) values (client_seq.nextval,?,?,?,?,?,1)");//수정3
			pstmt.setString(1,client_id);
			pstmt.setString(2,client_pw);
			pstmt.setString(3,client_name);
			pstmt.setString(4,client_phone);
			pstmt.setString(5,client_address);
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
	}
	public ClientVO logindo(String client_id, String client_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ClientVO login = new ClientVO();
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from client where client_id=? and client_pw=?");//수정3
			pstmt.setString(1,client_id);
			pstmt.setString(2,client_pw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				login.setClient_no(rs.getInt("client_no"));
				login.setClient_id(rs.getString("client_id"));
				login.setClient_pw(rs.getString("client_pw"));
				login.setClient_name(rs.getString("client_name"));
				login.setClient_phone(rs.getString("client_phone"));
				login.setClient_address(rs.getString("client_address"));
				login.setClient_chk(rs.getInt("client_chk"));
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return login;
	}
	public  List<ChoiceVO> choicelist(int pro_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<ChoiceVO> choicelist = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from choice where pro_no=?");
			pstmt.setInt(1,pro_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				choicelist = new ArrayList<ChoiceVO>();//수정4		
			do{
				ChoiceVO bean = new ChoiceVO();
				bean.setChoice_no(rs.getInt("Choice_no"));
				bean.setChoice_color(rs.getString("choice_color"));
				bean.setChoice_size(rs.getString("choice_size"));
				bean.setChoice_stock(rs.getInt("choice_stock"));
				bean.setPro_no(rs.getInt("pro_no"));
				choicelist.add(bean);	
			}while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return choicelist;
	}
	public int chkbasket(int choice_no, String client_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int chk=0;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from basket where choice_no=? and client_id=?");//수정3
			pstmt.setInt(1,choice_no);
			pstmt.setString(2,client_id);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				chk=rs.getInt(1);
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return chk;
	}
	public void plusbasket(int choice_no, String client_id, int basket_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update basket set basket_cnt=basket_cnt+? where choice_no=? and client_id=?");//수정3
			pstmt.setInt(1,basket_cnt);
			pstmt.setInt(2,choice_no);
			pstmt.setString(3,client_id);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		
	}
	public void addbasket(String client_id, int pro_no, int choice_no,int basket_cnt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("insert into basket (basket_no,pro_no,client_id,choice_no,basket_cnt) values (basket_seq.nextval,?,?,?,?)");//수정3
			pstmt.setInt(1,pro_no);
			pstmt.setString(2,client_id);
			pstmt.setInt(3,choice_no);
			pstmt.setInt(4,basket_cnt);
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		
	}
	public List<BasketVO> getbasketlist(String client_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<BasketVO> list = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select product.pro_name,product.pro_price,product.pro_img,basket.basket_no,basket.basket_cnt,choice.choice_size,choice.choice_color from basket inner join product on basket.pro_no=product.pro_no inner join choice on basket.choice_no=choice.choice_no where client_id=? order by basket.basket_no desc");//수정3

			pstmt.setString(1,client_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<BasketVO>();//수정4		
			do{
				BasketVO VO = new BasketVO();
				VO.setPro_name(rs.getString("pro_name"));
				VO.setPro_price(rs.getInt("pro_price"));
				VO.setPro_img(rs.getString("pro_img"));
				VO.setBasket_no(rs.getInt("basket_no"));
				VO.setBasket_cnt(rs.getInt("basket_cnt"));
				VO.setChoice_size(rs.getString("choice_size"));
				VO.setChoice_color(rs.getString("choice_color"));
				list.add(VO);	
			}while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return list;
	}
	public ClientVO editinfo(String client_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ClientVO bean = new ClientVO();
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from client where client_id=?");//수정3
			pstmt.setString(1,client_id);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				bean.setClient_no(rs.getInt("client_no"));
				bean.setClient_id(rs.getString("client_id"));
				bean.setClient_pw(rs.getString("client_pw"));
				bean.setClient_name(rs.getString("client_name"));
				bean.setClient_phone(rs.getString("client_phone"));
				bean.setClient_address(rs.getString("client_address"));
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return bean;
	}
	public void editinfodo(int client_no, String client_pw,String client_phone, String client_address) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update client set client_pw=?,client_phone=?,client_address=? where client_no=?");//수정3
			pstmt.setString(1,client_pw);
			pstmt.setString(2,client_phone);
			pstmt.setString(3,client_address);
			pstmt.setInt(4,client_no);
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
	}
	public ChoiceVO getcho_detail(int choice_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ChoiceVO bean2 = new ChoiceVO();
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from choice where choice_no=?");//수정3
			pstmt.setInt(1,choice_no);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				bean2.setChoice_no(rs.getInt("choice_no"));
				bean2.setChoice_size(rs.getString("choice_size"));
				bean2.setChoice_color(rs.getString("choice_color"));
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return bean2;
	}
	public int chkstock(int choice_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int chk=0;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select choice_stock from choice where choice_no=?");//수정3
			pstmt.setInt(1,choice_no);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				chk=rs.getInt(1);
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return chk;
	}
	public void pay(int pro_no, int choice_no, int ordering_num,int ordering_price, String client_id, String ordering_request,String ordering_payment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("insert into ordering (ordering_no,pro_no,choice_no,ordering_num,ordering_price,client_id,ordering_request,ordering_payment,ordering_date,ordering_status,ordering_confirm) values (ordering_seq.nextval,?,?,?,?,?,?,?,sysdate,'배송준비중',0)");//수정3
			pstmt.setInt(1,pro_no);
			pstmt.setInt(2,choice_no);
			pstmt.setInt(3,ordering_num);
			pstmt.setInt(4,ordering_price);
			pstmt.setString(5,client_id);
			pstmt.setString(6,ordering_request);
			pstmt.setString(7,ordering_payment);
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		
	}
	public List<OrderingVO> getorderlist(String client_id, String keyField, String keyWord,int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<OrderingVO> list = null;
		try{
			conn = ConnUtil.getConnection();
			if(keyField!=null || keyField=="" || keyWord!=null || keyWord==""){
				System.out.println("1");
				pstmt = conn.prepareStatement("select * from (select ROW_NUMBER() OVER(order by ordering_no desc) rnum, ordering.ordering_no, ordering.ordering_date, ordering.ordering_num, ordering.ordering_price, ordering.ordering_status, ordering.ordering_request, ordering.ordering_payment, ordering.ordering_confirm, product.pro_name, product.pro_img, choice.choice_size, choice.choice_color from ordering inner join product on ordering.pro_no = product.pro_no inner join  choice on ordering.choice_no = choice.choice_no where client_id=? and "+keyField+" like '%"+keyWord +"%' order by ordering_no desc) where rnum >= ? and rnum <= ?");//수정3
				pstmt.setString(1,client_id);
				pstmt.setInt(2,start);
				pstmt.setInt(3,end);
			}else{
				System.out.println("2");
				pstmt = conn.prepareStatement("select * from (select ROW_NUMBER() OVER(order by ordering_no desc) rnum, ordering.ordering_no, ordering.ordering_date, ordering.ordering_num, ordering.ordering_price, ordering.ordering_status, ordering.ordering_request, ordering.ordering_payment, ordering.ordering_confirm, product.pro_name, product.pro_img, choice.choice_size, choice.choice_color from ordering inner join product on ordering.pro_no = product.pro_no inner join  choice on ordering.choice_no = choice.choice_no where client_id=? order by ordering_no desc) where rnum >= ? and rnum <= ?");//수정3
				pstmt.setString(1,client_id);
				pstmt.setInt(2,start);
				pstmt.setInt(3,end);
			}
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<OrderingVO>();//수정4		
			do{
				OrderingVO order = new OrderingVO();
				order.setOrdering_no(rs.getInt("ordering_no"));
				order.setOrdering_date(rs.getDate("ordering_date"));
				order.setOrdering_num(rs.getInt("ordering_num"));
				order.setOrdering_price(rs.getInt("ordering_price"));
				order.setOrdering_status(rs.getString("ordering_status"));
				order.setOrdering_request(rs.getString("ordering_request"));
				order.setOrdering_payment(rs.getString("ordering_payment"));
				order.setOrdering_confirm(rs.getInt("ordering_confirm"));
				order.setPro_name(rs.getString("pro_name"));
				order.setPro_img(rs.getString("pro_img"));
				order.setChoice_size(rs.getString("choice_size"));
				order.setChoice_color(rs.getString("choice_color"));
				list.add(order);	
			}while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return list;
	}
	public static void delbasket(String basket_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("delete from basket where basket_no=?");//수정3
			pstmt.setString(1,basket_no);
			pstmt.executeUpdate();
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		
	}
	public List<BasketVO> getbbuy(String[] basket_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<BasketVO> list = null;
		String c="";
		String a=",";
		for(int i=0; i<basket_no.length; i++){
			String x=basket_no[i]+a;
			c+=x;
		}
		c=c.substring(0, c.length()-1);
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select product.pro_no,product.pro_img,product.pro_name,product.pro_price,basket.basket_no,basket.basket_cnt,choice.choice_no,choice.choice_size,choice.choice_color from basket inner join product on basket.pro_no=product.pro_no inner join choice on basket.choice_no=choice.choice_no where basket_no in ("+c+")");//수정3
			rs = pstmt.executeQuery();
			if(rs.next()){
				list = new ArrayList<BasketVO>();//수정4		
			do{
				BasketVO bean = new BasketVO();
				bean.setPro_no(rs.getInt("pro_no"));
				bean.setPro_name(rs.getString("pro_name"));
				bean.setPro_price(rs.getInt("pro_price"));
				bean.setPro_img(rs.getString("pro_img"));
				bean.setBasket_no(rs.getInt("basket_no"));
				bean.setBasket_cnt(rs.getInt("basket_cnt"));
				bean.setChoice_no(rs.getInt("choice_no"));
				bean.setChoice_size(rs.getString("choice_size"));
				bean.setChoice_color(rs.getString("choice_color"));
				list.add(bean);	
			}while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		
		return list;
	}
	public int chkinfo(String client_id, String client_pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int chk =0;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select count(*) from client where client_id=? and client_pw=?");
			pstmt.setString(1,client_id);
			pstmt.setString(2,client_pw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				chk=rs.getInt(1);				
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return chk;	
	}
	public int duplid(String client_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int chk =0;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select count(*) from client where client_id=?");
			pstmt.setString(1,client_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				chk=rs.getInt(1);				
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return chk;	
	}
	public int duplname(String client_name, String client_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int chk =0;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select count(*) from client where client_name=? and client_phone=?");
			pstmt.setString(1,client_name);
			pstmt.setString(2,client_phone);
			rs = pstmt.executeQuery();
			if(rs.next()){
				chk=rs.getInt(1);				
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return chk;	
	}
	public String findid(String client_name, String client_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String client_id=null;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select client_id from client where client_name=? and client_phone=?");
			pstmt.setString(1,client_name);
			pstmt.setString(2,client_phone);
			rs = pstmt.executeQuery();
			if(rs.next()){
				client_id=rs.getString("client_id");			
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return client_id;	
	}
	public String findpw(String client_name, String client_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String client_pw=null;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select client_pw from client where client_name=? and client_id=?");
			pstmt.setString(1,client_name);
			pstmt.setString(2,client_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				client_pw=rs.getString("client_pw");			
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return client_pw;	
	}
	public int chkstock(String chono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int stock =0;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select choice_stock from choice where choice_no=?");
			pstmt.setString(1,chono);
			rs = pstmt.executeQuery();
			if(rs.next()){
				stock=rs.getInt(1);				
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return stock;	
	}
	public int checkcnt(String basno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int bascnt =0;
		try{
			conn = ConnUtil.getConnection();
		//수정2
			pstmt = conn.prepareStatement("select basket_cnt from basket where basket_no=?");
			pstmt.setString(1,basno);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bascnt=rs.getInt(1);				
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		return bascnt;	
	}
	public BasketVO detailbasket(String basket_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		BasketVO bean = new BasketVO();
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from basket where basket_no=?");//수정3
			pstmt.setString(1,basket_no);
			rs = pstmt.executeQuery();
			if(rs.next()){	
				bean.setPro_no(rs.getInt("pro_no"));
				bean.setChoice_no(rs.getInt("choice_no"));
				bean.setBasket_cnt(rs.getInt("basket_cnt"));
				bean.setClient_id(rs.getString("client_id"));
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return bean;
	}
	public void minusstock(int choice_no, int ordering_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update choice set choice_stock=choice_stock-? where choice_no=?");//수정3
			pstmt.setInt(1,ordering_num);
			pstmt.setInt(2,choice_no);
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
		
	}
	public int procount(String keyField, String keyWord) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count=0;
		try{
			conn = ConnUtil.getConnection();
			
			if(keyField!=null || keyField=="" || keyWord!=null || keyWord==""){	
				pstmt = conn.prepareStatement("select count(*) from product where " + keyField + " like '%"+keyWord+"%'");//수정3
			}else{
				pstmt = conn.prepareStatement("select count(*) from product");//수정3
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()){	
				count=rs.getInt(1);
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return count;
	}
	public int orderlistcnt(String client_id, String keyField, String keyWord) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int count=0;
		try{
			conn = ConnUtil.getConnection();
			
			if(keyField!=null || keyField=="" || keyWord!=null || keyWord==""){	
				System.out.println("11");
				pstmt = conn.prepareStatement("select count(*) from (select ROW_NUMBER() OVER(order by ordering_no desc) rnum, ordering.ordering_no, ordering.ordering_date, ordering.ordering_num, ordering.ordering_price, ordering.ordering_status, ordering.ordering_request, ordering.ordering_payment, ordering.ordering_confirm, product.pro_name, product.pro_img, choice.choice_size, choice.choice_color from ordering inner join product on ordering.pro_no = product.pro_no inner join  choice on ordering.choice_no = choice.choice_no where client_id=? and "+keyField+" like '%"+keyWord +"%' order by ordering_no desc)");//수정3
				pstmt.setString(1,client_id);
			}else{
				System.out.println("22");
				pstmt = conn.prepareStatement("select count(*) from ordering where client_id=?");//수정3
				pstmt.setString(1,client_id);
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()){	
				count=rs.getInt(1);
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return count;
	}
	public void updatechk(String client_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("	update client set client_chk=client_chk+1 where client_id=?");//수정3
			pstmt.setString(1,client_id);
		
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}		
	}
	public int ddcheck(int client_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int client_chk9=0;
		try{
			conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("select client_chk from client where client_no=?");//수정3
				pstmt.setInt(1,client_no);
			
			
			rs = pstmt.executeQuery();
			if(rs.next()){	
				client_chk9=rs.getInt(1);
			while(rs.next());
			}
		}catch (Exception e) {e.printStackTrace();
		}finally{
			if(rs!=null) try{ rs.close();} catch (SQLException e2) {}
			if(pstmt!=null) try{ pstmt.close();} catch (SQLException e2) {}
			if(conn!=null) try{ conn.close();} catch (SQLException e2) {}
		}
		return client_chk9;
	}
}
