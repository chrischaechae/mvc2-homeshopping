package com.board.paging;

public class CopyOfPaging1 {
	private int startCount;
	private int endCount;
	private StringBuffer pagingHtml;

	public CopyOfPaging1(int currentPage, int totalCount, int blockCount,
			int blockPage, String pageUrl) {
		this(null, null, currentPage, totalCount, blockCount, blockPage,
				pageUrl, null);
	}
	
	public CopyOfPaging1(int currentPage, int totalCount, int blockCount,
			int blockPage, String pageUrl, String addKey) {
		this(null, null, currentPage, totalCount, blockCount, blockPage,
				pageUrl, addKey,null);
	}

	public CopyOfPaging1(String keyField, String keyWord, int currentPage,
			int totalCount, int blockCount, int blockPage, String pageUrl,String client_id) {
		this(keyField, keyWord, currentPage, totalCount, blockCount, blockPage,
				pageUrl, client_id, null);
	}

	public CopyOfPaging1(String keyField, String keyWord, int currentPage,
			int totalCount, int blockCount, int blockPage, String pageUrl,String client_id, 
			String addKey) {
		if (addKey == null) {
			addKey = "";
		}
		int totalPage = (int)Math.ceil((double)totalCount/blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		this.startCount = ((currentPage - 1) * blockCount + 1); //전달하는 값
		this.endCount = (currentPage * blockCount); //전달하는 값

		int startPage = (currentPage - 1) / blockPage * blockPage + 1;
		int endPage = startPage + blockPage - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	
		this.pagingHtml = new StringBuffer();		
		this.pagingHtml.append("<a href=" + pageUrl + "?client_id="+client_id+"&keyField="
				+ keyField + "&keyWord=" + keyWord + "&pageNum="
				+ 1 + addKey + ">"+"처음");
		this.pagingHtml.append("</a>");
		
		if (currentPage > blockPage) {
			if (keyWord == null) {
				this.pagingHtml.append("<a href=" + pageUrl +"?client_id="+client_id+ "&pageNum="
						+ (startPage - 1) + addKey + ">");
			} else {
				this.pagingHtml.append("<a href=" + pageUrl + "?client_id="+client_id+"&keyField="
						+ keyField + "&keyWord=" + keyWord + "&pageNum="
						+ (startPage - 1) + addKey + " style='text-decoration: none;'>");
			}
			this.pagingHtml.append("<font size='2'><</font>");
			this.pagingHtml.append("</a>");
		}
		//페이지를 만들어주는 함수
		this.pagingHtml.append("&nbsp;&nbsp;");
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				this.pagingHtml.append("&nbsp;<b> <font color='red'>");
				this.pagingHtml.append(i);
				this.pagingHtml.append("</font></b>");
			} else {
				if (keyWord == null) {
					this.pagingHtml.append("&nbsp;<a href='" + pageUrl+"?client_id="+client_id
							+ "&pageNum=");
				} else {
					this.pagingHtml.append("&nbsp;<a href='" + pageUrl+"?client_id="+client_id
							+ "&keyField=" + keyField + "&keyWord=" + keyWord
							+ "&pageNum=");
				}
				this.pagingHtml.append(i);
				this.pagingHtml.append(addKey + "'style='text-decoration: none; font-size:12px;'>");
				this.pagingHtml.append(i);
				this.pagingHtml.append("</a>");
			}
			this.pagingHtml.append("&nbsp;");
		}
		
		// '>' 만들어주기
		this.pagingHtml.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		if (totalPage - startPage >= blockPage) {
			if (keyWord == null) {
				this.pagingHtml.append("<a href=" + pageUrl +"?client_id="+client_id+ "&pageNum="
						+ (endPage + 1) + addKey + ">");
			} else {
				this.pagingHtml.append("<a href=" + pageUrl +"?client_id="+client_id+ "&keyField="
						+ keyField + "&keyWord=" + keyWord + "&pageNum="
						+ (endPage + 1) + addKey +" style='text-decoration: none;'>");
			}
			this.pagingHtml.append("<font size='2'>></font>");
			this.pagingHtml.append("</a>");
		}
		this.pagingHtml.append("<a href=" + pageUrl + "?client_id="+client_id+"&keyField="
				+ keyField + "&keyWord=" + keyWord + "&pageNum="
				+ endPage + addKey +">"+"끝");
		this.pagingHtml.append("</a>");
		
	}

	public StringBuffer getPagingHtml() {
		return this.pagingHtml;
	}

	public int getStartCount() {
		return this.startCount;
	}

	public int getEndCount() {
		return this.endCount;
	}
}