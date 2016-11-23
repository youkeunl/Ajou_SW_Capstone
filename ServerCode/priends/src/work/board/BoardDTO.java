package work.board;

import java.util.Date;

public class BoardDTO {
	private int num;
	private String writer;
	private String title;
	private String content;
	private int count;
	private Date regdate;
	private String category;
	private String img_addr;
	
	
	
	
	public String getImg_addr() {
		return img_addr;
	}
	public void setImg_addr(String img_addr) {
		this.img_addr = img_addr;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
