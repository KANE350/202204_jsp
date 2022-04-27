package dto;

import java.util.Date;

public class Member {
	private String userid;
	private String passwd;	//수정시 기본 비밀번호(db에 있는)
	private String newpasswd;	//수정시 새로운 비밀번호
	private String salt;
	private String zipcode;
	private String addrload;
	private String addrdetail;
	private String filename;
	private Date regidate;
	public Member() {
		super();
	}
	public Member(String userid, String passwd, String newpasswd, String salt, String zipcode, String addrload,
			String addrdetail, String filename, Date regidate) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.newpasswd = newpasswd;
		this.salt = salt;
		this.zipcode = zipcode;
		this.addrload = addrload;
		this.addrdetail = addrdetail;
		this.filename = filename;
		this.regidate = regidate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNewpasswd() {
		return newpasswd;
	}
	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddrload() {
		return addrload;
	}
	public void setAddrload(String addrload) {
		this.addrload = addrload;
	}
	public String getAddrdetail() {
		return addrdetail;
	}
	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", passwd=" + passwd + ", newpasswd=" + newpasswd + ", salt=" + salt
				+ ", zipcode=" + zipcode + ", addrload=" + addrload + ", addrdetail=" + addrdetail + ", filename="
				+ filename + ", regidate=" + regidate + "]";
	}
	
}


