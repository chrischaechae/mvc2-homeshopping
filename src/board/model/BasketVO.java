package board.model;

public class BasketVO {

	private int basket_no;
	private String client_id;
	private int pro_no;
	private int choice_no;
	private int basket_cnt;
	//productVO
	private String pro_name;
	private int pro_price;
	private String pro_img;
	private String pro_detail;
	//ChoiceVO
	private String choice_color;
	private String choice_size;
	private String choice_stock;
	
	
	
	
	
	public int getBasket_cnt() {
		return basket_cnt;
	}
	public void setBasket_cnt(int basket_cnt) {
		this.basket_cnt = basket_cnt;
	}	
	public int getBasket_no() {
		return basket_no;
	}
	public void setBasket_no(int basket_no) {
		this.basket_no = basket_no;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
	public int getChoice_no() {
		return choice_no;
	}
	public void setChoice_no(int choice_no) {
		this.choice_no = choice_no;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	public String getPro_img() {
		return pro_img;
	}
	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	public String getPro_detail() {
		return pro_detail;
	}
	public void setPro_detail(String pro_detail) {
		this.pro_detail = pro_detail;
	}
	public String getChoice_color() {
		return choice_color;
	}
	public void setChoice_color(String choice_color) {
		this.choice_color = choice_color;
	}
	public String getChoice_size() {
		return choice_size;
	}
	public void setChoice_size(String choice_size) {
		this.choice_size = choice_size;
	}
	public String getChoice_stock() {
		return choice_stock;
	}
	public void setChoice_stock(String choice_stock) {
		this.choice_stock = choice_stock;
	}
	
	
}
