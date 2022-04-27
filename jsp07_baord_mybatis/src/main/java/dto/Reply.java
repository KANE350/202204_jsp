package dto;

import java.util.Date;

public class Reply {
	private int rnum;
	private int bnum;
	private String content;
	private int restep;
	private int relevel;
	private Date regidate;
	public Reply() {
		super();
	}
	public Reply(int rnum, int bnum, String content, int restep, int relevel, Date regidate) {
		super();
		this.rnum = rnum;
		this.bnum = bnum;
		this.content = content;
		this.restep = restep;
		this.relevel = relevel;
		this.regidate = regidate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	@Override
	public String toString() {
		return "Reply [rnum=" + rnum + ", bnum=" + bnum + ", content=" + content + ", restep=" + restep + ", relevel="
				+ relevel + ", regidate=" + regidate + "]";
	}
	
}
